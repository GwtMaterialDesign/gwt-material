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
import gwt.material.design.client.base.HasFieldSensitivity;
import gwt.material.design.client.base.HasInputType;
import gwt.material.design.client.constants.InputType;

/**
 * @author Ben Dol
 */
public class FieldSensitivityMixin<T extends UIObject & HasFieldSensitivity & HasInputType> extends AbstractMixin<T> implements HasFieldSensitivity {

    protected final InputType defaultInputType;

    public FieldSensitivityMixin(T uiObject) {
        super(uiObject);

        defaultInputType = uiObject.getType();
    }

    @Override
    public void setSensitive(boolean sensitive) {
        uiObject.setType(sensitive ? InputType.PASSWORD : defaultInputType);
    }

    @Override
    public boolean isSensitive() {
        return false;
    }
}
