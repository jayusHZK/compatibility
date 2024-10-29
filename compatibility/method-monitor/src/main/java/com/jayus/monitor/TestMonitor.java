package com.jayus.monitor;

import com.jayus.monitor.service.MyService;

/**
 * @ClassName TestMonitor
 * @Description:
 * @date: 2024/10/29 19:56
 */
public class TestMonitor {

    public static void main(String[] args) {
        MyService service = new MyService();
        service.a("Hello");
    }

}
