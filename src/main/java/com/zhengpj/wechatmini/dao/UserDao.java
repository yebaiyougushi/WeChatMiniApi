package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/9 17:15
 */


public interface UserDao extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByNicknameLike(String nickname);
    List<UserEntity> findByUsername(String name);

}
