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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import gwt.material.design.client.pwa.serviceworker.Navigator;

public class PwaManager implements HasPwaFeature {

    private static PwaManager instance = GWT.create(PwaManager.class);
    public static boolean initialized = false;

    @Override
    public void configure() {
        if (!initialized) {
            String pwaManifest = System.getProperty("manifest");
            if (pwaManifest != null && !pwaManifest.isEmpty()) {
                setupManifest(pwaManifest);
            }

            String serviceWorker = System.getProperty("serviceWorker");
            if (serviceWorker != null && !serviceWorker.isEmpty()) {
                setupServiceWorker(serviceWorker);
            }
            initialized = true;
        }
    }

    @Override
    public void setupManifest(String manifestUrl) {
        Element head = Document.get().getElementsByTagName("head").getItem(0);
        Element linkManifest = Document.get().createLinkElement();
        linkManifest.setAttribute("rel", "manifest");
        linkManifest.setAttribute("href", manifestUrl);
        head.appendChild(linkManifest);
    }

    @Override
    public void setupServiceWorker(String serviceWorkerUrl) {
        if (Navigator.serviceWorker != null) {
            Navigator.serviceWorker.register(serviceWorkerUrl)
                    .then(arg -> {
                        GWT.log("Registered service worker successfully");
                        return null;
                    });
        } else {
            GWT.log("Service worker is not supported by this browser.");
        }
    }

    @Override
    public boolean isOnline() {
        return Navigator.onLine;
    }

    public static PwaManager getInstance() {
        return instance;
    }
}
