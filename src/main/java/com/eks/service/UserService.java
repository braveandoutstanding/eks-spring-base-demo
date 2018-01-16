package com.eks.service;

import com.eks.vo.UserVo;
import com.eks.vo.base.PageQueryResultVo;
import com.eks.vo.query.UserQueryVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String addUser(UserVo userVo);
    String deleteUser(Integer id);
    String updateUser(UserVo userVo);
    String updateUserIgnoreNull(UserVo userVo);
    UserVo getUser(Integer id);
    PageQueryResultVo listUser(UserQueryVo userQueryVo);
}
