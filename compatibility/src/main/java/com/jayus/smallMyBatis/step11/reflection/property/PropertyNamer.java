package com.jayus.smallMyBatis.step11.reflection.property;

import java.util.Locale;

/**
 * @ClassName PropertyNamer
 * @Description: 属性命名器
 * @date: 2024/5/14 16:03
 */
public class PropertyNamer {

    public PropertyNamer() {
    }

    public static String methodToProperty(String name) {
        if (name.startsWith("is")){
            name =  name.substring(2);
        } else if (name.startsWith("get") || name.startsWith("set")){
            name = name.substring(3);
        } else {
            throw new RuntimeException("Error parsing property name '" + name + "'.  Didn't start with 'is', 'get' or 'set'.");
        }

        if (name.length() == 1 || (name.length() > 1 && !Character.isUpperCase(name.charAt(1)))) {
            name = name.substring(0,1).toLowerCase(Locale.ENGLISH) + name.substring(1);
        }
        return name;
    }

    /**
     * 判断开头 get/is/set
     * @param name
     * @return
     */
    public static boolean isProperty(String name){
        return name.startsWith("get") || name.startsWith("set") || name.startsWith("is");
    }

    /**
     * 是否为 get
     * @param name
     * @return
     */
    public static boolean isGetter(String name) {
        return name.startsWith("get") ||  name.startsWith("is");
    }

    /*
    是否为 set
     */
    public static boolean isSetter(String name) {
        return name.startsWith("set");
    }
}
