package com.zhengpj.wechatmini.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/19 11:12 包含内容，评论和点赞
 */
public class FriendCircleEntity implements Comparable<FriendCircleEntity>{

    private MomentEntity momentEntity;
    private List<String> imgUrls;
    private List<PraiseEntity> praiseEntities;
    private UserEntity userEntity;
    private List<CommentEntity> commentEntities;
    public FriendCircleEntity() {
    }


    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public FriendCircleEntity(MomentEntity momentEntity, List<PraiseEntity> praiseEntities, List<CommentEntity> commentEntities) {
        this.momentEntity = momentEntity;
        imgUrls = new ArrayList<>();
        getUrlListFromMoment(momentEntity, imgUrls);
        this.praiseEntities = praiseEntities;
        this.commentEntities = commentEntities;
    }

    public List<CommentEntity> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    public List<PraiseEntity> getPraiseEntities() {
        return praiseEntities;
    }

    public void setPraiseEntities(List<PraiseEntity> praiseEntities) {
        this.praiseEntities = praiseEntities;
    }

    public MomentEntity getMomentEntity() {
        return momentEntity;
    }

    public void setMomentEntity(MomentEntity Moment) {
        this.momentEntity = Moment;
    }
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    private void getUrlListFromMoment(MomentEntity Moment, List<String> url) {
        int imageNum = Moment.getImageNum();
        if (imageNum >= 1) url.add(Moment.getImg1());
        if (imageNum >= 2) url.add(Moment.getImg2());
        if (imageNum >= 3) url.add(Moment.getImg3());
        if (imageNum >= 4) url.add(Moment.getImg4());
        if (imageNum >= 5) url.add(Moment.getImg5());
        if (imageNum >= 6) url.add(Moment.getImg6());
        if (imageNum >= 7) url.add(Moment.getImg7());
        if (imageNum >= 8) url.add(Moment.getImg8());
        if (imageNum >= 9) url.add(Moment.getImg9());

    }

    @Override
    public String toString() {
        return "FriendCircleEntity{" +
                "momentEntity:{" + momentEntity +
                "}, imgUrls:{" + imgUrls +
                "}, praiseEntities:{" + praiseEntities +
                "}, userEntity:{" + userEntity +
                "}}";
    }
    @Override
    public int compareTo(FriendCircleEntity o) {
        return this.momentEntity.compareTo(o.getMomentEntity());
    }

}
