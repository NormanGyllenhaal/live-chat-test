package com.rcplatform.livechat.dto.request;


import com.rcplatform.livechat.model.Push;

/**
 * Created by yang peng on 2016/9/12.
 */

public class PushDto extends Push {



    private Integer adminId;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PushDto{");
        sb.append("adminId=").append(adminId);
        sb.append('}');
        return sb.toString();
    }
}
