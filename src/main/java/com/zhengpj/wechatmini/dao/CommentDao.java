package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.CommentEntity;
import com.zhengpj.wechatmini.entity.TimelineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/30 10:38
 */
public interface CommentDao extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByMomentId(int momentId);

    void deleteById(int id);
}
