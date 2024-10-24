package com.jayus.rpc_study.step01;

/**
 * @ClassName ConsumerConfig
 * @Description:
 * @date: 2024/10/22 13:00
 */
public class ConsumerConfig<T> {

    private String nozzle; // 接口

    private String alias; // 别名

    protected synchronized T refer(){
        System.out.format("消费者信息=> [接口：%s] [别名：%s] \r\n", nozzle, alias);
        return null;
    }

}
