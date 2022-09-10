package com.sbpark.project.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //어노테이션 생성 위치 지정
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { //@interface -> 어노테이션 클래스 지정
}
