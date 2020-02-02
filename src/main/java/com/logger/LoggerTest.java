package com.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    public static void main(String[] args) {

        logger.debug("Hello from Logback");

        logger.debug("getNumber() : {}", getNumber());

        logger.error("getNumber() : {}", getNumber());

        logger.info("getNumber2() : {}", getNumber());





    }

    static int getNumber() {
        return 5;
    }

}