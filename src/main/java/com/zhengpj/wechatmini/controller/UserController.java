package com.zhengpj.wechatmini.controller;

import com.zhengpj.wechatmini.entity.User;
import com.zhengpj.wechatmini.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/9 17:19
 */
@RestController
@RequestMapping("api")

@Api(value = "UserController|用户数据控制器")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ApiOperation(value = "新增用户")
    public boolean addUser(@RequestBody User user) {

        return userService.addUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, headers = {"content-type=application/json"})
    @ApiOperation(value = "更新用户信息")
    public boolean updateUser(@RequestBody User user) {

        return userService.updateUser(user);
    }

    @RequestMapping(value = "/user/sex", method = RequestMethod.PUT)
    @ApiOperation(value = "通过用户id更新用户性别")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "sex", value = "性别", required = true, dataType = "String"),

    })
    public boolean updateUserSex(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "sex", required = true) String sex) {
        return userService.updateUserSex(userId, sex);
    }

    @RequestMapping(value = "/user/signature", method = RequestMethod.PUT)
    @ApiOperation(value = "通过用户id更新个性签名")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "signature", value = "个性签名", required = true, dataType = "String"),

    })
    public boolean updateUserSignature(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "signature", required = true) String signature) {
        return userService.updateUserSignature(userId, signature);
    }

    @RequestMapping(value = "/user/nickname", method = RequestMethod.PUT)
    @ApiOperation(value = "通过用户id更新昵称")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "nickname", value = "昵称", required = true, dataType = "String"),

    })
    public boolean updateUserNickname(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "nickname", required = true) String nickname) {
        return userService.updateUserNickname(userId, nickname);
    }
    @RequestMapping(value = "/user/avatar", method = RequestMethod.PUT)
    @ApiOperation(value = "通过用户id更新头像")
    public boolean updateUserAvatar(@RequestParam(value = "userId", required = true) String userId) {
        return false;
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    @ApiOperation(value = "通过用户id删除用户信息")
    @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer")
    public boolean delete(@RequestParam(value = "userId", required = true) int userId) {

        return userService.deleteUser(userId);
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation(value = "通过用户名查询用户信息")
    @ApiImplicitParam(paramType="query", name = "userName", value = "用户昵称", required = true, dataType = "String")
    public List<User> findByUserName(@RequestParam(value = "userName", required = true) String userName) {

        return userService.findUserByName(userName);
    }

    @RequestMapping(value = "/user/id", method = RequestMethod.GET)
    @ApiOperation(value = "通过用户Id查询用户信息")
    @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "int")
    public User findByUserId(@RequestParam(value = "userId", required = true) int userId) {

        return userService.findUserById(userId);
    }


    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有用户信息")
    public List<User> findAll() {

        return userService.findAll();
    }

}

