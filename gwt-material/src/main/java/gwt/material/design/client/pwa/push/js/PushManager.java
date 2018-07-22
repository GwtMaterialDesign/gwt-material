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
package gwt.material.design.client.pwa.push.js;

import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import gwt.material.design.jquery.client.api.Promise;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

//@formatter:off

/**
 * The PushManager interface of the Push API provides a way to receive notifications from third-party servers as well as request URLs for push notifications.
 * <p>
 * This interface is accessed via the {@link ServiceWorkerRegistration#update()} property.
 *
 * @author kevzlou7979@gmail.com
 */
//@formatter:on
@JsType(isNative = true)
public class PushManager {

    @JsProperty
    public String[] supportedContentEncodings;

    /**
     * Retrieves an existing push subscription. It returns a Promise that resolves to a PushSubscription object
     * containing details of an existing subscription. If no existing subscription exists, this resolves to a null value.
     */
    @JsMethod
    public native Promise getSubscription();

    /**
     * Returns a Promise that resolves to the permission state of the current {@link PushManager}, which will be one of 'granted',
     * 'denied', or 'prompt'.
     */
    @JsMethod
    public native Promise permissionState();

    /**
     * Subscribes to a push service. It returns a Promise that resolves to a PushSubscription object containing
     * details of a push subscription. A new push subscription is created if the current service worker does not
     * have an existing subscription.
     */
    @JsMethod
    public native Promise subscribe(PushSubscriptionOptions options);
}
