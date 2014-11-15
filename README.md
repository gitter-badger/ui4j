Ui4j
====

[![Download](http://img.shields.io/badge/download-latest--jar-orange.svg)](https://repo1.maven.org/maven2/com/ui4j/ui4j-all/1.0.0/ui4j-all-1.0.0.jar) [![Version](http://img.shields.io/badge/version-1.0.0-green.svg)](https://github.com/ui4j/ui4j) [![License](http://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)


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

This library is suitable for use in production systems.


If you have found a defect or you want to request a feature enhancement an issue report is the way to bring the attention to the Ui4j Community.


Integration with Maven
----------------------
[![Maven](http://img.shields.io/maven-central/v/com.ui4j/ui4j-all.svg)](http://search.maven.org/#search%7Cga%7C1%7Cui4j)

To use the official release of Ui4j, please use the following snippet in your pom.xml

```xml
    <dependency>
        <groupId>com.ui4j</groupId>
        <artifactId>ui4j-all</artifactId>
        <version>1.0.0</version>
    </dependency>
```

Using Ui4j without Maven
------------------------
If you use Ui4j without Maven, download [pre built jar file](https://repo1.maven.org/maven2/com/ui4j/ui4j-all/1.0.0/ui4j-all-1.0.0.jar).

Supported Platforms
-------------------

Ui4j has been tested under Windows 8.1 and Ubuntu 14.04, but should work on any platform where a Java 8 JRE or JDK is available.


Headless Mode
-------------

Ui4j can be run in "headless" mode using Xfvb.

Logging
-------
Both simple logger for java (SLF4J) and Java utility logger (JUL) is supported.
If slf4j is available on classpath com.ui4j.api.util.LoggerFactory use slf4j else java utility logger is used.

CSS Selector Engine
-------------------
Ui4j use W3C selector engine which is default selector engine of WebKit. Alternatively [Sizzle](http://http://sizzlejs.com) selector engine might be used.
Sizzle is the css selector engine of JQuery and it supports extra selectors like :has(div), :text, containts(text) etc.
Check [Sizzle.java](https://github.com/ui4j/ui4j/blob/master/ui4j-sample/src/main/java/com/ui4j/sample/Sizzle.java) for using sizzle with Ui4j.


Usage Examples
--------------

Here is a very basic sample program that uses Ui4j to display a web page with a "hello, world!" message. See the [ui4j-sample](https://github.com/ui4j/ui4j/tree/master/ui4j-sample/src/main/java/com/ui4j/sample) project for more sample code snippets.

```java
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

Here is another sampe code that list all front page news from Hacker News.

```java
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
```
