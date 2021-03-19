package com.zhengpj.wechatmini.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

/**
 * @author zhengpeijian
 * @date 2021/3/19 0:04 点赞
 */
@Entity
@Table(name = "praise")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@ApiModel(description = "点赞类")
public class PraiseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "praiseusernickname")
    private String praiseUserNickname;
    @Column(name = "praiseuserid")
    private int praiseUserId;
    @Column(name = "momentid")
    private int momentId;

    public PraiseEntity() {
    }

    public PraiseEntity(int momentId, int praiseUserId, String praiseUserNickname) {
        this.momentId = momentId;
        this.praiseUserId = praiseUserId;
        this.praiseUserNickname = praiseUserNickname;
    }

    public String getPraiseUserNickname() {
        return praiseUserNickname;
    }

    public void setPraiseUserNickname(String praiseUserNickname) {
        this.praiseUserNickname = praiseUserNickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPraiseUserId() {
        return praiseUserId;
    }

    public void setPraiseUserId(int praiseUserId) {
        this.praiseUserId = praiseUserId;
    }

    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    @Override
    public String toString() {
        return "id:" + id +
                ", praiseUserNickname:" + praiseUserNickname +
                ", praiseUserId:" + praiseUserId +
                ", momentId:" + momentId;
    }
}
