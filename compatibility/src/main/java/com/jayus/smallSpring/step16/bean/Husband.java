package com.jayus.smallSpring.step16.bean;

/**
 * @author : h zk
 * @date : 2023/7/18 10:28
 * @description :
 **/
public class Husband {

    private Wife wife;

    public String queryWife(){
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
