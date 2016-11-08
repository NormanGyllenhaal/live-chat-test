package com.rcplatform.livechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.rcplatform.livechat.common.enums.HeadImgTypeEnum;
import com.rcplatform.livechat.common.enums.UserGenderEnum;
import com.rcplatform.livechat.common.enums.UserHeadImgCheckedEnum;
import com.rcplatform.livechat.common.enums.UserHeadImgHandleEnum;
import com.rcplatform.livechat.common.response.Page;
import com.rcplatform.livechat.common.util.StringUtil;
import com.rcplatform.livechat.dto.request.ImgAdminReqDto;
import com.rcplatform.livechat.dto.request.UserHeadImgReqDto;
import com.rcplatform.livechat.mapper.UserHeadImgMapper;
import com.rcplatform.livechat.mapper.UserMapper;
import com.rcplatform.livechat.model.User;
import com.rcplatform.livechat.model.UserHeadImg;
import com.rcplatform.livechat.service.AbstractService;
import com.rcplatform.livechat.service.IUserHeadImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.rcplatform.livechat.common.constant.RedisKeyConstant.*;

/**
 * Created by Administrator on 2016/9/11.
 */
@Service
public class UserHeadImgServiceImpl extends AbstractService implements IUserHeadImgService {


    @Resource
    private UserHeadImgMapper userHeadImgMapper;

    @Resource
    private UserMapper userMapper;


    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Page getImg(ImgAdminReqDto imgAdminReqDto) {
        PageHelper.startPage(imgAdminReqDto.getPageNo(),imgAdminReqDto.getPageSize(),"create_time desc");
        List<UserHeadImg> imgList = userHeadImgMapper.select(new UserHeadImg(imgAdminReqDto.getGender(),imgAdminReqDto.getType(), UserHeadImgHandleEnum.UNHANDLE.key()));
        return getPage(imgList);
    }


    @Override
    public Page handleImg(UserHeadImgReqDto userHeadImgReqDto) {
        final String key = StringUtil.buildString(APP_NAME, USER_PROFILE);
        final String girlKey = StringUtil.buildString(APP_NAME, USER_PROFILE_GIRL);
        final String boyKey = StringUtil.buildString(APP_NAME, USER_PROFILE_BOY);
        List<UserHeadImgReqDto.HeadImgChecked> checkedList = userHeadImgReqDto.getList();
        for(UserHeadImgReqDto.HeadImgChecked imgChecked:checkedList){
            final UserHeadImg userHeadImg = userHeadImgMapper.selectByPrimaryKey(imgChecked.getImgId());
            userHeadImgMapper.updateByPrimaryKeySelective(new UserHeadImg(imgChecked.getImgId(),new Date(),UserHeadImgHandleEnum.HANDLE.key(), imgChecked.getChecked()));
            if(imgChecked.getChecked().equals(UserHeadImgCheckedEnum.UNPASS.key())){
                redisTemplate.executePipelined(new SessionCallback() {
                    @Override
                    public Object execute(RedisOperations redisOperations) throws DataAccessException {
                        redisOperations.opsForZSet().remove(key,userHeadImg.getUserId().toString());
                        redisOperations.opsForZSet().remove(boyKey,userHeadImg.getUserId().toString());
                        redisOperations.opsForZSet().remove(girlKey,userHeadImg.getUserId().toString());
                        return null;
                    }
                });
                if(userHeadImg.getType().equals(HeadImgTypeEnum.HEAD_IMG.key())){
                    userMapper.updateByPrimaryKeySelective(new User(userHeadImg.getUserId(),null,"",new Date()));
                }else if(userHeadImg.getType().equals(HeadImgTypeEnum.BACKGROUND.key())){
                    userMapper.updateByPrimaryKeySelective(new User(userHeadImg.getUserId(),"",null,new Date()));
                }
            }else{
                redisTemplate.execute(new SessionCallback() {
                    @Override
                    public Object execute(RedisOperations redisOperations) throws DataAccessException {
                        redisOperations.opsForZSet().incrementScore(key,userHeadImg.getUserId().toString(),0);
                        if(userHeadImg.getGender().equals(UserGenderEnum.GIRL.key())){
                            redisOperations.opsForZSet().incrementScore(girlKey,userHeadImg.getUserId().toString(),0);
                        }
                        if(userHeadImg.getGender().equals(UserGenderEnum.BOY.key())){
                            redisOperations.opsForZSet().incrementScore(boyKey,userHeadImg.getUserId().toString(),0);
                        }
                        return null;
                    }
                });
            }
        }
        return getImg(userHeadImgReqDto);
    }
}
