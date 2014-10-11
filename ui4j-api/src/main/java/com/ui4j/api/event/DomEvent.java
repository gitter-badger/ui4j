package com.ui4j.api.event;

import com.ui4j.api.dom.Element;

public interface DomEvent {

    String getType();

    Element getTarget();

    Element getCurrentTarget();

    int getOffsetX();

    int getOffsetY();

    int getClientX();

    int getClientY();
}
