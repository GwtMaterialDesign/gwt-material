/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2023 GwtMaterialDesign
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
package gwt.material.design.client.base.mixin;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasDependency;
import gwt.material.design.client.base.JsLoader;
import gwt.material.design.client.ui.MaterialToast;

import java.util.LinkedHashMap;
import java.util.Map;

public class DependencyMixin<T extends JsLoader> implements HasDependency {

    private static Map<Class<?>, Boolean> libs = new LinkedHashMap();
    private boolean debugMode = true;
    private final T loader;
    private DependencyCallback callback;

    public DependencyMixin(T loader) {
        this.loader = loader;
    }

    @Override
    public void install(TextResource minifiedJs, TextResource debugJs, TextResource minifiedCss, TextResource debugCss) {

        if (!isDependencyLoaded(loader.getClass())) {
            installJs(minifiedJs, debugJs, new DependencyCallback() {
                @Override
                public void onSuccess() {
                    setDependencyLoaded(loader.getClass(), true);
                    loader.load();
                }

                @Override
                public void onError(String error) {
                    MaterialToast.fireToast("Error injecting the Dependency url : " + error);
                }
            });
            installCss(minifiedCss, debugCss);
        } else {
            loader.load();
        }

    }

    @Override
    public void install(TextResource minifiedJs, TextResource debugJs) {
        install(minifiedJs, debugJs, null, null);
    }

    protected void installJs(TextResource minified, TextResource debug, DependencyCallback callback) {
        if (!debugMode) {
            installJs(minified, true, false, callback);
        } else {
            installJs(debug, false, true, callback);
        }
    }

    protected void installJs(TextResource resource, boolean removeTag, boolean sourceUrl, DependencyCallback callback) {
        this.callback = callback;
        try {
            if (libs.get(loader.getClass()) == null || !libs.get(loader.getClass())) {
                libs.put(loader.getClass(), false);

                String text = resource.getText() + (sourceUrl ?
                        "//# sourceURL=" + resource.getName() + ".js" : "");

                // Inject the script resource
                ScriptInjector.fromString(text)
                        .setWindow(ScriptInjector.TOP_WINDOW)
                        .setRemoveTag(removeTag)
                        .inject();
                if (callback != null) callback.onSuccess();

            }
        } catch (RuntimeException e) {
            if (callback != null) callback.onError(e.getMessage());
        }
    }

    public void installCss(TextResource minifiedCss, TextResource debugCss) {
        if (minifiedCss != null && debugCss != null) {
            String resource = null;
            if (!debugMode) {
                resource = minifiedCss.getText();
            } else {
                resource = debugCss.getText();
            }
            StyleInjector.inject(resource);
        }
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    @Override
    public void setDebug(boolean debug) {
        this.debugMode = debug;
    }

    @Override
    public DependencyCallback getCallback() {
        return callback;
    }

    @Override
    public boolean isDependencyLoaded(Class<?> loaderClass) {
        return libs != null && libs.get(loaderClass) != null ? libs.get(loaderClass) : false;
    }

    @Override
    public void setDependencyLoaded(Class<?> loaderClass, boolean loaded) {
        libs.put(loaderClass, loaded);
    }
}
