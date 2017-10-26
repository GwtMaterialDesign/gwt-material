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
import gwt.material.design.client.pwa.PwaFeature;
import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.pwa.serviceworker.constants.State;
import gwt.material.design.jscore.client.api.Navigator;
import gwt.material.design.jscore.client.api.serviceworker.ServiceWorker;
import gwt.material.design.jscore.client.api.serviceworker.ServiceWorkerRegistration;

public abstract class ServiceWorkerManager extends PwaFeature implements IsServiceWorker {

    private State state;
    private String url;
    private ServiceWorkerRegistration _registration;

    public ServiceWorkerManager(String url) {
        this.url = url;
    }

    public ServiceWorkerManager(PwaManager manager) {
        super(manager);
    }

    @Override
    public void load() {
        load(url);
    }

    @Override
    public void load(String url) {
        if (url != null && Navigator.serviceWorker != null) {
            registerServiceWorker(url);
        } else {
            GWT.log("Service worker is not supported by this browser.");
        }
    }

    protected void registerServiceWorker(String url) {
        this.url = url;
        Navigator.serviceWorker.register(url).then(registration -> {
            GWT.log("Service Worker Registered");
            _registration = (ServiceWorkerRegistration) registration;
            _registration.onupdatefound = e -> {

                ServiceWorker newServiceWorker = this._registration.installing;
                newServiceWorker.onstatechange = e1 -> {
                    String currentState = newServiceWorker.state;
                    if (currentState != null && !currentState.isEmpty()) {
                        state = State.fromStyleName(currentState);
                        onStateChange(state, newServiceWorker);
                    }
                    return true;
                };
                return true;
            };

            return null;
        }, error -> {
            GWT.log("ServiceWorker registration failed: " + error);
            return null;
        });
    }

    @Override
    public void unload() {
        _registration.unregister();
    }

    @Override
    public void reload() {
        unload();
        load(url);
    }

    public void update() {
        _registration.update();
    }

    public boolean isOnline() {
        return Navigator.onLine;
    }

    public boolean isOfflineReady() {
        return state.equals(State.ACTIVATED) && getServiceWorker() == null;
    }

    public boolean isNewCacheReleased() {
        return state.equals(State.INSTALLED) && getServiceWorker() != null;
    }

    public ServiceWorker getServiceWorker() {
        return Navigator.serviceWorker.controller;
    }

    public abstract void onStateChange(State state, ServiceWorker newServiceWorker);
}
