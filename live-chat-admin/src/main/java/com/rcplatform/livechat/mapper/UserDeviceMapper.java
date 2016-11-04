package com.rcplatform.livechat.mapper;


import com.rcplatform.livechat.common.mybatis.mapper.CommonMapper;
import com.rcplatform.livechat.model.UserDevice;

public interface UserDeviceMapper extends CommonMapper<UserDevice> {


    Integer updateByUserId(UserDevice userDevice);
}