package com.zhengpj.wechatmini.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;


/**
 * @author zhengpeijian
 * @date 2021/4/29 16:49
 * 用户id，用户密码
 */
@Entity
@Table(name = "user_pwd")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@ApiModel(description = "用户密码类")
public class UserPwdEntity {
    @Id
    @Column(name = "username")
    @ApiModelProperty(name = "username", example = "zpj")
    private String username;
    @ApiModelProperty(name = "pwd", example = "***")
    @Column(name = "pwd")
    private String pwd;

    public UserPwdEntity() {
    }

    public UserPwdEntity(String username, String password) {
        this.username = username;
        this.pwd = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

//    public String getPwd() {
//        return pwd;
//    }
//
//    public void setPwd(String pwd) {
//        this.pwd = pwd;
//    }

    @Override
    public String toString() {
        return "UserPwdEntity{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
