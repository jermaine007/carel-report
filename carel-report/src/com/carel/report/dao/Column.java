package com.carel.report.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String name();
	DBType type() default DBType.STRING;
}
