/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
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
package gwt.material.design.client.accessibility;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.resources.MaterialDebugResources;
import gwt.material.design.client.resources.MaterialResources;

import static gwt.material.design.client.js.JsMaterialElement.$;

public class AccessibilityControl {

    protected static final String FOCUS_VISIBILITY_CLASSNAME = "js-focus-visible";
    protected static final String FOCUS_VISIBILITY_ATTRIBUTE = "data-js-focus-visible";
    protected static final String FOCUS_VISIBLE_WIDGET = "focus-visible";
    protected static final String DATA_FOCUS_ADDED_ATTRIBUTE = "data-focus-visible-added";
    protected static JavaScriptObject resourceUrlObject;
    protected static boolean loaded;

    public static void load(boolean debug) {
        TextResource resource;
        if (!loaded) {
            if (debug) {
                resource = MaterialDebugResources.INSTANCE.focusVisibleJsDebug();
            } else {
                resource = MaterialResources.INSTANCE.focusVisibleJs();
            }
            injectJs(resource);
            loaded = true;
        }
    }

    protected static void injectJs(TextResource resource) {
        String text = resource.getText() + ("//# sourceURL=" + resource.getName() + ".js");

        // Inject the script resource
        resourceUrlObject = ScriptInjector.fromString(text)
            .setWindow(ScriptInjector.TOP_WINDOW)
            .inject();
    }

    public static void unloadWidgetAccessibility(Widget widget) {
        if (widget != null) {
            if (widget.getElement().hasClassName(FOCUS_VISIBLE_WIDGET)) {
                widget.removeStyleName(FOCUS_VISIBLE_WIDGET);
            }

            if (widget.getElement().hasAttribute(DATA_FOCUS_ADDED_ATTRIBUTE)) {
                widget.getElement().removeAttribute(DATA_FOCUS_ADDED_ATTRIBUTE);
            }
        }
    }

    public static void unload() {
        JsMaterialElement html = $("html");
        if (html != null) {
            if (html.hasClass(FOCUS_VISIBILITY_CLASSNAME)) {
                html.removeClass(FOCUS_VISIBILITY_CLASSNAME);
            }
            html.removeAttr(FOCUS_VISIBILITY_ATTRIBUTE);
        }

        if (resourceUrlObject != null) {
            JsMaterialElement scriptTag = $(resourceUrlObject.cast());
            scriptTag.remove();
        }
        loaded = false;
    }
}
