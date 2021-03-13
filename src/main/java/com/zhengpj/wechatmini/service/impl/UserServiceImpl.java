package com.zhengpj.wechatmini.service.impl;

import com.zhengpj.wechatmini.dao.UserDao;
import com.zhengpj.wechatmini.entity.User;
import com.zhengpj.wechatmini.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author zhengpeijian
 * @date 2021/3/9 17:17
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        boolean flag = false;
        System.out.println("开始新增, user="+user.toString());
        try {
            userDao.save(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUser(User user) {
        boolean flag = false;
        System.out.println("开始更新..., user="+user.toString());
        try {
            int oldId = user.getId();
            Optional<User> optional = userDao.findById(oldId);
            if (optional.isEmpty())
                return false;
            User oldUser = optional.get();
            oldUser.setSex(user.getSex());
            oldUser.setNickname(user.getNickname());
            oldUser.setSignature(user.getSignature());
            userDao.save(oldUser);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUserSex(int userId, String sex) {
        boolean flag = false;
        System.out.println("开始修改性别, userId="+userId+", sex="+sex);
        try {
            Optional<User> optional = userDao.findById(userId);
            if (optional.isEmpty())
                return false;
            User user = optional.get();
            user.setSex(sex);
            userDao.save(user);
            flag = true;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUserNickname(int userId, String nickname) {
        boolean flag = false;
        System.out.println("开始修改昵称, userId="+userId+", nickname="+nickname);
        try {
            Optional<User> optional = userDao.findById(userId);
            if (optional.isEmpty())
                return false;
            User user = optional.get();
            user.setNickname(nickname);
            userDao.save(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUserSignature(int userId, String signature) {
        boolean flag = false;
        System.out.println("开始修改个性签名, userId="+userId+", signature="+signature);
        try {

            Optional<User> optional = userDao.findById(userId);
            if (optional.isEmpty())
                return false;
            User user = optional.get();
            user.setSignature(signature);
            userDao.save(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUserAvatar(int userId) {
        System.out.println("开始修改头像");
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean flag = false;
        System.out.println("开始删除...");
        System.out.println("id=" + id);
        try {
            userDao.deleteById(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<User> findUserByName(String userName) {
        System.out.println("开始查询, username="+userName);
        return userDao.findByNickname(userName);
    }

    @Override
    public User findUserById(int userId) {
        System.out.println("开始查询, userId="+userId);
        Optional<User> optional = userDao.findById(userId);
        if(optional.isEmpty()){
            System.out.println("第一次登陆需要新建用户, userId="+userId);
            User newUser = new User();
            newUser.setId(userId);
            newUser.setNickname("昵称");
            newUser.setSex("1");
            newUser.setSignature("keep running");
            userDao.save(newUser);
            return newUser;
        }
        return optional.get();
    }


    @Override
    public List<User> findAll() {

        System.out.println("开始查询所有数据...");
        return userDao.findAll();
    }
}
