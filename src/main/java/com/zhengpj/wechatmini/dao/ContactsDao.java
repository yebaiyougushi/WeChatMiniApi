package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.ContactsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/20 22:00
 */
public interface ContactsDao extends JpaRepository<ContactsEntity, Integer> {
    List<ContactsEntity> findByUserId(int userId);
    @Query(value = "select * from contact where userId=?1 and contact=0", nativeQuery = true)
    List<ContactsEntity> findByUserIdNotBlack(int userId);
    void deleteByUserId(int userId);

}
