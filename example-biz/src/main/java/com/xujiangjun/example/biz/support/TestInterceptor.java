package com.xujiangjun.example.biz.support;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author xujiangjun
 * @date 2017-04-01 16:39
 */
public class TestInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Test Interceptor...");
        return methodInvocation.proceed();
    }
}
