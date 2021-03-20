package com.zhengpj.wechatmini.service;

import com.zhengpj.wechatmini.entity.UserEntity;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/9 17:16
 */

public interface UserService {
    /**
     * 新增用户
     * @param user
     * @return
     */
    boolean addUser(UserEntity user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    boolean updateUser(UserEntity user);

    boolean updateUserSex(int userId, String sex);

    boolean updateUserNickname(int userId, String nickname);

    boolean updateUserSignature(int userId, String signature);

    boolean updateUserAvatar(int userId, String avatar);

    boolean updateUserBackground(int userId, String background);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    boolean deleteUser(int userId);

    /**
     * 根据用户名字查询用户信息
     * @param nickname
     */
    List<UserEntity> findUserByName(String nickname);

    UserEntity findUserById(int userId);


    /**
     * 查询所有
     * @return
     */
    List<UserEntity> findAll();


}
