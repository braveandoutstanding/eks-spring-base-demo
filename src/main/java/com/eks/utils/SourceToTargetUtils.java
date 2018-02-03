package com.eks.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
/**
* @copyright create by XuYongJie on 2018/1/24 12:43
* @description 本工具类实现了深复制、将任意两个字段一致的对象进行转换
* @version 1.0.0
*/
@SuppressWarnings("unchecked")
public class SourceToTargetUtils{
    public static <T> T deepClone(T t,TypeReference<T> typereference){//深复制
        return (T)JSON.parseObject(JSON.toJSONString(t),typereference);
    }
    public static <SOURCE,TARGET> TARGET sourceToTarget(SOURCE source,TypeReference<TARGET> typereference){//将任意两个字段一致的对象进行转换
        return (TARGET)JSON.parseObject(JSON.toJSONString(source),typereference);
    }
}