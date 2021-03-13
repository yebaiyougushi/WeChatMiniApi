package com.zhengpj.wechatmini.controller;

import com.zhengpj.wechatmini.entity.Moment;
import com.zhengpj.wechatmini.service.MomentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/10 13:18
 */
@RestController
@RequestMapping("api")

@Api(value = "MomentController|朋友圈数据控制器")
public class MomentController {
    @Autowired
    MomentService momentService;

    @PostMapping(value = "/moment",headers = "content-type=application/json")
    @ApiOperation(value = "新增朋友圈")
    public boolean addMoment(@RequestBody Moment moment){
        System.out.println("开始新增moment, "+moment.toString());
        return momentService.addMoment(moment);
    }

    @GetMapping("/moment")
    @ApiOperation(value = "根据id获取朋友圈")
    public List<Moment> getMoment(@RequestParam(value = "userId", required = true)int userId){
        System.out.println("开始获取moment, userid = "+userId);
        return momentService.findMomentByUserid(userId);
    }

    @DeleteMapping("/moment")
    @ApiOperation(value = "根据id删除朋友圈")
    public boolean deleteMoment(@RequestParam(value = "id", required = true)int id){
        System.out.println("开始删除moment, id = "+id);
        return momentService.deleteMoment(id);
    }


}
