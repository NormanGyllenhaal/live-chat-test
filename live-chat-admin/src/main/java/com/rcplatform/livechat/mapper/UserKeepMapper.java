package com.rcplatform.livechat.mapper;


import com.rcplatform.livechat.common.mybatis.mapper.CommonMapper;
import com.rcplatform.livechat.model.UserKeep;

public interface UserKeepMapper extends CommonMapper<UserKeep> {


   UserKeep selectUserRecord();
}