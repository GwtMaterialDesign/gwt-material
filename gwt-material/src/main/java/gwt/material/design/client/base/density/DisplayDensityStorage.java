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
package gwt.material.design.client.base.density;

import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;

public class DisplayDensityStorage {

    public static final String STORAGE_ID = "app_density";
    private static Storage storage = Storage.getLocalStorageIfSupported();

    public static void apply(DisplayDensity density) {
        if (Storage.isLocalStorageSupported()) {
            storage.setItem(STORAGE_ID, density.getCssName());
            Window.Location.reload();
        } else {
            GWT.log("localStorage API is not supported by your browser.");
        }
    }

    public static DisplayDensity get() {
        if (Storage.isLocalStorageSupported()) {
            Storage storage = Storage.getLocalStorageIfSupported();
            return DisplayDensity.fromStyleName(storage.getItem(STORAGE_ID));
        } else {
            return DisplayDensity.DEFAULT;
        }
    }
}
