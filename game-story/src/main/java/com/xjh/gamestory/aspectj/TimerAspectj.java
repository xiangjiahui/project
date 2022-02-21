package com.xjh.gamestory.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author 向嘉晖
 * @date 2022/2/21
 */
@Aspect
@Component
public class TimerAspectj {

    @Around("execution(* com.xjh.gamestory.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录起始时间
        long startTime = System.currentTimeMillis();

        //执行连接点方法,即切面所在位置对应的方法,本项目中表示执行注册或执行登录
        Object result = joinPoint.proceed();

        //记录结束时间
        long endTime = System.currentTimeMillis();

        //计算耗时
        System.out.println("耗时:" + (endTime - startTime) + "ms.");

        return result;
    }
}
