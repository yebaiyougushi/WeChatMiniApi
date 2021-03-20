package com.zhengpj.wechatmini.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

/**
 * @author zhengpeijian
 * @date 2021/3/20 21:57
 */
@Entity
@Table(name = "contact")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@ApiModel(description = "好友列表类")
public class ContactsEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userid")
    private int userId;
    @Column(name = "friendid")
    private int friendId;
    @Column(name = "contact")
    private int contact;
    public ContactsEntity(){}
    public ContactsEntity(int id, int userId, int friendId, int contact) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.contact = contact;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactsEntity{" +
                "id:" + id +
                ", userId:" + userId +
                ", friendId:" + friendId +
                ", contact:" + contact +
                '}';
    }
}
