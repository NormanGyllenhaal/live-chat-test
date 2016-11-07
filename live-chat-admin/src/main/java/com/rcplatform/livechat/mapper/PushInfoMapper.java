package com.rcplatform.livechat.mapper;


import com.rcplatform.livechat.common.mybatis.mapper.CommonMapper;
import com.rcplatform.livechat.model.PushInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PushInfoMapper extends CommonMapper<PushInfo> {


    /**
     * 更新pushtoken
     * @return
     */
    Integer updatePushTokenByUserId(PushInfo pushInfo);


    /**
     * 获取pushToken
     * @param platformType
     * @param gender
     * @param userIdList
     * @return
     */
    List<PushInfo> selectPushToken(@Param("platformType") Integer platformType, @Param("gender") Integer gender, @Param("userIdList") List<Integer> userIdList);

}