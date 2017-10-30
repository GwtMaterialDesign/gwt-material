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
import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.pwa.serviceworker.constants.State;
import gwt.material.design.jscore.client.api.Navigator;
import gwt.material.design.jscore.client.api.serviceworker.ServiceWorker;
import gwt.material.design.jscore.client.api.serviceworker.ServiceWorkerRegistration;

/**
 * An abstract implementation of Service Worker to manage its lifecycle
 * For more references see <a href="https://developers.google.com/web/fundamentals/primers/service-workers/">Service Worker Lifecycle</a>
 */
public abstract class AbstractServiceWorkerManager implements ServiceWorkerManager {

    private PwaManager manager;
    private String resource;
    private ServiceWorkerRegistration registration;


    public AbstractServiceWorkerManager(PwaManager manager, String resource) {
        this(resource);
        this.manager = manager;
    }

    public AbstractServiceWorkerManager(String resource) {
        this.resource = resource;
    }

    @Override
    public void load() {
        if (getResource() != null && isServiceWorkerSupported()) {
            setupRegistration();
        } else {
            GWT.log("Service worker is not supported by this browser.");
        }
    }

    @Override
    public void unload() {
        if (registration != null) {
            registration.unregister();
        }
    }

    @Override
    public void reload() {
        unload();
        load();
    }

    protected void setupRegistration() {
        Navigator.serviceWorker.register(getResource()).then(object -> {

            GWT.log("Service worker has been successfully registered");

            registration = (ServiceWorkerRegistration) object;

            if (Navigator.serviceWorker == null) return null;

            if (registration.waiting != null) {
                onNewServiceWorkerFound(registration.waiting);
                return null;
            }

            if (registration.installing != null) {
                registration.onupdatefound = e -> {
                    onStateChange(registration.installing);
                    return true;
                };
            }

            return null;
        }, error -> {
            GWT.log("ServiceWorker registration failed: " + error);
            return null;
        });

        Navigator.serviceWorker.oncontrollerchange = e -> {
            onControllerChange();
            return true;
        };
    }

    /**
     * Will listen to any Service worker's {@link State} changes.
     */
    protected void onStateChange(ServiceWorker serviceWorker) {
        serviceWorker.onstatechange = e -> {
            State state = State.fromStyleName(serviceWorker.state);
            switch (state) {
                case INSTALLING:
                    onInstalling();
                    break;
                case INSTALLED:
                    onInstalled();
                    break;
                case ACTIVATING:
                    onActivating();
                    break;
                case ACTIVATED:
                    onActivated();
                    break;
                case REDUNDANT:
                    onRedundant();
                    break;
            }
            return true;
        };
    }

    /**
     * When a new service worker is registered using navigator.serviceWorker.register,
     * the JavaScript is downloaded, parsed, and enters the installing _state.
     * If the installation succeeds, the service worker will proceed to the installed _state.
     * If however, an error occurs during installation, the script will instead be banished
     * to the abyss of the redundant _state for all eternity (or until you attempt to
     * register it again by refreshing the page).
     */
    public abstract void onInstalling();

    /**
     * Once a service worker has successfully installed, it enters the installed _state.
     * It will then immediately move on to the activating _state unless another active
     * service worker is currently controlling this app, in which case it will remain waiting.
     */
    public abstract void onInstalled();

    /**
     * Before a service worker becomes active and takes control of your app, the activate event is triggered.
     * Similar to the installing _state, the activating _state can also be extended by calling event.waitUntil()
     * and passing it a promise.
     */
    public abstract void onActivating();

    /**
     * Once a service worker is activated, it is ready to take control of the page and listen to functional events (such as fetch).

     A service worker can only take control of pages before they start loading.
     This means that pages that began loading before the service worker became active cannot be controlled by it.
     */
    public abstract void onActivated();

    /**
     * Service workers that failed during registration, or installation, or were replaced by newer versions,
     * are placed in the redundant _state. Service workers in this _state no longer have any effect on your app.
     */
    public abstract void onRedundant();

    /**
     * Will listen to any event fired by the service worker.
     */
    protected abstract void onControllerChange();

    /**
     * Called when there are new service worker updates on the appcache.
     */
    protected abstract void onNewServiceWorkerFound(ServiceWorker serviceWorker);

    @Override
    public void update() {
        registration.update();
    }

    @Override
    public ServiceWorkerRegistration getServiceWorkerRegistration() {
        return registration;
    }

    @Override
    public ServiceWorker getServiceWorker() {
        return registration.active;
    }

    @Override
    public boolean isServiceWorkerSupported() {
        return Navigator.serviceWorker != null;
    }

    @Override
    public String getResource() {
        return resource;
    }

    @Override
    public PwaManager getManager() {
        return manager;
    }
}
