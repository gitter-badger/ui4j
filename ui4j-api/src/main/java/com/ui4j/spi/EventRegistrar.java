package com.ui4j.spi;

import java.util.Set;

import com.ui4j.api.dom.EventTarget;
import com.ui4j.api.event.EventHandler;

public interface EventRegistrar {

    void register(EventTarget target, String event, EventHandler handler);

    void unregister(EventTarget target, String event, EventHandler handler);

    Set<EventHandler> getHandlers();
}
