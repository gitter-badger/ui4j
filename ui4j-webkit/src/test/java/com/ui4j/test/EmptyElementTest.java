package com.ui4j.test;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.ui4j.api.dom.Element;
import com.ui4j.api.util.Point;
import com.ui4j.webkit.proxy.WebKitEmptyElementProxy;

public class EmptyElementTest {

	@Test
	public void test() {
		WebKitEmptyElementProxy proxy = new WebKitEmptyElementProxy();
		Element element = proxy.getEmptyElement();
		Assert.assertNotNull(element);
		Assert.assertTrue(element.isEmpty());
		Assert.assertEquals("", element.getText());
		Assert.assertEquals(Collections.emptyList(), element.find("div"));
		Assert.assertEquals(0f, element.getClientHeight(), 0f);
		Assert.assertEquals(new Point(), element.getPosition());
		Assert.assertEquals(element, element.query("input"));
		Assert.assertNull(element.getRadioButton());
		Assert.assertNull(element.getCheckBox());
		Assert.assertNull(element.getOption());
		Assert.assertNull(element.getSelect());
		Assert.assertNull(element.getForm());		
	}
}
