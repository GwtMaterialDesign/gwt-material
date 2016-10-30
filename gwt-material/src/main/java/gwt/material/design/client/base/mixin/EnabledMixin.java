/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.helper.StyleHelper;
import gwt.material.design.client.constants.CssName;

/**
 * @author Ben Dol
 * @author kevzlou7979
 */
public class EnabledMixin<T extends Widget & HasEnabled> extends AbstractMixin<T> implements HasEnabled {
    private static final String DISABLED = "disabled";

    private HandlerRegistration handler;

    public EnabledMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setUiObject(T uiObject) {
        super.setUiObject(uiObject);

        // Clean up previous handler
        if (handler != null) {
            handler.removeHandler();
            handler = null;
        }
    }

    @Override
    public boolean isEnabled() {
        return !StyleHelper.containsStyle(uiObject.getStyleName(), CssName.DISABLED);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (!uiObject.isAttached() && handler == null) {
            handler = uiObject.addAttachHandler(event -> {
                if (event.isAttached()) {
                    applyEnabled(enabled, uiObject);
                } else if (handler != null) {
                    handler.removeHandler();
                    handler = null;
                }
            });
        } else {
            applyEnabled(enabled, uiObject);
        }
    }

    public void setEnabled(MaterialWidget widget, boolean enabled) {
        setEnabled(enabled);
        for (Widget child : widget.getChildren()) {
            if (child instanceof MaterialWidget) {
                ((MaterialWidget) child).setEnabled(enabled);
                setEnabled((MaterialWidget) child, enabled);
            }
        }
    }

    private void applyEnabled(boolean enabled, UIObject obj) {
        if (enabled) {
            obj.removeStyleName(CssName.DISABLED);
            obj.getElement().removeAttribute(DISABLED);
        } else {
            obj.addStyleName(CssName.DISABLED);
            obj.getElement().setAttribute(DISABLED, "");
        }
    }
}
