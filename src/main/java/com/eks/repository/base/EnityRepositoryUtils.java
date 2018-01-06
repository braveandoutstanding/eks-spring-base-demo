package com.eks.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoRepositoryBean
public interface EnityRepositoryUtils<T,ID extends Serializable> extends JpaRepository<T,ID>, JpaSpecificationExecutor<T>{
    T findOneByIdAndRecordStatus(ID id,Integer recordStatus);
    List<T> findAllByIdAndRecordStatus(ID id, Integer recordStatus);
    List<T> findAllByCreateDateAndUpdateDateAndRecordStatus(Date createDate,Date updateDate,Integer recordStatus);//确保T必须还有createDate、updateDate、recordStatus字段
}