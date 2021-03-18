package com.zhengpj.wechatmini.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @author zhengpeijian
 * @date 2021/3/9 17:09
 * 用户
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@ApiModel(description = "用户信息类")
public class User {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    private int id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "nickname", example = "昵称")
    private String nickname;

    @ApiModelProperty(name = "username", example = "zpj")
    private String username;
    /**
     * 性别
     */
    @ApiModelProperty(value = "sex", example = "0/1")
    private String sex;
    //个性签名
    @ApiModelProperty(value = "signature", example = "个性签名")
    private String signature;
    @ApiModelProperty(value = "avatar", example = "头像链接")
    private String avatar;
    @ApiModelProperty(value = "background", example = "朋友圈背景图链接")
    private String background;
    @ApiModelProperty(value = "initialLetter", example = "z")
    @Column(name = "initialletter")
    private String initialLetter;
    private int contact;
    @Column(name = "modifynicknametimestamp")
    private long modifyNicknameTimestamp;
    @Column(name = "modifyinitiallettertimestamp")
    private long modifyInitialLetterTimestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getContact() {
        return contact;
    }

    public long getModifyInitialLetterTimestamp() {
        return modifyInitialLetterTimestamp;
    }

    public long getModifyNicknameTimestamp() {
        return modifyNicknameTimestamp;
    }

    public String getInitialLetter() {
        return initialLetter;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public void setInitialLetter(String initialLetter) {
        this.initialLetter = initialLetter;
    }

    public void setModifyInitialLetterTimestamp(long modifyInitialLetterTimestamp) {
        this.modifyInitialLetterTimestamp = modifyInitialLetterTimestamp;
    }

    public void setModifyNicknameTimestamp(long modifyNicknameTimestamp) {
        this.modifyNicknameTimestamp = modifyNicknameTimestamp;
    }


    public String getAvatar() {
        return avatar;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @Override
    public String toString() {
        return "User{id=" + id +
                ", nickname=" + nickname +
                ", sex=" + sex +
                ", signature=" + signature +
                ", avatar=" + avatar +
                ", background=" + background +
                ", initialLetter=" + initialLetter + "}";
    }
}
