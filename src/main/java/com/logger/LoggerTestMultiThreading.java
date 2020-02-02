package com.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

class MultithreadingDemo extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(MultithreadingDemo.class);

    public void run() {
        try {
            MDC.put("ThreadNumber", ""+ Thread.activeCount() );
            logger.info("Thread " +
                    Thread.currentThread().getId() +
                    " is running");

        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}

public class LoggerTestMultiThreading {
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            MultithreadingDemo object = new MultithreadingDemo();
            object.start();
        }
    }
}

