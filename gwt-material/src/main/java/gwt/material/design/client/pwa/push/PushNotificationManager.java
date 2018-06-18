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
import elemental2.dom.PushManager;
import elemental2.dom.PushSubscription;
import elemental2.dom.PushSubscriptionOptions;
import elemental2.dom.ServiceWorkerRegistration;
import gwt.material.design.jquery.Functions;

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
            getPushManager().getSubscription().then(subscription -> {
                subscribed = subscription != null;
                callback.call(subscription);
                return null;
            });
        }
    }

    @Override
    public void subscribe(boolean userVisibleOnly, String applicationServerKey, Functions.Func1<PushSubscription> callback) {
        PushSubscriptionOptions options = PushSubscriptionOptions.create();
        options.setUserVisibleOnly(userVisibleOnly);
        //options.applicationServerKey = PushCryptoHelper.Base64ToArrayBuffer(applicationServerKey);
        getPushManager().subscribe(options).then(subscription -> {
            subscribed = subscription != null;
            callback.call(subscription);
            return null;
        });
    }

    @Override
    public void subscribe(String applicationServerKey, Functions.Func1<PushSubscription> callback) {
        subscribe(true, applicationServerKey, callback);
    }

    @Override
    public void unsubscribe(Functions.Func callback) {
        getPushManager().getSubscription().then(subscription -> {
            if (subscription != null) {
                subscription.unsubscribe().then(o -> {
                    callback.call();
                    return null;
                });
                subscribed = false;
            }
            return null;
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
            return registration.getPushManager();
        } else {
            GWT.log("Service worker is not yet registered", new IllegalStateException());
        }
        return null;
    }
}
