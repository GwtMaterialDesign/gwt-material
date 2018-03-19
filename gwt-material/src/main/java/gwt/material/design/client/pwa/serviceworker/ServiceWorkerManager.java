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
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Window;
import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.pwa.base.PwaFeature;
import gwt.material.design.client.pwa.serviceworker.constants.ServiceWorkerMessage;
import gwt.material.design.client.pwa.serviceworker.constants.State;
import gwt.material.design.client.pwa.serviceworker.js.Navigator;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorker;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import gwt.material.design.client.pwa.serviceworker.network.NetworkStatusManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * An implementation of Service Worker to manage its lifecycle
 * For more references see <a href="https://developers.google.com/web/fundamentals/primers/service-workers/">Service Worker Lifecycle</a>
 *
 * @author kevzlou7979
 */
public class ServiceWorkerManager implements ServiceWorkerLifecycle, PwaFeature {

    private static final Logger logger = Logger.getLogger(ServiceWorkerManager.class.getSimpleName());

    private PwaManager manager;
    private String resource;
    private ServiceWorkerRegistration registration;

    private DefaultServiceWorkerPlugin defaultPlugin = new DefaultServiceWorkerPlugin(this);
    private List<ServiceWorkerPlugin> plugins = new ArrayList<>();
    private NetworkStatusManager networkStatusManager = new NetworkStatusManager();

    protected ServiceWorkerManager() {}

    public ServiceWorkerManager(PwaManager manager, String resource) {
        this(resource);
        this.manager = manager;
    }

    public ServiceWorkerManager(String resource) {
        assert resource != null : "Cannot have a null pwa resource.";
        this.resource = resource;
    }

    @Override
    public void load() {
        setupRegistration();

        networkStatusManager.load();
        networkStatusManager.addNetworkStatusChangeEvent(event -> {
            if (event.isOnline()) {
                onOnline();
            } else {
                onOffline();
            }
        });
    }

    @Override
    public void unload() {
        if (registration != null) {
            registration.unregister();
        }

        networkStatusManager.unload();
    }

    @Override
    public void reload() {
        unload();
        load();
    }

    /**
     * Initial setup of the service worker registration.
     */
    protected void setupRegistration() {
        if (isServiceWorkerSupported()) {
            Navigator.serviceWorker.register(getResource()).then(object -> {
                logger.info("Service worker has been successfully registered");
                registration = (ServiceWorkerRegistration) object;
                onRegistered(registration);
                observeLifeCycle(registration);
                // Setup events
                setupOnControllerChangeEvent(registration);
                setupOnMessageEvent(registration);
                return null;
            }, error -> {
                logger.info("ServiceWorker registration failed: " + error);
                return null;
            });
        } else {
            logger.info("Service worker is not supported by this browser.");
        }
    }

    /**
     * The oncontrollerchange property of the ServiceWorkerContainer interface is an event handler
     * fired whenever a controllerchange event occurs — when the document's associated
     * ServiceWorkerRegistration acquires a new ServiceWorkerRegistration.active worker.
     */
    protected void setupOnControllerChangeEvent(ServiceWorkerRegistration registration) {
        Navigator.serviceWorker.oncontrollerchange = e -> {
            onControllerChange();
            return true;
        };
    }

    /**
     * Will listen to any broadcast messages from the service worker
     */
    protected void setupOnMessageEvent(ServiceWorkerRegistration registration) {
        Navigator.serviceWorker.onmessage = e -> {
            boolean failing = false;
            if (e.data != null && e.data instanceof String) {
                switch (e.data.toString()) {
                    case ServiceWorkerMessage.FAILING_SERVER:
                        failing = true;
                        break;
                }
            }

            if (failing) {
                onServerFailing();
            } else {
                onMessageReceived(e.data);
            }

            return true;
        };
    }

    /**
     * Will observe the lifecycle of the Service Worker Registration
     */
    protected void observeLifeCycle(ServiceWorkerRegistration registration) {
        // If there's no controller, this page wasn't loaded
        // via a service worker, so they're looking at the latest version.
        // In that case, exit early
        if (Navigator.serviceWorker.controller == null) {
            if (registration.installing != null) {
                onStateChange(registration.installing);
            }
        }

        // If there's an updated worker already waiting,
        // call {@link #onNewServiceWorkerFound(serviceworker)
        if (registration.waiting != null) {
            onNewServiceWorkerFound(registration.waiting);
        }

        // If there's an updated worker installing, track its
        // progress. If it becomes "installed", call
        // {@link #onNewServiceWorkerFound(serviceworker)
        if (registration.installing != null) {
            trackServiceWorkerState(registration.installing);
        }

        // Otherwise, listen for new installing workers arriving
        // If on arrives, track its progress.
        // If it becomes "installed", call
        // {@link #onNewServiceWorkerFound(serviceworker)
        registration.onupdatefound = e -> {
            trackServiceWorkerState(registration.installing);
            return true;
        };
    }

