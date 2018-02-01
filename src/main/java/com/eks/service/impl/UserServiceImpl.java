package com.eks.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.eks.entity.User;
import com.eks.repository.UserRepository;
import com.eks.repository.query.UserQuerySpecification;
import com.eks.service.UserService;
import com.eks.utils.AssertUtils;
import com.eks.utils.BeanUtils2;
import com.eks.utils.SourceToTargetUtils;
import com.eks.vo.UserVo;
import com.eks.vo.base.PageQueryResultVo;
import com.eks.vo.query.UserQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

@Service
@Validated//对方法的参数进行校验
public class UserServiceImpl implements UserService{
    @Resource
    UserRepository userRepository;

    @Transactional
    @Override
    public UserVo addUser(UserVo userVo){
        User user = SourceToTargetUtils.sourceToTarget(userVo, new TypeReference<User>(){});
        user.setRecordStatus(1);
        return SourceToTargetUtils.sourceToTarget(userRepository.saveAndFlush(user),new TypeReference<UserVo>(){});
    }
    @Transactional
    @Override
    public UserVo deleteUser(Integer id){
        User user = AssertUtils.notNull(userRepository.findOneByIdAndRecordStatus(id, 1),"该用户不存在");
        user.setRecordStatus(0);
        return SourceToTargetUtils.sourceToTarget(userRepository.saveAndFlush(user),new TypeReference<UserVo>(){});
    }
    @Transactional
    @Override
    public UserVo updateUser(UserVo userVo){
        AssertUtils.notNull(userRepository.findOneByIdAndRecordStatus(userVo.getId(), 1),"该用户不存在");
        User user = SourceToTargetUtils.sourceToTarget(userVo, new TypeReference<User>(){});
        return SourceToTargetUtils.sourceToTarget(userRepository.saveAndFlush(user),new TypeReference<UserVo>(){});
    }
    @Transactional
    @Override
    public UserVo updateUserIgnoreNull(UserVo userVo){
        User user = AssertUtils.notNull(userRepository.findOneByIdAndRecordStatus(userVo.getId(), 1),"该用户不存在");
        BeanUtils2.copyPropertiesIgnoreNull(userVo,user);
        return SourceToTargetUtils.sourceToTarget(userRepository.saveAndFlush(user),new TypeReference<UserVo>(){});
    }
    @Override
    public UserVo getUser(Integer id){
        User user = AssertUtils.notNull(userRepository.findOneByIdAndRecordStatus(id, 1),"该用户不存在");
        return SourceToTargetUtils.sourceToTarget(user,new TypeReference<UserVo>(){});
    }
    @Override
    public PageQueryResultVo listUser(UserQueryVo userQueryVo){
        UserQuerySpecification userQuerySpecification = new UserQuerySpecification(userQueryVo);
        Integer page = userQueryVo.getPage() == null ? 1 : userQueryVo.getPage();
        Integer size = userQueryVo.getSize() == null ? 10 : userQueryVo.getSize();
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, "createDate"));//PageRequest构造三个参数分别表示：页数(0是第一页)、每页显示行数、排序字段
        Page<User> page2 = userRepository.findAll(userQuerySpecification, pageable);
        List<UserVo> userVoList = SourceToTargetUtils.sourceToTarget(page2.getContent(),new TypeReference<List<UserVo>>(){});
        PageQueryResultVo<UserVo> pageQueryResultVo = new PageQueryResultVo<UserVo>();
        pageQueryResultVo.setPage(userQueryVo.getPage());
        pageQueryResultVo.setTotal(page2.getTotalElements());
        pageQueryResultVo.setObjects(userVoList);
        return pageQueryResultVo;
    }
}