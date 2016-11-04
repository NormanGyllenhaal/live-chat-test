package com.rcplatform.livechat.service;


import com.rcplatform.livechat.common.response.Page;
import com.rcplatform.livechat.dto.request.ReportAdminReqDto;

/**
 * Created by Administrator on 2016/9/11.
 */
public interface IReportService {


    Page getReport(Integer adminId, Integer pageNo, Integer pageSize);


    Page handleReport(ReportAdminReqDto reportAdminReqDto);

}
