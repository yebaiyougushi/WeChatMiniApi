package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.PraiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/19 1:48
 */
public interface PraiseDao extends JpaRepository<PraiseEntity,Integer> {
    List<PraiseEntity> findByMomentId(int momentId);
    List<PraiseEntity> findByPraiseUserId(int userId);
}
