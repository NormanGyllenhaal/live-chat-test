package com.rcplatform.livechat.controller;


import com.rcplatform.livechat.common.response.Page;
import com.rcplatform.livechat.dto.request.PushDto;
import com.rcplatform.livechat.service.IPushService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/9/10.
 */
@RequestMapping("push")
@RestController
public class PushController {

    @Resource
    private IPushService pushService;

    @RequestMapping("addPush")
    @ResponseBody
    public void addPush(@RequestBody PushDto pushDto){
        pushService.addPush(pushDto);
    }





    @RequestMapping("/getPushHistory")
    @ResponseBody
    public Page getPushHistory(Integer adminId, Integer pageNo, Integer pageSize){
        int num = pageNo == null ? 1 : pageNo;
        int size = pageSize == null ? 10 :  pageSize;
        return pushService.getPushHistory(adminId,num,size);
    }
}
