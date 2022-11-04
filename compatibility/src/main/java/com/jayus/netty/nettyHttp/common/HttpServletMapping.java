package com.jayus.netty.nettyHttp.common;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author : h zk
 * @date : 2022/10/26 17:34
 * @description :
 **/
public class HttpServletMapping {

    private volatile  static Map<String,BaseServlet> servletMapping;

    private static final Properties PROPERTIES = new Properties();

    public static Map<String,BaseServlet> getServletMapping(){
        if (servletMapping == null) init();
        return servletMapping;
    }

    private static synchronized void init() {
        if (servletMapping == null) {
            servletMapping = new HashMap<>(32);
            try {
                String path = HttpServletMapping.class.getResource("/").getPath();
                FileInputStream fis = new FileInputStream(path + "http.properties");
                PROPERTIES.load(fis);
                for (Object item : PROPERTIES.keySet()) {
                    String key = item.toString();
                    if (key.startsWith("servlet.") && key.equals(".uri")) {
                        String uri = PROPERTIES.getProperty(key);
                        String className = PROPERTIES.getProperty(key.replace(".uri", ".class"));
                        BaseServlet baseServlet = (BaseServlet) Class.forName(className).getDeclaredConstructor().newInstance();
                        servletMapping.put(uri,baseServlet);
                    }
                }
            } catch (Exception e){
                System.out.println("请检查 http.properties 的 servlet配置字段");
                e.printStackTrace();
            }
        }
    }

}
