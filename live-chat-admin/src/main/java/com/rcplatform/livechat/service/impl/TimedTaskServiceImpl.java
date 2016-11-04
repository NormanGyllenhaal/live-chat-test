package com.rcplatform.livechat.service.impl;


import com.rcplatform.livechat.common.util.DateUtil;
import com.rcplatform.livechat.mapper.*;
import com.rcplatform.livechat.model.*;
import com.rcplatform.livechat.service.ITimedTaskService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by yang peng on 2016/8/26.
 */
@Component
public class TimedTaskServiceImpl implements ITimedTaskService {


    @Resource
    private UserStatisticsMapper userStatisticsMapper;
    
    
    @Resource
    private StatisticsDayMapper statisticsDayMapper;

    @Resource
    private PayStatDayMapper payStatDayMapper;


    @Resource
    private MatchNumDayMapper matchNumDayMapper;


    @Resource
    private UserKeepMapper userKeepMapper;


    @Resource
    private ActiveUserHourMapper activeUserHourMapper;



    @Scheduled(cron="0 59 23 * * ?")
    @Override
    public void userStatisticsTask() {
        UserStatistics userStatistics = userStatisticsMapper.selectFromUser();
        userStatisticsMapper.insertSelective(userStatistics);
        UserKeep userKeep = userKeepMapper.selectUserRecord();
        userKeepMapper.insertSelective(userKeep);
    }



    @Scheduled(cron="0 59 23 * * ?")
    @Override
    public void statisticsDayTask(){
        StatisticsDay statisticsDay = statisticsDayMapper.selectFromVideoRecord();
        Integer videoBefriendCount = statisticsDayMapper.selectFromAddFriendRecord();
        StatisticsDay consumeRecode = statisticsDayMapper.selectFromConsumeRecord();
        StatisticsDay bothFriendStatistics = statisticsDayMapper.selectUserFriend();
        statisticsDay.setVideoBefriendCount(videoBefriendCount);
        statisticsDay.setMatchPagePayPeople(consumeRecode.getMatchPagePayPeople());
        statisticsDay.setMatchPagePayCount(consumeRecode.getMatchPagePayCount());
        statisticsDay.setGirlPayCount(consumeRecode.getGirlPayCount());
        statisticsDay.setGirlPayPeople(consumeRecode.getGirlPayPeople());
        statisticsDay.setBoyPayCount(consumeRecode.getBoyPayCount());
        statisticsDay.setBoyPayPeople(consumeRecode.getBoyPayPeople());
        statisticsDay.setFriendVideoCount(statisticsDay.getFriendVideoPeople()/2);
        statisticsDay.setBothFriendTotal(bothFriendStatistics.getBothFriendTotal());
        statisticsDay.setBothFriendDay(bothFriendStatistics.getBothFriendDay());
        statisticsDayMapper.insertSelective(statisticsDay);
    }



    @Scheduled(cron="0 59 23 * * ?")
    @Override
    public void payStatDayTask(){
        PayStatDay payStatDay = payStatDayMapper.selectPayRecord();
        payStatDayMapper.insertSelective(payStatDay);
    }



    @Scheduled(cron="0 59 23 * * ?")
    @Override
    public void matchNumDayTask(){
        MatchNumDay matchNumDay = matchNumDayMapper.selectJoinMatchStat();
        matchNumDayMapper.insertSelective(matchNumDay);
    }



    @Scheduled(cron = "0 0 0/1 * * ?")
    public void userActiveHourTask(){
        ActiveUserHour activeUserHour = activeUserHourMapper.selectUserApiRecord(DateUtil.getDatePlusHour(new Date(), -1), new Date());
        activeUserHourMapper.insertSelective(activeUserHour);
    }



}
