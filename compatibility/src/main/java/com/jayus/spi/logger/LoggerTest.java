package com.jayus.spi.logger;

public class LoggerTest {

    public static void main(String[] args) {
        LoggerService log = LoggerService.getService();
        log.info("abcd");
        log.debug("abcd");
    }

}
