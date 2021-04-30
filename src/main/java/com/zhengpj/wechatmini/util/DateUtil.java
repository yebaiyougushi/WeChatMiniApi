package com.zhengpj.wechatmini.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhengpeijian
 * @date 2021/4/29 21:00
 */
public class DateUtil {
    public static Object dateToString(Date date) {
        //将Date转换为String
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }
}
