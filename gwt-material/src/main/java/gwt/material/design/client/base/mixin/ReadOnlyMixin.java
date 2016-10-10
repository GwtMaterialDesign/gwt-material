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

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasReadOnly;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.helper.StyleHelper;
import gwt.material.design.client.constants.CssName;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class ReadOnlyMixin<T extends UIObject, H extends UIObject> extends AbstractMixin<T> implements HasReadOnly {

    private H target;
    private boolean toggle;

    public ReadOnlyMixin(T uiObject, H target) {
        super(uiObject);
        this.target = target;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        uiObject.removeStyleName(CssName.READ_ONLY);
        if (readOnly) {
            uiObject.addStyleName(CssName.READ_ONLY);
            target.getElement().setAttribute("disabled", "");
        } else {
            target.getElement().removeAttribute("disabled");
            uiObject.removeStyleName(CssName.READ_ONLY);
        }
    }

    @Override
    public boolean isReadOnly() {
        return StyleHelper.containsStyle(uiObject.getStyleName(), CssName.READ_ONLY);
    }

    @Override
    public void setToggleReadOnly(boolean value) {
        uiObject.removeStyleName(CssName.READ_ONLY_TOGGLE);
        if (value) {
            uiObject.addStyleName(CssName.READ_ONLY_TOGGLE);
            if (uiObject instanceof MaterialWidget) {
                ((MaterialWidget) uiObject).setEnabled(true);
            }
            $(target).off("mousedown");
            $(uiObject).mousedown((e, param1) -> {
                setReadOnly(false);
                return true;
            });

            $(target).off("blur");
            $(target).blur((e, param1) -> {
                setReadOnly(true);
                return true;
            });
        }
    }

    @Override
    public boolean isToggleReadOnly() {
        return StyleHelper.containsStyle(uiObject.getStyleName(), CssName.READ_ONLY_TOGGLE);
    }
}