package com.rcplatform.livechat.service;

/**
 * Created by yang peng on 2016/8/26.
 */
public interface ITimedTaskService {

    void userStatisticsTask();


    void statisticsDayTask();


    void payStatDayTask();


    void matchNumDayTask();



    void userActiveHourTask();
}
