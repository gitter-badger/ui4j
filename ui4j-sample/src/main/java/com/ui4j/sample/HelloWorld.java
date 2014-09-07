package com.ui4j.sample;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;

public class HelloWorld {

    public static void main(String[] args) {
        // get the instance of the webkit
        BrowserEngine browser = BrowserFactory.getWebKit();

        // navigate to blank page
        Page page = browser.navigate("about:blank");

        // show the browser page
        page.show();

        // append html header to the document body
        page.getDocument().getBody().append("<h1>Hello, World!</h1>");
    }
}
