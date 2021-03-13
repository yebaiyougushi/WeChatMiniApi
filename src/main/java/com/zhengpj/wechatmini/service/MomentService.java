package com.zhengpj.wechatmini.service;

import com.zhengpj.wechatmini.entity.Moment;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/10 13:03
 */
public interface MomentService {
    boolean addMoment(Moment moment);

    boolean deleteMoment(int id);

    List<Moment> findMomentByUserid(int userId);
}
