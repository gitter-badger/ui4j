package com.ui4j.webkit.browser;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.PromptData;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.ui4j.api.browser.BrowserType;
import com.ui4j.api.browser.Page;
import com.ui4j.api.dialog.AlertHandler;
import com.ui4j.api.dialog.ConfirmHandler;
import com.ui4j.api.dialog.DialogEvent;
import com.ui4j.api.dialog.PromptDialogEvent;
import com.ui4j.api.dialog.PromptHandler;
import com.ui4j.api.dom.Document;
import com.ui4j.api.dom.Window;
import com.ui4j.api.event.DocumentListener;
import com.ui4j.api.event.DocumentLoadEvent;
import com.ui4j.api.util.Logger;
import com.ui4j.api.util.LoggerFactory;
import com.ui4j.api.util.Ui4jException;
import com.ui4j.spi.JavaScriptEngine;
import com.ui4j.spi.PageView;
import com.ui4j.spi.Ui4jExecutionTimeoutException;
import com.ui4j.webkit.spi.WebKitJavaScriptEngine;

public class WebKitPage implements Page, PageView, JavaScriptEngine {

    private static final Logger LOG = LoggerFactory.getLogger(WebKitPage.class);

    private WebView webView;

    private Window window;

    private Document document;

    private Scene scene;

    private Stage stage;

    private List<DocumentDelegationListener> listeners = new ArrayList<>();

    private WebKitJavaScriptEngine engine;

    public static class SyncDocumentListener implements DocumentListener {

        private CountDownLatch latch;

        private Window window;

        private Document document;

        public SyncDocumentListener(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void onLoad(DocumentLoadEvent event) {
            this.window = event.getWindow();
            this.document = event.getDocument();
            latch.countDown();
        }

        public Document getDocument() {
            return document;
        }

        public Window getWindow() {
            return window;
        }
    }

    public static class AlertDelegationHandler implements EventHandler<WebEvent<String>> {

        private AlertHandler handler;

        public AlertDelegationHandler(AlertHandler handler) {
            this.handler = handler;
        }

        @Override
        public void handle(WebEvent<String> event) {
            handler.handle(new DialogEvent(event.getData()));
        }
    }

    public static class PromptDelegationHandler implements Callback<PromptData, String> {

        private PromptHandler handler;

        public PromptDelegationHandler(PromptHandler handler) {
            this.handler = handler;
        }

        @Override
        public String call(PromptData param) {
            return handler.handle(new PromptDialogEvent(param.getMessage(), param.getDefaultValue()));
        }
    }

    public static class ConfirmDelegationHandler implements Callback<String, Boolean> {

        private ConfirmHandler handler;

        public ConfirmDelegationHandler(ConfirmHandler handler) {
            this.handler = handler;
        }

        @Override
        public Boolean call(String message) {
            return handler.handle(new DialogEvent(message));
        }
    }

    public static class DocumentDelegationListener implements ChangeListener<Worker.State> {

        private Document document;

        private Window window;

        private DocumentListener listener;

        public DocumentDelegationListener(Window window, Document document, DocumentListener listener) {
            this.document = document;
            this.window = window;
            this.listener = listener;
        }

        @Override
        public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
            if (newState == Worker.State.SUCCEEDED) {
                DocumentLoadEvent event = new DocumentLoadEvent(window, document);
                listener.onLoad(event);
            }
        }

        public DocumentListener getDocumentListener() {
            return listener;
        }
    }

    public WebKitPage(WebView webView, WebKitJavaScriptEngine engine, Window window, Document document) {
        this.webView = webView;
        this.window = window;
        this.document = document;
        this.engine = engine;
    }

    @Override
    public void show(boolean maximized) {
        if (stage == null && scene == null) {
            stage = new Stage();
            scene = new Scene(webView, 600, 600);
            stage.setMaximized(maximized);
            stage.setScene(scene);
            stage.toFront();
            stage.show();
        }
    }

    @Override
    public void show() {
        show(false);
    }

    @Override
    public void addDocumentListener(DocumentListener listener) {
        WebEngine engine = webView.getEngine();
        DocumentDelegationListener delegationListener = new DocumentDelegationListener(window, document, listener);
        listeners.add(delegationListener);
        engine.getLoadWorker().stateProperty().addListener(delegationListener);
    }

    @Override
    public void removeListener(DocumentListener listener) {
        for (DocumentDelegationListener delegation : listeners) {
            if (delegation.getDocumentListener().equals(listener)) {
                WebEngine engine = webView.getEngine();
                engine.getLoadWorker().stateProperty().removeListener(delegation);
            }
        }
    }

    @Override
    public void close() {
        if (getStage() != null) {
            getStage().close();
        }
    }

    @Override
    public void setAlertHandler(AlertHandler handler) {
        webView.getEngine().setOnAlert(new AlertDelegationHandler(handler));
    }

    @Override
    public void setPromptHandler(PromptHandler handler) {
        webView.getEngine().setPromptHandler(new PromptDelegationHandler(handler));
    }

    @Override
    public void setConfirmHandler(ConfirmHandler handler) {
        webView.getEngine().setConfirmHandler(new ConfirmDelegationHandler(handler));
    }

    @Override
    public void waitUntilDocReady() {
        waitUntilDocReady(60, TimeUnit.SECONDS);
    }

    @Override
    public void waitUntilDocReady(int timeout, TimeUnit unit) {
        LOG.debug("Waiting document ready, timeout=" + timeout + " " + unit.toString());
        CountDownLatch latch = new CountDownLatch(1);
        DocumentListener listener = new SyncDocumentListener(latch);
        addDocumentListener(listener);
        try {
            latch.await(timeout, unit);
        } catch (InterruptedException e) {
            throw new Ui4jExecutionTimeoutException(e, timeout, unit);
        }
        removeListener(listener);
    }

    @Override
    public void wait(int milliseconds) {
        LOG.debug("Waiting " + milliseconds + " milliseconds");
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new Ui4jException(e);
        }
    }

    public WebView getWebView() {
        return webView;
    }

    @Override
    public Document getDocument() {
        return document;
    }

    @Override
    public Window getWindow() {
        return window;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void hide() {
        if (stage != null) {
            stage.hide();
        }
    }

    @Override
    public Object executeScript(String script) {
    	Object result = engine.executeScript(script);

    	String resultStr = String.valueOf(result);

    	NumberFormat formatter = NumberFormat.getInstance();
    	ParsePosition pos = new ParsePosition(0);
    	Number number = formatter.parse(resultStr, pos);
    	if (resultStr.length() == pos.getIndex()) {
    		return number;
    	}

    	return result;
    }

    @Override
    public WebView getView() {
        return webView;
    }

    @Override
    public WebEngine getEngine() {
        return engine.getEngine();
    }

    @Override
    public BrowserType getBrowserType() {
        return BrowserType.WebKit;
    }

    public String getDocumentState() {
        return String.valueOf(webView.getEngine().executeScript("document.readyState"));
    }
}
