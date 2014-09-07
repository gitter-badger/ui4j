package com.ui4j.api.browser;

public class PageConfiguration {

    private SelectorType selectorEngine = SelectorType.W3C;

    private String userAgent;

    public SelectorType getSelectorEngine() {
        return selectorEngine;
    }

    public void setSelectorEngine(SelectorType selectorEngine) {
        this.selectorEngine = selectorEngine;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

	@Override
	public String toString() {
		return "PageConfiguration [selectorEngine=" + selectorEngine
				+ ", userAgent=" + userAgent + "]";
	}
}
