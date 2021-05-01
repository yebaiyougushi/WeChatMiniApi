package com.zhengpj.wechatmini.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhengpj.wechatmini.dao.UserDao;
import com.zhengpj.wechatmini.dao.UserPwdDao;
import com.zhengpj.wechatmini.entity.UserEntity;
import com.zhengpj.wechatmini.entity.UserPwdEntity;
import com.zhengpj.wechatmini.util.DateUtil;
import com.zhengpj.wechatmini.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhengpeijian
 * @date 2021/4/29 20:39
 */
@Service
public
class RegisterLoginService {
    @Autowired
    private UserPwdDao userPwdDao;

    @Autowired
    private UserDao userDao;

    public String register(UserPwdEntity pwdEntity) {
        System.out.println(pwdEntity.toString());

        JSONObject json=new JSONObject();
        UserEntity user = new UserEntity();
        user.setId(pwdEntity.getUsername().hashCode());
        user.setUsername(pwdEntity.getUsername());
        user.setAvatar("http://81.69.0.70:8082/images/user/avatar/2021/03/18/49874_20210318_030015.png");
        user.setBackground("http://81.69.0.70:8082/images/user/avatar/2021/03/18/49874_20210318_030015.png");
        user.setSignature("keep running");
        user.setInitialLetter("t");
        user.setSex("1");
        user.setModifyInitialLetterTimestamp(0);
        user.setModifyNicknameTimestamp(0);
        user.setContact(0);
//        UserPwdEntity userPwdEntity = new UserPwdEntity(user.getId(),pwd);
        try{
            userDao.save(user);
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            System.out.println("加密之前密码为："+pwdEntity.getPwd());
            pwdEntity.setPwd(encode.encode(pwdEntity.getPwd()));
            System.out.println("加密之后密码为："+pwdEntity.getPwd());
            userPwdDao.save(pwdEntity);
            json.put("success", true);
            json.put("code", 1);
            json.put("message", "注册成功");
        } catch (Exception e){
            json.put("success", false);
            json.put("code", -1);
            json.put("message", "注册失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(json);
    }

//    private UserPwdEntity getPwdEntity(String id) {
//        UserPwdEntity pwdEntity = userPwdDao.findByUsername(id);
//        return pwdEntity;
//    }

    public String login(UserPwdEntity pwdEntity) {
        JSONObject json=new JSONObject();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        try {
            UserPwdEntity user1 = userPwdDao.findByUsername(pwdEntity.getUsername());
            if (user1!=null) {
                System.out.println("db_user1="+user1.toString());
                String dbPassWord = user1.getPwd();
                if (bCryptPasswordEncoder.matches(pwdEntity.getPwd(),dbPassWord)) {
                    //创建token
                    String token = JwtUtil.generateToken(pwdEntity);
                    json.put("success", true);
                    json.put("code", 1);
                    //json.put("result", user1);
                    json.put("time", DateUtil.dateToString(new Date()));
                    json.put("message", "登陆成功");
                    json.put(JwtUtil.AUTHORIZATION,token);
                } else {
                    json.put("success", false);
                    json.put("code", -1);

                    json.put("message", "登陆失败,密码错误");
                }
            }else {
                json.put("success", false);
                json.put("code", 0);
                json.put("message", "无此用户信息");
            }
        } catch (Exception e) {
            json.put("code", -2);
            json.put("success", false);
            json.put("message", e.getMessage());
            e.printStackTrace();
        }

        return JSON.toJSONString(json);
    }
}
