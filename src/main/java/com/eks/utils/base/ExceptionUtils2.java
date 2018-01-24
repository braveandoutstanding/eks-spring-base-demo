package com.eks.utils.base;

import java.io.PrintWriter;
import java.io.StringWriter;
/**
* @copyright create by XuYongJie on 2018/1/20 16:12
* @description 异常处理工具类
*/
public class ExceptionUtils2 {
    public static String exceptionString(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(new StringWriter(),true));
        return stringWriter.toString();
    }
    public static Result exceptionResult(Exception exception) {
        if(exception.getMessage() == null){
            return new Result().setSuccess(false).setErrorMsg(exception.toString());
        }
        return new Result().setSuccess(false).setErrorMsg(exception.getMessage());
    }
}
