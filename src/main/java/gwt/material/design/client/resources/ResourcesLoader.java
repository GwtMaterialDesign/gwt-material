package gwt.material.design.client.resources;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 GwtMaterial
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

import com.google.gwt.core.client.ScriptInjector;

public class ResourcesLoader {

    public ResourcesLoader(MaterialResources resources) {
        injectJQuery(resources);
        injectMaterializeJs(resources);
    }

    private void injectJQuery(MaterialResources resources) {
        if (!isjQueryLoaded()) {
            ScriptInjector.fromString(resources.jQuery().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .inject();
        }
    }

    private void injectMaterializeJs(MaterialResources resources) {
        ScriptInjector.fromString(resources.materializeJs().getText())
                .setWindow(ScriptInjector.TOP_WINDOW)
                .inject();
    }

    /**
     * Check to see if jQuery is loaded already
     *
     * @return true is jQuery is loaded, false otherwise
     */
    private native boolean isjQueryLoaded() /*-{
        return (typeof $wnd['jQuery'] !== 'undefined');
    }-*/;
}
