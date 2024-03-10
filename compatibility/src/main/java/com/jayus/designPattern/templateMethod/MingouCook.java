package com.jayus.designPattern.templateMethod;

/**
 * 某人的做饭流程
 */
public class MingouCook extends Cooking{

    @Override
    void buyFood() {
        System.out.println("买菜：肉、包菜……");
    }

    @Override
    void handleFood() {
        System.out.println("处理菜：搅肉、撕包菜……");
    }

    @Override
    void cookFood() {
        System.out.println("烹饪菜：抄肉、煮包菜……");
    }

    @Override
    void clear() {
       // System.out.println("清理菜：洗碗、拖地……");
    }

    @Override
    boolean isClear() {
        return false;
    }
}
