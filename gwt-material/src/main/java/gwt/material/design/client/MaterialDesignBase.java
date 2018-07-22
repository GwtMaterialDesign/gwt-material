package gwt.material.design.client;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterial
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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;
import gwt.material.design.client.resources.MaterialResources;

import java.util.ArrayList;
import java.util.List;

public class MaterialDesignBase {

    static class FutureResource {
        TextResource resource;
        boolean removeTag, sourceUrl;

        public FutureResource(TextResource resource, boolean removeTag, boolean sourceUrl) {
            this.resource = resource;
            this.removeTag = removeTag;
            this.sourceUrl = sourceUrl;
        }
    }

    static final JQueryProvider jQueryProvider = GWT.create(JQueryProvider.class);
    static List<FutureResource> futureResources;
    static boolean jqueryWarning;

    protected void load() {
        checkJQuery(false);
        if(!isMaterializeLoaded()) {
            injectJs(MaterialResources.INSTANCE.materializeJs());
            injectJs(MaterialResources.INSTANCE.animationJs());
            injectJs(MaterialResources.INSTANCE.appInstallerJs());
        }
        onModuleLoaded();
    }

    protected void onModuleLoaded() {
        if (futureResources != null) {
            for (FutureResource res : futureResources) {
                injectJs(res.resource, res.removeTag, res.sourceUrl);
            }
        }
    }

    public static void injectJs(TextResource resource) {
        injectJs(resource, true, false);
    }

    public static void injectDebugJs(TextResource resource) {
        injectJs(resource, false, true);
    }

    public static void injectJs(TextResource resource, boolean removeTag, boolean sourceUrl) {
        if (!resource.getName().equals("jQuery") && !resource.getName().equals("jQueryDebug") && !checkJQuery(sourceUrl)) {
            // We need to wait for jQuery to load
            if (futureResources == null) {
                futureResources = new ArrayList<>();
            }
            futureResources.add(new FutureResource(resource, removeTag, sourceUrl));
        } else {
            directInjectJs(resource, removeTag, sourceUrl);
        }
    }

    protected static void directInjectJs(TextResource resource) {
        directInjectJs(resource, true, false);
    }

    protected static void directInjectDebugJs(TextResource resource) {
        directInjectJs(resource, false, true);
    }

    protected static void directInjectJs(TextResource resource, boolean removeTag, boolean sourceUrl) {
        String text = resource.getText() + (sourceUrl ?
            "//# sourceURL=" + resource.getName() + ".js" : "");

        // Inject the script resource
        ScriptInjector.fromString(text)
            .setWindow(ScriptInjector.TOP_WINDOW)
            .setRemoveTag(removeTag)
            .inject();
    }

    public static void injectCss(TextResource resource) {
        StyleInjector.inject(resource.getText());
    }

    public static boolean checkJQuery(boolean debug) {
        if (!isjQueryLoaded()) {
            if(isProvidingJQuery()) {
                if (debug) {
                    directInjectDebugJs(jQueryProvider.jQuery());
                } else {
                    directInjectJs(jQueryProvider.jQuery());
                }
            } else if(!jqueryWarning) {
                GWT.log("Warning: GWT Material is not providing JQuery. You must ensure JQuery " +
                        "is loaded manually or use one of the GwtMaterialWithJQuery modules, failing " +
                        "to do so will result in an endless resource loop. This message can be ignored " +
                        "if you are doing so already (message will not appear in production).");
                jqueryWarning = true;
            }
        }
        return isjQueryLoaded();
    }

    public static boolean isProvidingJQuery() {
        return !(jQueryProvider instanceof JQueryProvider.NoJQuery);
    }

    /**
     * Check to see if jQuery is loaded already
     *
     * @return true is jQuery is loaded, false otherwise
     */
    public static native boolean isjQueryLoaded() /*-{
        return (typeof $wnd['jQuery'] !== 'undefined');
    }-*/;

    public static native boolean isMaterializeLoaded() /*-{
        return (typeof $wnd['Materialize'] !== 'undefined')
    }-*/;
}
