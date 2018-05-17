/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasFieldTypes;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.FieldType;
import gwt.material.design.jscore.client.api.core.Element;
import gwt.material.design.jscore.client.api.media.WebkitURL;

/**
 * @author kevzlou7979
 */
public class FieldTypeMixin<T extends UIObject & HasFieldTypes> extends AbstractMixin<T> implements HasFieldTypes {

    protected CssNameMixin<T, FieldType> cssNameMixin;
    protected Widget label;
    protected Widget field;
    protected Widget errorLabel;

    public FieldTypeMixin(T uiObject) {
        super(uiObject);
    }

    public FieldTypeMixin(T uiObject, Widget label, Widget field) {
        super(uiObject);

        this.label = label;
        this.field = field;
    }

    public FieldTypeMixin(T uiObject, Widget label, Widget field, Widget errorLabel) {
        this(uiObject, label, field);

        this.errorLabel = errorLabel;
    }

    @Override
    public void setFieldType(FieldType type) {
        getCssNameMixin().setCssName(type);
    }

    @Override
    public FieldType getFieldType() {
        return getCssNameMixin().getCssName();
    }

    @Override
    public void setLabelWidth(double percentWidth) {
        if (label != null) {
            label.getElement().getStyle().setWidth(percentWidth, Style.Unit.PCT);
            errorLabel.getElement().getStyle().setPaddingLeft(percentWidth, Style.Unit.PCT);
        } else {
            GWT.log("Label is not defined", new IllegalStateException());
        }
    }

    @Override
    public void setFieldWidth(double percentWidth) {
        if (field != null) {
            field.getElement().getStyle().setWidth(percentWidth, Style.Unit.PCT);
        } else {
            GWT.log("Field is not defined", new IllegalStateException());
        }
    }

    protected CssNameMixin<T, FieldType> getCssNameMixin() {
        if (cssNameMixin == null) {
            cssNameMixin = new CssNameMixin<>(uiObject);
        }
        return cssNameMixin;
    }
}
