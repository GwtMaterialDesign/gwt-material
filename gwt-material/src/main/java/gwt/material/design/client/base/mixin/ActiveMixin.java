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
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasActive;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;

public class ActiveMixin<T extends UIObject & HasActive> extends AbstractMixin<T> implements HasActive {

    private boolean active;
    private MaterialWidget target;

    public ActiveMixin(final T widget) {
        super(widget);
    }

    public ActiveMixin(final T widget, final MaterialWidget target) {
        super(widget);
        this.target = target;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
        if (target != null) {
            applyActiveStyle(target, active);
        } else {
            applyActiveStyle((Widget) uiObject, active);
        }
    }

    protected void applyActiveStyle(Widget widget, boolean active) {
        if (active) {
            widget.removeStyleName(CssName.INACTIVE);
            widget.addStyleName(CssName.ACTIVE);
        } else {
            widget.removeStyleName(CssName.ACTIVE);
            widget.addStyleName(CssName.INACTIVE);
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
