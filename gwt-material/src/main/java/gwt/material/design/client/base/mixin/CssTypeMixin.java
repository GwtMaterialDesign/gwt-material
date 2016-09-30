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
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.constants.CssType;

/**
 * @author Ben Dol
 */
public class CssTypeMixin<T extends CssType, H extends UIObject & HasType<T>> extends AbstractMixin<H> implements HasType<T> {

    private T type;
    private UIObject target;

    public CssTypeMixin(final H widget) {
        super(widget);
    }

    public CssTypeMixin(final H widget, UIObject target) {
        super(widget);
        this.target = target;
    }

    @Override
    public void setType(T type) {
        if (this.type != null && !this.type.getCssName().isEmpty()) {
            if (target != null) {
                target.removeStyleName(this.type.getCssName());
            } else {
                uiObject.removeStyleName(this.type.getCssName());
            }
        }
        this.type = type;

        if (type != null && !type.getCssName().isEmpty()) {
            if (target != null) {
                target.addStyleName(type.getCssName());
            } else {
                uiObject.addStyleName(type.getCssName());
            }
        }
    }

    @Override
    public T getType() {
        return type;
    }
}