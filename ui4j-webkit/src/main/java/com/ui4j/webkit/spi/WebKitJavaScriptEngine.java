package com.ui4j.webkit.spi;

import com.ui4j.spi.JavaScriptEngine;

import javafx.scene.web.WebEngine;

public class WebKitJavaScriptEngine implements JavaScriptEngine {

    private WebEngine engine;

    public WebKitJavaScriptEngine(WebEngine engine) {
        this.engine = engine;
    }

    @Override
    public WebEngine getEngine() {
        return engine;
    }

    @Override
    public Object executeScript(String script) {
        return engine.executeScript(script);
    }
}
