package com.zhengpj.wechatmini.controller;

import com.alibaba.fastjson.JSON;
import com.zhengpj.wechatmini.entity.Moment;
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
        Moment moment = JSON.parseObject(momentStr, Moment.class);
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

    @GetMapping("/moment")
    @ApiOperation(value = "根据id获取朋友圈")
    public List<Moment> getMoment(@RequestParam(value = "userId", required = true) int userId) {
        System.out.println("开始获取moment, userid = " + userId);
        return momentService.findMomentByUserid(userId);
    }

    @DeleteMapping("/moment")
    @ApiOperation(value = "根据id删除朋友圈")
    public boolean deleteMoment(@RequestParam(value = "id", required = true) int id) {
        System.out.println("开始删除moment, id = " + id);
        return momentService.deleteMoment(id);
    }


}
