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
import gwt.material.design.client.js.Navigator;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorker;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerContainer;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import gwt.material.design.client.pwa.serviceworker.network.NetworkStatusManager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
    private Map<Class<? extends ServiceWorkerPlugin>, ServiceWorkerPlugin> plugins = new LinkedHashMap();
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
                onOnline(new ServiceEvent());
            } else {
                onOffline(new ServiceEvent());
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

                onRegistered(new ServiceEvent(), registration);

                // Observe service worker lifecycle
                observeLifecycle(registration);

                // Setup Service Worker events events
                setupOnControllerChangeEvent();
                setupOnMessageEvent();
                setupOnErrorEvent();
                return null;
            }, error -> {
                logger.info("ServiceWorker registration failed: " + error);
                return null;
            });
        } else {
            logger.info("Service worker is not supported by this browser.");
        }
    }

    protected void setupOnErrorEvent() {
        getServiceWorkerContainer().onerror = (e) -> {
            onError(new ServiceEvent(), "");
            return true;
        };
    }

    /**
     * The oncontrollerchange property of the ServiceWorkerContainer interface is an event handler
     * fired whenever a controllerchange event occurs — when the document's associated
     * ServiceWorkerRegistration acquires a new ServiceWorkerRegistration.active worker.
     */
    protected void setupOnControllerChangeEvent() {
        Navigator.serviceWorker.oncontrollerchange = e -> {
            onControllerChange(new ServiceEvent());
            return true;
        };
    }

    /**
     * Will listen to any broadcast messages from the service worker
     */
    protected void setupOnMessageEvent() {
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
                onServerFailing(new ServiceEvent());
            } else {
                onMessageReceived(new ServiceEvent(), e.data);
            }

            return true;
        };
    }

    /**
     * Will listen and observe to service worker life cycle. This method also tracks if there are incoming service worker.
     * @see <a href="https://developers.google.com/web/fundamentals/primers/service-workers/lifecycle">Service Worker Lifecycles</a>
     * @param registration - The Service Worker Registration
     */
    protected void observeLifecycle(ServiceWorkerRegistration registration) {
        // Will listen to Service Worker lifecycle
        if (registration.installing != null) {
            registration.onupdatefound = event -> {
                onStateChange(registration.installing);
                return true;
            };
        }

        // Will check if there's a waiting service worker to be installed from the current registration
        // If any then fire {@link #onNewServiceWorkerFound}
        if (registration.waiting != null) {
            onNewServiceWorkerFound(new ServiceEvent(), registration.waiting);
        }
    }

    /**
     * Will listen to any Service worker's {@link State} changes.
     */
    protected void onStateChange(ServiceWorker serviceWorker) {
        serviceWorker.onstatechange = e -> {
            State state = State.fromStyleName(serviceWorker.state);
            switch (state) {
                case INSTALLING:
                    onInstalling(new ServiceEvent());
                    break;
                case INSTALLED:
                    onInstalled(new ServiceEvent());
                    break;
                case ACTIVATING:
                    onActivating(new ServiceEvent());
                    break;
                case ACTIVATED:
                    onActivated(new ServiceEvent());
                    break;
                case REDUNDANT:
                    onRedundant(new ServiceEvent());
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

    public ServiceWorkerContainer getServiceWorkerContainer() {
        return Navigator.serviceWorker;
    }

    /**
     * Get the active Service Worker from the registration stack.
     */
    public ServiceWorker getServiceWorker() {
        return registration.active;
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
        plugins.put(plugin.getClass(), plugin);
    }

    public void removePlugin(ServiceWorkerPlugin plugin) {
        plugin.setServiceWorkerManager(null);
        plugins.remove(plugin.getClass());
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

    public List<ServiceWorkerPlugin> getPlugins() {
        return plugins.values().stream().collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceWorkerPlugin> T getPlugin(Class<T> plugin) {
        return (T) plugins.get(plugin);
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
    public boolean onRegistered(ServiceEvent event, ServiceWorkerRegistration registration) {
        GWT.log("Service Worker is registered");

        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onRegistered(event, registration) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onRegistered(event, registration);
        }
        return true;
    }

    @Override
    public boolean onInstalling(ServiceEvent event) {
        GWT.log("Service worker is installing");

        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onInstalling(event) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onInstalling(event);
        }
        return false;
    }

    @Override
    public boolean onInstalled(ServiceEvent event) {
        GWT.log("Service worker is installed");

        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onInstalled(event) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onInstalled(event);
        }
        return false;
    }

    @Override
    public boolean onActivating(ServiceEvent event) {
        GWT.log("Service worker is activating");

        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onActivating(event) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onActivating(event);
        }
        return false;
    }

    @Override
    public boolean onActivated(ServiceEvent event) {
        GWT.log("Service worker is activated");

        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onActivated(event) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onActivated(event);
        }
        return false;
    }

    @Override
    public boolean onRedundant(ServiceEvent event) {
        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onRedundant(event) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onRedundant(event);
        }
        return false;
    }

    @Override
    public boolean onControllerChange(ServiceEvent event) {
        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onControllerChange(event) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onControllerChange(event);
        }
        return false;
    }

    @Override
    public boolean onNewServiceWorkerFound(ServiceEvent event, ServiceWorker serviceWorker) {
        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onNewServiceWorkerFound(event, serviceWorker) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onNewServiceWorkerFound(event, serviceWorker);
        }
        return false;
    }

    @Override
    public boolean onOnline(ServiceEvent event) {
        GWT.log("Network Status is now online");

        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onOnline(event) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onOnline(event);
        }
        return false;
    }

    @Override
    public boolean onOffline(ServiceEvent event) {
        GWT.log("Network Status is now offline");

        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onOffline(event) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onOffline(event);
        }
        return false;
    }

    @Override
    public boolean onServerFailing(ServiceEvent event) {
        GWT.log("Can't connect to the server at the moment.", new RuntimeException());

        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onServerFailing(event) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onServerFailing(event);
        }
        return false;
    }

    @Override
    public boolean onMessageReceived(ServiceEvent event, Object data) {
        GWT.log("Message received: " + data);

        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onMessageReceived(event, data) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onMessageReceived(event, data);
        }
        return false;
    }

    @Override
    public boolean onError(ServiceEvent event, String message) {
        for (ServiceWorkerPlugin plugin : plugins.values()) {
            if(plugin.onError(event, message) || event.isStopPropagation()) {
                break; // Stop propagation
            }
        }

        if (isUsingDefaultPlugin() && !event.isPreventDefault()) {
            defaultPlugin.onError(event, message);
        }
        return false;
    }
}
