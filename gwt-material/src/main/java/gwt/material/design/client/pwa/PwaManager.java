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
import gwt.material.design.client.pwa.manifest.WebManifestManager;
import gwt.material.design.client.pwa.serviceworker.IsServiceWorker;
import gwt.material.design.client.pwa.theme.BrowserThemeManager;

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
public class PwaManager {

    private static PwaManager instance = GWT.create(PwaManager.class);

    private Element headElement;

    private IsServiceWorker serviceWorker;
    private WebManifestManager webManifestManager;
    private BrowserThemeManager browserThemeManager;

    public PwaManager() {
        load();
    }

    public void load() {
        if (headElement == null) {
            headElement = Document.get().getElementsByTagName("head").getItem(0);
        }
    }

    public void reload() {
        unLoad();
        load();
    }

    public void unLoad() {
        serviceWorker.unload();
        getWebManifestManager().unload();
        getBrowserThemeManager().unload();
    }

    public PwaManager loadServiceWorker(IsServiceWorker serviceWorker) {
        this.serviceWorker = serviceWorker;
        serviceWorker.load();
        return this;
    }

    public PwaManager loadWebManifest(String url) {
        getWebManifestManager().load(url);
        return this;
    }

    public PwaManager loadThemeColor(String themeColor) {
        getBrowserThemeManager().load(themeColor);
        return this;
    }

    public Element getHeadElement() {
        return headElement;
    }

    public static PwaManager getInstance() {
        return instance;
    }

    public BrowserThemeManager getBrowserThemeManager() {
        if (browserThemeManager == null) {
            browserThemeManager = new BrowserThemeManager(this);
        }
        return browserThemeManager;
    }

    public WebManifestManager getWebManifestManager() {
        if (webManifestManager == null) {
            webManifestManager = new WebManifestManager(this);
        }
        return webManifestManager;
    }
}
