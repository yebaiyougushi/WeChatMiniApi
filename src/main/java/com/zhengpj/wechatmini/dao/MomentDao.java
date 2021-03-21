package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.MomentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/10 12:57
 */
public interface MomentDao extends JpaRepository<MomentEntity, Integer> {
    @Query(value = "select * from moment where userId=?1 order by id desc limit 10", nativeQuery = true)
    List<MomentEntity> findMomentByUserid(int userId);
    @Query(value = "select * from moment where userId=?1 and id <?2 order by id desc limit 10", nativeQuery = true)
    List<MomentEntity> findByUseridOrOrderByIdDesc(int userId, int momentId);
    void deleteById(int id);

    @Query(value = "select LAST_INSERT_ID()", nativeQuery = true)
    int findMaxMomentId();

    void deleteByUserid(int userId);
}
