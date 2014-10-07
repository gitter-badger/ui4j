Ui4j
====

Ui4j is a web-automation library for Java. It is a thin wrapper library around the JavaFx WebKit Engine, and can be used for automating the use of web pages and for testing web pages.


Supported Java Versions
-----------------------

Oracle Java 8.

Both the JRE and the JDK are suitable for use with this library.


Licensing
---------

Ui4j is released under the terms of the MIT License (MIT).

You are free to use Ui4j or any of its constituent parts in any other project (even commercial projects) so long as its copyright headers are left intact.


Stability
---------

This library is not currently considered suitable for use in production systems, but can be used for testing purposes.

Please wait until the 1.0 release before using for production systems.


Supported Platforms
-------------------

Ui4j has been tested under Windows 8.1 and Ubuntu 14.04, but should work on any platform where a Java 8 JRE or JDK is available.


Headless Mode
-------------

Ui4j can be run in "headless" mode using Xfvb.


Sample Code
-----------

Here is a very basic sample program that uses Ui4j to display a web page with a "hello, world!" message. See the ui4j-sample project for more sample code snippets.

```
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
```
