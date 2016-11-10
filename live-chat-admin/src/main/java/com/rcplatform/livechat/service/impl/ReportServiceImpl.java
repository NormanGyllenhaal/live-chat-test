package com.rcplatform.livechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.rcplatform.livechat.common.enums.ReportHandleWayEnum;
import com.rcplatform.livechat.common.enums.ReportIsHandleEnum;
import com.rcplatform.livechat.common.enums.ReportPageEnum;
import com.rcplatform.livechat.common.enums.ReportResultEnum;
import com.rcplatform.livechat.common.response.Page;
import com.rcplatform.livechat.common.util.StringUtil;
import com.rcplatform.livechat.dto.request.ReportAdminReqDto;
import com.rcplatform.livechat.mapper.ReportMapper;
import com.rcplatform.livechat.mapper.UserMapper;
import com.rcplatform.livechat.model.Report;
import com.rcplatform.livechat.model.User;
import com.rcplatform.livechat.service.AbstractService;
import com.rcplatform.livechat.service.IReportService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.rcplatform.livechat.common.constant.RedisKeyConstant.*;

/**
 * Created by Administrator on 2016/9/11.
 */
@Service
public class ReportServiceImpl extends AbstractService implements IReportService {

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 获取举报信息列表
     * @param adminId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page getReport(Integer adminId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize,"create_time desc");
        List reportList = reportMapper.selectJoinUser(ReportIsHandleEnum.NOHANDLE.key(), ReportPageEnum.SHOW.key());
        return getPage(reportList);
    }


    /**
     * 处理举报
     * @param reportAdminReqDto
     * @return
     */
    @Override
    public Page handleReport(ReportAdminReqDto reportAdminReqDto) {
        final String key = StringUtil.buildString(APP_NAME, USER_PROFILE);
        final String girlKey = StringUtil.buildString(APP_NAME, USER_PROFILE_GIRL);
        final String boyKey = StringUtil.buildString(APP_NAME, USER_PROFILE_BOY);
        for(ReportAdminReqDto.ReportStaticAdmin reportDto:reportAdminReqDto.getList()){
            reportMapper.updateByPrimaryKeySelective(new Report(reportDto.getReportId(),
                    ReportIsHandleEnum.HANDLE.key(),reportDto.getResult(),reportDto.getDescription(),new Date()));
            if(reportDto.getResult().equals(ReportResultEnum.ACCEPT.key())){
                final Report report = reportMapper.selectByPrimaryKey(reportDto.getReportId());
                redisTemplate.executePipelined(new SessionCallback() {
                    @Override
                    public Object execute(RedisOperations redisOperations) throws DataAccessException {
                        redisOperations.opsForZSet().remove(key,report.getReportedUserId().toString());
                        redisOperations.opsForZSet().remove(girlKey,report.getReportedUserId().toString());
                        redisOperations.opsForZSet().remove(boyKey,report.getReportedUserId().toString());
                        return null;
                    }
                });
                if(reportDto.getHandleWay().equals(ReportHandleWayEnum.HEAD_IMG.key())){
                    userMapper.updateByPrimaryKeySelective(new User(report.getReportedUserId(),null,"",new Date()));
                }else if(reportDto.getHandleWay().equals(ReportHandleWayEnum.BACKGROUND.key())){
                    userMapper.updateByPrimaryKeySelective(new User(report.getReportedUserId(),"",null,new Date()));
                }else if(reportDto.getHandleWay().equals(ReportHandleWayEnum.HEAD_BACKGROUND.key())){
                    userMapper.updateByPrimaryKeySelective(new User(report.getReportedUserId(),"","",new Date()));
                }
            }
        }
        return getReport(reportAdminReqDto.getAdminId(),1,8);
    }
}
