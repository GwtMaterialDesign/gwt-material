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

import gwt.material.design.client.base.HasActivates;

import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Ben Dol
 */
public class ActivatesMixin<T extends UIObject & HasActivates> extends AttributeMixin<T> implements HasActivates {

    public ActivatesMixin(final T widget) {
        super(widget, "data-activates");
    }

    @Override
    public void setActivates(String activates) {
        super.setAttribute(activates);
    }

    @Override
    public String getActivates() {
        return super.getAttribute();
    }
}
