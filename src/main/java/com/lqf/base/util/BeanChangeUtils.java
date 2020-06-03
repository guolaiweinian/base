package com.lqf.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\5\9 0009  9:24
 * @description 实体类工具类
 */
public class BeanChangeUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanChangeUtils.class);

    public static <E> E mapToBean(Map source, Class<E> newClass) throws Exception {
        if (source == null){
            return null;
        }
        E newInstance  = newClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(newInstance.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(newInstance, source.get(property.getName()));
            }
        }
        return newInstance;
    }

    /**
     * bean类型转换
     * @param source 数据来源
     * @param newClass 新数据类型
     * @param <E>
     * @return
     */
    public static <E> E beanChange(Object source, Class<E> newClass){
        // 数据不能为空
        if(source == null){
            return null;
        }
        // 要转换的数据类型不能为空
        if (newClass == null) {
            return null;
        }
        try {
            // 创建新的对象实例
            E newInstance = newClass.newInstance();
            BeanUtils.copyProperties(source, newInstance);
            // 返回新对象
            return newInstance;
        } catch (Exception e){
            logger.error("bean类型转换异常，异常为" + e.getMessage());
            return null;
        }
    }

    /**
     * bean数组转换 <>list循环模式</>
     * @param source 数据源
     * @param target 新数组元素类型
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<V> listBeanChange(List<T> source, Class<V> target){
        try {
            // 建立目标新数组
            List<V> results = new ArrayList<>();
            // 循环拷贝数据到新数组
            for (T t : source){
                // 新建数组的子元素
                V result = (V) beanChange(t, target.newInstance().getClass());
                results.add(result);
            }
            return results;
        } catch (Exception e) {
            logger.error("bean数组转换异常，异常为：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bean数组转换 <>stream模式</>
     * @param source 数据源
     * @param target 新数组元素类型
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<V> listBeanChangeStream(List<T> source, Class<V> target){
        List<V> results = source.stream().map(item -> {
            try {
                return (V) beanChange(item, target.newInstance().getClass());
            } catch (Exception e){
                logger.error("bean数组转换异常，异常为：" + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return null;
    }
}
