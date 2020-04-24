package com.logger;

//import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.LoggerContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.Random;
import java.util.UUID;


public class LoggerTestMultiThreading {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTestMultiThreading.class);
    public static void main(String[] args) {
        MDC.put("domain", "helloworld");
        logger.info("Thread " +
                Thread.currentThread().getId() +
                " is running");

        Integer runTimeinMins = 5;
        if(args.length > 0) {
            String time = args[0];
            runTimeinMins = Integer.valueOf(time);
        }


        //setLogLevel(Level.ERROR, "com.logger");
        for (int i = 0; i < 10; i++) {
            MultithreadingDemo object = new MultithreadingDemo(runTimeinMins);
            object.start();
        }
    }

    /*private static void setLogLevel(Level logLevel, String packageName) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        ch.qos.logback.classic.Logger logger = loggerContext.getLogger(packageName);
        System.out.println(packageName + " current logger level: " + logger.getLevel());
        System.out.println(" You entered: " + logLevel);

        logger.setLevel(logLevel);
    }*/
}

