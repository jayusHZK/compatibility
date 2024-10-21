package com.jayus.smallMyBatis.step19.logging.slf4j;

import com.jayus.smallMyBatis.step19.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Slf4jImpl
 * @Description: slf4j log 设计模式类，不做实现
 * @date: 2024/10/17 22:55
 */
public class Slf4jImpl implements Log {

    public Slf4jImpl(String clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void error(String s, Throwable e) {

    }

    @Override
    public void error(String s) {

    }

    @Override
    public void debug(String s) {

    }

    @Override
    public void trace(String s) {

    }

    @Override
    public void warn(String s) {

    }
}
