package com.ui4j.api.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JulLogger implements com.ui4j.api.util.Logger {

    private Logger log;

    public JulLogger(Class<?> klass) {
        log = java.util.logging.Logger.getLogger(klass.getName());
    }

    @Override
    public void info(String message) {
        log.log(Level.INFO, message);
    }

    @Override
    public void error(String message) {
        log.log(Level.SEVERE, message);
    }

    @Override
    public void debug(String message) {
        log.log(Level.FINE, message);
    }

    @Override
    public void error(Throwable t) {
        log.log(Level.SEVERE, t.getMessage(), t);
    }
}
