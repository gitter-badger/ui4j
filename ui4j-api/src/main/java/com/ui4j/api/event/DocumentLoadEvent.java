package com.ui4j.api.event;

import com.ui4j.api.dom.Document;
import com.ui4j.api.dom.Window;

public class DocumentLoadEvent {

    private Window window;

    private Document document;

    public DocumentLoadEvent(Window window, Document document) {
        this.window = window;
        this.document = document;
    }

    public Window getWindow() {
        return window;
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public String toString() {
        return "DocumentLoadEvent [window=" + window + ", document=" + document
                + "]";
    }
}
