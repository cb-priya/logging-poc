package com.logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

class MultithreadingDemo extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(MultithreadingDemo.class);

    Map<String, String> context = MDC.getCopyOfContextMap();

    public void run() {
        try {
            Random random = new Random();

            MDC.setContextMap(context);
            MDC.put("requestId", UUID.randomUUID().toString());
            MDC.put("domain", UUID.randomUUID().toString().substring(0,10));

            for(int i = 0; i < 100000; i++) {
                //TODO: Add Random string
                logger.info("Thread " +
                        Thread.currentThread().getId() +
                        ": " + RandomStringUtils.randomAlphabetic(Math.abs(random.nextInt()%100)));
                Thread.sleep();
            }

            logger.error("test error");
            logger.error("new exception", new NumberFormatException("test"));
        } catch (Exception e) {
            logger.error("exception", e);
        }
    }
}