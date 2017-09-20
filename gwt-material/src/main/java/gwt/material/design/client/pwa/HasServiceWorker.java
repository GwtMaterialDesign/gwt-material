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
package gwt.material.design.client.pwa;

import gwt.material.design.jscore.client.api.serviceworker.ServiceWorker;

public interface HasServiceWorker {

    /**
     * Set up the service worker configuration file (service-worker.js) to enable
     * the Offline Feature of your GMD PWA App.
     * @param serviceWorkerUrl
     */
    PwaManager setServiceWorkerUrl(String serviceWorkerUrl);

    /**
     * Unregister the Service Worker
     */
    void unRegisterServiceWorker();

    /**
     * Checks the server for an updated version of the service worker without consulting caches.
     */
    void updateServiceWorker();

    ServiceWorker getServiceWorker();
}
