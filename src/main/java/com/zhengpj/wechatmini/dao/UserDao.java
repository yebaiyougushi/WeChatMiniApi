package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/9 17:15
 */


public interface UserDao extends JpaRepository<User, Integer> {
    List<User> findByNickname(String nickname);


}
