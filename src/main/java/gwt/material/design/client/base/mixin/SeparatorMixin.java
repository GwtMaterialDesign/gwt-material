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
import gwt.material.design.client.base.HasSeparator;

/**
 * @author Ben Dol
 */
public class SeparatorMixin<T extends UIObject & HasSeparator> extends AbstractMixin<T> implements HasSeparator {

    public SeparatorMixin(final T uiObject) {
        super(uiObject);
    }

    @Override
    public void setSeparator(boolean separator) {
        if (separator) {
            uiObject.getElement().getStyle().setProperty("borderBottom", "1px solid #e9e9e9");
        } else {
            uiObject.getElement().getStyle().setProperty("borderBottom", "");
        }
    }

    @Override
    public boolean isSeparator() {
        String borderBottom = uiObject.getElement().getStyle().getProperty("borderBottom");
        return borderBottom != null && !borderBottom.isEmpty();
    }
}
