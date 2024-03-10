package com.jayus.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 使用封装好的 GroovyShell 调用
 */
public class GroovyTest {

    public static void main(String[] args) throws Exception {
        //groovyStr();
        //groovyFile();
        //groovyFileParam();
        groovyStrObjectParam();
    }

    /**
     * 代码里面写脚本执行
     */
    private static void groovyStr(){
        String ss = "def x = 5;print('groovy hello wrold' + x)";
        GroovyShell shell = new GroovyShell();
        shell.evaluate(ss);
        // GroovyClassLoader的clearCache()函数在调用完毕销毁GroovyShell、Script等实例 避免 OOM
        shell.getClassLoader().clearCache();
    }

    /**
     * 调用文件内无参方法
     * @throws IOException
     */
    private static void groovyFile() throws IOException {
        GroovyShell shell = new GroovyShell();
        Object evaluate = shell.evaluate(new File("D:\\idea\\ccc\\compatibility\\compatibility\\src\\main\\java\\com\\jayus\\groovy\\groovyScript.groovy"));
        System.out.println(evaluate);
        // GroovyClassLoader的clearCache()函数在调用完毕销毁GroovyShell、Script等实例 避免 OOM
        shell.getClassLoader().clearCache();
    }

    /**
     * 调用带参方法
     * @throws IOException
     */
    private static void groovyFileParam() throws IOException {
        // 调用带参数的 groovy shell 时，使用 bind 绑定数据
        Binding binding = new Binding();
        binding.setProperty("name","mingou");
        GroovyShell shell = new GroovyShell(binding);
        Object evaluate = shell.evaluate(new File("D:\\idea\\ccc\\compatibility\\compatibility\\src\\main\\java\\com\\jayus\\groovy\\groovyScript.groovy"));
        System.out.println(evaluate);
        // GroovyClassLoader的clearCache()函数在调用完毕销毁GroovyShell、Script等实例 避免 OOM
        shell.getClassLoader().clearCache();
    }

    /**
     * 调用对象类型参数的方法
     * @throws IOException
     */
    private static void groovyFileObjectParam() throws IOException {
        Binding binding = new Binding();
        ArrayList list = new ArrayList();
        list.add(2);
        binding.setProperty("list",list);
        GroovyShell shell = new GroovyShell(binding);
        Object evaluate = shell.evaluate(new File("D:\\idea\\ccc\\compatibility\\compatibility\\src\\main\\java\\com\\jayus\\groovy\\groovyScript.groovy"));
        System.out.println(evaluate);
        // GroovyClassLoader的clearCache()函数在调用完毕销毁GroovyShell、Script等实例 避免 OOM
        shell.getClassLoader().clearCache();
    }

    /**
     * 代码里面写脚本执行对象参数方法
     */
    private static void groovyStrObjectParam(){
        String ss = "def objectParam(List list){\n" +
                "    list.add(1);\n" +
                "    list;\n" +
                "} \n" +
                "objectParam(list)";
        Binding binding = new Binding();
        ArrayList list = new ArrayList();
        list.add(2);
        binding.setProperty("list",list);
        GroovyShell shell = new GroovyShell(binding);
        System.out.println(shell.evaluate(ss));
        // GroovyClassLoader的clearCache()函数在调用完毕销毁GroovyShell、Script等实例 避免 OOM
        shell.getClassLoader().clearCache();
    }

}
