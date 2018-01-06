package com.eks.utils.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultUtils<T> {
    private static final Logger logger = LoggerFactory.getLogger(ResultUtils.class);
    public static <T> Result handle(Class<T> c,T t){
        try {
            return new Result<T>().setResult(t);
        } catch (Throwable throwable) {
            logger.error(ThrowableUtils.throwableString(throwable));
            return ThrowableUtils.throwableResult(throwable);
        }
    }
}
