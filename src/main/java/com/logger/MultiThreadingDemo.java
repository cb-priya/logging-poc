package com.logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.*;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

class MultithreadingDemo extends Thread {

    private Integer runTimeInMins;
    private Boolean logExceptions;

    public MultithreadingDemo(Integer runTimeInMins, Boolean logExceptions) {
        this.runTimeInMins = runTimeInMins;
        this.logExceptions = logExceptions;
    }

    private static final Logger logger = LoggerFactory.getLogger(MultithreadingDemo.class);

    Map<String, String> context = MDC.getCopyOfContextMap();

    public void run() {

        Long startTime = System.currentTimeMillis();
        try {
            Random random = new Random();

            MDC.setContextMap(context);
            MDC.put("request_id", UUID.randomUUID().toString());
            MDC.put("domain", UUID.randomUUID().toString().substring(0,10));

            Marker marker = MarkerFactory.getMarker("TEST");
            Marker onemore = MarkerFactory.getMarker("ONEMORE");
            marker.add(onemore);

            while(true) {
                //TODO: Add Random string
                logger.info(marker, "Thread " +
                        Thread.currentThread().getId() +
                        ": " + RandomStringUtils.randomAlphabetic(10 + Math.abs(random.nextInt() % 100)));

                Long diff = System.currentTimeMillis() - startTime;

                //Thread.sleep(10);

                if(this.logExceptions) {
                    logException(0);
                }

                if (diff > (runTimeInMins * 60 * 1000)) {
                    break;
                }
            }

            logger.error("test error");
            //logger.error("new exception", new NumberFormatException("test"));
        } catch (Exception e) {
            logger.error("exception", e);
        }
    }

    private void logException(int i) {
        if(i == 20) {
            logger.error("this is an error", new NumberFormatException());
            return;
        }
        logException(++i);
    }


}