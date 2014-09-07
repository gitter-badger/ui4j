package com.ui4j.api.util;

public interface Logger {

    void info(String message);

    void error(String message);

    void debug(String message);

    void error(Throwable t);
}
