package com.jayus.designPattern.prototype;

/**
 * @ClassName Product
 * @Description:
 * @date: 2024/10/27 18:52
 */
public interface Product extends Cloneable {

    public void use(String s);

    public Product createClone();

}
