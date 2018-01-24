package com.eks.utils.base;
/**
* @copyright create by XuYongJie on 2018/1/20 16:15
* @description Controller层工具类
*/
public class ResultUtils<T> {
    public static <T> Result handle(Class<T> c,T t){
        return new Result<T>().setResult(t);
    }
}
