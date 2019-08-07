/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorker;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;

public class ServiceWorkerPlugin implements ServiceWorkerLifecycle {

    private ServiceWorkerManager serviceWorkerManager;

    public ServiceWorkerPlugin() {}

    public ServiceWorkerPlugin(ServiceWorkerManager serviceWorkerManager) {
        this.serviceWorkerManager = serviceWorkerManager;
    }

    @Override
    public boolean onRegistered(ServiceEvent event, ServiceWorkerRegistration registration) {
        GWT.log("ServiceWorker State : Registered");
        return false;
    }

    @Override
    public boolean onInstalling(ServiceEvent event) {
        GWT.log("ServiceWorker State : Installing");
        return false;
    }

    @Override
    public boolean onInstalled(ServiceEvent event) {
        GWT.log("ServiceWorker State : Installed");
        return false;
    }

    @Override
    public boolean onActivating(ServiceEvent event) {
        GWT.log("ServiceWorker State : Activating");
        return false;
    }

    @Override
    public boolean onActivated(ServiceEvent event) {
        GWT.log("ServiceWorker State : Activated");
        return false;
    }

    @Override
    public boolean onRedundant(ServiceEvent event) {
        GWT.log("ServiceWorker State : Redundant");
        return false;
    }

    @Override
    public boolean onControllerChange(ServiceEvent event) {
        GWT.log("ServiceWorker : On Controller Change");
        return false;
    }

    @Override
    public boolean onNewServiceWorkerFound(ServiceEvent event, ServiceWorker serviceWorker) {
        GWT.log("New Service worker found");
        return false;
    }

    @Override
    public boolean onOnline(ServiceEvent event) {
        return false;
    }

    @Override
    public boolean onOffline(ServiceEvent event) {
        return false;
    }

    @Override
    public boolean onServerFailing(ServiceEvent event) {
        return false;
    }

    @Override
    public boolean onMessageReceived(ServiceEvent event, Object data) {
        return false;
    }

    @Override
    public boolean onError(ServiceEvent event, String message) {
        return false;
    }

    public void setServiceWorkerManager(ServiceWorkerManager serviceWorkerManager) {
        this.serviceWorkerManager = serviceWorkerManager;
    }

    public ServiceWorkerManager getServiceWorkerManager() {
        return serviceWorkerManager;
    }
}