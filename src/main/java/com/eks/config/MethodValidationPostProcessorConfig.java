package com.eks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration//@Configuration标注在类上，相当于把该类作为spring的xml配置文件中的<beans>，作用为：配置spring容器(应用上下文)
public class MethodValidationPostProcessorConfig{
    //Spring Boot项目由于自带了Hibernate validator 5,即在pom.xml无需再加如下核心的pom依赖
    /**
     <dependency>
     <groupId>org.hibernate</groupId>
     <artifactId>hibernate-validator</artifactId>
     <version>5.3.1.Final</version>
     </dependency>
     */
    @Bean//@Bean标注在方法上(返回某个实例的方法)，等价于spring的xml配置文件中的<bean>，作用为：注册bean对象
    public MethodValidationPostProcessor methodValidationPostProcessor(){//注入校验器到Spring Boot的运行环境
        return new MethodValidationPostProcessor();
    }
}
