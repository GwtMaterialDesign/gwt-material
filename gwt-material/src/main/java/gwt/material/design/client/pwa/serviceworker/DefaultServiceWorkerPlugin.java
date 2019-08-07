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

import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.constants.CssName;

class DefaultServiceWorkerPlugin extends ServiceWorkerPlugin {

    public DefaultServiceWorkerPlugin(ServiceWorkerManager serviceWorkerManager) {
        super(serviceWorkerManager);
    }

    @Override
    public boolean onControllerChange(ServiceEvent event) {
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