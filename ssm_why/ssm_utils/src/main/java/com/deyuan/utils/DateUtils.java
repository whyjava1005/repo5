package com.deyuan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

//    日期转换成字符串的方法 date是参数，patt是字符串格式
    public static String date2string(Date date,String patt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        String format = simpleDateFormat.format(date);

        return format;
    }
//    字符串转成日期
    public static Date string2date(String str,String patt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
            return date;
        } catch (ParseException e) {
            throw  new RuntimeException("日期格式转换异常"+e);
        }

    }
}
