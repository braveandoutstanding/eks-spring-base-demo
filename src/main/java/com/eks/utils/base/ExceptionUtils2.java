package com.eks.utils.base;

import com.eks.utils.StringUtils3;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

/**
 * @copyright create by XuYongJie on 2018/1/20 16:12
 * @description 异常处理工具类
 */
public class ExceptionUtils2 {
    private final static String SEPARATIVE_SIGN = ",";
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
        for (ConstraintViolation<?> constraintViolation : constraintViolationSet) {
            String message = constraintViolation.getMessage();
            String messageTemplate = constraintViolation.getMessageTemplate();
            if(message != null && message.equals(messageTemplate)){
                stringBuffer.append(message);
            }else{
                stringBuffer.append(constraintViolation.toString());
            }
            stringBuffer.append(ExceptionUtils2.SEPARATIVE_SIGN);
        }
        return new Result().setSuccess(false).setErrorMsg(StringUtils3.removeEndString(stringBuffer.toString(),ExceptionUtils2.SEPARATIVE_SIGN));
    }
    public static Result exceptionResult(MethodArgumentNotValidException exception) {//使用hibernate-validation对参数进行校验的异常处理
        BindingResult bindingResult = exception.getBindingResult();
        if(bindingResult != null){
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            StringBuffer stringBuffer = new StringBuffer();
            for(FieldError fieldError : fieldErrorList){
                String defaultMessage = fieldError.getDefaultMessage();
                if(defaultMessage != null && !"".equals(defaultMessage)){
                    stringBuffer.append(defaultMessage);
                }else{
                    stringBuffer.append(fieldError.toString());
                }
                stringBuffer.append(ExceptionUtils2.SEPARATIVE_SIGN);
            }
            return new Result().setSuccess(false).setErrorMsg(StringUtils3.removeEndString(stringBuffer.toString(),ExceptionUtils2.SEPARATIVE_SIGN));
        }
        return new Result().setSuccess(false).setErrorMsg(exception.toString());
    }
}