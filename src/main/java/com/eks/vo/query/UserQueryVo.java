package com.eks.vo.query;

import com.eks.vo.UserVo;
import com.eks.vo.base.BaseQueryVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data//注解在类上;提供类所有非static且非final属性的get和set方法,final属性只提供get方法,此外还提供了equals、canEqual、hashCode、toString 方法
@Accessors(chain = true)//chain=boolean值，默认false。如果设置为true，setter返回的是此对象，方便链式调用方法
@EqualsAndHashCode(callSuper = true)//@Data默认不会调用父类的equals(Object other) 和 hashCode()方法,因此需要加上此注解
@JsonInclude(JsonInclude.Include.NON_NULL)//为NULL或者为空不参加序列化
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)//蛇形策略 - 单词小写，使用下划线'_'连接
public class UserQueryVo extends BaseQueryVo{
    @NotNull(message = "userVo不可为NULL")
    private UserVo userVo;
}
