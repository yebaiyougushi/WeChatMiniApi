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
    public boolean updateContact(int userId, int contact){
        boolean flag = false;

        return flag;
    }
}
