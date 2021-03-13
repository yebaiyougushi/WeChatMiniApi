package com.zhengpj.wechatmini.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author zhengpeijian
 * @date 2021/3/9 17:12
 * 朋友圈
 */
@Entity
@Table(name = "moment")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@ApiModel(description = "朋友圈类")
public class Moment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example="1")
    private int id;
    @ApiModelProperty(value = "content",example="moment content")
    private String content;
    @ApiModelProperty(example="2018-12-12T11:11:11.111Z")
    private Timestamp timestamp;
    @ApiModelProperty(example="6666666",required = true)
    private int userid;

    public Moment() {
    }

    ;

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getContent() {
        return content;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {

//        Timestamp now = new Timestamp(System.currentTimeMillis());
//        System.out.println("System.currentTimeMillis()=" + System.currentTimeMillis());
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "id=" + id + ", content=" + content + ", timestamp=" + timestamp + ", userid=" + userid;
    }
}
