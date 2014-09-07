package com.ui4j.webkit;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserType;
import com.ui4j.spi.BrowserProvider;
import com.ui4j.spi.ShutdownListener;

public class WebKitBrowserProvider implements BrowserProvider {

    private ShutdownListener shutdownListener;

    @Override
    public BrowserType getBrowserType() {
        return BrowserType.WebKit;
    }

    @Override
    public BrowserEngine create() {
        return new WebKitBrowser(shutdownListener);
    }

    @Override
    public void setShutdownListener(ShutdownListener listener) {
        this.shutdownListener = listener;
    }
}
