package com.zhengpj.wechatmini.controller;

import com.zhengpj.wechatmini.entity.Moment;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhengpeijian
 * @date 2021/3/18 19:42
 */
public class Helper {
    public static String storeImage(MultipartFile img, String basePath) {
        String datePath = getDatePath();
        String realPath = basePath + datePath;
        //获取上传文件名
        String fileName = img.getOriginalFilename();
        System.out.println("path=" + basePath + datePath);
        File tmpFile = new File(realPath);
        tmpFile.mkdirs();
        File file = new File(realPath, fileName);
        try {
            img.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return datePath + "/" + fileName;
    }
    public static String getDatePath() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'-'HH:mm:ss");
        String time = dateFormat.format(date);
        String[] str = time.split("-");
        String datePath = "/" + str[0] + "/" + str[1] + "/" + str[2];
        return datePath;
    }
    public static void setImagesUrlForMoment(Moment moment, List<String> images){
        int size = images.size();
        moment.setImageNum(size);
        if(size>=1)moment.setImg1(images.get(0));
        if(size>=2)moment.setImg2(images.get(1));
        if(size>=3)moment.setImg3(images.get(2));
        if(size>=4)moment.setImg4(images.get(3));
        if(size>=5)moment.setImg5(images.get(4));
        if(size>=6)moment.setImg6(images.get(5));
        if(size>=7)moment.setImg7(images.get(6));
        if(size>=8)moment.setImg8(images.get(7));
        if(size>=9)moment.setImg9(images.get(8));
    }

}
