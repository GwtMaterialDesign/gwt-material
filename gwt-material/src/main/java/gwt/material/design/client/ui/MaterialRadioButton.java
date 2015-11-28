package gwt.material.design.client.ui;

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

import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RadioButton;
import gwt.material.design.client.base.HasGrid;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.TypeWidget;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.base.mixin.GridMixin;
import gwt.material.design.client.constants.RadioButtonType;

//@formatter:off

/**
* Material Radio button has two types 
* - NO-GAP <br>
* - GAP
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* <m:MaterialRadioButton text="Sample"/>
* <m:MaterialRadioButton type="GAP"/>
}
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material Radio Button</a>
* @author kevzlou7979
*/
public class MaterialRadioButton extends RadioButton implements HasGrid, HasType<RadioButtonType> {

    private CssTypeMixin<RadioButtonType, TypeWidget<RadioButtonType>> typeMixin;
    private final GridMixin<MaterialRadioButton> gridMixin = new GridMixin<>(this);

    public MaterialRadioButton() {
        super("");

        // Since the input element handles the type
        // styles we need to override the mixin.
        typeMixin = new CssTypeMixin<>(
            new TypeWidget<RadioButtonType>(DOM.getChild(getElement(), 0)));
    }

    public MaterialRadioButton(String name, SafeHtml label, Direction dir) {
        super(name, label, dir);
    }

    public MaterialRadioButton(String name, SafeHtml label, DirectionEstimator directionEstimator) {
        super(name, label, directionEstimator);
    }

    public MaterialRadioButton(String name, SafeHtml label) {
        super(name, label);
    }

    public MaterialRadioButton(String name, String label, boolean asHTML) {
        super(name, label, asHTML);
    }

    public MaterialRadioButton(String name, String label, Direction dir) {
        super(name, label, dir);
    }

    public MaterialRadioButton(String name, String label, DirectionEstimator directionEstimator) {
        super(name, label, directionEstimator);
    }

    public MaterialRadioButton(String name, String label) {
        super(name, label);
    }

    public MaterialRadioButton(String name) {
        super(name);
    }

    @Override
    public RadioButtonType getType() {
        return typeMixin.getType();
    }

    @Override
    public void setType(RadioButtonType type) {
        typeMixin.setType(type);
    }

    @Override
    public void setGrid(String grid) {
        gridMixin.setGrid(grid);
    }

    @Override
    public void setOffset(String offset) {
        gridMixin.setOffset(offset);
    }
}
