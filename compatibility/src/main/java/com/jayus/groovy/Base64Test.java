package com.jayus.groovy;

import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @ClassName Base64Test
 * @Description:
 * @date: 2024/10/24 21:05
 */
public class Base64Test {

    public static void main(String[] args) throws Exception {
        System.out.println(new String(Base64.getEncoder().encode("123".getBytes(StandardCharsets.UTF_8))));
        System.out.println(new String(Base64.getDecoder().decode("bWluZ291".getBytes(StandardCharsets.UTF_8))));
        GroovyShell shell = new GroovyShell();
        Script script = shell.parse(new File("D:\\idea\\ccc\\compatibility\\compatibility\\src\\main\\java\\com\\jayus\\groovy\\base64Utils.groovy"));
        byte[] bytes = (byte[]) script.invokeMethod("decoder", "123");
        System.out.println(new String(Base64.getEncoder().encode(bytes)));
    }

}
