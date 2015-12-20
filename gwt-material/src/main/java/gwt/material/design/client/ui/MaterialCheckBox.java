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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.CheckBox;
import gwt.material.design.client.base.HasGrid;
import gwt.material.design.client.base.mixin.GridMixin;
import gwt.material.design.client.constants.CheckBoxType;

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
 * @see <a href="http://gwt-material-demo.herokuapp.com/#forms">CheckBox</a>
 */
public class MaterialCheckBox extends CheckBox implements HasClickHandlers, HasGrid {

    private Object object;
    private String old;

    private final GridMixin<MaterialCheckBox> gridMixin = new GridMixin<>(this);

    public MaterialCheckBox() {
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
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        this.getElement().getStyle().setDisplay(Display.BLOCK);
    }

    public String getOld() {
        return old;
    }

    /**
     * Used the old checkbox.
     */
    public void setOld(String old) {
        this.old = old;
        this.addStyleName("oldCheckBox");
    }

    /**
     * Setting the type of Checkbox.
     */
    public void setType(CheckBoxType type) {
        switch (type) {
            case FILLED:
                Element cb = this.getElement();
                Element input = DOM.getChild(cb, 0);
                input.setAttribute("class", "filled-in");
                break;
            case INTERMEDIATE:
                this.addStyleName(type + "-checkbox");
                break;
            default:
                this.addStyleName(type.getCssName());
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
