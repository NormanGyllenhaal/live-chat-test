package com.rcplatform.livechat.controller;


import com.rcplatform.livechat.common.response.Page;
import com.rcplatform.livechat.dto.request.ReportAdminReqDto;
import com.rcplatform.livechat.service.IReportService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/9/10.
 */
@RequestMapping("report")
@RestController
public class ReportController {


    @Resource
    private IReportService reportService;

    /**
     * 获取举报信息列表
     * @return
     */
    @RequestMapping("getReport")
    @ResponseBody
    public Page getReport(Integer adminId, Integer pageNo, Integer pageSize){
        int num = pageNo == null ? 1 : pageNo;
        int size = pageSize == null ? 10 :  pageSize;
        return reportService.getReport(adminId,num,size);
    }

    /**
     * 处理举报信息
     * @return
     */
    @RequestMapping("handleReport")
    @ResponseBody
    public Page handleReport(@RequestBody ReportAdminReqDto reportAdminReqDto){
        return reportService.handleReport(reportAdminReqDto);
    }
}
