package com.eks.utils.base;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

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
    public static Result exceptionResult(ConstraintViolationException exception) {//使用hibernate-validation对参数进行校验的异常处理
        Set<ConstraintViolation<?>> constraintViolationSet = exception.getConstraintViolations();
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = System.getProperty("line.separator", "\n");//从当前系统中获取换行符，默认是"\n"
        int i = 0;
        for (ConstraintViolation<?> constraintViolation : constraintViolationSet) {
            i = i + 1;
            String message = constraintViolation.getMessage();
            String messageTemplate = constraintViolation.getMessageTemplate();
            if(message != null && message.equals(messageTemplate)){
                stringBuffer.append(message);
                if(i != 1){
                    stringBuffer.append(lineSeparator);
                }
                continue;
            }
            stringBuffer.append(constraintViolation.toString());
            if(i != 1){
                stringBuffer.append(lineSeparator);
            }
        }
        return new Result().setSuccess(false).setErrorMsg(stringBuffer.toString());
    }
}
