package com.zhengpj.wechatmini.dao;

import com.zhengpj.wechatmini.entity.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/10 12:57
 */
public interface MomentDao extends JpaRepository<Moment,Integer> {
    List<Moment> findMomentByUserid(int userId);
    void deleteById(int id);
}
