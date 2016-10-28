package com.rcplatform.livechat.common.constant;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Menue,Inc Copyright (c) 2016年3月7日 下午5:53:42
 * <p>
 * Team:Menue Beijing
 * <p>
 *
 * @author yuan.baohua@menue.com.cn
 * @version 1.0.0
 */
public class RedisKeyConstant {

    /**
     * 应用名称
     */
    public static final String APP_NAME = "videochat";

    /**
     * redis key
     */
    public static final String USER = "user";

    /**
     * 保存token 的key
     */
    public static final String TOKEN = "token";


    /**
     * 用户性别key
     */
    public static final String GENDER = "gender";


    /**
     * 用户朋友key set
     */
    public static final String USERFRIEND = "userfriend";

    /**
     * 用户黑名单key  set
     */
    public static final String BLACKLIST = "blacklist";

    /**
     * 临时存储key  set
     */
    public static final String TEMP = "temp";


    /**
     * 用户匹配关系生存时间 key
     */
    public static final String USERSURVIVAL = "usersurvival";


    /**
     * 用户举报集合key
     */
    public static final String USERREPORT = "userreport";

    /**
     * 系统举报key
     */
    public static final String REPORT = "report";

    /**
     * 上次匹配的用户key
     */
    public static final String LASTUSER = "lastuser";

    /**
     * 国家key
     */
    public static final String COUNTRY = "country";

    /**
     * 地理位置
     */
    public static final String GEO="geo";


    /**
     * 临时地理位置存储
     */
    public static final String TEMPGEO ="tempgeo";


    /**
     * 临时并集key
     */
    public static final String TEMPSINTER="tempsinter";


    /**
     * 用户地理位置
     */
    public static final String USER_LOCATION = "userlocation";


    /**
     * 用户被举报时间 zset key
     */
    public static final String REPORT_TIME = "reporttime";


    /**
     * 管理员账号
     */
    public static final String ADMIN = "admin";


    /**
     * 用户生存时间key zset
     */
    public  static final String USER_LIVE_TIME ="userlivetime";


    /**
     * 用户的性别条件hash
     */
    public static final String USER_GENDER_CONDITION="usergendercondition";

    /**
     * 用户id设备key hash
     */
    public static final String USER_DEVICE = "userdevice";


    /**
     * 匹配record
     */
    public static final String MATCH_STAT = "matchstat";


}
