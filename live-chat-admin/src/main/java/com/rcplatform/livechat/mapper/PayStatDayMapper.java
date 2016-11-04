package com.rcplatform.livechat.mapper;


import com.rcplatform.livechat.common.mybatis.mapper.CommonMapper;
import com.rcplatform.livechat.model.PayStatDay;

public interface PayStatDayMapper extends CommonMapper<PayStatDay> {


    PayStatDay selectPayRecord();
}