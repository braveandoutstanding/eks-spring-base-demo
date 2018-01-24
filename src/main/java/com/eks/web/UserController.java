package com.eks.web;

import com.eks.service.UserService;
import com.eks.utils.base.Result;
import com.eks.utils.base.ResultUtils;
import com.eks.vo.UserVo;
import com.eks.vo.base.PageQueryResultVo;
import com.eks.vo.query.UserQueryVo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/eks/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/add-user")
    public Result addUser(@RequestBody UserVo userVo){
        userService.addUser(userVo);
        return ResultUtils.handle(String.class,"添加成功");
    }
    @RequestMapping("/delete-user")
    public Result deleteUser(Integer id){//如果参数名就是id,则不需要加@RequestParam("id")
        userService.deleteUser(id);
        return ResultUtils.handle(String.class,"删除成功");
    }
    @RequestMapping("/update-user")
    public Result updateUser(@RequestBody UserVo userVo){
        userService.updateUser(userVo);
        return ResultUtils.handle(String.class,"修改成功");
    }
    @RequestMapping("/update-user-ignore-null")
    public Result updateUserIgnoreNull(@RequestBody UserVo userVo){
        userService.updateUserIgnoreNull(userVo);
        return ResultUtils.handle(String.class,"按需修改成功");
    }
    @RequestMapping("/get-user")
    public Result getUser(Integer id){
        return ResultUtils.handle(UserVo.class,userService.getUser(id));
    }
    @RequestMapping("/list-user")
    public Result listUser(@RequestBody UserQueryVo userQueryVo){
        return ResultUtils.handle(PageQueryResultVo.class,userService.listUser(userQueryVo));
    }
    @RequestMapping("/test")
    public List<Person> test(){
        List<Person> list = new ArrayList<>();
        Person person1 = new Person().setKey("1").setName("EKS1").setAge(1).setAddress("西湖区湖底公园1号");
        Person person2 = new Person().setKey("2").setName("EKS2").setAge(2).setAddress("西湖区湖底公园2号");
        list.add(person1);
        list.add(person2);
        return list;
    }
}

@Data
@Accessors(chain = true)
class Person{
    private String key;
    private String name;
    private Integer age;
    private String address;
}