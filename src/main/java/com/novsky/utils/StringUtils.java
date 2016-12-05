package com.novsky.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class StringUtils {


    /**
     * @param arrayStr  数组字符串  a,b,c
     * @param separator ,
     * @return 字符串转数组
     */
    public static String[] str2Array(String arrayStr, String separator) {
        String[] array = arrayStr.split(separator);
        return array;
    }


    /**
     * @param arrayStr  数组字符串  1,2,3
     * @param separator ,
     * @return 字符串转集合 Long
     */
    public static List<Long> str2List(String arrayStr, String separator) {
        List<Long> longList = new ArrayList<Long>();
        String[] array = arrayStr.split(separator);
        for (String str : array) {
            longList.add(Long.parseLong(str));
        }
        return longList;
    }


    /**
     * @param arrayStr  数组字符串  1,2,3
     * @param separator ,
     * @return 字符串转集合 Long
     */
    public static String replaceStrSeparator(String arrayStr, String separator) {

        String array[] = arrayStr.split(separator);
        String newStr = "";

        for (String s : array) {
            if (s != null && !s.equals("")) {
                newStr += s + ",";
            }
        }
        newStr = "(" + newStr.substring(0, newStr.length() - 1) + ")";
        return newStr;
    }


    /**
     * @param str
     * @return 首字母大写
     */
    public static String upperCaseFirstOne(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (i == 0) {
                ch[0] = Character.toUpperCase(ch[0]);
            } else {
                ch[i] = Character.toLowerCase(ch[i]);
            }
        }
        StringBuffer a = new StringBuffer();
        a.append(ch);
        return a.toString();
    }

    /**
     * @param str
     * @return 首字母小写
     */
    public static String upperCaseCamel(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }



    /**
     * @param str
     * @return 首字母大写
     */
    public static String lowerCaseCamel(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
    }

    /**
     * @param array
     * @return 数组去空值
     */
    public static List<String> removeNullValue(String array[]) {
        List<String> strList = new ArrayList<String>();
        for (String str : array) {
            if (str != null && !str.equals("")) {
                strList.add(str);
            }

        }
        return strList;
    }

}
