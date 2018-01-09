package com.eks.utils.base;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils2 {
    public static String exceptionString(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(new StringWriter(),true));
        return stringWriter.toString();
    }
    public static Result exceptionResult(Exception exception) {
        return new Result().setSuccess(false).setErrorMsg(exception.getMessage());
    }
}