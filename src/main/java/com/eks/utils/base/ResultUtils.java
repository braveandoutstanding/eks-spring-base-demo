package com.eks.utils.base;

public class ResultUtils<T> {
    public static <T> Result handle(Class<T> c,T t){
        return new Result<T>().setResult(t);
    }
}
