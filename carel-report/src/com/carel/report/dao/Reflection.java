package com.carel.report.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reflection {
	private static Logger logger = LoggerFactory.getLogger(Reflection.class);

	public static <T> T newInstance(Class<T> cls) {
		try {
			logger.debug("create instance for:{}",cls);
			return cls.newInstance();
		} catch (InstantiationException e) {
			
			logger.error("error:", e);
		} catch (IllegalAccessException e) {
			
			logger.error("error:", e);
		}
		return null;
	}

	public static <T> Annotation[] getAnnotations(Class<T> cls) {
		return cls.getAnnotations();
	}

	public static <T, A extends Annotation> Annotation getAnnotaion(
			Class<T> cls, Class<A> annotationClass) {
		if (cls.isAnnotationPresent(annotationClass)) {
			return cls.getAnnotation(annotationClass);
		}
		return null;
	}

	public static <T> Field[] getFields(Class<T> cls) {
		return cls.getDeclaredFields();
	}

	public static <T, A extends Annotation> Field[] getAnnotationPresentFields(
			Class<T> cls, Class<A> annotationClass) {
		Field[] files = cls.getDeclaredFields();
		List<Field> list = new ArrayList<Field>();
		for (Field field : files) {
			if (field.isAnnotationPresent(annotationClass)) {
				logger.debug("find annotation presented field:{},{}",
						new Object[]{field.getName(),annotationClass});
				list.add(field);
			}
		}
		return list.toArray(new Field[list.size()]);
	}
	
	public static void setFieldValue(Field field,Object instance,Object value){
		try {
			logger.debug("set field value-field:{},value:{}",
					new Object[]{field.getName(),value});
			field.setAccessible(true);
			field.set(instance, value);
		} catch (IllegalArgumentException e) {
			logger.error("error:",e);
		} catch (IllegalAccessException e) {
			logger.error("error:",e);
		}
	}
	
	public static Object getFieldValue(Field field,Object instance){
		try {
			logger.debug("get field value-field:{}",field.getName());
			field.setAccessible(true);
			return field.get(instance);
		} catch (IllegalArgumentException e) {
			logger.error("error:",e);
		} catch (IllegalAccessException e) {
			logger.error("error:",e);
		}
		return null;
	}
}
