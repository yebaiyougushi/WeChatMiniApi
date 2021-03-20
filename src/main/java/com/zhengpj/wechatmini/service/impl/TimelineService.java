package com.zhengpj.wechatmini.service.impl;

import com.zhengpj.wechatmini.dao.TimelineDao;
import com.zhengpj.wechatmini.entity.TimelineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/20 22:09
 */
@Service
public class TimelineService {
    @Autowired
    TimelineDao timelineDao;
    public boolean addTimeline(TimelineEntity timeline){
        boolean flag = false;
        try{
            timelineDao.save(timeline);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteByMomentId(int momentId){
        boolean flag = false;
        try{
            timelineDao.deleteById(momentId);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteByUserId(int userId){
        boolean flag = false;
        try{
            timelineDao.deleteByUserId(userId);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public List<TimelineEntity> findByUserId(int userId) {
        return timelineDao.findTimelineEntitiesByPage(userId);
    }

    public List<TimelineEntity> findByUserIdOrderByTimestamp(int userId, Timestamp timestamp) {
        return timelineDao.findTimelineEntitiesByUserIdOrderByTimestampIdDesc(userId, timestamp);
    }
}
