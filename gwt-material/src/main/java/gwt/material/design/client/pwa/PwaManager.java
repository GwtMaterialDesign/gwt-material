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
import gwt.material.design.jscore.client.api.Navigator;
import gwt.material.design.jscore.client.api.serviceworker.ServiceWorker;
import gwt.material.design.jscore.client.api.serviceworker.ServiceWorkerRegistration;

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
public class PwaManager implements HasPwaFeature {

    private static PwaManager instance = GWT.create(PwaManager.class);

    private Element headElement, manifestElement, themeColorElement;

    private String serviceWorkerUrl, manifestUrl, themeColor;


    @Override
    public void load() {
        if (headElement == null) {
            headElement = Document.get().getElementsByTagName("head").getItem(0);
        }

        injectManifest(manifestUrl);
        injectMetaThemeColor(themeColor);
        loadServiceWorker(serviceWorkerUrl);
    }


    @Override
    public void reload() {
        unLoad();
        load();
    }

    @Override
    public void unLoad() {
        // Unregister the manifest
        if (manifestElement != null) {
            manifestElement.removeFromParent();
            manifestElement = null;
            GWT.log("Web manifest has been unloaded.");
        }
        // Unregister the theme color
        if (themeColorElement != null) {
            themeColorElement.removeFromParent();
            themeColorElement = null;
            GWT.log("Meta theme color has been unloaded.");
        }

        // Unregister the service worker
        unRegisterServiceWorker();
    }

    @Override
    public PwaManager setWebManifestUrl(String manifestUrl) {
        this.manifestUrl = manifestUrl;
        return this;
    }

    /**
     * Will inject the manifest url into the head element.
     */
    protected void injectManifest(String manifestUrl) {
        if (manifestUrl != null && !manifestUrl.isEmpty()) {
            // Check whether manifestElement was already attached to the head element.
            if (manifestElement == null) {
                manifestElement = Document.get().createLinkElement();
                headElement.appendChild(manifestElement);
            }

            manifestElement.setAttribute("rel", "manifest");
            manifestElement.setAttribute("href", manifestUrl);
        }
    }

    @Override
    public PwaManager setThemeColor(String themeColor) {
        this.themeColor = themeColor;
        return this;
    }

    /**
     * Will inject the meta link to specify the theme color of your app design.
     */
    protected void injectMetaThemeColor(String themeColor) {
        if (themeColor != null && !themeColor.isEmpty()) {
            // Check whether themeColor was already attached to the head element.
            if (themeColorElement == null) {
                themeColorElement = Document.get().createMetaElement();
                headElement.appendChild(themeColorElement);
            }
            themeColorElement.setAttribute("name", "theme-color");
            themeColorElement.setAttribute("content", themeColor);
        }
    }

    @Override
    public PwaManager setServiceWorkerUrl(String serviceWorkerUrl) {
        this.serviceWorkerUrl = serviceWorkerUrl;
        return this;
    }

    /**
     * Will load the service worker with provided url.
     */
    protected void loadServiceWorker(String serviceWorkerUrl) {
        if (serviceWorkerUrl != null && Navigator.serviceWorker != null) {
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
    public void unRegisterServiceWorker() {
        if (Navigator.serviceWorker != null) {
            Navigator.serviceWorker.getRegistration().then(obj -> {
                ServiceWorkerRegistration registration = (ServiceWorkerRegistration) obj;
                if (registration != null) {
                    registration.unregister();
                    GWT.log("Successfully unregistered Service Worker");
                } else {
                    GWT.log("There's no Service worker that is registered.");
                }
                return null;
            });
        }
    }

    @Override
    public void updateServiceWorker() {
        if (Navigator.serviceWorker != null) {
            Navigator.serviceWorker.getRegistration().then(obj -> {
                ServiceWorkerRegistration registration = (ServiceWorkerRegistration) obj;
                if (registration != null) {
                    registration.update();
                    GWT.log("Successfully updated Service Worker");
                } else {
                    GWT.log("There's no Service worker that is registered.");
                }
                return null;
            });
        }
    }

    @Override
    public ServiceWorker getServiceWorker() {
        return Navigator.serviceWorker.controller;
    }

    @Override
    public boolean isOnline() {
        return Navigator.onLine;
    }

    public static PwaManager getInstance() {
        return instance;
    }
}
