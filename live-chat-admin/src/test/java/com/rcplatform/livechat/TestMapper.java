package com.rcplatform.livechat;

import com.rcplatform.livechat.common.enums.IsPushEnum;
import com.rcplatform.livechat.common.response.Page;
import com.rcplatform.livechat.dto.request.ReportAdminReqDto;
import com.rcplatform.livechat.mapper.PushCountryMapper;
import com.rcplatform.livechat.mapper.UserMapper;
import com.rcplatform.livechat.model.PushCountry;
import com.rcplatform.livechat.service.IReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private PushCountryMapper pushCountryMapper;

    public void testUserMapper(){
        UserMapper userMapper  ;

    }


    //@Test
    public void testRedis(){
        Example example = new Example(PushCountry.class);
        example.createCriteria().andEqualTo("isPush", IsPushEnum.NO_PUSH.key()).andLessThan("pushTime", new Date());
        List<PushCountry> pushCountries = pushCountryMapper.selectByExample(example);
        System.out.println(pushCountries);
    }


    //@Test
    public void testReport(){
        final List list = new ArrayList<ReportAdminReqDto.ReportStaticAdmin>();
        list.add(new ReportAdminReqDto.ReportStaticAdmin(3,1,"",1));
        Page page = reportService.handleReport(new ReportAdminReqDto() {
            {
                setList(list);
            }
        });
    }

    @Test
    public void testRedisHash(){
         redisTemplate.opsForHash().get("videochat:user:1", "52");
    }



}
