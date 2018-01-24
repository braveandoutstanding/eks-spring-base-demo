package com.eks.service;

import com.eks.entity.User;
import com.eks.vo.UserVo;
import com.eks.vo.base.PageQueryResultVo;
import com.eks.vo.query.UserQueryVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserVo addUser(UserVo userVo);
    UserVo deleteUser(Integer id);
    UserVo updateUser(UserVo userVo);
    UserVo updateUserIgnoreNull(UserVo userVo);
    UserVo getUser(Integer id);
    PageQueryResultVo listUser(UserQueryVo userQueryVo);
}
