/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.gwt.junit;

import com.gargoylesoftware.htmlunit.AlertHandler;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.BrowserVersionFeatures;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.InteractivePage;
import com.gargoylesoftware.htmlunit.OnbeforeunloadHandler;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.gargoylesoftware.htmlunit.util.WebClientUtils;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.dev.shell.HostedModePluginObject;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableSet;
import com.google.gwt.thirdparty.guava.common.collect.Maps;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sourceforge.htmlunit.corejs.javascript.Context;
import net.sourceforge.htmlunit.corejs.javascript.Function;
import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//TODO: Temporary workaround for old HTMLUnitTest that is not compatible with latest JQuery 3.x
public class RunStyleHtmlUnit extends RunStyle {
    private static final Map<String, BrowserVersion> BROWSER_MAP = Maps.newHashMap();
    private static final Map<BrowserVersion, String> USER_AGENT_MAP = Maps.newHashMap();
    private static final int DEFAULT_TRIES = 1;
    private static final Set<Platform> PLATFORMS;
    private Set<BrowserVersion> browsers = new HashSet();
    private boolean developmentMode;
    private final List<Thread> threads = new ArrayList();

    private static void addBrowser(BrowserVersion browser, String userAgent) {
        BROWSER_MAP.put(browser.getNickname(), browser);
        USER_AGENT_MAP.put(browser, userAgent);
    }

    public RunStyleHtmlUnit(JUnitShell shell) {
        super(shell);
    }

    public Set<Platform> getPlatforms() {
        return PLATFORMS;
    }

    public int initialize(String args) {
        if (args == null || args.length() == 0) {
            args = "FF38";
        }

        Set<BrowserVersion> browserSet = new HashSet();
        Set<String> userAgentSet = new HashSet();
        String[] var4 = args.split(",");
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String browserName = var4[var6];
            BrowserVersion browser = (BrowserVersion)BROWSER_MAP.get(browserName);
            if (browser == null) {
                this.getLogger().log(TreeLogger.ERROR, "RunStyleHtmlUnit: Unknown browser name " + browserName + ", expected browser name: one of " + BROWSER_MAP.keySet());
                return -1;
            }

            browserSet.add(browser);
            userAgentSet.add((String)USER_AGENT_MAP.get(browser));
        }

