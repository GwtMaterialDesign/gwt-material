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

import gwt.material.design.client.pwa.manifest.HasManifest;
import gwt.material.design.jscore.client.api.serviceworker.HasServiceWorker;

public interface HasPwaFeature extends HasServiceWorker, HasManifest {

    /**
     * Configures the whole application structure of progressive web app to be
     * integrated to Gwt Material Design
     */
    void load();

    /**
     * Reloads the PWA Feature and reconfigure it.
     */
    void reload();

    /**
     * Unregisters / Uninstall the PWA Feature into
     * your GMD App.
     */
    void unLoad();

    /**
     * Returns a network status of your GMD PWA App.
     * @return true if your network status is Online.
     */
    boolean isOnline();
}
