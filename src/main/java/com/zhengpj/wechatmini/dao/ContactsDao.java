package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.ContactsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/20 22:00
 */
public interface ContactsDao extends JpaRepository<ContactsEntity, Integer> {
    List<ContactsEntity> findByUserId(int userId);
    @Query(value = "select * from contact where userId=?1 and contact=0", nativeQuery = true)
    List<ContactsEntity> findByUserIdNotBlack(int userId);
    @Modifying
    @Transactional
    @Query(value = "update contact set contact=?3 where userId=?1 and friendId=?2", nativeQuery = true)
    void updateByUserIdAndFriendId(int userId, int friendId, int contact);
    void deleteByUserId(int userId);
    @Transactional
    void deleteByUserIdAndFriendId(int userId, int friendId);


}
