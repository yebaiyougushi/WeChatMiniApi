package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.PraiseEntity;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/19 1:48
 */
public interface PraiseDao extends JpaRepository<PraiseEntity, Integer> {
    List<PraiseEntity> findByMomentId(int momentId);

    List<PraiseEntity> findByPraiseUserId(int userId);

    void deleteByPraiseUserId(int userId);

    void deleteById(int id);

    @Transactional
    @Delete(value = "delete from praise where praiseUserId=?1 and momentId=?2")
    void deleteByPraiseUserIdAndMomentId(int userId, int momentId);
}
