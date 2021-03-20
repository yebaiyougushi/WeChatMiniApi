package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.MomentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/10 12:57
 */
public interface MomentDao extends JpaRepository<MomentEntity, Integer> {
    List<MomentEntity> findMomentByUserid(int userId);

    void deleteById(int id);

    @Query(value = "select max(id) from moment ", nativeQuery = true)
    int findMaxMomentId();

    void deleteByUserid(int userId);
}
