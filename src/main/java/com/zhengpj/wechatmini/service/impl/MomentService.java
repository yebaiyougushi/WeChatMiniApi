package com.zhengpj.wechatmini.service.impl;


import com.alibaba.fastjson.JSON;
import com.zhengpj.wechatmini.controller.Helper;
import com.zhengpj.wechatmini.dao.ContactsDao;
import com.zhengpj.wechatmini.dao.MomentDao;
import com.zhengpj.wechatmini.dao.PraiseDao;
import com.zhengpj.wechatmini.dao.TimelineDao;
import com.zhengpj.wechatmini.entity.ContactsEntity;
import com.zhengpj.wechatmini.entity.MomentEntity;
import com.zhengpj.wechatmini.entity.PraiseEntity;
import com.zhengpj.wechatmini.entity.TimelineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zhengpeijian
 * @date 2021/3/10 13:06
 */
@Service
public class MomentService {
    @Autowired
    MomentDao momentDao;
    @Autowired
    PraiseDao praiseDao;
    @Autowired
    ContactsDao contactsDao;

    @Autowired
    TimelineDao timelineDao;

    public boolean addMoment(MomentEntity moment, List<MultipartFile> images) {
        //写入moment时需要根据contacts表写入timeline表


        List<String> imageUrls = new ArrayList<>();
        if (images != null)
            for (MultipartFile img : images) {
                String url = "";
                if (img == null) continue;
                if (img.getSize() > 0) {
                    //String basePath = "E:/images/background";
                    String basePath = "/home/ubuntu/www/images/moment";
                    url = Helper.storeImage(img, basePath);
                    url = "http://81.69.0.70:8082/images/moment" + url;
                }
                imageUrls.add(url);
//            System.out.println(img.ge);
            }
        Helper.setImagesUrlForMoment(moment, imageUrls);
        List<ContactsEntity> friends = contactsDao.findByUserIdNotBlack(moment.getUserid());

        boolean flag = false;
        int lastMomentId = -1;
        try {
            momentDao.save(moment);
            lastMomentId = momentDao.findMaxMomentId();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        TimelineEntity t = new TimelineEntity(0, moment.getUserid(), moment.getId(), moment.getTimestamp());
        try {
            timelineDao.save(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (friends.size() == 0) {
            return flag;
        }
        if (flag) {
            System.out.println("last momentid=" + lastMomentId + ", momentRealId=" + moment.getId());
            for (ContactsEntity contactsEntity : friends) {
                System.out.println("friendid = " + contactsEntity.getFriendId());
                TimelineEntity timelineEntity = new TimelineEntity(0, contactsEntity.getFriendId(), moment.getId(), moment.getTimestamp());
                timelineDao.save(timelineEntity);
            }
        }
        return flag;
    }

    public boolean deletePraiseById(int id) {
        boolean flag = false;
        try {
            praiseDao.deleteById(id);

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deletePraise(int userId, int momentId) {
        boolean flag = false;
        try {
            praiseDao.deleteByPraiseUserIdAndMomentId(userId, momentId);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteMoment(int id) {
        boolean flag = false;
        try {
            momentDao.deleteById(id);
            timelineDao.deleteByMomentId(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public boolean addPraise(PraiseEntity praise) {
        boolean flag = false;
        try {
            praiseDao.save(praise);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public List<MomentEntity> findMomentByUserid(int userId, int momentId) {
        if (momentId == -1) {
            return momentDao.findMomentByUserid(userId);
        } else {

        }
        List<MomentEntity> result = momentDao.findByUseridOrOrderByIdDesc(userId, momentId);
        return result;
    }


    public MomentEntity findMomentById(int id) {
        Optional<MomentEntity> optional = momentDao.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        return optional.get();
    }


    public List<PraiseEntity> findPraisesByMomentId(int momentId) {
        List<PraiseEntity> result = praiseDao.findByMomentId(momentId);
        return result;
    }
}
