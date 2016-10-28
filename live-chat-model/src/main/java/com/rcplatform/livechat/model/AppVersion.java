package com.rcplatform.livechat.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "rc_app_version")

public class AppVersion implements Serializable {

    public AppVersion() {
    }

    public AppVersion(Integer appId) {
        this.appId = appId;
    }

    @Id

    private Integer id;

    /**
     * 应用id
     */
    @Column(name = "app_id")

    private Integer appId;

    /**
     * 应用的描述信息
     */

    private String description;

    /**
     * 安卓最近版本
     */
    @Column(name = "android_max_version_name")

    private String androidMaxVersionName;

    /**
     * 安卓版本
     */
    @Column(name = "android_max_version")

    private String androidMaxVersion;

    /**
     * 安卓最低兼容版本
     */
    @Column(name = "android_min_version")

    private String androidMinVersion;

    /**
     * ios最近版本
     */
    @Column(name = "ios_max_version_name")

    private String iosMaxVersionName;

    /**
     * iso最新版本
     */
    @Column(name = "ios_max_version")

    private String iosMaxVersion;

    /**
     * ios最低兼容版本
     */
    @Column(name = "ios_min_version")

    private String iosMinVersion;


    @Column(name = "ios_status")

    private Integer iosStatus;
    /**
     *
     * 创建时间
     */
    @Column(name = "create_time")

    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")

    private Date updateTime;



    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取应用id
     *
     * @return app_id - 应用id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 设置应用id
     *
     * @param appId 应用id
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取应用的描述信息
     *
     * @return description - 应用的描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置应用的描述信息
     *
     * @param description 应用的描述信息
     */
    public void setDescription(String description) {
        this.description = description;
    }



    /**
     * 获取安卓版本
     *
     * @return android_max_version - 安卓版本
     */
    public String getAndroidMaxVersion() {
        return androidMaxVersion;
    }

    /**
     * 设置安卓版本
     *
     * @param androidMaxVersion 安卓版本
     */
    public void setAndroidMaxVersion(String androidMaxVersion) {
        this.androidMaxVersion = androidMaxVersion;
    }

    /**
     * 获取安卓最低兼容版本
     *
     * @return android_min_version - 安卓最低兼容版本
     */
    public String getAndroidMinVersion() {
        return androidMinVersion;
    }

    /**
     * 设置安卓最低兼容版本
     *
     * @param androidMinVersion 安卓最低兼容版本
     */
    public void setAndroidMinVersion(String androidMinVersion) {
        this.androidMinVersion = androidMinVersion;
    }



    /**
     * 获取iso最新版本
     *
     * @return ios_max_version - iso最新版本
     */
    public String getIosMaxVersion() {
        return iosMaxVersion;
    }

    /**
     * 设置iso最新版本
     *
     * @param iosMaxVersion iso最新版本
     */
    public void setIosMaxVersion(String iosMaxVersion) {
        this.iosMaxVersion = iosMaxVersion;
    }

    /**
     * 获取ios最低兼容版本
     *
     * @return ios_min_version - ios最低兼容版本
     */
    public String getIosMinVersion() {
        return iosMinVersion;
    }

    /**
     * 设置ios最低兼容版本
     *
     * @param iosMinVersion ios最低兼容版本
     */
    public void setIosMinVersion(String iosMinVersion) {
        this.iosMinVersion = iosMinVersion;
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


    public String getAndroidMaxVersionName() {
        return androidMaxVersionName;
    }

    public void setAndroidMaxVersionName(String androidMaxVersionName) {
        this.androidMaxVersionName = androidMaxVersionName;
    }


    public AppVersion(String iosMaxVersionName) {
        this.iosMaxVersionName = iosMaxVersionName;
    }


    public String getIosMaxVersionName() {
        return iosMaxVersionName;
    }

    public void setIosMaxVersionName(String iosMaxVersionName) {
        this.iosMaxVersionName = iosMaxVersionName;
    }

    public Integer getIosStatus() {
        return iosStatus;
    }

    public void setIosStatus(Integer iosStatus) {
        this.iosStatus = iosStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppVersion{");
        sb.append("id=").append(id);
        sb.append(", appId=").append(appId);
        sb.append(", description='").append(description).append('\'');
        sb.append(", androidMaxVersionName='").append(androidMaxVersionName).append('\'');
        sb.append(", androidMaxVersion='").append(androidMaxVersion).append('\'');
        sb.append(", androidMinVersion='").append(androidMinVersion).append('\'');
        sb.append(", iosMaxVersionName='").append(iosMaxVersionName).append('\'');
        sb.append(", iosMaxVersion='").append(iosMaxVersion).append('\'');
        sb.append(", iosMinVersion='").append(iosMinVersion).append('\'');
        sb.append(", iosStatus=").append(iosStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append('}');
        return sb.toString();
    }
}