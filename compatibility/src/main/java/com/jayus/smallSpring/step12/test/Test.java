package com.jayus.smallSpring.step12.test;

import com.jayus.smallSpring.step11.bean.UserServiceInterceptor;
import com.jayus.smallSpring.step12.aop.AdvisedSupport;
import com.jayus.smallSpring.step12.aop.ClassFilter;
import com.jayus.smallSpring.step12.aop.MethodMatcher;
import com.jayus.smallSpring.step12.aop.TargetSource;
import com.jayus.smallSpring.step12.aop.aspectj.AspectJExpressionPointcut;
import com.jayus.smallSpring.step12.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.jayus.smallSpring.step12.aop.framework.ProxyFactory;
import com.jayus.smallSpring.step12.aop.framework.ReflectiveMethodInvocation;
import com.jayus.smallSpring.step12.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.jayus.smallSpring.step12.bean.IUserService;
import com.jayus.smallSpring.step12.bean.UserService;
import com.jayus.smallSpring.step12.bean.UserServiceBeforeAdvice;
import com.jayus.smallSpring.step12.context.support.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        //test_proxyFactory();
        //test_beforeAdvice();
        //test_advisor();
        //test_aop();
        test_proxy_method();
    }

    public static void test_proxyFactory() {
        // false/true JDK动态代理，CGLIB动态代理
        advisedSupport.setProxyTargetClass(false);
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println(proxy.queryUserInfo());
    }

    public static void test_beforeAdvice() {
        UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy.queryUserInfo());
    }

    public static void test_advisor(){
        IUserService userService = new UserService();

        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* com.jayus.smallSpring.step12.bean.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())){
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(userService);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true);

            IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
            System.out.println("测试结果："+proxy.queryUserInfo());
        }
    }

    public static void test_aop(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果："+userService.queryUserInfo());
    }

    public static void test_proxy_method(){
        Object tatgetObj = new UserService();

        IUserService proxy = (IUserService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), tatgetObj.getClass().getInterfaces(), new InvocationHandler() {

            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.jayus.smallSpring.step12.bean.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method,tatgetObj.getClass())){
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(tatgetObj,method,args));
                }
                return method.invoke(tatgetObj,args);
            }
        });
        System.out.println("测试结果" + proxy.queryUserInfo());

    }

}
