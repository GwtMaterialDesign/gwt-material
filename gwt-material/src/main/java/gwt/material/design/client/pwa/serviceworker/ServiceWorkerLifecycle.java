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

import com.google.web.bindery.requestfactory.shared.Service;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorker;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;

public interface ServiceWorkerLifecycle {

    /**
     *
     * @param registration
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onRegistered(ServiceEvent event, ServiceWorkerRegistration registration);

    /**
     * When a new service worker is registered using navigator.serviceWorker.register,
     * the JavaScript is downloaded, parsed, and enters the installing _state.
     * If the installation succeeds, the service worker will proceed to the installed _state.
     * If however, an error occurs during installation, the script will instead be banished
     * to the abyss of the redundant _state for all eternity (or until you attempt to
     * register it again by refreshing the page).
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onInstalling(ServiceEvent event);

    /**
     * Once a service worker has successfully installed, it enters the installed _state.
     * It will then immediately move on to the activating _state unless another active
     * service worker is currently controlling this app, in which case it will remain waiting.
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onInstalled(ServiceEvent event);

    /**
     * Before a service worker becomes active and takes control of your app, the activate event is triggered.
     * Similar to the installing _state, the activating _state can also be extended by calling event.waitUntil()
     * and passing it a promise.
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onActivating(ServiceEvent event);

    /**
     * Once a service worker is activated, it is ready to take control of the page and listen to functional events (such as fetch).
     * <p>
     * A service worker can only take control of pages before they start loading.
     * This means that pages that began loading before the service worker became active cannot be controlled by it.
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onActivated(ServiceEvent event);

    /**
     * Service workers that failed during registration, or installation, or were replaced by newer versions,
     * are placed in the redundant _state. Service workers in this _state no longer have any effect on your app.
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onRedundant(ServiceEvent event);

    /**
     * Will listen to any event fired by the service worker.
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onControllerChange(ServiceEvent event);

    /**
     * Called when there are new service worker updates on the appcache.
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onNewServiceWorkerFound(ServiceEvent event, ServiceWorker serviceWorker);

    /**
     * Called when the network status is online
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onOnline(ServiceEvent event);

    /**
     * Called when the network status is offline
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onOffline(ServiceEvent event);

    /**
     * Called when the service worker can't fetch any request to the server or the server was already dead.
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onServerFailing(ServiceEvent event);

    /**
     * Will be called once service worker send data to client
     *
     * @param event the service event
     * @return stop propagation, default: false
     */
    boolean onMessageReceived(ServiceEvent event, Object data);

    /**
     * Will be called whenever an error event occurs in the associated service workers.
     */
    boolean onError(ServiceEvent event, String message);
}