package com.rcplatform.livechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.rcplatform.livechat.common.enums.HeadImgTypeEnum;
import com.rcplatform.livechat.common.enums.UserHeadImgCheckedEnum;
import com.rcplatform.livechat.common.enums.UserHeadImgHandleEnum;
import com.rcplatform.livechat.common.response.Page;
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
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.rcplatform.livechat.common.constant.RedisKeyConstant.USER_LOGIN_TIME;
import static com.rcplatform.livechat.common.util.RedisKeyFactory.getKey;

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


    private static final Long DEFAULT_TIME = 1000000000000L;


    @Override
    public Page getImg(ImgAdminReqDto imgAdminReqDto) {
        PageHelper.startPage(imgAdminReqDto.getPageNo(),imgAdminReqDto.getPageSize(),"create_time desc");
        Example example = new Example(UserHeadImg.class);
        example.createCriteria().andBetween("createTime",imgAdminReqDto.getBeginDate(),imgAdminReqDto.getEndDate()).andEqualTo("gender",imgAdminReqDto.getGender()).andEqualTo("type",imgAdminReqDto.getType()).andEqualTo("handle",UserHeadImgHandleEnum.UNHANDLE.key());
        List<UserHeadImg> imgList = userHeadImgMapper.selectByExample(example);
        return getPage(imgList);
    }


    @Override
    public Page handleImg(UserHeadImgReqDto userHeadImgReqDto) {
        List<UserHeadImgReqDto.HeadImgChecked> checkedList = userHeadImgReqDto.getList();
        for(UserHeadImgReqDto.HeadImgChecked imgChecked:checkedList){
            final UserHeadImg userHeadImg = userHeadImgMapper.selectByPrimaryKey(imgChecked.getImgId());
            userHeadImgMapper.updateByPrimaryKeySelective(new UserHeadImg(imgChecked.getImgId(),new Date(),UserHeadImgHandleEnum.HANDLE.key(), imgChecked.getChecked()));
            if(imgChecked.getChecked().equals(UserHeadImgCheckedEnum.UNPASS.key())){
                if(userHeadImg.getType().equals(HeadImgTypeEnum.HEAD_IMG.key())){
                    userMapper.updateByPrimaryKeySelective(new User(userHeadImg.getUserId(),null,"",new Date()));
                }else if(userHeadImg.getType().equals(HeadImgTypeEnum.BACKGROUND.key())){
                    userMapper.updateByPrimaryKeySelective(new User(userHeadImg.getUserId(),"",null,new Date()));
                }
            }else{
                if(userHeadImg.getType().equals(HeadImgTypeEnum.HEAD_IMG.key())){
                    final User user = userMapper.selectByPrimaryKey(userHeadImg.getUserId());
                    redisTemplate.execute(new SessionCallback() {
                        @Override
                        public Object execute(RedisOperations redisOperations) throws DataAccessException {
                            redisOperations.opsForZSet().add(getKey(USER_LOGIN_TIME,user.getCountryId()),user.getId().toString(),DEFAULT_TIME);
                            redisOperations.opsForZSet().add(getKey(USER_LOGIN_TIME,user.getGender()),user.getId().toString(),System.currentTimeMillis());
                            redisOperations.opsForZSet().add(getKey(USER_LOGIN_TIME),user.getId().toString(),System.currentTimeMillis());
                            return null;
                        }
                    });
                }
            }
        }
        return getImg(userHeadImgReqDto);
    }


}
