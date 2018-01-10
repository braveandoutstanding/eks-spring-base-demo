package com.eks.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor//全参构造器
public enum UserSexualEnum {
    MALE("男"),FEMALE("女");
    @Getter//为标识的字段生成对应的get方法
    private String sexual;

}