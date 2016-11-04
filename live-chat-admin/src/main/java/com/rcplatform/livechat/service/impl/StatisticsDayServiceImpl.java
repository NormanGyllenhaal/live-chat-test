package com.rcplatform.livechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.rcplatform.livechat.common.response.Page;
import com.rcplatform.livechat.dto.request.PayStatReqDto;
import com.rcplatform.livechat.dto.request.StatisticsReqDto;
import com.rcplatform.livechat.dto.response.PayStatDayDto;
import com.rcplatform.livechat.dto.response.UserPayRecordAdminDto;
import com.rcplatform.livechat.mapper.*;
import com.rcplatform.livechat.model.MatchNumDay;
import com.rcplatform.livechat.model.PayStatDay;
import com.rcplatform.livechat.model.StatisticsDay;
import com.rcplatform.livechat.model.UserKeep;
import com.rcplatform.livechat.service.AbstractService;
import com.rcplatform.livechat.service.IStatisticsDayService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;



/**
 * Created by yang peng on 2016/8/26.
 */
@Service
public class StatisticsDayServiceImpl extends AbstractService implements IStatisticsDayService {


    @Resource
    private StatisticsDayMapper statisticsDayMapper;

    @Resource
    private PayStatDayMapper payStatDayMapper;

    @Resource
    private UserPayRecordMapper userPayRecordMapper;

    @Resource
    private MatchNumDayMapper matchNumDayMapper;

    @Resource
    private UserKeepMapper userKeepMapper;

    @Resource
    private ActiveUserHourMapper activeUserHourMapper;


    /**
     * 视频数据统计
     * @param statisticsReqDto
     * @return
     */
    @Override
    public List getVideoStatistics(StatisticsReqDto statisticsReqDto) {
        return statisticsDayMapper.selectVideo(statisticsReqDto.getBeginDate(),statisticsReqDto.getEndDate());
    }

    /**
     * 获取朋友数据统计
     * @param statisticsReqDto
     * @return
     */
    @Override
    public List getFriendStatistics(StatisticsReqDto statisticsReqDto) {
        return statisticsDayMapper.selectFriend(statisticsReqDto.getBeginDate(),statisticsReqDto.getEndDate());
    }

    /**
     * 获取消费数据统计
     * @param statisticsReqDto
     * @return
     */
    @Override
    public List getConsumeStatistics(StatisticsReqDto statisticsReqDto) {
        return statisticsDayMapper.selectConsume(statisticsReqDto.getBeginDate(),statisticsReqDto.getEndDate());
    }


    /**
     * 获取视频统计
     * @return
     */
    public StatisticsDay getVideoNow(Integer adminId){
        StatisticsDay statisticsDay = statisticsDayMapper.selectFromVideoRecord();
        return statisticsDay;
    }


    /**
     * 获取添加好友统计
     * @return
     */
    public StatisticsDay getFriendNow(Integer adminId){
        Integer videoBefriendCount = statisticsDayMapper.selectFromAddFriendRecord();
        StatisticsDay statisticsDay = statisticsDayMapper.selectUserFriend();
        statisticsDay.setVideoBefriendCount(videoBefriendCount);
        return statisticsDay;
    }


    /**
     * 获取消费统计
     * @return
     */
    public StatisticsDay getConsumeNow(Integer adminId){
        StatisticsDay consumeRecode = statisticsDayMapper.selectFromConsumeRecord();
        return consumeRecode;
    }


    /**
     * 获取支付数据
     * @return
     */
    public PayStatDay getPayStatDayNow(Integer adminId){
        PayStatDay payStatDay = payStatDayMapper.selectPayRecord();
        return payStatDay;
    }


    /**
     * 获取匹配数据
     * @return
     */
    public MatchNumDay getMatchDayNow(Integer adminId){
        MatchNumDay matchNumDay = matchNumDayMapper.selectJoinMatchStat();
        return matchNumDay;
    }



    /**
     * 获取匹配统计
     * @param statisticsReqDto
     * @return
     */
    public List getMatchNum(StatisticsReqDto statisticsReqDto){
        Example example = new Example(MatchNumDay.class);
        example.createCriteria().andBetween("createTime",statisticsReqDto.getBeginDate(),statisticsReqDto.getEndDate());
        PageHelper.orderBy("create_time desc");
        return matchNumDayMapper.selectByExample(example);
    }


    /**
     * 获取购买统计
     * @param payStatReqDto
     * @return
     */
    public PayStatDayDto getPayStatDay(PayStatReqDto payStatReqDto){
        PayStatDayDto payStatDayDto = new PayStatDayDto();
        Example example = new Example(PayStatDay.class);
        example.createCriteria().andBetween("createTime",payStatReqDto.getBeginDate(),payStatReqDto.getEndDate());
        PageHelper.orderBy("create_time desc");
        List<PayStatDay> payStatDays = payStatDayMapper.selectByExample(example);
        payStatDayDto.setList(payStatDays);
        payStatDayDto.setPage(getUserPayRecord(payStatReqDto));
        return payStatDayDto;
    }


    /**
     * 获取购买明细
     * @param payStatReqDto
     * @return
     */
    public Page getUserPayRecord(PayStatReqDto payStatReqDto){
        PageHelper.startPage(payStatReqDto.getPageNo(),payStatReqDto.getPageSize(),"a.create_time desc");
        List<UserPayRecordAdminDto> userPayRecordAdminDtoList = userPayRecordMapper.selectJoinUser(payStatReqDto.getBeginDate(), payStatReqDto.getEndDate());
        return getPage(userPayRecordAdminDtoList);
    }


    /**
     * 获取用户存留和活跃
     * @param statisticsReqDto
     * @return
     */
    @Override
    public List getUserKeepDay(StatisticsReqDto statisticsReqDto) {
        Example example = new Example(UserKeep.class);
        example.createCriteria().andBetween("createTime",statisticsReqDto.getBeginDate(),statisticsReqDto.getEndDate());
        PageHelper.orderBy("create_time desc");
        List<UserKeep> userKeeps = userKeepMapper.selectByExample(example);
        return userKeeps;
    }


    /**
     * 获取用户存留和活跃
     * @param adminId
     * @return
     */
    @Override
    public UserKeep getUserKeepNow(Integer adminId) {
        return userKeepMapper.selectUserRecord();
    }


    /**
     * 获取用户活跃
     * @return
     */
    public List getActiveUserHour(Integer adminId,Date date){
        return activeUserHourMapper.selectActiveHour(date);
    }


}
