package com.jayus.smallMyBatis.step19.executor.keygen;

import com.jayus.smallMyBatis.step19.executor.Executor;
import com.jayus.smallMyBatis.step19.mapping.MappedStatement;

import java.sql.Statement;

/**
 * @ClassName NoKeyGenerator
 * @Description: 不用键值生成器
 * @date: 2024/10/20 14:56
 */
public class NoKeyGenerator implements KeyGenerator{

    @Override
    public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {

    }

    @Override
    public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {

    }
}
