package com.ui4j.sample;

import com.ui4j.api.browser.BrowserFactory;

public class HackerNews {

    public static void main(String[] args) {
        BrowserFactory
            .getWebKit()
            .navigate("https://news.ycombinator.com")
            .getDocument()
            .queryAll(".title a")
            .forEach(e -> {
                System.out.println(e.getText());
            });
    }
}
