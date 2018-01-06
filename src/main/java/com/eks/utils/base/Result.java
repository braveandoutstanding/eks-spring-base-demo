package com.eks.utils.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data//注解在类上;提供类所有非static且非final属性的get和set方法,final属性只提供get方法,此外还提供了equals、canEqual、hashCode、toString 方法
@Accessors(chain = true)//chain=boolean值，默认false。如果设置为true，setter返回的是此对象，方便链式调用方法
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1287667385448554314L;//serialVersionUID适用于Java的序列化机制。简单来说，Java的序列化机制是通过判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体类的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常，即是InvalidCastException。
    private Boolean success = true;//是否成功,boolean类型生成的"get"方法为"isSuccess()",最好使用包装类型Boolean,生成的"get"方法为"getSuccess()"
    private String errorCode;
    private String errorMsg;
    private T result;
}