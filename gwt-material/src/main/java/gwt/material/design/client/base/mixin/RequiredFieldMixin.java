/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasRequiredField;

/**
 * A mixin for identifying mandatory field and if {@link #setRequired(boolean)} is enabled then
 * we marked the field to be mandatory by not allowing null values and will validate the field on blur.
 *
 * @author kevzlou7979
 */
public class RequiredFieldMixin <T extends AbstractValueWidget & HasRequiredField, H extends UIObject>
        extends AbstractMixin<T> implements HasRequiredField {

    private final String REQUIRED = "required";
    private H targetLabel;
    private boolean required;
    private ToggleStyleMixin<UIObject> toggleStyleMixin;

    public RequiredFieldMixin(T uiObject) {
        super(uiObject);
    }

    public RequiredFieldMixin(T uiObject, H targetLabel) {
        this(uiObject);

        this.targetLabel = targetLabel;
    }

    @Override
    public void setRequired(boolean required) {
        this.required = required;

        uiObject.setValidateOnBlur(required);
        uiObject.setAllowBlank(!required);

        if (targetLabel != null) {
            getToggleStyleMixin().setOn(required);
        }
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public ToggleStyleMixin<UIObject> getToggleStyleMixin() {
        if (toggleStyleMixin == null) {
            toggleStyleMixin = new ToggleStyleMixin(targetLabel, REQUIRED);
        }
        return toggleStyleMixin;
    }
}
