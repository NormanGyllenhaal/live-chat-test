package com.rcplatform.livechat.task;

import com.google.android.gcm.server.MulticastResult;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.rcplatform.livechat.common.enums.IsPushEnum;
import com.rcplatform.livechat.common.enums.PlatformTypeEnum;
import com.rcplatform.livechat.common.push.GoogleGcmPush;
import com.rcplatform.livechat.common.push.IosPush;
import com.rcplatform.livechat.dto.response.PushUserDto;
import com.rcplatform.livechat.mapper.*;
import com.rcplatform.livechat.model.Push;
import com.rcplatform.livechat.model.PushCountry;
import com.rcplatform.livechat.model.PushInfo;
import com.rcplatform.livechat.model.PushLanguage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    @Autowired
    private PushLanguageMapper pushLanguageMapper;


    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 推送定时任务
     */
    @Scheduled(cron = "0/30 * *  * * ? ")
    public void addPush() {
        logger.info("推送定时任务");
        //获取push 任务
        Example example = new Example(PushCountry.class);
        example.createCriteria().andEqualTo("isPush",IsPushEnum.NO_PUSH.key()).andGreaterThanOrEqualTo("pushTime",new Date());
        List<PushCountry> pushCountries = pushCountryMapper.selectByExample(example);
        Integer fail = 0;
        Integer success = 0;
        if (CollectionUtils.isNotEmpty(pushCountries)) {
            for(final PushCountry pushCountry:pushCountries){
                Push push = pushMapper.selectByPrimaryKey(pushCountry.getPushId());
                Example pushLanguageExample = new Example(PushLanguage.class);
                pushLanguageExample.createCriteria().andEqualTo("pushId",pushCountry.getPushId());
                List<PushLanguage> pushLanguages = pushLanguageMapper.selectByExample(pushLanguageExample);
                String countryId = pushCountry.getCountryId();
                List<String> strings = Splitter.on(",").trimResults().splitToList(countryId);
                List<Integer> countryIdList = Lists.transform(strings, new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s);
                    }
                });
                List<PushUserDto> pushUserDtos = pushInfoMapper.selectJoinUser(PlatformTypeEnum.ANDROID.key(), countryIdList);



            }



            /*for (Push push : pushList) {
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
            }*/
        }
    }



    private void push( List<PushUserDto> pushUserDtos, List<PushLanguage> pushLanguages,final PushCountry pushCountry ){
        for(final PushLanguage pushLanguage:pushLanguages){
            Collection<PushUserDto> filterList = Collections2.filter(pushUserDtos, new Predicate<PushUserDto>() {
                @Override
                public boolean apply(PushUserDto pushUserDto) {
                    return pushUserDto.getLanguageId().contains(pushLanguage.getLanguageId().toString());
                }
            });
            pushUserDtos.removeAll(filterList);
            List<PushUserDto> changeList = new ArrayList<>();
            changeList.addAll(filterList);
            final List<String> pushTokenList = Lists.transform(changeList , new Function<PushUserDto, String>() {
                @Override
                public String apply(PushUserDto pushUserDto) {
                    return pushUserDto.getPushToken();
                }
            });

        }
         final List<String> pushTokenList = Lists.transform(pushUserDtos, new Function<PushUserDto, String>() {
            @Override
            public String apply(PushUserDto pushUserDto) {
                return pushUserDto.getPushToken();
            }
        });

    }


    public MulticastResult androidPush(final Push push,final List<String> pushTokenList ) throws ExecutionException, InterruptedException {
        Future<MulticastResult> result = threadPoolTaskExecutor.submit(new Callable<MulticastResult>() {
            @Override
            public MulticastResult call() throws Exception {
                MulticastResult multicastResult = GoogleGcmPush.push(new GoogleGcmPush.PushMessage(push.getMode(), push.getContent()), pushTokenList);
                return multicastResult;
            }
        });
        return result.get();
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
