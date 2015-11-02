package gwt.material.design.client.custom.mixin;

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
import gwt.material.design.client.custom.HasActivates;
import gwt.material.design.client.custom.HasGrid;

/**
 * @author Ben Dol
 */
public class ActivatesMixin<T extends UIObject & HasActivates> extends AbstractMixin<T> implements HasActivates {

    public ActivatesMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setActivates(String activates) {
        if(activates != null) {
            uiObject.getElement().setAttribute("data-activates", activates);
        } else {
            uiObject.getElement().removeAttribute("data-activates");
        }
    }

    @Override
    public String getActivates() {
        return uiObject.getElement().getAttribute("data-activates");
    }
}
