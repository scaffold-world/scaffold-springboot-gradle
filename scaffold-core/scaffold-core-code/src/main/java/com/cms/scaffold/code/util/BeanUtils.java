/**  
 * @Title: BeanUtils.java
 * @Package com.rd.common.util
 * TODO:TODO
 * @author zjh
 * @date 2016-11-2
 */
package com.cms.scaffold.code.util;

import com.cms.scaffold.common.util.DateUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * TODO:TODO
 * @author zjh
 * @date 2016-11-2
 */
public class BeanUtils extends org.springframework.beans.BeanUtils{
	
	
	/**
	 * 
	 * TODO：只拷贝特定的字段(数据更新时使用)
	 * @param source 源类
	 * @param target 目标类
	 * @param copyFields 需拷贝的字段
	 * @throws BeansException
	 * @author zjh
	 * @date 2016-11-2
	 */
	public static void copyPropertiesByList(Object source, Object target, String[] copyFields)
			throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (copyFields != null ? Arrays.asList(copyFields) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && ignoreList != null && ignoreList.contains(targetPd.getName())) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null &&
							ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
						catch (Throwable ex) {
							throw new FatalBeanException(
									"Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * TODO：拷贝源类不为空的内容
	 * @param source 源类
	 * @param target 目标类
	 * @param ignoreProperties 不需拷贝的字段
	 * @throws BeansException
	 * @author zjh
	 * @date 2016-11-8
	 */
	public static void copyPropertiesNotNull(Object source, Object target,String... ignoreProperties)
			throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		

		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null &&
							ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if(value != null){
								if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
									writeMethod.setAccessible(true);
								}
								writeMethod.invoke(target, value);
							}
						}
						catch (Throwable ex) {
							throw new FatalBeanException(
									"Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
	}
	/**
	 * 
	 * 拷贝源类进行类型转换
	 * @param source 源类
	 * @param target 目标类
	 * @throws BeansException
	 * @author zjh
	 * @date 2016-11-8
	 */
	public static void copyPropertiesConver(Object source, Object target)
			throws BeansException {
		copyPropertiesConver(source, target,null);
	}
	/**
	 * 
	 * 拷贝源类进行类型转换
	 * @param source 源类
	 * @param target 目标类
	 * @param ignoreProperties 不需拷贝的字段
	 * @throws BeansException
	 * @author zjh
	 * @date 2017-6-26
	 */
	public static void copyPropertiesConver(Object source, Object target,String[] ignoreProperties)
			throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		

		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null ) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							//类型转换
							if(!ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())){
								if ("java.util.Date".equals(readMethod.getReturnType().getCanonicalName())
										&& "java.lang.String".equals(writeMethod
												.getParameterTypes()[0]
												.getCanonicalName())) {
									
									value = DateUtil.dateStr4((Date)value);
								}else if("java.util.Date".equals(writeMethod.getParameterTypes()[0].getCanonicalName())
										&& "java.lang.String".equals(readMethod.getReturnType().getCanonicalName())){
									value = DateUtil.parseDate(value);
								}else{
									value = null;
								}
							}
							if(value != null){
								if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
									writeMethod.setAccessible(true);
								}
								writeMethod.invoke(target, value);
							}
						} catch (Throwable ex) {
							throw new FatalBeanException(
									"Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
	}
	
    public static <S, T> T copyPropertiesConver(S source, Class<T> destinationClass){
        if(source == null ){
            return null;
        }
        T destinationObject = BeanUtils.instantiateClass(destinationClass);
        try
        {
        	BeanUtils.copyPropertiesConver(source, destinationObject);
        }
        catch(Exception e)
        {
        }
        return destinationObject;
    }
	
    public static <S, T> T copyProperties(S source, Class<T> destinationClass){
        if(source == null ){
            return null;
        }
        T destinationObject = BeanUtils.instantiateClass(destinationClass);
        try
        {
        	BeanUtils.copyProperties(source, destinationObject);
        }
        catch(Exception e)
        {
        }
        return destinationObject;
    }
    

}
