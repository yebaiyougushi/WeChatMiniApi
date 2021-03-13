package com.zhengpj.wechatmini.service;

import com.zhengpj.wechatmini.entity.User;

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
    boolean addUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    boolean updateUser(User user);

    boolean updateUserSex(int userId, String sex);

    boolean updateUserNickname(int userId, String nickname);

    boolean updateUserSignature(int userId, String signature);

    boolean updateUserAvatar(int userId);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    boolean deleteUser(int userId);

    /**
     * 根据用户名字查询用户信息
     * @param userName
     */
    List<User> findUserByName(String userName);

    User findUserById(int userId);


    /**
     * 查询所有
     * @return
     */
    List<User> findAll();
}
