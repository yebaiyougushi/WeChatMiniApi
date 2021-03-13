package com.zhengpj.wechatmini.service.impl;


import com.zhengpj.wechatmini.dao.MomentDao;
import com.zhengpj.wechatmini.entity.Moment;
import com.zhengpj.wechatmini.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/10 13:06
 */
@Service
public class MomentServiceImpl implements MomentService {
    @Autowired
    MomentDao momentDao;
    @Override
    public boolean addMoment(Moment moment) {
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
    public List<Moment> findMomentByUserid(int userId) {
        List<Moment> result = momentDao.findMomentByUserid(userId);
        return result;
    }
}
