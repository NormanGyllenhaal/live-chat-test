package com.rcplatform.livechat.mapper;

import com.rcplatform.livechat.common.mybatis.mapper.CommonMapper;
import com.rcplatform.livechat.model.MatchNumDay;

public interface MatchNumDayMapper extends CommonMapper<MatchNumDay> {



    MatchNumDay selectJoinMatchStat();
}