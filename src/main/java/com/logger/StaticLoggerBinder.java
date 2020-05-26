package com.logger;

import org.apache.logging.slf4j.Log4jLoggerFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * Forced use of log4j2
 */
@SuppressWarnings("UnusedDeclaration")
public class StaticLoggerBinder implements LoggerFactoryBinder {
    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

    public static final StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    private StaticLoggerBinder() {
    }

    @Override
    public ILoggerFactory getLoggerFactory() {
        return new Log4jLoggerFactory();
    }

    @Override
    public String getLoggerFactoryClassStr() {
        return "org.apache.logging.slf4j.Log4jLoggerFactory";
    }
}
