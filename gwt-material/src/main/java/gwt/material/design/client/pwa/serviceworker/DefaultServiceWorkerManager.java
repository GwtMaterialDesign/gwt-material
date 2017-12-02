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
package gwt.material.design.client.pwa.serviceworker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.base.AbstractButton;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorker;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;

/**
 * Default Service Worker Manager that delegates all methods needed
 * for service worker lifecycle from {@link AbstractServiceWorkerManager}.
 * You can check all the lifecycle states of a service worker here
 * {@link gwt.material.design.client.pwa.serviceworker.constants.State}
 */
public class DefaultServiceWorkerManager extends AbstractServiceWorkerManager {

    protected DefaultServiceWorkerManager() {}

    public DefaultServiceWorkerManager(String resource) {
        super(resource);
    }

    @Override
    public void onRegistered(ServiceWorkerRegistration registration) {
        GWT.log("Service Worker is registered");
    }

    @Override
    public void onInstalling() {
        GWT.log("Service worker is installing");
    }

    @Override
    public void onInstalled() {
        GWT.log("Service worker is installed");
    }

    @Override
    public void onActivating() {
        GWT.log("Service worker is activating");
    }

    @Override
    public void onActivated() {
        GWT.log("Service worker is activated");

        // Prompt the user with a toast to notify him that the service worker is activated
        // and ready for future offline visits.
        new MaterialToast().toast("Caching complete! Future visits will work offline.");
    }

    @Override
    public void onRedundant() {
        GWT.log("Service worker is redundant");
    }

    @Override
    protected void onControllerChange() {
        Window.Location.reload();
    }

    @Override
    protected void onNewServiceWorkerFound(ServiceWorker serviceWorker) {
        MaterialLink reload = new MaterialLink("REFRESH");
        onNewServiceWorkerFound(serviceWorker, reload, "New updates available.");
    }

    protected void onNewServiceWorkerFound(ServiceWorker serviceWorker, AbstractButton refreshButton, String message) {
        refreshButton.addClickHandler(clickEvent -> skipWaiting(serviceWorker));
        new MaterialToast(refreshButton).toast(message, 0);
    }

    @Override
    protected void onOnline() {
        GWT.log("Network Status is now online");
        RootPanel.get().removeStyleName(CssName.OFFLINE);
    }

    @Override
    protected void onOffline() {
        GWT.log("Network Status is now offline");
        RootPanel.get().addStyleName(CssName.OFFLINE);
    }

    @Override
    protected void onServerFailing() {
        GWT.log("Can't connect to the server at the moment.", new RuntimeException());
    }
}
