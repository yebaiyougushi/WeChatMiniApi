package com.zhengpj.wechatmini.service.impl;

import com.zhengpj.wechatmini.controller.MomentController;
import com.zhengpj.wechatmini.dao.MomentDao;
import com.zhengpj.wechatmini.dao.PraiseDao;
import com.zhengpj.wechatmini.dao.UserDao;
import com.zhengpj.wechatmini.entity.PraiseEntity;
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
    @Autowired
    private PraiseDao praiseDao;
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
            //更新昵称之后需要更新点赞中的昵称
            List<PraiseEntity> praiseEntities = praiseDao.findByPraiseUserId(user.getId());
            //System.out.println("praise size="+praiseEntities.size());
            for(PraiseEntity praiseEntity:praiseEntities){
                praiseEntity.setPraiseUserNickname(nickname);
                praiseDao.save(praiseEntity);
            }
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
    public boolean updateUserAvatar(int userId, String avatar) {
        System.out.println("开始修改头像, userid="+userId+", avatar="+avatar);
        boolean flag = false;
        try {

            Optional<User> optional = userDao.findById(userId);
            if (optional.isEmpty())
                return false;
            User user = optional.get();
            user.setAvatar(avatar);
            userDao.save(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUserBackground(int userId, String background) {
        System.out.println("开始修改背景，useId="+userId+", background="+background);
        boolean flag = false;
        try {

            Optional<User> optional = userDao.findById(userId);
            if (optional.isEmpty())
                return false;
            User user = optional.get();
            user.setBackground(background);
            userDao.save(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
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
    public List<User> findUserByNickname(String nickname) {
        System.out.println("开始查询, nickname="+nickname);
        return userDao.findByNicknameLike("%"+nickname+"%");
    }

    @Override
    public User findUserById(int userId) {
        System.out.println("开始查询, userId="+userId);
        Optional<User> optional = userDao.findById(userId);
        if(optional.isEmpty()){
            return null;
        }
        return optional.get();
    }


    @Override
    public List<User> findAll() {

        System.out.println("开始查询所有数据...");
        return userDao.findAll();
    }
}
