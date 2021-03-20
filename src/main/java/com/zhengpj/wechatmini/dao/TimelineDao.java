package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.TimelineEntity;
import com.zhengpj.wechatmini.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/20 22:00
 */
public interface TimelineDao extends JpaRepository<TimelineEntity, Integer> {
    //朋友圈下拉刷新用
    @Query(value = "select * from timeline where userId=?1 and timstamp < ?2 order by timestamp desc limit 10", nativeQuery = true)
    List<TimelineEntity> findTimelineEntitiesByUserIdOrderByTimestampIdDesc(int userId, Timestamp timestamp);

    @Query(value = "select * from timeline where userId=?1 order by timestamp desc limit 10", nativeQuery = true)
    List<TimelineEntity> findTimelineEntitiesByPage(int userId);
    void deleteByMomentId(int momentId);
    void deleteByUserId(int userId);
}
