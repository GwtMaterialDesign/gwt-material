/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorker;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;

class DefaultServiceWorkerPlugin extends ServiceWorkerPlugin {

    public DefaultServiceWorkerPlugin(ServiceWorkerManager serviceWorkerManager) {
        super(serviceWorkerManager);
    }

    @Override
    public boolean onActivated(ServiceEvent event) {
        // Prompt the user with a toast to notify him that the service worker is activated
        // and ready for future offline visits.
        new MaterialToast().toast("Caching complete! Future visits will work offline.");
        return false;
    }

    @Override
    public boolean onControllerChange(ServiceEvent event) {
        Window.Location.reload();
        return false;
    }

    @Override
    public boolean onNewServiceWorkerFound(ServiceEvent event, ServiceWorker serviceWorker) {
        MaterialLink reload = new MaterialLink("REFRESH");
        reload.addClickHandler(clickEvent -> getServiceWorkerManager().skipWaiting(serviceWorker));
        new MaterialToast(reload).toast("New updates available.", 0);
        return false;
    }

    @Override
    public boolean onOnline(ServiceEvent event) {
        RootPanel.get().removeStyleName(CssName.OFFLINE);
        return false;
    }

    @Override
    public boolean onOffline(ServiceEvent event) {
        RootPanel.get().addStyleName(CssName.OFFLINE);
        return false;
    }
}