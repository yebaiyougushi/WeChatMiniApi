package com.zhengpj.wechatmini.service.impl;


import com.zhengpj.wechatmini.dao.MomentDao;
import com.zhengpj.wechatmini.dao.PraiseDao;
import com.zhengpj.wechatmini.entity.MomentEntity;
import com.zhengpj.wechatmini.entity.PraiseEntity;
import com.zhengpj.wechatmini.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author zhengpeijian
 * @date 2021/3/10 13:06
 */
@Service
public class MomentServiceImpl implements MomentService {
    @Autowired
    MomentDao momentDao;
    @Autowired
    PraiseDao praiseDao;
    @Override
    public boolean addMoment(MomentEntity moment) {
        boolean flag = false;
        try{
            momentDao.save(moment);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteMoment(int id) {
        boolean flag = false;
        try {
            momentDao.deleteById(id);
            //momentDao.deleteById(id);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addPraise(PraiseEntity praise) {
        boolean flag = false;
        try {
            praiseDao.save(praise);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<MomentEntity> findMomentByUserid(int userId) {
        List<MomentEntity> result = momentDao.findMomentByUserid(userId);
        return result;
    }

    @Override
    public MomentEntity findMomentById(int id) {
        Optional<MomentEntity> optional = momentDao.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        return optional.get();
    }

    @Override
    public List<PraiseEntity> findPraisesByMomentId(int momentId){
        List<PraiseEntity> result = praiseDao.findByMomentId(momentId);
        return result;
    }
}
