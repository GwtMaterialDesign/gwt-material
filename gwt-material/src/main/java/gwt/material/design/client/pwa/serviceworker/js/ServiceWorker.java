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
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


//@formatter:off

/**
 * The ServiceWorker JSInterop class provides a reference to a service worker.
 * Multiple browsing contexts (e.g. pages, workers, etc.) can be associated with the same
 * service worker, each through a unique ServiceWorker object.
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#serviceworker">GMD Service Worker</a>
 */
//@formatter:on
@JsType(isNative = true, name = "controller")
public class ServiceWorker {

    /**
     * Returns the ServiceWorker serialized script URL defined as part of
     * {@link ServiceWorkerRegistration}. The URL must be on the same origin
     * as the document that registers the {@link ServiceWorker}.
     */
    @JsProperty
    public String scriptURL;

    /**
     * Returns the state of the service worker. It returns one of the
     * following values: installing, installed, activating, activated, or redundant.
     */
    @JsProperty
    public String state;

    /**
     * An EventListener property called whenever an event of type statechange is fired;
     * it is basically fired anytime the {@link ServiceWorker#state} changes.
     */
    @JsProperty
    public Functions.EventFunc onstatechange;

    @JsMethod
    public native void postMessage(Object data);
}