package com.eks.config;

import com.eks.utils.base.ExceptionUtils2;
import com.eks.utils.base.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
* @copyright create by XuYongJie on 2018/1/20 16:13
* @description 全局异常处理器
*/
@ControllerAdvice//该注解是spring2.3以后新增的一个注解，主要是用来Controller的一些公共的需求的低侵入性增强提供辅助，作用于@RequestMapping标注的方法上。
@ResponseBody//如果返回的为json数据或其它对象，添加该注解
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)//该注解是配合@ExceptionHandler一起使用的注解，自定义错误处理器，可自己组装json字符串，并返回到页面。
    public Result globalExceptionHandler(HttpServletRequest request,Exception exception) throws Exception {
        logger.error(ExceptionUtils2.exceptionString(exception));
        return ExceptionUtils2.exceptionResult(exception);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)//该注解是配合@ExceptionHandler一起使用的注解，自定义错误处理器，可自己组装json字符串，并返回到页面。
    public Result constraintViolationExceptionHandler(HttpServletRequest request,ConstraintViolationException exception) throws Exception {
        logger.error(ExceptionUtils2.exceptionString(exception));
        return ExceptionUtils2.exceptionResult(exception);
    }
}