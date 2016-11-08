package com.rcplatform.livechat.task;

import com.google.android.gcm.server.MulticastResult;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.rcplatform.livechat.common.enums.IsPushEnum;
import com.rcplatform.livechat.common.enums.PlatformTypeEnum;
import com.rcplatform.livechat.common.enums.PushTypeEnum;
import com.rcplatform.livechat.common.enums.UserGenderEnum;
import com.rcplatform.livechat.common.push.GoogleGcmPush;
import com.rcplatform.livechat.common.push.IosPush;
import com.rcplatform.livechat.mapper.PushCountryMapper;
import com.rcplatform.livechat.mapper.PushInfoMapper;
import com.rcplatform.livechat.mapper.PushMapper;
import com.rcplatform.livechat.mapper.PushUserMapper;
import com.rcplatform.livechat.model.Push;
import com.rcplatform.livechat.model.PushInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yang peng on 2016/11/8.
 */
@Service
public class PushTask {


    private Logger logger = LoggerFactory.getLogger(PushTask.class);


    @Resource
    private PushMapper pushMapper;

    @Resource
    private PushInfoMapper pushInfoMapper;

    @Resource
    private PushUserMapper pushUserMapper;


    @Autowired
    private IosPush iosPush;


    @Autowired
    private PushCountryMapper pushCountryMapper;


    /**
     * 推送定时任务
     */
    @Scheduled(cron = "0/30 * *  * * ? ")
    public void addPush() {
        logger.info("推送定时任务");
        //获取push 任务
        List<Push> pushList = pushMapper.select(new Push(IsPushEnum.NO_PUSH.key()));
        Integer fail = 0;
        Integer success = 0;
        if (CollectionUtils.isNotEmpty(pushList)) {
            for (Push push : pushList) {
                if (push.getPushTime().getTime() <= System.currentTimeMillis()) {
                    List<String> androidPushToken = null;
                    List<String> iosPushToken = null;
                    switch (PushTypeEnum.valueOf(push.getType())) {
                        case ALL:
                            List<PushInfo> androidPushInfoList = pushInfoMapper.selectPushToken(PlatformTypeEnum.ANDROID.key(), null, null);
                            List<PushInfo> iosPushInfoList = pushInfoMapper.selectPushToken(PlatformTypeEnum.IOS.key(), null, null);
                            androidPushToken = getTokenList(androidPushInfoList);
                            iosPushToken = getTokenList(iosPushInfoList);
                            break;
                        case BOY:
                            androidPushToken = getTokenList(pushInfoMapper.selectPushToken(PlatformTypeEnum.ANDROID.key(), UserGenderEnum.BOY.key(), null));
                            iosPushToken = getTokenList(pushInfoMapper.selectPushToken(PlatformTypeEnum.IOS.key(), UserGenderEnum.BOY.key(), null));
                            break;
                        case GIRL:
                            androidPushToken = getTokenList(pushInfoMapper.selectPushToken(PlatformTypeEnum.ANDROID.key(), UserGenderEnum.GIRL.key(), null));
                            iosPushToken = getTokenList(pushInfoMapper.selectPushToken(PlatformTypeEnum.IOS.key(), UserGenderEnum.GIRL.key(), null));
                            break;
                        case TEXT:
                            List<Integer> userIdList = pushUserMapper.selectByPushId(push.getId());
                            androidPushToken = getTokenList(pushInfoMapper.selectPushToken(PlatformTypeEnum.ANDROID.key(), null, userIdList));
                            iosPushToken = getTokenList(pushInfoMapper.selectPushToken(PlatformTypeEnum.IOS.key(), null, userIdList));
                    }
                    if (CollectionUtils.isNotEmpty(androidPushToken)) {
                        MulticastResult multicastResult = GoogleGcmPush.push(new GoogleGcmPush.PushMessage(push.getMode(), push.getContent()), androidPushToken);
                        fail += multicastResult.getFailure();
                        success += multicastResult.getSuccess();
                    }
                    if (CollectionUtils.isNotEmpty(iosPushToken)) {
                        List<String> isoToken = Lists.transform(iosPushToken, new Function<String, String>() {
                            @Override
                            public String apply(String input) {
                                return input.replace(" ", "");
                            }
                        });
                        List<Integer> iosResult = iosPush.sendPush(isoToken, push.getContent());
                        fail += iosResult.get(0);
                        success += iosResult.get(1);
                    }
                    pushMapper.updateByPrimaryKeySelective(new Push(push.getId(), IsPushEnum.PUSH.key(), fail, success));
                }
            }
        }
    }

    private List<String> getTokenList(List<PushInfo> pushInfoList) {
        return Lists.transform(pushInfoList, new Function<PushInfo, String>() {
            @Override
            public String apply(PushInfo input) {
                return input.getPushToken();
            }
        });
    }
}
