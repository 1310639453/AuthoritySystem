package cn.cui.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther cui
 * @Date 2020/2/10
 * @Desc 时间转换工具类
 */
public class DateUtils {

    /**
     * @param date 时间
     * @param pattern 转换格式
     * @return java.lang.String
     * @exception 
     * @Desc Date转字符串
     **/
    public static String date2String(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * @param dateStr 时间字符串形式
     * @param pattern 转换格式
     * @return java.util.Date
     * @exception ParseException
     * @Desc 字符串转Date
     **/
    public static Date string2Date(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateStr);
    }
}
