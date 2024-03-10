package com.jayus.designPattern.templateMethod;

/**
 * 做饭
 */
public abstract class Cooking {

    // 买菜 买什么菜
    abstract void buyFood();

    // 处理菜 切、捣……
    abstract void handleFood();

    // 做饭 煎炒煮……
    abstract void cookFood();

    // 收拾 洗碗……
    abstract void clear();

    boolean isClear(){
        return true;
    };

    // 整个做饭过程
    final void cook(){
        this.buyFood();
        this.handleFood();
        this.cookFood();
        if (this.isClear())
            this.clear();
    }
}
