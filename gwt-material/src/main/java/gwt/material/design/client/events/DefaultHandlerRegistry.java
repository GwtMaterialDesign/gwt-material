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
package gwt.material.design.client.events;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class DefaultHandlerRegistry implements HandlerRegistry {

    private final Widget widget;
    private List<HandlerRegistration> handlers;
    private HandlerRegistration attachHandler;

    private boolean clearOnUnload = true;
    private boolean loaded = false;

    public DefaultHandlerRegistry(IsWidget widget) {
        this(widget, true);
    }

    public DefaultHandlerRegistry(IsWidget widget, boolean clearOnUnload) {
        this(widget.asWidget(), clearOnUnload);
    }

    public DefaultHandlerRegistry(Widget widget, boolean clearOnUnload) {
        assert widget != null : "Widget cannot be null";
        this.widget = widget;
        this.clearOnUnload = clearOnUnload;

        load();
    }

    public void load() {
        assert !loaded : "Cannot load an already loaded handler registry.";
        attachHandler = widget.asWidget().addAttachHandler(event -> {
            // Detach event
            if(clearOnUnload && !event.isAttached()) {
                clearHandlers();
            }
        });
    }

    public void unload() {
        if(attachHandler != null) {
            attachHandler.removeHandler();
            attachHandler = null;
        }

        clearHandlers();
        loaded = false;
    }

    @Override
    public HandlerRegistration registerHandler(HandlerRegistration handler) {
        if(handlers == null) {
            handlers = new ArrayList<>();
        }

        handlers.add(handler);
        return handler;
    }

    @Override
    public void removeHandler(HandlerRegistration registration) {
        if (registration != null) {
            registration.removeHandler();
        }
    }

    @Override
    public void clearHandlers() {
        if(handlers != null) {
            for(HandlerRegistration handler : handlers) {
                removeHandler(handler);
            }
            handlers.clear();
        }
    }

    public void setClearOnUnload(boolean clearOnUnload) {
        this.clearOnUnload = clearOnUnload;
    }

    public boolean isClearOnUnload() {
        return clearOnUnload;
    }

    @Override
    public Widget asWidget() {
        return widget;
    }
}
