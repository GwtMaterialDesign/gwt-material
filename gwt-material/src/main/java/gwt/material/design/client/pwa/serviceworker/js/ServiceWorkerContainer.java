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


import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.jscore.client.api.promise.Promise;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


//@formatter:off

/**
 * The ServiceWorkerContainer JSInterop Class of the {@link ServiceWorker} API provides an
 * object representing the service worker as an overall unit in the network ecosystem,
 * including facilities to register, unregister and update service workers,
 * and access the state of service workers and their registrations.
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#serviceworker">GMD Service Worker</a>
 */
//@formatter:on
//TODO Need to transfer this at GMD JQuery
@JsType(isNative = true, name = "serviceWorker", namespace = "serviceWorker")
public class ServiceWorkerContainer {

    /**
     * Returns a {@link ServiceWorker} object if its state is activated (the same object returned
     * by {@link ServiceWorkerRegistration#active}). This property returns null if the request is
     * a force refresh (Shift + refresh) or if there is no active worker.
     */
    @JsProperty
    public ServiceWorker controller;

    /**
     * Defines whether a service worker is ready to control a page or not. It returns a {@link Promise}
     * that will never reject, which resolves to a {@link ServiceWorkerRegistration} with an
     * {@link ServiceWorkerRegistration#active} worker.
     */
    @JsProperty
    public Promise ready;

    /**
     * An event handler fired whenever a controllerchange event occurs — when the document's associated
     * {@link ServiceWorkerRegistration} acquires a new {@link ServiceWorkerRegistration#active} worker.
     */
    @JsProperty
    public Functions.EventFunc oncontrollerchange;

    /**
     * An event handler fired whenever an error event occurs in the associated service workers.
     */
    @JsProperty
    public Functions.EventFunc onerror;

    /**
     * An event handler fired whenever a message event occurs — when incoming messages are received
     * to the {@link ServiceWorkerContainer}object (e.g. via a MessagePort.postMessage() call.)
     */
    @JsProperty
    public Functions.EventFunc onmessage;

    /**
     * Creates or updates a {@link ServiceWorkerRegistration} for the given scriptURL.
     */
    @JsMethod
    public native Promise register(String scriptName);

    /**
     * Gets a {@link ServiceWorkerRegistration} object whose scope URL matches the provided document URL.
     * If the method can't return a {@link ServiceWorkerRegistration}, it returns a {@link Promise}.
     */
    @JsMethod
    public native Promise getRegistration();

    /**
     * Returns all ServiceWorkerRegistrations associated with a {@link ServiceWorkerContainer} in an array.
     * If the method can't return ServiceWorkerRegistrations, it returns a {@link Promise}.
     */
    @JsMethod
    public native Promise getRegistrations();

}