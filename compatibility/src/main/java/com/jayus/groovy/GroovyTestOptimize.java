package com.jayus.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用 GroovyClassLoader 调用
 */
public class GroovyTestOptimize {

    // 脚本容器 缓存 Script 避免创建太多
    private static final Map<String, GroovyObject> map = new HashMap<>();

    // groovy 脚本执行器
    private static final GroovyClassLoader gcl = new GroovyClassLoader();

    public static void main(String[] args) throws Exception {
        //groovyStr();
        //groovyStrParam();
        //groovyStrTwoParam();
        groovyStrObjectParam();
    }

    public static GroovyObject loadScript(String key,String rule){
        if (map.containsKey(key)) return map.get(key);
        GroovyObject object = loadScript(rule);
        map.put(key,object);
        return object;
    }

    public static GroovyObject loadScript(String rule){
        if (StringUtils.isEmpty(rule)) return null;
        try {
            Class<?> ruleClass = gcl.parseClass(rule);
            if (ruleClass != null){
                GroovyObject object = (GroovyObject) ruleClass.newInstance();
                return object;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // GroovyClassLoader的clearCache()函数在调用完毕销毁GroovyShell、Script等实例 避免 OOM
            gcl.clearCache();
        }
        return null;
    }

    /**
     * 代码里面写脚本执行
     */
    private static void groovyStr(){
        String ss = "def sayHello() {\n" +
                "    def x = 5;\n" +
                "    println('hello world:' + x);\n" +
                " '111111' \n"+
                "}";
        GroovyObject object = loadScript("groovyStr", ss);
        Object sayHello = object.invokeMethod("sayHello", null);
        System.out.println(sayHello);
    }

    /**
     * 调用带参方法
     * @throws IOException
     */
    private static void groovyStrParam() {
        String ss = "def sayHello(name) {\n" +
                "    println('hello world:' + name);\n" +
                " '222222' \n"+
                "}";
        GroovyObject object = loadScript("groovyStrParam", ss);
        Object sayHello = object.invokeMethod("sayHello", new Object[]{"mingou"});
        System.out.println(sayHello);
    }

    /**
     * 调用两个带餐带参方法
     * @throws IOException
     */
    private static void groovyStrTwoParam() {
        String ss = "def sayHello(name,pwd) {\n" +
                "    println('hello world:' + name+':'+pwd);\n" +
                " '222222' \n"+
                "}";
        GroovyObject object = loadScript("groovyStrTwoParam", ss);
        Object sayHello = object.invokeMethod("sayHello", new Object[]{"mingou",123456});
        System.out.println(sayHello);
    }

    /**
     * 调用对象类型参数的方法
     * @throws IOException
     */
    private static void groovyStrObjectParam() throws IOException {
        String ss = "def sayHello(List list,List list2) {\n" +
                "    list.add(1);\n" +
                "println(list2.get(0)); \n"+
                " list \n"+
                "}";
        GroovyObject object = loadScript("groovyStrObjectParam", ss);
        ArrayList list = new ArrayList();
        list.add(2);
        Object sayHello = object.invokeMethod("sayHello", new Object[]{list,list});
        System.out.println(sayHello);
    }

}
