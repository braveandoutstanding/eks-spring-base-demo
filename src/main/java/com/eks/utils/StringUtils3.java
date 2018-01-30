package com.eks.utils;
/**
* @copyright create by XuYongJie on 2018/1/27 17:21
* @description 如果string以endString则将去除结尾部分endString后的新string返回
* @version 1.0.0
*/
public class StringUtils3 {
    public static String removeEndString(String string,String endString){
        if(string != null && !"".equals(string) && endString != null && !"".equals(endString)){
            if(string.endsWith(endString)){
                return string.substring(0,string.length() - endString.length());
            }
        }
        return string;
    }
}