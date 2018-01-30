package com.eks.utils;
/**
* @copyright create by XuYongJie on 2018/1/25 16:33
* @description 根据指定的换行符去除String中的空行及前后空格
* @version 1.0.0
*/
public class StringUtils2 {
    public final static String LINUX_LINE_SEPARATOR = "\n";
    public final static String WINDOWS_LINE_SEPARATOR = "\r\n";
    public static String removeBlankUseSystemLineSeparator(String string){
        return StringUtils2.removeBlank(string,System.getProperty("line.separator", LINUX_LINE_SEPARATOR));//从当前系统中获取换行符，默认是"\n"
    }
    public static String removeBlankUseLinuxLineSeparator(String string){
        return StringUtils2.removeBlank(string,LINUX_LINE_SEPARATOR);
    }
    public static String removeBlankUseWindowsLineSeparator(String string){
        return StringUtils2.removeBlank(string,WINDOWS_LINE_SEPARATOR);
    }
    public static String removeBlank(String string, String lineSeparator){
        if(string == null || string.length() == 0){//string为NULL或"",则直接返回
            return string;
        }
        String stringTrim = string.trim();
        if(stringTrim.length() == 0){//string为" ",则返回""
            return stringTrim;
        }
        String[] splitArray = string.split(lineSeparator);//以换行符进行分割
        int splitArrayLength = splitArray.length;
        if(splitArrayLength == 1){//string为" test ",则返回"test"
            return string.trim();
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for(String stringTemp : splitArray){
            i = i + 1;
            String stringTempTrim = stringTemp.trim();//去除当前行的前后空格
            if(stringTempTrim.length() == 0){//如果去除当前行的前后空格后的字符串长度为0则跳过
                continue;
            }
            stringBuffer.append(stringTempTrim);
            if(i != splitArrayLength){//最后一行不要拼接换行符
                stringBuffer.append(lineSeparator);
            }
        }
        return stringBuffer.toString();
    }
}