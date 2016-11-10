package com.rcplatform.livechat;

import com.rcplatform.livechat.common.response.Page;
import com.rcplatform.livechat.dto.request.ReportAdminReqDto;
import com.rcplatform.livechat.mapper.UserMapper;
import com.rcplatform.livechat.service.IReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by yang peng on 2016/11/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TestMapper {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IReportService reportService;

    public void testUserMapper(){
        UserMapper userMapper  ;

    }


    @Test
    public void testRedis(){
        Set set = redisTemplate.opsForZSet().reverseRangeByScore("videochat:userprofilegirl", 0, 1);
        System.out.println("数量"+set);
    }


    @Test
    public void testReport(){
        final List list = new ArrayList<ReportAdminReqDto.ReportStaticAdmin>();
        list.add(new ReportAdminReqDto.ReportStaticAdmin(3,1,"",1));
        Page page = reportService.handleReport(new ReportAdminReqDto() {
            {
                setList(list);
            }
        });
    }



}
