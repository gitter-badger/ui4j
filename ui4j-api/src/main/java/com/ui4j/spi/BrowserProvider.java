package com.ui4j.spi;

import com.ui4j.api.browser.BrowserType;
import com.ui4j.api.browser.BrowserEngine;

public interface BrowserProvider {

    BrowserType getBrowserType();

    BrowserEngine create();

    void setShutdownListener(ShutdownListener listener);
}
