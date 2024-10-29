package com.jayus.designPattern.prototype;

import java.util.HashMap;

/**
 * @ClassName Manager
 * @Description:
 * @date: 2024/10/27 18:53
 */
public class Manager {

    private HashMap<String,Product> showcase = new HashMap();

    public void register(String name,Product proto) {
        showcase.put(name,proto);
    }

    public Product create(String protoname){
        Product p =  showcase.get(protoname);
        return p.createClone();
    }

}
