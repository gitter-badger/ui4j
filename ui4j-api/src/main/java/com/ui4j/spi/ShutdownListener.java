package com.ui4j.spi;

import com.ui4j.api.browser.BrowserEngine;

public interface ShutdownListener {

    void onShutdown(BrowserEngine engine);
}
