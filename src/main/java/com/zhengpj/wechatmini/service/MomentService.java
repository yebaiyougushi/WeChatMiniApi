package com.zhengpj.wechatmini.service;

import com.zhengpj.wechatmini.entity.MomentEntity;
import com.zhengpj.wechatmini.entity.PraiseEntity;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/10 13:03
 */
public interface MomentService {
    boolean addMoment(MomentEntity moment);

    boolean deleteMoment(int id);

    boolean addPraise(PraiseEntity praise);

    List<MomentEntity> findMomentByUserid(int userId);

    MomentEntity findMomentById(int id);

    List<PraiseEntity> findPraisesByMomentId(int momentId);
}
