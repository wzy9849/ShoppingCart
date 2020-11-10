package com.wzy.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNoUtils {
    public static synchronized String generateUniqueNo(){
        Random random = new Random();
        // 随机数的量 自由定制，这是9位随机数
        Integer r = random.nextInt(900000000) + 100000000;
        // 返回  14位时间
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        // E+14位时间+9位随机数
        return  "E"+timeStr + r;
    }
}
