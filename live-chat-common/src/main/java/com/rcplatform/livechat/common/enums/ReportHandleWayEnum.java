package com.rcplatform.livechat.common.enums;

/**
 * Created by yang peng on 2016/9/12.
 */
public enum ReportHandleWayEnum implements IEnum{


    HEAD_IMG(1,"未处理"),

    BACKGROUND(2,"已处理"),

    
    HEAD_BACKGROUND(3,"图片和背景");


    // 枚举值
    private final int key;

    // 枚举描述
    private final String desc;


    ReportHandleWayEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public int key() {
        return key;
    }

    @Override
    public String desc() {
        return desc;
    }
}
