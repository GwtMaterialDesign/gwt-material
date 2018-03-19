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

public interface ServiceWorkerLifecycle {

    void onRegistered(ServiceWorkerRegistration registration);

    /**
     * When a new service worker is registered using navigator.serviceWorker.register,
     * the JavaScript is downloaded, parsed, and enters the installing _state.
     * If the installation succeeds, the service worker will proceed to the installed _state.
     * If however, an error occurs during installation, the script will instead be banished
     * to the abyss of the redundant _state for all eternity (or until you attempt to
     * register it again by refreshing the page).
     */
    void onInstalling();

    /**
     * Once a service worker has successfully installed, it enters the installed _state.
     * It will then immediately move on to the activating _state unless another active
     * service worker is currently controlling this app, in which case it will remain waiting.
     */
    void onInstalled();

    /**
     * Before a service worker becomes active and takes control of your app, the activate event is triggered.
     * Similar to the installing _state, the activating _state can also be extended by calling event.waitUntil()
     * and passing it a promise.
     */
    void onActivating();

    /**
     * Once a service worker is activated, it is ready to take control of the page and listen to functional events (such as fetch).
     * <p>
     * A service worker can only take control of pages before they start loading.
     * This means that pages that began loading before the service worker became active cannot be controlled by it.
     */
    void onActivated();

    /**
     * Service workers that failed during registration, or installation, or were replaced by newer versions,
     * are placed in the redundant _state. Service workers in this _state no longer have any effect on your app.
     */
    void onRedundant();

    /**
     * Will listen to any event fired by the service worker.
     */
    void onControllerChange();

    /**
     * Called when there are new service worker updates on the appcache.
     */
    void onNewServiceWorkerFound(ServiceWorker serviceWorker);

    /**
     * Called when the network status is online
     */
    void onOnline();

    /**
     * Called when the network status is offline
     */
    void onOffline();

    /**
     * Called when the service worker can't fetch any request to the server or the server was already dead.
     */
    void onServerFailing();

    /**
     * Will be called once service worker send data to client
     */
    void onMessageReceived(Object data);
}