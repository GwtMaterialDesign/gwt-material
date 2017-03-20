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
package gwt.material.design.client.pwa.pushnotifications;

import gwt.material.design.jquery.client.api.Functions;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Options for Push Notification Services for PWA
 *
 * @author kevzlou7979
 */
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class PushNotificationOptions {

    /**
     * The body text of the notification.
     */
    @JsProperty
    public String body;

    /**
     * Data to pass to ServiceWorker notifications
     */
    @JsProperty
    public String data;

    /**
     * Icon Image url for your notifcation
     */
    @JsProperty
    public String icon;

    /**
     * A relative URL path to navigate to when the user clicks on
     * the notification on mobile (e.g. if you want users to navigate to your
     * page http://example.com/page, then the relative URL is just page).
     */
    @JsProperty
    public String link;

    /**
     * Time in milliseconds until the notification closes automatically.
     */
    @JsProperty
    public double timeout;

    /**
     * Callback to execute when the notification is clicked.
     */
    @JsProperty
    public Functions.Func onClick;

    /**
     * Callback to execute when the notification is closed (obsolete).
     */
    @JsProperty
    public Functions.Func onClose;

    /**
     * Callback to execute when if the notification throws an error.
     */
    @JsProperty
    public Functions.Func onError;

    /**
     * Callback to execute when the notification is shown (obsolete).
     */
    @JsProperty
    public Functions.Func onShow;

    /**
     * When set to true, the notification will not close unless the user manually
     * closes or clicks on it.
     */
    @JsProperty
    public boolean requireInteraction;

    /**
     * Path to the service worker script registered on Push mobile. Defaults to
     * "serviceWorker.js" if a path is not specified.
     */
    @JsProperty
    public String serviceWorker;

    /**
     * Unique tag used to identify the notification. Can be used to later close the
     * notification manually.
     */
    @JsProperty
    public String tag;
}
