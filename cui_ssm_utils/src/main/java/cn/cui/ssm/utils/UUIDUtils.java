package cn.cui.ssm.utils;

import java.util.UUID;

/**
 * @Auther cui
 * @Date 2020/2/10
 * @Desc UUID工具类
 */
public class UUIDUtils {

    public static String randomUUIDString(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
