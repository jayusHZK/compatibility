package com.jayus.rpc_study.step03.config;

/**
 * @ClassName ProviderConfig
 * @Description:
 * @date: 2024/10/22 20:48
 */
public class ProviderConfig {

    protected String nozzle;

    protected String ref;
    protected String alias;

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
