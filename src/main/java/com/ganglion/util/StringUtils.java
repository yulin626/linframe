package com.ganglion.util;


/**
 * JSON 转换
 */
public final class StringUtils {

    /**
     * 把字符串首字母大写
     */
    public static String capitalizeFirstLatter(String str) {
        if (str == null || str.equals("")) {
            return null;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    /**
     * 判断字符串相等，空字符串的情况返回false
     */
    public static Boolean compare(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        } else {
            return str1.equals(str2);
        }
    }
}