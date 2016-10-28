package com.rcplatform.livechat.model;

import com.bj58.spat.gaea.serializer.component.annotation.GaeaMember;
import com.bj58.spat.gaea.serializer.component.annotation.GaeaSerializable;

import java.io.Serializable;
import java.util.Date;

@Table(name = "rc_report_record")

public class ReportRecord implements Serializable {
    /**
     * 主键
     */
    @Id

    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "use_id")

    private Integer useId;

    /**
     * 累计被封禁的次数
     */
    @Column(name = "off_count")

    private Integer offCount;

    /**
     * 举报别人的次数
     */
    @Column(name = "report_num")

    private Integer reportNum;

    /**
     * 被举报的次数
     */
    @Column(name = "reported_num")

    private Integer reportedNum;

    /**
     * 禁用的时间
     */
    @Column(name = "off_time")

    private Date offTime;

    /**
     * 禁用的时间长度 ，小时
     */
    @Column(name = "off_duration")

    private Integer offDuration;

    /**
     * 更新时间
     */
    @Column(name = "update_time")

    private Date updateTime;

    /**
     * 记录创建时间
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
     * 获取用户id
     *
     * @return use_id - 用户id
     */
    public Integer getUseId() {
        return useId;
    }

    /**
     * 设置用户id
     *
     * @param useId 用户id
     */
    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    /**
     * 获取累计被封禁的次数
     *
     * @return off_count - 累计被封禁的次数
     */
    public Integer getOffCount() {
        return offCount;
    }

    /**
     * 设置累计被封禁的次数
     *
     * @param offCount 累计被封禁的次数
     */
    public void setOffCount(Integer offCount) {
        this.offCount = offCount;
    }

    /**
     * 获取举报别人的次数
     *
     * @return report_num - 举报别人的次数
     */
    public Integer getReportNum() {
        return reportNum;
    }

    /**
     * 设置举报别人的次数
     *
     * @param reportNum 举报别人的次数
     */
    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    /**
     * 获取被举报的次数
     *
     * @return reported_num - 被举报的次数
     */
    public Integer getReportedNum() {
        return reportedNum;
    }

    /**
     * 设置被举报的次数
     *
     * @param reportedNum 被举报的次数
     */
    public void setReportedNum(Integer reportedNum) {
        this.reportedNum = reportedNum;
    }

    /**
     * 获取禁用的时间
     *
     * @return off_time - 禁用的时间
     */
    public Date getOffTime() {
        return offTime;
    }

    /**
     * 设置禁用的时间
     *
     * @param offTime 禁用的时间
     */
    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    /**
     * 获取禁用的时间长度 ，小时
     *
     * @return off_duration - 禁用的时间长度 ，小时
     */
    public Integer getOffDuration() {
        return offDuration;
    }

    /**
     * 设置禁用的时间长度 ，小时
     *
     * @param offDuration 禁用的时间长度 ，小时
     */
    public void setOffDuration(Integer offDuration) {
        this.offDuration = offDuration;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取记录创建时间
     *
     * @return create_time - 记录创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置记录创建时间
     *
     * @param createTime 记录创建时间
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
        sb.append(", useId=").append(useId);
        sb.append(", offCount=").append(offCount);
        sb.append(", reportNum=").append(reportNum);
        sb.append(", reportedNum=").append(reportedNum);
        sb.append(", offTime=").append(offTime);
        sb.append(", offDuration=").append(offDuration);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}