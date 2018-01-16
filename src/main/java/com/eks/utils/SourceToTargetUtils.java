package com.eks.utils;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("unchecked")
public class SourceToTargetUtils<SOURCE,TARGET> {
    public static <SOURCE,TARGET> TARGET sourceToTargetUtils(SOURCE source,TARGET target){
        return (TARGET)JSON.parseObject(JSON.toJSONString(source),target.getClass());
    }
}