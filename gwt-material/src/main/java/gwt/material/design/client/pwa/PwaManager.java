/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
package gwt.material.design.client.pwa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import gwt.material.design.client.base.JsLoader;
import gwt.material.design.client.js.Navigator;
import gwt.material.design.client.pwa.base.PwaFeature;
import gwt.material.design.client.pwa.manifest.WebManifestManager;
import gwt.material.design.client.pwa.manifest.js.AppInstaller;
import gwt.material.design.client.pwa.serviceworker.ServiceWorkerManager;
import gwt.material.design.client.pwa.theme.BrowserThemeManager;
import gwt.material.design.jquery.client.api.Functions;

//@formatter:off

/**
 * Progressive Web Apps (PWA) are reliable, fast, and engaging, <br/>
 * although there are many things that can take a PWA from a baseline to exemplary experience. <br/><br/>
 * PWA supports: <br/>
 * 1. Installable @see(https://gwtmaterialdesign.github.io/gwt-material-demo/pwa/) <br/>
 * - Using Web App Manifest you can load how your app will behave like native web app by adding it to your homescreen.<br/><br/>
 * 2. Service Worker  <br/>
 * - Enables offline support for your progressive web app.<br/><br/>
 * 3. Push Notifications <br/>
 * - A rich notification service provided by google.
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#pwa">Progressive Web Apps</a>
 */
//@formatter:on
public class PwaManager implements JsLoader {

    private static PwaManager instance = GWT.create(PwaManager.class);

    private Element headElement;

    private ServiceWorkerManager serviceWorkerManager;
    private WebManifestManager webManifestManager;
    private BrowserThemeManager browserThemeManager;
    private AppInstaller appInstaller;

    @Override
    public void load() {
        loadFeature(getServiceWorkerManager());
        loadFeature(getBrowserThemeManager());
        loadFeature(getWebManifestManager());
    }

    protected void loadFeature(PwaFeature feature) {
        if (feature != null) {
            feature.load();
        }
    }

    @Override
    public void unload() {
        unloadFeature(getServiceWorkerManager());
        unloadFeature(getWebManifestManager());
        unloadFeature(getBrowserThemeManager());
    }

    protected void unloadFeature(PwaFeature feature) {
        if (feature != null) {
            feature.unload();
        }
    }

    public void reload() {
        unload();
        load();
    }

    /**
     * Load the service worker manager in order to register the service-worker.js
     * and start up the caching strategy.
     */
    public PwaManager setServiceWorker(ServiceWorkerManager serviceWorkerManager) {
        this.serviceWorkerManager = serviceWorkerManager;
        return this;
    }

    /**
     * This will load {@link ServiceWorkerManager} providing the service worker
     * url to initialize the service worker.
     */
    public PwaManager setServiceWorker(String serviceWorkerUrl) {
        this.serviceWorkerManager = new ServiceWorkerManager(serviceWorkerUrl);
        return this;
    }

    /**
     * Load the manifest file provided in the parameter to specify
     * app's Installable Feature on native apps browser.
     */
    public PwaManager setWebManifest(String manifestUrl) {
        webManifestManager = new WebManifestManager(this, manifestUrl);
        return this;
    }

    /**
     * Load the Browser theme color for Site's UI Look
     */
    public PwaManager setThemeColor(String themeColor) {
        browserThemeManager = new BrowserThemeManager(this, themeColor);
        return this;
    }

    /**
     * Will prompt a user the "Add to Homescreen" feature
     *
     * @param callback A callback function after the method has been executed.
     */
    public void installApp(Functions.Func callback) {
        if (isPwaSupported()) {
            appInstaller = new AppInstaller(callback);
            appInstaller.prompt();
        }
    }

    public static PwaManager getInstance() {
        return instance;
    }

    public Element getHeadElement() {
        if (headElement == null) {
            headElement = Document.get().getElementsByTagName("head").getItem(0);
        }
        return headElement;
    }

    public static boolean isPwaSupported() {
        return Navigator.serviceWorker != null;
    }

    public BrowserThemeManager getBrowserThemeManager() {
        return browserThemeManager;
    }

    public WebManifestManager getWebManifestManager() {
        return webManifestManager;
    }

    public ServiceWorkerManager getServiceWorkerManager() {
        return serviceWorkerManager;
    }
}
