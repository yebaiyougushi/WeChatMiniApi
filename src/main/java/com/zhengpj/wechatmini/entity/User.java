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
    @ApiModelProperty(value = "nickname",example="昵称")
    private String nickname;
    /**
     * 性别
     */
    @ApiModelProperty(value = "sex",example="0/1")
    private String sex;
    //个性签名
    @ApiModelProperty(value = "signature",example="个性签名")
    private String signature;


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

    @Override
    public String toString() {
        return "id=" + id + ", nickname=" + nickname + ", sex=" + sex + ", signature=" + signature;
    }
}
