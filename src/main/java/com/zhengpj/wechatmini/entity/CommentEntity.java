package com.zhengpj.wechatmini.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

/**
 * @author zhengpeijian
 * @date 2021/3/19 0:04 评论
 */
@Entity
@Table(name = "comment")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@ApiModel(description = "朋友圈评论")
public class CommentEntity implements Comparable<CommentEntity> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "commenttype")
    private int commentType;//评论类型，是否
    @Column(name = "parentusername")
    private String parentUserName;
    @Column(name = "childusername")
    private String childUserName;
    @Column(name = "parentuserid")
    private int parentUserId;//被回复者
    @Column(name = "childuserid")
    private int childUserId;
    @Column(name = "momentid")
    private int momentId;
    @Column(name = "commentcontent")
    private String commentContent;//评论内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public String getParentUserName() {
        return parentUserName;
    }

    public void setParentUserName(String parentUserName) {
        this.parentUserName = parentUserName;
    }

    public String getChildUserName() {
        return childUserName;
    }

    public void setChildUserName(String childUserName) {
        this.childUserName = childUserName;
    }

    public int getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(int parentUserId) {
        this.parentUserId = parentUserId;
    }

    public int getChildUserId() {
        return childUserId;
    }

    public void setChildUserId(int childUserId) {
        this.childUserId = childUserId;
    }

    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }


    @Override
    public String toString() {
        return "CommentEntity{" +
                "commentType=" + commentType +
                ", parentUserName='" + parentUserName +
                ", childUserName='" + childUserName +
                ", parentUserId=" + parentUserId +
                ", childUserId=" + childUserId +
                ", momentId=" + momentId +
                ", commentContent='" + commentContent +
                '}';
    }


    @Override
    public int compareTo(CommentEntity o) {
        if (id > o.id) return 1;
        else return -1;
    }
}
