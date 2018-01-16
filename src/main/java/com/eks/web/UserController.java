package com.eks.web;

import com.eks.service.UserService;
import com.eks.utils.base.Result;
import com.eks.utils.base.ResultUtils;
import com.eks.vo.UserVo;
import com.eks.vo.base.PageQueryResultVo;
import com.eks.vo.query.UserQueryVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/eks/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/add-user")
    public Result addUser(@RequestBody UserVo userVo) {
        return ResultUtils.handle(String.class,userService.addUser(userVo));
    }
    @RequestMapping("/delete-user")
    public Result deleteUser(@RequestParam("id") Integer id){
        return ResultUtils.handle(String.class,userService.deleteUser(id));
    }
    @RequestMapping("/update-user")
    public Result updateUser(@RequestBody UserVo userVo){
        return ResultUtils.handle(String.class,userService.updateUser(userVo));
    }
    @RequestMapping("/update-user-ignore-null")
    public Result updateUserIgnoreNull(@RequestBody UserVo userVo){
        return ResultUtils.handle(String.class,userService.updateUserIgnoreNull(userVo));
    }
    @RequestMapping("/get-user")
    public Result getUser(@RequestParam("id") Integer id){
        return ResultUtils.handle(UserVo.class,userService.getUser(id));
    }
    @RequestMapping("/list-user")
    public Result listUser(@RequestBody UserQueryVo userQueryVo){
        return ResultUtils.handle(PageQueryResultVo.class,userService.listUser(userQueryVo));
    }
}