package com.ui4j.api.browser;

public interface BrowserEngine {

    void shutdown();

    Page navigate(String url);

    Page navigate(String url, PageConfiguration configuration);

    BrowserType getBrowserType();
}
