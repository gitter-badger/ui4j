package com.ui4j.spi;

import com.ui4j.api.browser.SelectorEngine;

public interface PageContext {

    EventRegistrar getEventRegistrar();

    EventManager getEventManager();

    SelectorEngine getSelectorEngine();
}
