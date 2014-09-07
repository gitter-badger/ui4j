package com.ui4j.api.util;

class Slf4jLogger implements Logger {

    private final org.slf4j.Logger log;

    public Slf4jLogger(Object log) {
        this.log = (org.slf4j.Logger) log;
    }

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void error(String message) {
        log.error(message);
    }

    @Override
    public void debug(String message) {
        log.debug(message);
    }

    @Override
    public void error(Throwable t) {
        log.error(t.getMessage(), t);
    }
}
