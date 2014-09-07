Ui4j
====

Ui4j is a Web automation library for Java. Its a thin wrapper library arround JavaFx WebKit Engine.

It could be used for Web Automation and Web Testing.

Supported Java Versions
-----------------------

Oracle Java 8

Both JRE and JDK is suitable to use this library.

Licensing
---------
Ui4j released uner the terms of the MIT License (MIT).

You are free to use any Ui4j project in any other project (even commercial projects) as long as the copyright header is left intact.


Stability
---------
This library is not suitable run for productions system, but you could use for testing purpose.

Wait until 1.0 release for using production systems.


Supported Platforms
-------------------
Ui4j tested under Windows 8.1 and Ubuntu 14.04 and probably will be run under all platforms thats supported by Java 8.


Headless Mode
-------------
Ui4j could be run under headless mode with using Xfvb.


Sample Code
-----------

Here is a very basic sample to show hello, world! message.
Check the ui4j-sample project for more sample code snippets.

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
