package com.ui4j.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.BrowserType;
import com.ui4j.api.browser.Page;

public class JavasciptEngineTest {

	private static Page page;

    @BeforeClass public static void beforeTest() {
        String url = ElementTest.class.getResource("/TestPage.html").toExternalForm();
        BrowserEngine browser = BrowserFactory.getBrowser(BrowserType.WebKit);
        page = browser.navigate(url);
    }

    @Test public void testBoolean() {
    	Object executeScript = page.executeScript("1 === 1");
    	Assert.assertEquals(Boolean.class, executeScript.getClass());
    }

    @Test public void testString() {
    	Object result = page.executeScript("'test string'");
    	Assert.assertEquals(String.class, result.getClass());
    }

    @Test public void testLong() {
    	Object result = page.executeScript("'20'");
    	Assert.assertEquals(Long.class, result.getClass());
    }

    @Test public void testDouble() {
    	Object result = page.executeScript("'20.20'");
    	Assert.assertEquals(Double.class, result.getClass());
    }

    @Test public void testNull() {
    	Object result = page.executeScript("null");
    	Assert.assertNull(result);
    }

    @Test public void testUndefined() {
    	Object result = page.executeScript("undefined");
    	Assert.assertEquals("undefined", result);
    }
}
