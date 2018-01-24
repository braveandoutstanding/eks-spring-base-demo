package com.eks.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
/**
* @copyright create by XuYongJie on 2018/1/20 16:49
* @description BeanUtils的copyProperties方法会将source为NULL的字段也赋给target，本工具类将不会将source为NULL的字段赋给target
*/
public class BeanUtils2 {
    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptorArray = beanWrapper.getPropertyDescriptors();
        Set<String> emptyNameSet = new HashSet<String>();
        for(PropertyDescriptor propertyDescriptor : propertyDescriptorArray) {
            Object srcValue = beanWrapper.getPropertyValue(propertyDescriptor.getName());
            if (srcValue == null){
                emptyNameSet.add(propertyDescriptor.getName());
            }
        }
        return emptyNameSet.toArray(new String[emptyNameSet.size()]);
    }
    public static void copyPropertiesIgnoreNull(Object source, Object target){
        BeanUtils.copyProperties(source, target, BeanUtils2.getNullPropertyNames(source));
    }
}