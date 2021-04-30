package com.zhengpj.wechatmini.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhengpj.wechatmini.entity.UserEntity;
import com.zhengpj.wechatmini.entity.UserPwdEntity;
import com.zhengpj.wechatmini.service.UserService;
import com.zhengpj.wechatmini.service.impl.RegisterLoginService;
import com.zhengpj.wechatmini.util.DateUtil;
import com.zhengpj.wechatmini.util.IpUtil;
import com.zhengpj.wechatmini.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

    @Autowired
    private RegisterLoginService registerLoginService;

//     * 方法名：作用：登陆校验密码
//     * 输入 username password  用户名，密码
//     * 输出：code: 状态码   1 为认证成功 0 为用户不存在 -1 为密码不一致 -2 表示程序错误
//     *       success:  true or false 执行成功或失败
//     *       result：只在认证成功时返回，包含用户的全部信息
//     *       messsage:
//            *
//            *
//            */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String toRegister(@RequestBody UserPwdEntity pwdEntity){

        return registerLoginService.register(pwdEntity);
    }
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String toLogin(@RequestBody UserPwdEntity pwdEntity) {
        return registerLoginService.login(pwdEntity);

    }


    @RequestMapping(value = "/user", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ApiOperation(value = "新增用户")
    public boolean addUser(@RequestBody UserEntity user) {

        return userService.addUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, headers = {"content-type=application/json"})
    @ApiOperation(value = "更新用户信息")
    public boolean updateUser(@RequestBody UserEntity user) {

        return userService.updateUser(user);
    }

    @RequestMapping(value = "/user/sex", method = RequestMethod.PUT)
    @ApiOperation(value = "通过用户id更新用户性别")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "sex", value = "性别", required = true, dataType = "String"),

    })
    public boolean updateUserSex(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "sex", required = true) String sex) {
        return userService.updateUserSex(userId, sex);
    }

    @RequestMapping(value = "/user/signature", method = RequestMethod.PUT)
    @ApiOperation(value = "通过用户id更新个性签名")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "signature", value = "个性签名", required = true, dataType = "String"),

    })
    public boolean updateUserSignature(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "signature", required = true) String signature) {
        return userService.updateUserSignature(userId, signature);
    }

    @RequestMapping(value = "/user/nickname", method = RequestMethod.PUT)
    @ApiOperation(value = "通过用户id更新昵称")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "nickname", value = "昵称", required = true, dataType = "String"),

    })
    public boolean updateUserNickname(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "nickname", required = true) String nickname) {
        return userService.updateUserNickname(userId, nickname);
    }


    @RequestMapping(value = "/user/avatar", method = RequestMethod.POST)
    @ApiOperation(value = "通过用户id更新头像")
    public String updateUserAvatar(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "app_user_header") MultipartFile img) {
        String avatar = "";
        if (img.getSize() > 0) {
            //String basePath = "E:/images/header/";
            String basePath = "/home/ubuntu/www/images/user/avatar";
            avatar = Helper.storeImage(img, basePath);
        }
        if ("".equals(avatar)) {
            return "";
        }
        avatar = "http://81.69.0.70:8082/images/user/avatar" + avatar;
        if(!userService.updateUserAvatar(userId, avatar)){
            return "";
        }
        return avatar;
    }

    @RequestMapping(value = "/user/background", method = RequestMethod.POST)
    @ApiOperation(value = "通过用户id更新朋友圈背景")
    public String updateUserBackground(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "app_user_background") MultipartFile img) {
        String background = "";
        if (img.getSize() > 0) {
            //String basePath = "E:/images/background/";
            String basePath = "/home/ubuntu/www/images/user/background";
            background = Helper.storeImage(img, basePath);
        }
        if (background.equals("")) return "";
        background = "http://81.69.0.70:8082/images/user/background" + background;
        if(!userService.updateUserBackground(userId, background)){
            return "";
        }
        return background;
    }




    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    @ApiOperation(value = "通过用户id删除用户信息")
    @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "Integer")
    public boolean delete(@RequestParam(value = "userId", required = true) int userId) {

        return userService.deleteUser(userId);
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation(value = "通过用户昵称或者名称id查询用户信息")
    @ApiImplicitParam(paramType = "query", name = "name", value = "用户昵称或者名称ID", required = true, dataType = "String")
    public List<UserEntity> findByName(@RequestParam(value = "name", required = true) String name) {

        return userService.findUserByName(name);
    }

//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    @ApiOperation(value = "通过用户昵称查询用户信息")
//    @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String")
//    public UserEntity findByUsername(@RequestParam(value = "username", required = true) String username) {
//        return userService.findUserById(username.hashCode());
//        //return userService.findUserByName(nickname);
//    }


    @RequestMapping(value = "/user/id", method = RequestMethod.GET)
    @ApiOperation(value = "通过用户Id查询用户信息")
    @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "int")
    public UserEntity findByUserId(@RequestParam(value = "userId", required = true) int userId) {
        return userService.findUserById(userId);
    }


    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有用户信息")
    public List<UserEntity> findAll() {

        return userService.findAll();
    }

}

