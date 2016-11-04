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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
@Service
public class UserHeadImgServiceImpl extends AbstractService implements IUserHeadImgService {


    @Resource
    private UserHeadImgMapper userHeadImgMapper;

    @Resource
    private UserMapper userMapper;


    @Override
    public Page getImg(ImgAdminReqDto imgAdminReqDto) {
        PageHelper.startPage(imgAdminReqDto.getPageNo(),imgAdminReqDto.getPageSize(),"create_time desc");
        List imgList = userHeadImgMapper.select(new UserHeadImg(imgAdminReqDto.getGender(),imgAdminReqDto.getType(), UserHeadImgHandleEnum.UNHANDLE.key()));
        return getPage(imgList);
    }


    @Override
    public Page handleImg(UserHeadImgReqDto userHeadImgReqDto) {
        List<UserHeadImgReqDto.HeadImgChecked> checkedList = userHeadImgReqDto.getList();
        for(UserHeadImgReqDto.HeadImgChecked imgChecked:checkedList){
            userHeadImgMapper.updateByPrimaryKeySelective(new UserHeadImg(imgChecked.getImgId(),new Date(),UserHeadImgHandleEnum.HANDLE.key(), imgChecked.getChecked()));
            if(imgChecked.getChecked().equals(UserHeadImgCheckedEnum.UNPASS.key())){
                UserHeadImg userHeadImg = userHeadImgMapper.selectByPrimaryKey(imgChecked.getImgId());
                if(userHeadImg.getType().equals(HeadImgTypeEnum.HEAD_IMG.key())){
                    userMapper.updateByPrimaryKeySelective(new User(userHeadImg.getUserId(),null,"",new Date()));
                }else if(userHeadImg.getType().equals(HeadImgTypeEnum.BACKGROUND.key())){
                    userMapper.updateByPrimaryKeySelective(new User(userHeadImg.getUserId(),"",null,new Date()));
                }
            }
        }
        return getImg(userHeadImgReqDto);
    }
}
