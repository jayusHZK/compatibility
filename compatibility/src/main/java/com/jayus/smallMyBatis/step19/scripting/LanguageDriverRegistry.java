package com.jayus.smallMyBatis.step19.scripting;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LanguageDriverRestry
 * @Description: 脚本语言注册器
 * @date: 2024/10/18 12:46
 */
public class LanguageDriverRegistry {

    private final Map<Class<?>,LanguageDriver> LANGUAGE_DRIVER_MAP = new HashMap<>();

    private Class<?> defaultDriverClass = null;

    public void registry(Class<?> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("null is not a valid Language Driver");
        }
        if (!LanguageDriver.class.isAssignableFrom(cls)) {
            throw new RuntimeException(cls.getName() + " does not implements " + LanguageDriver.class.getName());
        }
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

    public LanguageDriver getDriver(Class<?> cls) {
        return LANGUAGE_DRIVER_MAP.get(cls);
    }

    public LanguageDriver getDefaultDriver(){
        return getDriver(getDefaultDriverClass());
    }

    public Class<?> getDefaultDriverClass() {
        return defaultDriverClass;
    }

    public void setDefaultDriverClass(Class<?> defaultDriverClass) {
        registry(defaultDriverClass);
        this.defaultDriverClass = defaultDriverClass;
    }
}
