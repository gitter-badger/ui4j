package com.ui4j.api.browser;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.ui4j.api.util.Logger;
import com.ui4j.api.util.LoggerFactory;
import com.ui4j.spi.BrowserProvider;
import com.ui4j.spi.ShutdownListener;

public final class BrowserFactory {

    private static final Logger LOG = LoggerFactory.getLogger(BrowserFactory.class);

    private static final ConcurrentMap<BrowserType, BrowserEngine> BROWSERS = new ConcurrentHashMap<>();

    private static final ShutdownListener SHUTDOWN_LISTENER = new ShutdownAdapter();

    private static class ShutdownAdapter implements ShutdownListener {

        @Override
        public void onShutdown(BrowserEngine engine) {
            for (Map.Entry<BrowserType, BrowserEngine> next : BROWSERS.entrySet()) {
                if (next.getValue().equals(engine)) {
                    BROWSERS.remove(next.getKey());
                }
            }
        }
    }

    private BrowserFactory() {
    }

    public static synchronized BrowserEngine getBrowser(BrowserType type) {
        if (BROWSERS.containsKey(type)) {
            return BROWSERS.get(type);
        }
        ServiceLoader<BrowserProvider> loader = ServiceLoader.load(BrowserProvider.class);
        Iterator<BrowserProvider> iter = loader.iterator();
        while (iter.hasNext()) {
            BrowserProvider provider = iter.next();
            provider.setShutdownListener(SHUTDOWN_LISTENER);
            if (type.equals(provider.getBrowserType())) {
                LOG.info("Initializing " + type);
                BrowserEngine browser = provider.create();
                BROWSERS.put(type, browser);
            }
        }
        return BROWSERS.get(type);
    }

    public static synchronized BrowserEngine getWebKit() {
        return getBrowser(BrowserType.WebKit);
    }
}
