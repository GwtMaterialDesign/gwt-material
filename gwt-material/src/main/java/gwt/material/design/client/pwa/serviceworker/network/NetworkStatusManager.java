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
package gwt.material.design.client.pwa.serviceworker.network;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import gwt.material.design.client.pwa.serviceworker.network.events.NetworkStatusChangeEvent;
import gwt.material.design.jquery.client.api.JQuery;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * A Network Status Manager that listens to any Online / Offline network status updates.
 *
 * @author kevzlou7979@gmail.com
 */
public class NetworkStatusManager extends SimpleEventBus {

    public NetworkStatusManager() { }

    public void load() {
        $(JQuery.window()).on("online", (e, param1) -> {
            NetworkStatusChangeEvent.fire(this, true);
            return true;
        });

        $(JQuery.window()).on("offline", (e, param1) -> {
            NetworkStatusChangeEvent.fire(this, false);
            return true;
        });
    }

    public void unload() {
        $(JQuery.window()).off("online");
        $(JQuery.window()).off("offline");
    }

    public HandlerRegistration addNetworkStatusChangeEvent(NetworkStatusChangeEvent.NetworkStatusChangeHandler handler) {
        return addHandler(NetworkStatusChangeEvent.TYPE, handler);
    }
}
