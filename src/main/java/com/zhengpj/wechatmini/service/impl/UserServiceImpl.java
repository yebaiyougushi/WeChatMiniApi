package com.zhengpj.wechatmini.service.impl;

import com.zhengpj.wechatmini.dao.PraiseDao;
import com.zhengpj.wechatmini.dao.UserDao;
import com.zhengpj.wechatmini.entity.PraiseEntity;
import com.zhengpj.wechatmini.entity.UserEntity;
import com.zhengpj.wechatmini.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
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
    public boolean addUser(UserEntity user) {
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
    public boolean updateUser(UserEntity user) {
        boolean flag = false;
        System.out.println("开始更新..., user="+user.toString());
        try {
            int oldId = user.getId();
            Optional<UserEntity> optional = userDao.findById(oldId);
            if (optional.isEmpty())
                return false;
            UserEntity oldUser = optional.get();
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
            Optional<UserEntity> optional = userDao.findById(userId);
            if (optional.isEmpty())
                return false;
            UserEntity user = optional.get();
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
            Optional<UserEntity> optional = userDao.findById(userId);
            if (optional.isEmpty())
                return false;
            UserEntity user = optional.get();
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

            Optional<UserEntity> optional = userDao.findById(userId);
            if (optional.isEmpty())
                return false;
            UserEntity user = optional.get();
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

            Optional<UserEntity> optional = userDao.findById(userId);
            if (optional.isEmpty())
                return false;
            UserEntity user = optional.get();
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

            Optional<UserEntity> optional = userDao.findById(userId);
            if (optional.isEmpty())
                return false;
            UserEntity user = optional.get();
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
    public List<UserEntity> findUserByName(String name) {
        System.out.println("开始查询, nickname="+name);
        List<UserEntity> result = new ArrayList<>();
        result.addAll(userDao.findByNicknameLike("%"+name+"%"));
        result.addAll(userDao.findByUsername(name));
        HashSet<UserEntity> hashSet = new HashSet<>(result);
        result.clear();
        result.addAll(hashSet);
        return result;
    }

    @Override
    public UserEntity findUserById(int userId) {
        System.out.println("开始查询, userId="+userId);
        Optional<UserEntity> optional = userDao.findById(userId);
        if(optional.isEmpty()){
            return null;
        }
        return optional.get();
    }


    @Override
    public List<UserEntity> findAll() {

        System.out.println("开始查询所有数据...");
        return userDao.findAll();
    }
}
