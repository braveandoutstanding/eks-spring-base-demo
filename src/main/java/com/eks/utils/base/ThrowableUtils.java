package com.eks.utils.base;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ThrowableUtils {
    public static String throwableString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(new StringWriter(),true));
        return stringWriter.toString();
    }
    public static Result throwableResult(Throwable throwable) {
        return new Result().setSuccess(false).setErrorMsg(throwable.getMessage());
    }
}
