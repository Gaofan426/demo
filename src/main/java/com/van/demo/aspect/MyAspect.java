package com.van.demo.aspect;

import com.van.demo.annotation.My;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class MyAspect {

    @Pointcut("@annotation(com.van.demo.annotation.My)")
    private void my() { }

    /**
     * 定制一个环绕通知
     * @param joinPoint
     */
    @Around("my()")
    public void advice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        My my = method.getAnnotation(My.class);
        String value = my.value();
        System.out.println(value + "环绕开始");
        joinPoint.proceed();
        System.out.println(value + "环绕结束");
    }

    //当想获得注解里面的属性，可以直接注入改注解
    //方法可以带参数，可以同时设置多个方法用&&
    @Before("my()")
    public void record(JoinPoint joinPoint) {
        System.out.println("准备");
    }

    @After("my()")
    public void after() {
        System.out.println("结束");
    }
}
