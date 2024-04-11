package com.jayus.smallMyBatis.step10.scripting;

import java.util.HashMap;
import java.util.Map;

/**
 * 脚本语言注册器
 */
public class LanguageDriverRegistry {

    private final Map<Class<?>,LanguageDriver> LANGUAGE_DRIVER_MAP =new HashMap<>();

    private Class<?> defaultDriverClass = null;

    public void regist(Class<?> cls){
        if (cls == null){
            throw new IllegalArgumentException("null is not a valid Language Driver");
        }
        if (!LanguageDriver.class.isAssignableFrom(cls)){
            throw new RuntimeException(cls.getName() + " does not implements " + LanguageDriver.class.getName());
        }
        // 如果没注册过 再去注册
        LanguageDriver driver = LANGUAGE_DRIVER_MAP.get(cls);
        if (driver == null) {
            try {
                driver = (LanguageDriver) cls.newInstance();
                LANGUAGE_DRIVER_MAP.put(cls,driver);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load language driver for " + cls.getName(), e);
            }
        }
    }

    public LanguageDriver getDriver(Class<?> cls){
        return LANGUAGE_DRIVER_MAP.get(cls);
    }

    public LanguageDriver getDefaultDriver(){
        return getDriver(getDefaultDriverClass());
    }

    public Class<?> getDefaultDriverClass(){
        return defaultDriverClass;
    }

    public void setDefaultDriverClass(Class<?> defaultDriverClass) {
        regist(defaultDriverClass);
        this.defaultDriverClass = defaultDriverClass;
    }

}
