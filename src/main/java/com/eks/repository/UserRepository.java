package com.eks.repository;

import com.eks.entity.User;
import com.eks.repository.base.EnityRepositoryUtils;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends EnityRepositoryUtils<User,Integer>{
}