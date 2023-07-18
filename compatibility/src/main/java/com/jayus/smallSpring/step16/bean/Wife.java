package com.jayus.smallSpring.step16.bean;

/**
 * @author : h zk
 * @date : 2023/7/18 10:29
 * @description :
 **/
public class Wife {

    private Husband husband;

    private IMother mother;

    public String queryHusband(){
        return "wife.husband、Mother.callMother：" + mother.callMother();
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }
}
