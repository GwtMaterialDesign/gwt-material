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

/**
 * @author Ben Dol
 */
public class AttributeMixin<T extends UIObject> extends AbstractMixin<T> {

    private String attribute;

    public AttributeMixin(final T widget, String attribute) {
        super(widget);

        this.attribute = attribute;
    }

    public void setAttribute(String value) {
        if(value != null) {
            uiObject.getElement().setAttribute(attribute, value);
        } else {
            uiObject.getElement().removeAttribute(attribute);
        }
    }

    public String getAttribute() {
        return uiObject.getElement().getAttribute(attribute);
    }
}
