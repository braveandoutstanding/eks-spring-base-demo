package com.eks.vo.query;

import com.eks.vo.UserVo;
import com.eks.vo.base.BaseQueryVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)//为NULL或者为空不参加序列化
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)//蛇形策略 - 单词小写，使用下划线'_'连接
public class UserQueryVo extends BaseQueryVo{
    private UserVo userVo;
}
