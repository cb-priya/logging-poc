package com.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.Random;
import java.util.UUID;


public class LoggerTestMultiThreading {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTestMultiThreading.class);
    public static void main(String[] args) throws Exception {
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

        /*while(true) {
            updateLogLevels(Level.OFF);
            Thread.sleep(5000);
            logger.info("1");
            updateLogLevels(Level.INFO);
            Thread.sleep(5000);
            logger.info("2");
        }*/
    }

    /*private static void setLogLevel(Level logLevel, String packageName) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        ch.qos.logback.classic.Logger logger = loggerContext.getLogger(packageName);
        System.out.println(packageName + " current logger level: " + logger.getLevel());
        System.out.println(" You entered: " + logLevel);

        logger.setLevel(logLevel);
    }*/

    public static void updateLogLevels(Level level) throws Exception {
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        Configuration config = loggerContext.getConfiguration();
        Map<String, LoggerConfig> loggerConfigMap = config.getLoggers(); ////logger name for root is coming as empty  - to check TODO
        for(String configKey : loggerConfigMap.keySet()) {
            LoggerConfig loggerConfig = loggerConfigMap.get(configKey);
            loggerConfig.setLevel(level);
        }
        loggerContext.updateLoggers();
    }

    private static Level getCorrespondingLogLevel(String logLevel) {
        switch (logLevel) {
            case "ALL":
            case "TRACE":
            case "DEBUG":
            case "INFO":
                return  Level.INFO;
            case "WARN":
                return  Level.WARN;
            case "ERROR":
                return Level.ERROR;
            case "FATAL":
                return Level.FATAL;
            case "OFF":
            default:
                return Level.OFF;
        }
    }
}

