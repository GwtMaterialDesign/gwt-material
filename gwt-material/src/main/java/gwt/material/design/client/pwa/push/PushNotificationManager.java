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

import com.google.gwt.core.client.GWT;
import gwt.material.design.client.pwa.push.helper.PushCryptoHelper;
import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.client.pwa.push.js.PushManager;
import gwt.material.design.client.pwa.push.js.PushSubscription;
import gwt.material.design.client.pwa.push.js.PushSubscriptionOptions;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;

/**
 * A class that manage all Push Notification Services to the Service Worker.
 *
 * @author kevzlou7979@gmail.com
 */
public class PushNotificationManager implements HasPushNotificationFeatures {

    private ServiceWorkerRegistration registration;
    private boolean subscribed;

    public PushNotificationManager(ServiceWorkerRegistration registration) {
        this.registration = registration;
    }

    @Override
    public void load(Functions.Func1<PushSubscription> callback) {
        if (isSupported()) {
            getPushManager().getSubscription().then(object -> {
                subscribed = object != null;
                PushSubscription subscription = (PushSubscription) object;
                callback.call(subscription);
                return true;
            });
        }
    }

    @Override
    public void subscribe(boolean userVisibleOnly, String applicationServerKey, Functions.Func1<PushSubscription> callback) {
        PushSubscriptionOptions options = new PushSubscriptionOptions();
        options.userVisibleOnly = userVisibleOnly;
        options.applicationServerKey = PushCryptoHelper.Base64ToArrayBuffer(applicationServerKey);
        getPushManager().subscribe(options).then(object -> {
            PushSubscription subscription = (PushSubscription) object;
            subscribed = subscription != null;
            callback.call(subscription);
            return true;
        });
    }

    @Override
    public void subscribe(String applicationServerKey, Functions.Func1<PushSubscription> callback) {
        subscribe(true, applicationServerKey, callback);
    }

    @Override
    public void unsubscribe(Functions.Func callback) {
        getPushManager().getSubscription().then(object -> {
            PushSubscription subscription;
            if (object != null) {
                subscription = (PushSubscription) object;
                subscription.unsubscribe().then(o -> {
                    callback.call();
                     return true;
                });
                subscribed = false;
            }
            return true;
        });
    }

    @Override
    public boolean isSubscribed() {
        return subscribed;
    }

    @Override
    public boolean isSupported() {
        return getPushManager() != null;
    }

    @Override
    public PushManager getPushManager() {
        if (registration != null) {
            return registration.pushManager;
        } else {
            GWT.log("Service worker is not yet registered", new IllegalStateException());
        }
        return null;
    }
}
