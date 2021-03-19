package com.zhengpj.wechatmini.controller;

import com.alibaba.fastjson.JSON;
import com.zhengpj.wechatmini.entity.FriendCircleEntity;
import com.zhengpj.wechatmini.entity.MomentEntity;
import com.zhengpj.wechatmini.entity.PraiseEntity;
import com.zhengpj.wechatmini.service.MomentService;
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

    @PostMapping(value = "/moment", headers = {"content-type=multipart/form-data"})
    @ApiOperation(value = "新增朋友圈")
    public boolean addMoment(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> images = ((MultipartHttpServletRequest) request)
                .getFiles("moment_images");
        String momentStr = params.getParameter("moment");
        MomentEntity moment = JSON.parseObject(momentStr, MomentEntity.class);
        System.out.println("开始新增momentstr, " + momentStr + ", moment=" + moment.toString());

        List<String> imageUrls = new ArrayList<>();
        if (images != null)
            for (MultipartFile img : images) {
                String url = "";
                if (img == null) continue;
                if (img.getSize() > 0) {
                    // String basePath = "E:/images/background";
                    String basePath = "/home/ubuntu/www/images/moment";
                    url = Helper.storeImage(img, basePath);
                    url = "http://81.69.0.70:8082/images/moment" + url;
                }
                imageUrls.add(url);
//            System.out.println(img.ge);
            }
        Helper.setImagesUrlForMoment(moment, imageUrls);
        return momentService.addMoment(moment);
//        return true;
    }

    @GetMapping("/moment/userId")
    @ApiOperation(value = "根据用户id获取朋友圈")
    public List<FriendCircleEntity> getMoments(@RequestParam(value = "userId", required = true) int userId) {
        System.out.println("开始获取moment, userid = " + userId);
        List<FriendCircleEntity> result = new ArrayList<>();
        List<MomentEntity> moments = momentService.findMomentByUserid(userId);
        for(MomentEntity moment:moments){
            List<PraiseEntity> praises = momentService.findPraisesByMomentId(moment.getId());
            FriendCircleEntity friendCircleEntity = new FriendCircleEntity(moment, praises);
            result.add(friendCircleEntity);
        }
        return result;
    }

    @GetMapping("moment/id")
    @ApiOperation(value = "根据朋友圈id获取单条朋友圈，刷新用")
    public FriendCircleEntity getSingleMoment(@RequestParam(value = "id")int id){
        System.out.println("开始获取moment, momentId = " + id);
        MomentEntity moment = momentService.findMomentById(id);
        List<PraiseEntity> praises = momentService.findPraisesByMomentId(moment.getId());
        FriendCircleEntity friendCircleEntity = new FriendCircleEntity(moment, praises);
        return friendCircleEntity;
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
        System.out.println("开始获取评论,momentId=" + momentId);
        return momentService.findPraisesByMomentId(momentId);
    }

    @RequestMapping(value = "/moment/praise", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ApiOperation(value = "新增点赞")
    public boolean addPraise(@RequestBody PraiseEntity praise) {
        System.out.println("开始新增点赞, praise:"+praise.toString());
        return momentService.addPraise(praise);
    }


}
