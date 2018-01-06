package com.eks.service;

import com.eks.entity.User;
import com.eks.vo.base.UserVo;
import com.eks.vo.query.UserQueryVo;
import com.eks.vo.result.PageQueryResultVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String addUser(UserVo userVo);
    String deleteUser(Integer id);
    String updateUser(UserVo userVo);
    User getUser(Integer id);
    PageQueryResultVo listUser(UserQueryVo userQueryVo);
}
