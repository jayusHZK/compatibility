package com.jayus.rpc_study.step03.config;

/**
 * @ClassName ConsumerConfig
 * @Description:
 * @date: 2024/10/22 20:48
 */
public class ConsumerConfig<T> {

    protected String nozzle; // 接口

    protected String alias; // 别名

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
