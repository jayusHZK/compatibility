package com.jayus.smallMyBatis.step19.logging;

/**
 * @ClassName Log
 * @Description: 日志接口
 * @date: 2024/10/17 22:54
 */
public interface Log {

    boolean isDebugEnabled();

    boolean isTraceEnabled();

    void error(String s, Throwable e);

    void error(String s);

    void debug(String s);

    void trace(String s);

    void warn(String s);

}
