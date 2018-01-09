package com.eks.service.impl;

import com.eks.entity.User;
import com.eks.repository.UserRepository;
import com.eks.repository.query.UserQuerySpecification;
import com.eks.service.UserService;
import com.eks.utils.BeanUtils2;
import com.eks.utils.EntityToVoUtils;
import com.eks.vo.UserVo;
import com.eks.vo.base.PageQueryResultVo;
import com.eks.vo.query.UserQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Resource
    UserRepository userRepository;

    @Override
    public String addUser(UserVo userVo) {
        User user = new User().setRecordStatus(1);
        BeanUtils.copyProperties(userVo,user);
        userRepository.saveAndFlush(user);
        return "添加成功";
    }

    @Override
    public String deleteUser(Integer id) {
        User user = userRepository.findOneByIdAndRecordStatus(id, 1).setRecordStatus(0);
        userRepository.saveAndFlush(user);
        return "删除成功";
    }

    @Override
    public String updateUser(UserVo userVo) {
        User user = userRepository.findOneByIdAndRecordStatus(userVo.getId(), 1);
        BeanUtils2.copyPropertiesIgnoreNull(userVo,user);
        userRepository.saveAndFlush(user);
        return "修改成功";
    }

    @Override
    public UserVo getUser(Integer id) {
        if(id == 6){
            throw new IllegalArgumentException("bug test");
        }
        User user = userRepository.findOneByIdAndRecordStatus(id, 1);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    @Override
    public PageQueryResultVo listUser(UserQueryVo userQueryVo) {
        UserQuerySpecification userQuerySpecification = new UserQuerySpecification(userQueryVo);
        Integer page = userQueryVo.getPage() == null ? 1 : userQueryVo.getPage();
        Integer size = userQueryVo.getSize() == null ? 10 : userQueryVo.getSize();
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, "createDate"));//PageRequest构造三个参数分别表示：页数(0是第一页)、每页显示行数、排序字段
        Page<User> page2 = userRepository.findAll(userQuerySpecification, pageable);
        List<User> userList = page2.getContent();
        List<UserVo> userVoList = new ArrayList<>();
        userVoList = EntityToVoUtils.entityToVo(userList, userVoList);
        PageQueryResultVo<UserVo> pageQueryResultVo = new PageQueryResultVo<UserVo>();
        pageQueryResultVo.setPage(userQueryVo.getPage());
        pageQueryResultVo.setTotal(page2.getTotalElements());
        pageQueryResultVo.setObjects(userVoList);
        return pageQueryResultVo;
    }
}
