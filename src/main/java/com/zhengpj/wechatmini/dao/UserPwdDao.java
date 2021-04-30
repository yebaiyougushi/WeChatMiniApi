package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.UserPwdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhengpeijian
 * @date 2021/4/29 17:14
 */
public interface UserPwdDao extends JpaRepository<UserPwdEntity, String> {
    UserPwdEntity findByUsername(String username);
}