        this.browsers = Collections.unmodifiableSet(browserSet);
        this.setUserAgents(Collections.unmodifiableSet(userAgentSet));
        this.setTries(1);
        return this.browsers.size();
    }

    public void launchModule(String moduleName) {
        Iterator var2 = this.browsers.iterator();

        while(var2.hasNext()) {
            BrowserVersion browser = (BrowserVersion)var2.next();
            String url = this.shell.getModuleUrl(moduleName);
            RunStyleHtmlUnit.HtmlUnitThread hut = this.createHtmlUnitThread(browser, url);
            TreeLogger logger = this.shell.getTopLogger();
            if (logger.isLoggable(TreeLogger.INFO)) {
                logger.log(TreeLogger.INFO, "Starting " + url + " on browser " + browser.getNickname());
            }

            hut.start();
            this.threads.add(hut);
        }

    }

    public int numBrowsers() {
        return this.browsers.size();
    }

    public boolean setupMode(TreeLogger logger, boolean developmentMode) {
        this.developmentMode = developmentMode;
        return true;
    }

    protected RunStyleHtmlUnit.HtmlUnitThread createHtmlUnitThread(BrowserVersion browser, String url) {
        return new RunStyleHtmlUnit.HtmlUnitThread(browser, url, this.shell.getTopLogger().branch(TreeLogger.SPAM, "logging for HtmlUnit thread"), this.developmentMode);
    }

    static {
        addBrowser(BrowserVersion.EDGE, "safari");
        addBrowser(BrowserVersion.FIREFOX_38, "gecko1_8");
        addBrowser(BrowserVersion.CHROME, "safari");
        //addBrowser(BrowserVersion.INTERNET_EXPLORER_8, "ie8");
        addBrowser(BrowserVersion.INTERNET_EXPLORER_11, "gecko1_8");
        PLATFORMS = ImmutableSet.of(Platform.HtmlUnitBug, Platform.HtmlUnitLayout, Platform.HtmlUnitUnknown);
    }

    private static class WebJavaScriptEngine extends JavaScriptEngine {
        private static final Log LOG = LogFactory.getLog(JavaScriptEngine.class);

        public WebJavaScriptEngine(WebClient webClient) {
            super(webClient);
        }

        protected void handleJavaScriptException(ScriptException scriptException, boolean triggerOnError) {
            InteractivePage page = scriptException.getPage();
            if (triggerOnError && page != null) {
                WebWindow window = page.getEnclosingWindow();
                if (window != null) {
                    Window w = (Window)window.getScriptableObject();
                    if (w != null) {
                        try {
                            this.triggerOnError(w, scriptException);
                        } catch (Exception var7) {
                            this.handleJavaScriptException(new ScriptException(page, var7, (String)null), false);
                        }
                    }
                }
            }

            JavaScriptErrorListener javaScriptErrorListener = this.getWebClient().getJavaScriptErrorListener();
            if (javaScriptErrorListener != null) {
                javaScriptErrorListener.scriptException(page, scriptException);
            }

            if (this.getWebClient().getOptions().isThrowExceptionOnScriptError()) {
                throw scriptException;
            } else {
                LOG.info("Caught script exception", scriptException);
            }
        }

        private void triggerOnError(Window w, ScriptException e) {
            Object o = w.getOnerror();
            if (o instanceof Function) {
                Function f = (Function)o;
                String msg = e.getMessage();
                String url = e.getPage().getUrl().toExternalForm();
                int line = e.getFailingLineNumber();
                Object[] args;
                /*if (w.getBrowserVersion().hasFeature(BrowserVersionFeatures.JS_WINDOW_ONERROR_COLUMN_ERROR_ARGUMENT)) {
                    int column = e.getFailingColumnNumber();
                    Object jsError = null;
                    if (e.getCause() instanceof JavaScriptException) {
                        jsError = ((JavaScriptException)e.getCause()).getValue();
                    }

                    args = new Object[]{msg, url, line, column, jsError};
                } else {
                    args = new Object[]{msg, url, line};
                }*/
                args = new Object[]{msg, url, line};
                f.call(Context.getCurrentContext(), w, w, args);
            }

        }
    }

    private static class HostedJavaScriptEngine extends JavaScriptEngine {
        private static final long serialVersionUID = 3594816610842448691L;
        private final TreeLogger logger;

        public HostedJavaScriptEngine(WebClient webClient, TreeLogger logger) {
            super(webClient);
            this.logger = logger;
        }

        public void initialize(WebWindow webWindow) {
            super.initialize(webWindow);
            Window window = (Window)webWindow.getScriptableObject();
            window.defineProperty("__gwt_HostedModePlugin", new HostedModePluginObject(this, this.logger), 1);
        }
    }

    protected static class HtmlUnitThread extends Thread implements AlertHandler, IncorrectnessListener, OnbeforeunloadHandler {
        private final BrowserVersion browser;
        private final boolean developmentMode;
        private final TreeLogger treeLogger;
        private final String url;
        private Object waitForUnload = new Object();

        public HtmlUnitThread(BrowserVersion browser, String url, TreeLogger treeLogger, boolean developmentMode) {
            this.browser = browser;
            this.url = url;
            this.treeLogger = treeLogger;
            this.setName("htmlUnit client thread");
            this.developmentMode = developmentMode;
        }

        public void handleAlert(Page page, String message) {
            this.treeLogger.log(TreeLogger.ERROR, "Alert: " + message);
        }

        public boolean handleEvent(Page page, String returnValue) {
            synchronized(this.waitForUnload) {
                this.waitForUnload.notifyAll();
                return true;
            }
        }

        public void notify(String message, Object origin) {
            if (!"Obsolete content type encountered: 'text/javascript'.".equals(message) && !"Obsolete content type encountered: 'application/x-javascript'.".equals(message)) {
                this.treeLogger.log(TreeLogger.WARN, message);
            }
        }

        public void run() {
            WebClient webClient = new WebClient(this.browser);
            webClient.setAlertHandler(this);
            webClient.setIncorrectnessListener(this);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setThrowExceptionOnScriptError(this.developmentMode);
            webClient.setOnbeforeunloadHandler(this);
            webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {
                public void loadScriptError(InteractivePage htmlPage, URL scriptUrl, Exception exception) {
                    HtmlUnitThread.this.treeLogger.log(TreeLogger.ERROR, "Load Script Error: " + exception, exception);
                }

                public void malformedScriptURL(InteractivePage htmlPage, String url, MalformedURLException malformedURLException) {
                    HtmlUnitThread.this.treeLogger.log(TreeLogger.ERROR, "Malformed Script URL: " + malformedURLException.getLocalizedMessage());
                }

                public void scriptException(InteractivePage htmlPage, ScriptException scriptException) {
                    HtmlUnitThread.this.treeLogger.log(TreeLogger.DEBUG, "Script Exception: " + scriptException.getLocalizedMessage() + ", line " + scriptException.getFailingLine());
                }

                public void timeoutError(InteractivePage htmlPage, long allowedTime, long executionTime) {
                    HtmlUnitThread.this.treeLogger.log(TreeLogger.ERROR, "Script Timeout Error " + executionTime + " > " + allowedTime);
                }
            });
            this.setupWebClient(webClient);

            try {
                Page page = webClient.getPage(this.url);
                webClient.waitForBackgroundJavaScriptStartingBefore(2000L);
                if (this.treeLogger.isLoggable(TreeLogger.SPAM)) {
                    this.treeLogger.log(TreeLogger.SPAM, "getPage returned " + ((HtmlPage)page).asXml());
                }

            } catch (FailingHttpStatusCodeException var3) {
                this.treeLogger.log(TreeLogger.ERROR, "HTTP request failed", var3);
            } catch (MalformedURLException var4) {
                this.treeLogger.log(TreeLogger.ERROR, "Bad URL", var4);
            } catch (IOException var5) {
                this.treeLogger.log(TreeLogger.ERROR, "I/O error on HTTP request", var5);
            }
        }

        protected void setupWebClient(WebClient webClient) {
            if (this.developmentMode) {
                JavaScriptEngine hostedEngine = new RunStyleHtmlUnit.HostedJavaScriptEngine(webClient, this.treeLogger);
                webClient.setJavaScriptEngine(hostedEngine);
            } else {
                JavaScriptEngine webEngine = new RunStyleHtmlUnit.WebJavaScriptEngine(webClient);
                webClient.setJavaScriptEngine(webEngine);
            }

            if (System.getProperty("gwt.htmlunit.debug") != null) {
                WebClientUtils.attachVisualDebugger(webClient);
            }

        }
    }
}
