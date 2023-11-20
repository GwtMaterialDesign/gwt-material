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

import java.util.LinkedHashMap;
import java.util.Map;

public class DependencyMixin implements HasDependency {

    private static Map<Class<? extends Widget>, Boolean> libs = new LinkedHashMap();
    private boolean debugMode = false;
    private DependencyCallback callback;
    private final Widget widget;

    public DependencyMixin(Widget widget) {
        this.widget = widget;
    }

    @Override
    public void onDependencyLoaded() {

    }

    public void installJs(TextResource minified, TextResource debug, DependencyCallback callback) {
        if (!debugMode) {
            installJs(minified, true, false, callback);
        } else {
            installJs(debug, false, true, callback);
        }
    }

    public void installJs(TextResource resource, boolean removeTag, boolean sourceUrl, DependencyCallback callback) {
        try {
            if (libs.get(widget.getClass()) == null || !libs.get(widget.getClass())) {
                libs.put(widget.getClass(), false);

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

    public void installCss(TextResource minifiedCss, TextResource debug) {
        String resource = null;
        if (!debugMode) {
            resource = minifiedCss.getText();
        } else {
            resource = debug.getText();
        }
        StyleInjector.inject(resource);
    }

    public DependencyCallback getCallback() {
        return callback;
    }

    public void setCallback(DependencyCallback callback) {
        this.callback = callback;
    }

    public boolean isDependencyLoaded(Class<? extends Widget> widgetClass) {
        return libs != null && libs.get(widgetClass) != null ? libs.get(widgetClass) : false;
    }
}
