package cn.cui.ssm.controller.converters;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther cui
 * @Date 2020/2/10
 * @Desc 日期Converter类
 */
public class DateConverter implements Converter<String, Date> {
    /**
     * @param source 时间字符串形式
     * @return java.util.Date
     * @exception 
     * @Desc
     **/
    @Override
    public Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
