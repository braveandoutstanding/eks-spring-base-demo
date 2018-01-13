package com.eks.entity;

import com.eks.enums.UserSexualEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "tbl_user")
@EntityListeners(AuditingEntityListener.class)//将表中数据的创建时间、修改时间交给spring
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)//默认通过数字,设置映射枚举字段为枚举的name
    private UserSexualEnum userSexualEnum;
    //必备的三个字段,不采用继承父类的方式是为了避免某些框架不兼容,需要给入口加@EnableJpaAuditing及使用new SpringApplicationBuilder(Main.class).web(true).run(args);
    @CreatedDate
    private Date createDate;//创建时间
    @LastModifiedDate
    private Date updateDate;//修改时间
    private Integer recordStatus;//状态
}
