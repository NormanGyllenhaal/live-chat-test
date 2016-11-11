package com.rcplatform.livechat;

import com.rcplatform.livechat.common.enums.PushWayEnum;
import com.rcplatform.livechat.dto.request.PushDto;
import com.rcplatform.livechat.model.PushLanguage;
import com.rcplatform.livechat.service.IPushService;
import com.rcplatform.livechat.task.PushTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by yang peng on 2016/11/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PushServiceTest {

    @Autowired
    private IPushService pushService;

    @Autowired
    private PushTask pushTask;

    @Test
    public void AddPush() throws InterruptedException {
        final List<PushLanguage> pushLanguages = new ArrayList<>();
        pushLanguages.add(new PushLanguage(){
            {
                setContext("123");
                setLanguageId(1);
            }
        });
        pushLanguages.add(new PushLanguage(){
            {
                setContext("中文");
                setLanguageId(2);
            }
        });
        pushService.addPush(new PushDto(){
             {
                 setAppId(1);
                 setType(1);
                 setContent("hahaha");
                 setPushWay(PushWayEnum.MESSAGE.key());
                 setPushLanguages(pushLanguages);
                 setPushTime(new Date());
             }
         });
        Thread.sleep(5000);
    }


    @Test
    public void testPushTask() throws InterruptedException {
        try {
            pushTask.executePush();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.sleep(5000);
    }
}
