package com.rcplatform.livechat;

import com.rcplatform.livechat.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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

    public void testUserMapper(){
        UserMapper userMapper  ;

    }


    @Test
    public void testRedis(){
        Set set = redisTemplate.opsForZSet().reverseRangeByScore("videochat:userprofilegirl", 0, 1);
        System.out.println("数量"+set);
    }
}
