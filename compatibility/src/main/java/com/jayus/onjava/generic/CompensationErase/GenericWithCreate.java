package com.jayus.onjava.generic.CompensationErase;

/**
 * @author : h zk
 * @date : 2022/8/10 15:18
 * @description :
 **/
public abstract class GenericWithCreate<T>{
    final T element;

    GenericWithCreate(){
        element = create();
    }

    abstract T create();
}
