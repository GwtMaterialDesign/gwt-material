package gwt.material.design.client.base.mixin;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.base.helper.StyleHelper;

/**
 * @author Ben Dol
 */
public class EnabledMixin<T extends Widget & HasEnabled> extends AbstractMixin<T> implements HasEnabled {
    private static final String DISABLED = "disabled";

    private HandlerRegistration handler;

    public EnabledMixin(final T widget) {
        super(widget);
    }

    @Override
    public boolean isEnabled() {
        return !StyleHelper.containsStyle(uiObject.getStyleName(), "disabled");
    }

    @Override
    public void setEnabled(boolean enabled) {
        setEnabled(uiObject, enabled);
    }

    private void setEnabled(final Widget widget, final boolean enabled) {
        if(!widget.isAttached() && handler == null) {
            handler = widget.addAttachHandler(new AttachEvent.Handler() {
                @Override
                public void onAttachOrDetach(AttachEvent event) {
                    if(event.isAttached()) {
                        applyEnabled(enabled, widget);
                    } else if(handler != null) {
                        handler.removeHandler();
                        handler = null;
                    }
                }
            });
        } else {
            applyEnabled(enabled, widget);
        }
    }

    private void applyEnabled(boolean enabled, UIObject obj) {
        if(enabled) {
            obj.removeStyleName("disabled");
            obj.getElement().removeAttribute(DISABLED);
        } else {
            obj.addStyleName("disabled");
            obj.getElement().setAttribute(DISABLED, "");
        }
    }
}
