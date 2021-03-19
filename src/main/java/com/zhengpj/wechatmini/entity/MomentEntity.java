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
public class MomentEntity implements Comparable<MomentEntity>{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "1")
    private int id;
    @ApiModelProperty(value = "content", example = "moment content")
    private String content;
    @ApiModelProperty(example = "2018-12-12T11:11:11.111Z")
    private Timestamp timestamp;
    @ApiModelProperty(example = "6666666", required = true)
    private int userid;
    @Column(name = "imagenum")
    private int imageNum;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String img6;
    private String img7;
    private String img8;
    private String img9;

    public MomentEntity() {
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

        this.timestamp = timestamp;
    }

    public String getImg9() {
        return img9;
    }

    public void setImg9(String img9) {
        this.img9 = img9;
    }

    public String getImg8() {
        return img8;
    }

    public void setImg8(String img8) {
        this.img8 = img8;
    }

    public String getImg7() {
        return img7;
    }

    public void setImg7(String img7) {
        this.img7 = img7;
    }

    public String getImg6() {
        return img6;
    }

    public void setImg6(String img6) {
        this.img6 = img6;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public void setImageNum(int imageNum) {
        this.imageNum = imageNum;
    }

    public int getImageNum() {
        return imageNum;
    }

    public String getImg1() {
        return img1;
    }

    public String getImg2() {
        return img2;
    }


    @Override
    public String toString() {
        return "MomentEntity{id=" + id +
                ", content=" + content +
                ", timestamp=" + timestamp +
                ", userId=" + userid +
                ", img1=" + img1 +
                ", img2=" + img2 +
                ", img3=" + img3 +
                ", img4=" + img4 +
                ", img5=" + img5 +
                ", img6=" + img6 +
                ", img7=" + img7 +
                ", img8=" + img8 +
                ", img9=" + img9 + "}";
    }
    @Override
    public int compareTo(MomentEntity o) {
        return o.timestamp.compareTo(this.getTimestamp());
    }
}
