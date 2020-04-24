package com.logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

class MultithreadingDemo extends Thread {

    private Integer runTimeInMins;

    public MultithreadingDemo(Integer runTimeInMins) {
        this.runTimeInMins = runTimeInMins;
    }

    private static final Logger logger = LoggerFactory.getLogger(MultithreadingDemo.class);

    Map<String, String> context = MDC.getCopyOfContextMap();

    public void run() {

        Long startTime = System.currentTimeMillis();
        try {
            Random random = new Random();

            MDC.setContextMap(context);
            MDC.put("requestId", UUID.randomUUID().toString());
            MDC.put("domain", UUID.randomUUID().toString().substring(0,10));

            while(true) {
                //TODO: Add Random string
                logger.info("Thread " +
                        Thread.currentThread().getId() +
                        ": " + RandomStringUtils.randomAlphabetic(Math.abs(random.nextInt() % 100)));

                Long diff = System.currentTimeMillis() - startTime;

                if (diff > (runTimeInMins * 60 * 1000)) {
                    break;
                }
            }

            logger.error("test error");
            logger.error("new exception", new NumberFormatException("test"));
        } catch (Exception e) {
            logger.error("exception", e);
        }
    }
}