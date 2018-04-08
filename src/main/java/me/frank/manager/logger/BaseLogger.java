package me.frank.manager.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by frank on 2017/7/18.
 */
public final class BaseLogger {

    public static void info(String message) {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        String className = stack[2].getClassName();
        String methodName = stack[2].getMethodName();
        int lineNumber = stack[2].getLineNumber();
        Logger logger = LoggerFactory.getLogger(className);
        logger.info(methodName + "[" + lineNumber + "] :" + message);
    }

    public static void debug(String message) {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        String className = stack[2].getClassName();
        String methodName = stack[2].getMethodName();
        int lineNumber = stack[2].getLineNumber();
        Logger logger = LoggerFactory.getLogger(className);
        logger.debug(methodName + "[" + lineNumber + "] :" + message);
    }

    public static void error(String message) {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        String className = stack[2].getClassName();
        String methodName = stack[2].getMethodName();
        int lineNumber = stack[2].getLineNumber();
        Logger logger = LoggerFactory.getLogger(className);
        logger.error(methodName + "[" + lineNumber + "] :" + message);
    }

    public static void error(String message,Throwable throwable) {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        String className = stack[2].getClassName();
        String methodName = stack[2].getMethodName();
        int lineNumber = stack[2].getLineNumber();
        Logger logger = LoggerFactory.getLogger(className);
        logger.error(methodName + "[" + lineNumber + "] :" + message,throwable);
    }



}
