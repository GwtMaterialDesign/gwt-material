package gwt.material.design.client;

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
import com.google.gwt.resources.client.TextResource;
import gwt.material.design.client.resources.MaterialResources;

public class MaterialDesignBase {

    public void load() {
        inject(MaterialResources.INSTANCE.materializeJs());
        inject(MaterialResources.INSTANCE.animationJs());
        inject(MaterialResources.INSTANCE.shrinkJs());
    }

    protected void inject(TextResource resource) {
        inject(resource, true, false);
    }

    protected void injectDebug(TextResource resource) {
        inject(resource, false, true);
    }

    protected void inject(TextResource resource, boolean removeTag, boolean sourceUrl) {
        String text = resource.getText() +
            (sourceUrl ? "//# sourceURL="+resource.getName()+".js" : "");

        // Inject the script resource
        ScriptInjector.fromString(text)
            .setWindow(ScriptInjector.TOP_WINDOW)
            .setRemoveTag(removeTag)
            .inject();
    }

    /**
     * Check to see if jQuery is loaded already
     *
     * @return true is jQuery is loaded, false otherwise
     */
    public static native boolean isjQueryLoaded() /*-{
        return (typeof $wnd['jQuery'] !== 'undefined');
    }-*/;
}
