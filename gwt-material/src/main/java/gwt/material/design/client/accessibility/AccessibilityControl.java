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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.resources.MaterialDebugResources;
import gwt.material.design.client.resources.MaterialResources;
import gwt.material.design.jquery.client.api.JQuery;

import java.util.HashMap;
import java.util.Map;

import static gwt.material.design.client.js.JsMaterialElement.$;

public class AccessibilityControl {

    protected static final String FOCUS_VISIBILITY_CLASSNAME = "js-focus-visible";
    protected static final String FOCUS_VISIBILITY_ATTRIBUTE = "data-js-focus-visible";
    protected static final String FOCUS_VISIBLE_WIDGET = "focus-visible";
    protected static final String DATA_FOCUS_ADDED_ATTRIBUTE = "data-focus-visible-added";
    public static AccessibilityControl instance;
    protected JavaScriptObject resourceUrlObject;
    protected Map<HandlerRegistration, Widget> accessibilityHandlerRegistration;
    protected boolean enabled;
    protected boolean loaded;

    public AccessibilityControl() {
        accessibilityHandlerRegistration = new HashMap<>();
    }

    public void load(boolean debug) {
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

    protected void injectJs(TextResource resource) {
        String text = resource.getText() + ("//# sourceURL=" + resource.getName() + ".js");

        // Inject the script resource
        resourceUrlObject = ScriptInjector.fromString(text)
            .setWindow(ScriptInjector.TOP_WINDOW)
            .inject();
    }

    /**
     * This will register the accessibility control for a widget provided with keycodes
     */
    public void registerWidget(MaterialWidget widget, int key) {
        registerWidget(widget, key, null);
    }

    /**
     * This will register the accessibility control for a widget provided with keycodes and
     * TriggerCallback
     */
    public void registerWidget(MaterialWidget widget, int key, TriggerCallback callback) {
        if (widget != null) {
            widget.setTabIndex(isEnabled() ? 0 : -1);
            HandlerRegistration handlerRegistration = widget.registerHandler(widget.addKeyUpHandler(event -> {
                if (isEnabled() && event.getNativeKeyCode() == key) {
                    if (callback != null) {
                        callback.call(event);
                    } else {
                        JQuery.$(widget.getElement()).click();
                    }
                }
            }));
            accessibilityHandlerRegistration.put(handlerRegistration, widget);
        }
    }

    /**
     * This will unregister any accessibility control to a widget
     * @param widget
     */
    public void unregisterWidget(Widget widget) {
        if (widget != null) {
            if (widget.getElement().hasClassName(FOCUS_VISIBLE_WIDGET)) {
                widget.removeStyleName(FOCUS_VISIBLE_WIDGET);
            }

            if (widget.getElement().hasAttribute(DATA_FOCUS_ADDED_ATTRIBUTE)) {
                widget.getElement().removeAttribute(DATA_FOCUS_ADDED_ATTRIBUTE);
            }
            accessibilityHandlerRegistration.forEach((handlerRegistration, widget1) -> {

            });
        }
    }

    public void unload() {
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


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static AccessibilityControl get() {
        if (instance == null) {
            instance = new AccessibilityControl();
        }
        return instance;
    }
}
