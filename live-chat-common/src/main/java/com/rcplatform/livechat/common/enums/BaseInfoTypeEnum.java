package com.rcplatform.livechat.common.enums;


import com.bj58.spat.gaea.serializer.component.annotation.GaeaMember;
import com.bj58.spat.gaea.serializer.component.annotation.GaeaSerializable;

/**
 * Created by yang peng on 2016/8/16.
 */
@GaeaSerializable
public enum BaseInfoTypeEnum implements IEnum{

    @GaeaMember
    ALL(0,"送花"),

    @GaeaMember
    COUNTRY(1,"国家"),

    @GaeaMember
    LANGUAGE(2,"语言"),

    @GaeaMember
    CONSUME(3,"消费品");


    // 枚举值
    private final int key;

    // 枚举描述
    private final String desc;


    BaseInfoTypeEnum(int key, String desc) {
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
