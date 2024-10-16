package com.jayus.smallMyBatis.step11.test;

import com.jayus.smallMyBatis.step11.io.Resources;
import com.jayus.smallMyBatis.step11.session.SqlSession;
import com.jayus.smallMyBatis.step11.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step11.session.SqlSessionFactoryBuilder;
import com.jayus.smallMyBatis.step11.test.dao.IUserDao;
import com.jayus.smallMyBatis.step11.test.po.User;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

/**
 * @ClassName ApiTest
 * @Description:
 * @date: 2024/9/18 21:18
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    private static SqlSession sqlSession;

    public static void main(String[] args) throws IOException {
        init();
        test_queryUserInfoById();
        //test_queryUserInfo();
        //cglibProxyTest();
    }

    public static void init() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    public static void test_queryUserInfoById() {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryUserInfoById(1L));
    }

    public static void test_queryUserInfo() {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryUserInfo(new User(1L, "superAdmin")));
        Supplier<String> f = new User(1L, "superAdmin")::getUser_name;
    }

    public static void jdkProxyTest() {
        while (true) {
            Object o = Proxy.newProxyInstance(IUserDao.class.getClassLoader(), new Class[]{IUserDao.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return 1;
                }
            });
            System.out.println(o.getClass().getSimpleName());
        }
    }

    public static void jdkProxyTest1() {
        while (true) {
            Object o = Proxy.newProxyInstance(IUserDao.class.getClassLoader(), new Class[]{IUserDao.class},
                    ((ob,m,a) -> {
                        System.out.println(1);
                        return 1;
                    }));
            System.out.println(o.getClass().getSimpleName());
        }
    }


    public static void cglibProxyTest() {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(User.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invoke(obj, args);
                }
            });
            Object o = enhancer.create();
            System.out.println(o.getClass().getSimpleName());
        }
    }

}
