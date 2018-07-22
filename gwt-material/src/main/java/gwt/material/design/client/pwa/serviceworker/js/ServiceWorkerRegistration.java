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
package gwt.material.design.client.pwa.serviceworker.js;


import gwt.material.design.client.pwa.push.js.Notification;
import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.jscore.client.api.promise.Promise;
import gwt.material.design.client.pwa.push.js.PushManager;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The {@link ServiceWorkerRegistration} JSInterop of the {@link ServiceWorker} API represents the service
 * worker registration. You register a service worker to control one or more pages that share
 * the same origin.
 * <p>
 * The lifetime of a service worker registration is beyond that of the {@link ServiceWorkerRegistration}
 * objects that represent them within the lifetime of their corresponding service worker clients.
 * The browser maintains a persistent list of active {@link ServiceWorkerRegistration} objects.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class ServiceWorkerRegistration {

    /**
     * Returns a unique identifier for a service worker registration.
     * This must be on the same origin as the document that registers the {@link ServiceWorker}.
     */
    @JsProperty
    public String scope;

    /**
     * Returns a service worker whose state is installing. This is initially set to null.
     */
    @JsProperty
    public ServiceWorker installing;

    /**
     * Returns a service worker whose state is installed. This is initially set to null.
     */
    @JsProperty
    public ServiceWorker waiting;

    /**
     * Returns a service worker whose state is either activating or activated.
     * This is initially set to null. An active worker will control a ServiceWorkerClient
     * if the client's URL falls within the scope of the registration (the scope option set
     * when {@link ServiceWorkerContainer#register} is first called.)
     */
    @JsProperty
    public ServiceWorker active;

    /**
     * An EventListener property called whenever an event of type updatefound is fired;
     * it is fired any time the ServiceWorkerRegistration.installing property acquires
     * a new service worker.
     */
    @JsProperty
    public Functions.EventFunc onupdatefound;


    /**
     * Returns a reference to the {@link SyncManager} interface, which manages background synchronization processes.
     */
    @JsProperty
    public SyncManager sync;

    /**
     * Returns a reference to the {@link PushManager} interface for managing push subscriptions
     * including subscribing, getting an active subscription, and accessing push permission status.
     */
    @JsProperty
    public PushManager pushManager;

    /**
     * Checks the server for an updated version of the service worker without consulting caches.
     */
    @JsMethod
    public native void update();

    /**
     * Unregisters the service worker registration and returns a promise (see Promise).
     * The service worker will finish any ongoing operations before it is unregistered.
     */
    @JsMethod
    public native Promise unregister();

    /**
     * Returns a Promise that resolves to an array of {@link Notification} objects.
     */
    @JsMethod
    public native Promise getNotifications();

    /**
     * Displays the notification with the requested title.
     */
    @JsMethod
    public native void showNotification(String title, Notification notification);
}