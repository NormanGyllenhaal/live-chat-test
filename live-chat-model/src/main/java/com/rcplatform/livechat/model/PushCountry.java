package com.rcplatform.livechat.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "rc_push_country")
public class PushCountry implements Serializable {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * pushid
     */
    private Integer pushid;

    /**
     * 国家id
     */
    @Column(name = "country_id")
    private Integer countryId;

    /**
     * 是否推送
     */
    @Column(name = "push_id")
    private Integer pushId;

    /**
     * 推送时间
     */
    @Column(name = "push_time")
    private Date pushTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取pushid
     *
     * @return pushid - pushid
     */
    public Integer getPushid() {
        return pushid;
    }

    /**
     * 设置pushid
     *
     * @param pushid pushid
     */
    public void setPushid(Integer pushid) {
        this.pushid = pushid;
    }

    /**
     * 获取国家id
     *
     * @return country_id - 国家id
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * 设置国家id
     *
     * @param countryId 国家id
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * 获取是否推送
     *
     * @return push_id - 是否推送
     */
    public Integer getPushId() {
        return pushId;
    }

    /**
     * 设置是否推送
     *
     * @param pushId 是否推送
     */
    public void setPushId(Integer pushId) {
        this.pushId = pushId;
    }

    /**
     * 获取推送时间
     *
     * @return push_time - 推送时间
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * 设置推送时间
     *
     * @param pushTime 推送时间
     */
    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pushid=").append(pushid);
        sb.append(", countryId=").append(countryId);
        sb.append(", pushId=").append(pushId);
        sb.append(", pushTime=").append(pushTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}