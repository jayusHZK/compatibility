package com.jayus.smallSpring.step12.test;

import com.jayus.smallSpring.step11.bean.UserServiceInterceptor;
import com.jayus.smallSpring.step12.aop.AdvisedSupport;
import com.jayus.smallSpring.step12.aop.TargetSource;
import com.jayus.smallSpring.step12.aop.aspectj.AspectJExpressionPointcut;
import com.jayus.smallSpring.step12.aop.framework.ProxyFactory;
import com.jayus.smallSpring.step12.bean.IUserService;
import com.jayus.smallSpring.step12.bean.UserService;

/**
 * @author : h zk
 * @date : 2023/4/12 17:39
 * @description :
 **/
public class Test {

    static private AdvisedSupport advisedSupport;

    static {
        IUserService userService = new UserService();
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.jayus.smallSpring.step12.bean.IUserService.*(..))"));
    }

    public static void main(String[] args) {
        test_proxyFactory();
    }

    public static void test_proxyFactory(){
        advisedSupport.setProxyTargetClass(false);
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getrProxy();
        System.out.println(proxy.queryUserInfo());
    }

}