    /**
     * Will track the service worker phase of the service worker and once
     * the service worker state was installed then call {@link #onNewServiceWorkerFound(ServiceWorker)}
     */
    protected void trackServiceWorkerState(ServiceWorker serviceWorker) {
        serviceWorker.onstatechange = e -> {
            if (serviceWorker.state.equals(State.INSTALLED.getCssName())) {
                onNewServiceWorkerFound(serviceWorker);
            }
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
     * Will set the polling request interval in milliseconds for new Service Worker instance.
     *
     * @param interval Interval must be in milliseconds and must be at least 1000ms.
     */
    public void setPollingInterval(int interval) {
        if (interval >= 1000) {
            Scheduler.get().scheduleFixedDelay(() -> {
                if (registration != null) {
                    registration.update();
                }
                return true;
            }, interval);
        } else {
            logger.warning("Polling interval must be at least 1000ms to perform the request.");
        }
    }

    /**
     * Checks the server for an updated version of the service worker without consulting caches.
     */
    public void update() {
        registration.update();
    }

    /**
     * Get the Service Worker Registration
     */
    public ServiceWorkerRegistration getServiceWorkerRegistration() {
        return registration;
    }

    /**
     * Get the active Service Worker from the registration stack.
     */
    public ServiceWorker getServiceWorker() {
        return registration.active;
    }

    /**
     * Will remove the defined plugin upon registration of the service worker.
     */
    public boolean removePlugin(ServiceWorkerPlugin plugin) {
        return plugins.remove(plugin);
    }

    /**
     * Forces the waiting service worker to become the active service worker.
     */
    public void skipWaiting(ServiceWorker serviceWorker) {
        serviceWorker.postMessage("skipWaiting");
        Window.Location.reload();
    }

    /**
     * Will post a message to service-worker.js file with predefined data message.
     * It requires a command and object pattern to be extracted on the service worker.
     */
    public void postMessage(ServiceWorker serviceWorker, Object message) {
        serviceWorker.postMessage(message);
    }

    public void postMessage(Object message) {
        getServiceWorker().postMessage(message);
    }

    /**
     * Will detect if your browser supports service worker.
     *
     * @see <a href="https://caniuse.com/#feat=serviceworkers">Browser Support</a>
     */
    public boolean isServiceWorkerSupported() {
        return Navigator.serviceWorker != null;
    }

    /**
     * Will add a {@link ServiceWorkerPlugin} for customized implementation
     * i.e Firebase Push Notification plugins
     */
    public void addPlugin(ServiceWorkerPlugin plugin) {
        plugin.setServiceWorkerManager(this);
        plugins.add(plugin);
    }

    /**
     * Will set if we want to use the default plugin or not
     */
    public void useDefaultPlugin(boolean use) {
        if (use) {
            if (defaultPlugin == null) {
                defaultPlugin = new DefaultServiceWorkerPlugin(this);
            }
        } else {
            defaultPlugin = null;
        }
    }

    public boolean isUsingDefaultPlugin() {
        return defaultPlugin != null;
    }

    @Override
    public String getResource() {
        return resource;
    }

    @Override
    public PwaManager getManager() {
        return manager;
    }

    @Override
    public void onRegistered(ServiceWorkerRegistration registration) {
        GWT.log("Service Worker is registered");

        if (isUsingDefaultPlugin()) {
            defaultPlugin.onRegistered(registration);
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onRegistered(registration);
        }
    }

    @Override
    public void onInstalling() {
        GWT.log("Service worker is installing");

        if (isUsingDefaultPlugin()) {
            defaultPlugin.onInstalling();
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onInstalling();
        }
    }

    @Override
    public void onInstalled() {
        GWT.log("Service worker is installed");

        if (isUsingDefaultPlugin()) {
            defaultPlugin.onInstalled();
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onInstalled();
        }
    }

    @Override
    public void onActivating() {
        GWT.log("Service worker is activating");

        if (isUsingDefaultPlugin()) {
            defaultPlugin.onActivating();
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onActivating();
        }
    }

    @Override
    public void onActivated() {
        GWT.log("Service worker is activated");

        if (isUsingDefaultPlugin()) {
            defaultPlugin.onActivated();
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onActivated();
        }
    }

    @Override
    public void onRedundant() {
        if (isUsingDefaultPlugin()) {
            defaultPlugin.onRedundant();
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onRedundant();
        }
    }

    @Override
    public void onControllerChange() {
        if (isUsingDefaultPlugin()) {
            defaultPlugin.onControllerChange();
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onControllerChange();
        }
    }

    @Override
    public void onNewServiceWorkerFound(ServiceWorker serviceWorker) {
        if (isUsingDefaultPlugin()) {
            defaultPlugin.onNewServiceWorkerFound(serviceWorker);
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onNewServiceWorkerFound(serviceWorker);
        }
    }

    @Override
    public void onOnline() {
        GWT.log("Network Status is now online");

        if (isUsingDefaultPlugin()) {
            defaultPlugin.onOnline();
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onOnline();
        }
    }

    @Override
    public void onOffline() {
        GWT.log("Network Status is now offline");

        if (isUsingDefaultPlugin()) {
            defaultPlugin.onOffline();
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onOffline();
        }
    }

    @Override
    public void onServerFailing() {
        GWT.log("Can't connect to the server at the moment.", new RuntimeException());

        if (isUsingDefaultPlugin()) {
            defaultPlugin.onServerFailing();
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onServerFailing();
        }
    }

    @Override
    public void onMessageReceived(Object data) {
        GWT.log("Message received: " + data);

        if (isUsingDefaultPlugin()) {
            defaultPlugin.onMessageReceived(data);
        }

        for (ServiceWorkerPlugin plugin : plugins) {
            plugin.onMessageReceived(data);
        }
    }
}
