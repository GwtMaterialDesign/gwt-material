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
package gwt.material.design.client.base.pwa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

public class PwaManager implements HasPwaFeature {

    private static PwaManager instance = GWT.create(PwaManager.class);
    public static boolean initialized = false;

    @Override
    public void configure() {
        if (!initialized) {
            String pwaManifest = System.getProperty("manifest");
            if (pwaManifest != null && !pwaManifest.isEmpty()) {
                PwaManager.getInstance().setupManifest(pwaManifest);
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

    public static PwaManager getInstance() {
        return instance;
    }
}
