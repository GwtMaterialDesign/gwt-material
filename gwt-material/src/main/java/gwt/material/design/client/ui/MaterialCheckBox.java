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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ListBox;
import gwt.material.design.client.base.BaseCheckBox;
import gwt.material.design.client.base.HasGrid;
import gwt.material.design.client.base.mixin.GridMixin;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CheckBoxType;
import gwt.material.design.client.constants.CssName;

//@formatter:off

/**
 * Checkbox component provides two types
 * - FILLED
 * - INTERMEDIATE
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * // Default
 * <m:MaterialCheckBox text="Option 1"/>
 *
 * // Filled
 * <m:MaterialCheckBox text="Option 1" type="FILLED"/>
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#forms">CheckBox</a>
 */
public class MaterialCheckBox extends BaseCheckBox implements HasGrid {

    private Object object;

    private final GridMixin<MaterialCheckBox> gridMixin = new GridMixin<>(this);
    private ToggleStyleMixin<MaterialCheckBox> toggleOldMixin = new ToggleStyleMixin<>(this, CssName.OLD_CHECKBOX);

    public MaterialCheckBox() {
        super();
    }

    public MaterialCheckBox(Element elem) {
        super(elem);
    }

    public MaterialCheckBox(SafeHtml label, Direction dir) {
        super(label, dir);
    }

    public MaterialCheckBox(SafeHtml label, DirectionEstimator directionEstimator) {
        super(label, directionEstimator);
    }

    public MaterialCheckBox(SafeHtml label) {
        super(label);
    }

    public MaterialCheckBox(String label, boolean asHTML) {
        super(label, asHTML);
    }

    public MaterialCheckBox(String label, Direction dir) {
        super(label, dir);
    }

    public MaterialCheckBox(String label, DirectionEstimator directionEstimator) {
        super(label, directionEstimator);
    }

    public MaterialCheckBox(String label) {
        super(label);
    }

    public MaterialCheckBox(String label, CheckBoxType type) {
        super(label);
        setType(type);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        getElement().getStyle().setDisplay(isVisible() ? Display.BLOCK : Display.NONE);
    }

    public boolean isOld() {
        return toggleOldMixin.isOn();
    }

    /**
     * Used the old checkbox.
     */
    public void setOld(boolean old) {
        toggleOldMixin.setOn(old);
    }

    /**
     * Setting the type of Checkbox.
     */
    public void setType(CheckBoxType type) {
        switch (type) {
            case FILLED:
                Element input = DOM.getChild(getElement(), 0);
                input.setAttribute("class", CssName.FILLED_IN);
                break;
            case INTERMEDIATE:
                addStyleName(type.getCssName() + "-checkbox");
                break;
            default:
                addStyleName(type.getCssName());
                break;
        }
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
