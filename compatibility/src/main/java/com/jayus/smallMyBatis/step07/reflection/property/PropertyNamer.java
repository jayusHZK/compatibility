package com.jayus.smallMyBatis.step07.reflection.property;

/**
 * 属性命名器
 */
public class PropertyNamer {

    private PropertyNamer() {

    }

    /**
     * 根据方法名获取属性名
     *
     * @param name
     * @return
     */
    public static String methodToProperty(String name) {
        if (name.startsWith("is")) {
            name = name.substring(2);
        } else if (name.startsWith("get") || name.startsWith("set")) {
            return name.substring(3);
        } else {
            throw new RuntimeException("Error parsing property name '" + name + "'.  Didn't start with 'is', 'get' or 'set'.");
        }
        // 对获取到的属性名进行处理 即全部转换为小写 这里分两种情况
        // 如果只有一个字母 转换为小写
        // 如果大于一个字母 第二个字母不是大写字母 那么就把第一个字母转换为小写字母
        if (name.length() == 1 || (name.length() > 1 && !Character.isUpperCase(name.charAt(1)))) {
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        return name;
    }

    public static boolean isProperty(String name) {
        return name.startsWith("is") || name.startsWith("get") || name.startsWith("set");
    }

    public static boolean isGetter(String name){
        return name.startsWith("get") ||name.startsWith("is");
    }

    public static boolean isSetter(String name){
        return name.startsWith("set");
    }

}
