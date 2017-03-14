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
import gwt.material.design.client.pwa.serviceworker.Navigator;

//@formatter:off

/**
 * Progressive Web Apps (PWA) are reliable, fast, and engaging, <br/>
 * although there are many things that can take a PWA from a baseline to exemplary experience. <br/><br/>
 * PWA supports: <br/>
 * 1. Installable @see(https://gwtmaterialdesign.github.io/gwt-material-demo/pwa/) <br/>
 * - Using Web App Manifest you can configure how your app will behave like native web app by adding it to your homescreen.<br/><br/>
 * 2. Service Worker  <br/>
 * - Enables offline support for your progressive web app.<br/><br/>
 * 3. Push Notifications <br/>
 * - A rich notification service provided by google.
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#pwa">Progressive Web Apps</a>
 */
//@formatter:on
public class PwaManager implements HasPwaFeature {

    private static PwaManager instance = GWT.create(PwaManager.class);
    public static boolean initialized = false;

    @Override
    public void configure() {
        if (!initialized) {
            String pwaManifest = System.getProperty("manifest");
            if (pwaManifest != null && !pwaManifest.isEmpty()) {
                setupManifest(pwaManifest);
            }

            String serviceWorker = System.getProperty("serviceWorker");
            if (serviceWorker != null && !serviceWorker.isEmpty()) {
                setupServiceWorker(serviceWorker);
            }

            String metaThemeColor = System.getProperty("metaThemeColor");
            if (metaThemeColor != null && !metaThemeColor.isEmpty()) {
                setupMetaThemeColor(metaThemeColor);
            }
            initialized = true;
        }
    }

    @Override
    public void setupManifest(String manifestUrl) {
        Element head = Document.get().getElementsByTagName("head").getItem(0);
        Element linkManifest = Document.get().createLinkElement();
        linkManifest.setAttribute("rel", "manifest");
        linkManifest.setAttribute("href", manifestUrl);
        head.appendChild(linkManifest);
    }

    @Override
    public void setupMetaThemeColor(String themeColor) {
        Element head = Document.get().getElementsByTagName("head").getItem(0);
        Element metaThemeColor = Document.get().createMetaElement();
        metaThemeColor.setAttribute("name", "theme-color");
        metaThemeColor.setAttribute("content", themeColor);
        head.appendChild(metaThemeColor);
    }

    @Override
    public void setupServiceWorker(String serviceWorkerUrl) {
        if (Navigator.serviceWorker != null) {
            Navigator.serviceWorker.register(serviceWorkerUrl)
                    .then(arg -> {
                        GWT.log("Registered service worker successfully");
                        return null;
                    });
        } else {
            GWT.log("Service worker is not supported by this browser.");
        }
    }

    @Override
    public boolean isOnline() {
        return Navigator.onLine;
    }

    public static PwaManager getInstance() {
        return instance;
    }
}
