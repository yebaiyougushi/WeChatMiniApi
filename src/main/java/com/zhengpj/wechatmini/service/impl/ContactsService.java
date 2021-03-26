package com.zhengpj.wechatmini.service.impl;

import com.zhengpj.wechatmini.dao.ContactsDao;
import com.zhengpj.wechatmini.entity.ContactsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/20 22:10
 */
@Service
public class ContactsService {
    @Autowired
    ContactsDao contactsDao;
    public List<ContactsEntity> findByUserId(int userId){
        return contactsDao.findByUserId(userId);
    }
    /**
      双方都要更新contact数据段
     */
    public boolean updateContact(int userId, int friendId, int contact){
        System.out.println("根据用户id, 好友id更新好友关系, userId="+userId+", friendId="+friendId+", contact="+contact);
        boolean flag = false;
        try {
            contactsDao.updateByUserIdAndFriendId(userId, friendId, contact);
            contactsDao.updateByUserIdAndFriendId(friendId, userId, contact);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    /**
    双方都要删除关系
     */
    public boolean deleteContact(int userId, int friendId){
        System.out.println("根据用户id, 好友id删除好友关系, userId="+userId+", friendId="+friendId);
        boolean flag = false;
        try {
            contactsDao.deleteByUserIdAndFriendId(userId, friendId);
            contactsDao.deleteByUserIdAndFriendId(friendId, userId);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public boolean addContact(int userId, int friendId){
        System.out.println("根据用户id, 好友id新增好友关系, userId="+userId+", friendId="+friendId);
        boolean flag = false;
        try {
            ContactsEntity contactsEntity1 = new ContactsEntity();
            contactsEntity1.setContact(0);
            contactsEntity1.setFriendId(friendId);
            contactsEntity1.setUserId(userId);
            ContactsEntity contactsEntity2 = new ContactsEntity();
            contactsEntity2.setContact(0);
            contactsEntity2.setFriendId(userId);
            contactsEntity2.setUserId(friendId);
            contactsDao.save(contactsEntity1);
            contactsDao.save(contactsEntity2);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
