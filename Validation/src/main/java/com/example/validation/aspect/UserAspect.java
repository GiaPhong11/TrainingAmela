package com.example.validation.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class UserAspect {

    @Before(value = "executeController()")
    public void beforeExecuteController(){
        System.out.println("Before Controller");
    }

    @After(value = "executeController()")
    public void afterExecuteController(){
        System.out.println("After Controller");
    }

    //Đánh dấu joinpoint khi tác động vào Controller
    @Pointcut(value = "within(com.example.validation.controller.*)")
    public void executeController(){}
}
