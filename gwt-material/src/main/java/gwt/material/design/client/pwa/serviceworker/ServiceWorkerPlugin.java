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
        return false;
    }

    @Override
    public boolean onInstalling(ServiceEvent event) {
        return false;
    }

    @Override
    public boolean onInstalled(ServiceEvent event) {
        return false;
    }

    @Override
    public boolean onActivating(ServiceEvent event) {
        return false;
    }

    @Override
    public boolean onActivated(ServiceEvent event) {
        return false;
    }

    @Override
    public boolean onRedundant(ServiceEvent event) {
        return false;
    }

    @Override
    public boolean onControllerChange(ServiceEvent event) {
        return false;
    }

    @Override
    public boolean onNewServiceWorkerFound(ServiceEvent event, ServiceWorker serviceWorker) {
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