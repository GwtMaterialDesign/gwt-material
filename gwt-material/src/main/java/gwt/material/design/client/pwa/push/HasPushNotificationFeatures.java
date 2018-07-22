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
package gwt.material.design.client.pwa.push;

import com.google.gwt.typedarrays.shared.Uint8Array;
import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.client.pwa.push.js.PushManager;
import gwt.material.design.client.pwa.push.js.PushSubscription;

public interface HasPushNotificationFeatures {

    /**
     * Will intialize the subscription value and will check if the
     * Push Notification services is supported by the browser.
     */
    void load(Functions.Func1<PushSubscription> callback);

    /**
     * Will subscribe the user to push notification feature and we take the application server's public key,
     * which is base 64 URL safe encoded, and we convert it to a UInt8Array as this is the expected input
     * of the subscribe call. We provided a method {@link PushNotificationManager#urlB64ToUint8Array(String)} to convert from String
     * to {@link Uint8Array}. The userVisibleOnly parameter is basically an admission that you will show a notification
     * every time a push is sent. At the time of writing this value is required and must be true.
     */
    void subscribe(boolean userVisibleOnly, String applicationServerKey, Functions.Func1<PushSubscription> callback);

    /**
     * Will do the same thing as {@link #subscribe(boolean, String, gwt.material.design.jquery.client.api.Functions.Func1<PushSubscription)} but
     * the param for userVisibleOnly will be set to true.
     */
    void subscribe(String applicationServerKey, Functions.Func1<PushSubscription> callback);

    /**
     * Will unsubscribe the user from receiving push notifications.
     */
    void unsubscribe(Functions.Func callback);

    /**
     * Will return if the user is subscribed or not to Push Notification services.
     */
    boolean isSubscribed();

    /**
     * Will return if the browser supported Push Notification services.
     */
    boolean isSupported();

    /**
     * Return the {@link PushManager} instance.
     */
    PushManager getPushManager();
}
