package com.rcplatform.livechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.rcplatform.livechat.common.response.Page;
import com.rcplatform.livechat.dto.request.PushDto;
import com.rcplatform.livechat.mapper.PushMapper;
import com.rcplatform.livechat.model.Push;
import com.rcplatform.livechat.service.AbstractService;
import com.rcplatform.livechat.service.IPushService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
@Service
public class PushServiceImpl extends AbstractService implements IPushService {

    @Resource
    private PushMapper pushMapper;

    @Override
    public void addPush(PushDto pushDto) {
        pushMapper.insertSelective(pushDto);
    }


    @Override
    public Page getPushHistory(Integer adminId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize,"create_time desc");
        List<Push> pushList = pushMapper.selectAll();
        return getPage(pushList);
    }
}
