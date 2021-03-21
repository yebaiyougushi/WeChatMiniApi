package com.zhengpj.wechatmini.controller;

import com.alibaba.fastjson.JSON;
import com.zhengpj.wechatmini.entity.*;

import com.zhengpj.wechatmini.service.UserService;
import com.zhengpj.wechatmini.service.impl.MomentService;
import com.zhengpj.wechatmini.service.impl.TimelineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Autowired
    UserService userService;
    @Autowired
    TimelineService timelineService;

    @PostMapping(value = "/moment", headers = {"content-type=multipart/form-data"})
    @ApiOperation(value = "新增朋友圈")
    public boolean addMoment(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> images = ((MultipartHttpServletRequest) request)
                .getFiles("moment_images");
        String momentStr = params.getParameter("moment");
        MomentEntity moment = JSON.parseObject(momentStr, MomentEntity.class);
        System.out.println("开始新增momentstr, " + momentStr + ", moment=" + moment.toString());
        return momentService.addMoment(moment, images);
//        return true;
    }

    @GetMapping("/moment/self")
    @ApiOperation(value = "根据用户id获取自己的朋友圈")
    public List<FriendCircleEntity> getSelfMoments(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "momentId", required = true) int momentId) {
        System.out.println("开始获取moment, userid = " + userId + ", momentId=" + momentId);
        List<FriendCircleEntity> result = new ArrayList<>();
        List<MomentEntity> moments = momentService.findMomentByUserid(userId, momentId);
        UserEntity userEntity = userService.findUserById(userId);
        for (MomentEntity moment : moments) {
            List<PraiseEntity> praises = momentService.findPraisesByMomentId(moment.getId());
            FriendCircleEntity friendCircleEntity = new FriendCircleEntity(moment, praises);
            friendCircleEntity.setUserEntity(userEntity);
            result.add(friendCircleEntity);

        }
        return result;
    }

    @GetMapping("/moment/all")
    @ApiOperation(value = "根据用户id获取朋友和自己的朋友圈")
    public List<FriendCircleEntity> getAllMoments(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "momentId", required = true) int momentId) {
        System.out.println("开始获取moment, userid = " + userId + ", momentId=" + momentId);
        List<FriendCircleEntity> result = new ArrayList<>();
        List<TimelineEntity> timelineEntities = timelineService.findByUserId(userId, momentId);
        List<MomentEntity> moments = new ArrayList<>();
        for (TimelineEntity t : timelineEntities) {
            moments.add(momentService.findMomentById(t.getMomentId()));
        }

        for (MomentEntity moment : moments) {
            result.add(getFriendCircleEntity(moment));
        }
        return result;
    }

    private FriendCircleEntity getFriendCircleEntity(MomentEntity moment) {
        List<PraiseEntity> praises = momentService.findPraisesByMomentId(moment.getId());
        UserEntity userEntity = userService.findUserById(moment.getUserid());
        FriendCircleEntity friendCircleEntity = new FriendCircleEntity(moment, praises);
        friendCircleEntity.setUserEntity(userEntity);
        return friendCircleEntity;
    }

    @GetMapping("moment/id")
    @ApiOperation(value = "根据朋友圈id获取单条朋友圈，刷新用")
    public FriendCircleEntity getSingleMoment(@RequestParam(value = "id") int id) {
        System.out.println("开始获取moment, momentId = " + id);
        MomentEntity moment = momentService.findMomentById(id);
//        List<PraiseEntity> praises = momentService.findPraisesByMomentId(moment.getId());
//        UserEntity userEntity = userService.findUserById(moment.getUserid());
//        FriendCircleEntity friendCircleEntity = new FriendCircleEntity(moment, praises);
//        friendCircleEntity.setUserEntity(userEntity);
//        return friendCircleEntity;
        return getFriendCircleEntity(moment);
    }

    @DeleteMapping("/moment")
    @ApiOperation(value = "根据id删除朋友圈")
    public boolean deleteMoment(@RequestParam(value = "id", required = true) int id) {
        System.out.println("开始删除moment, id = " + id);
        return momentService.deleteMoment(id);
    }

    @GetMapping("/moment/praise")
    @ApiOperation(value = "根据momentId获取点赞")
    public List<PraiseEntity> getPraises(@RequestParam(value = "momentId", required = true) int momentId) {
        System.out.println("开始获取点赞,momentId=" + momentId);
        return momentService.findPraisesByMomentId(momentId);
    }

    @RequestMapping(value = "/moment/praise", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ApiOperation(value = "新增点赞")
    public boolean addPraise(@RequestBody PraiseEntity praise) {
        System.out.println("开始新增点赞, praise:" + praise.toString());
        return momentService.addPraise(praise);
    }

    @DeleteMapping("/moment/praise")
    @ApiOperation(value = "根据momentId和userId取消点赞")
    public boolean deletePraises(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "momentId", required = true) int momentId) {
        System.out.println("开始取消点赞,momentId=" + momentId + ", userId=" + userId);
        return momentService.deletePraise(userId, momentId);
    }

}
