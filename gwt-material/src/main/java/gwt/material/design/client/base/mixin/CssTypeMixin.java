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

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.constants.CssType;

/**
 * @author Ben Dol
 */
public class CssTypeMixin<T extends CssType, H extends UIObject & HasType<T>> extends AbstractMixin<H> implements HasType<T> {

    private T type;

    public CssTypeMixin(final H widget) {
        super(widget);
    }

    @Override
    public void setType(T type) {
        if(this.type != null && !this.type.getCssName().isEmpty()) {
            uiObject.removeStyleName(this.type.getCssName());
        }
        this.type = type;

        if(type != null && !type.getCssName().isEmpty()) {
            uiObject.addStyleName(type.getCssName());
        }
    }

    @Override
    public T getType() {
        return type;
    }
}
