package com.eks.utils;

import com.alibaba.fastjson.JSON;
/**
* xuyj,深复制工具类,注意进行深复制的pojo类(T)的属性需有对应的get、set方法
 */
@SuppressWarnings("unchecked")
public class DeepCloneUtils<T> {
    public static <T> T deepClone(T t){
       return (T)JSON.parseObject(JSON.toJSONString(t),t.getClass());
    }
}