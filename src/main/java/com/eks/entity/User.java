package com.eks.entity;

import com.eks.entity.base.EntityUtils;
import com.eks.enums.UserSexualEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "tbl_user")
@EntityListeners(AuditingEntityListener.class)//将表中数据的创建时间、修改时间交给spring
public class User extends EntityUtils{
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)//默认通过数字,设置映射枚举字段为枚举的name
    private UserSexualEnum userSexualEnum;
}
