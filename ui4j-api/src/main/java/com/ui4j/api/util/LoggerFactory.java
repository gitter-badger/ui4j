package com.ui4j.api.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoggerFactory {

    private static Class<?> slf4jFactory;

    static {
        slf4jFactory = null;
        try {
            slf4jFactory = Thread.currentThread().getContextClassLoader().loadClass("org.slf4j.LoggerFactory");
        } catch (ClassNotFoundException e) {
            // ignore
        }
    }

    public static Logger getLogger(Class<?> klass) {
        if (!klass.getPackage().getName().startsWith("com.ui4j")) {
            return null;
        }
        if (slf4jFactory != null) {
            Method m = null;
            try {
                m = slf4jFactory.getDeclaredMethod("getLogger", Class.class);
            } catch (NoSuchMethodException | SecurityException e) {
                throw new Ui4jException(e);
            }
            Object slf4jLogger = null;
            try {
                slf4jLogger = m.invoke(null, klass);
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new Ui4jException(e);
            }
            return new Slf4jLogger(slf4jLogger);
        } else {
            return new JulLogger(klass);
        }
    }
}
