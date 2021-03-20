package com.zhengpj.wechatmini.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author zhengpeijian
 * @date 2021/3/20 21:57
 */
@Entity
@Table(name = "timeline")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@ApiModel(description = "朋友圈时间线类")
public class TimelineEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userid")
    private int userId;
    @Column(name = "momentid")
    private int momentId;
    @Column(name = "timestamp")
    private Timestamp timestamp;

    public TimelineEntity(){}

    public TimelineEntity(int id, int userId, int momentId, Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.momentId = momentId;
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
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
        return "TimelineEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", momentId=" + momentId +
                ", timestamp=" + timestamp +
                '}';
    }
}
