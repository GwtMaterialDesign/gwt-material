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

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasVisibility;
import gwt.material.design.client.base.MaterialWidget;
//@formatter:off
/**
 * MaterialColumn is panel that act as a fluid panel to easily sets your desired column.We are using 12 grid layout with screens small, medium and large.
 * Just set grid='s12 m12 l12' to define your grid layout values.
 *
 * <p>
 * <h4>UiBinder Usage:</h4>
 * <pre>
 * {@code
 * <m:MaterialColumn grid='s12 m6 l4'/>
 *     Small Device - 12 grid
 *     Medium Device - 6 grid
 *     Large Device - 4 grid
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#!grid">Material Column</a>
 */
//@formatter:on
public class MaterialColumn extends MaterialWidget implements HasWaves, HasVisibility, HasClickHandlers,
        HasAllMouseHandlers, HasDoubleClickHandlers {

    public MaterialColumn() {
        super(Document.get().createDivElement(), "col");
    }

    public MaterialColumn(int small, int medium, int large) {
        this();
        addStyleName("s"+small+" m"+medium + " l" + large);
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()){
                handler.onClick(event);
            }
        }, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()){
                handler.onDoubleClick(event);
            }
        }, DoubleClickEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()) {
                handler.onMouseDown(event);
            }
        }, MouseDownEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()) {
                handler.onMouseMove(event);
            }
        }, MouseMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()){
                handler.onMouseOut(event);
            }
        }, MouseOutEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()){
                handler.onMouseOver(event);
            }
        }, MouseOverEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()){
                handler.onMouseUp(event);
            }
        }, MouseUpEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(final MouseWheelHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()){
                handler.onMouseWheel(event);
            }
        }, MouseWheelEvent.getType());
    }

    @Override
    public void setEnabled(boolean enabled) {
        getEnabledMixin().setEnabled(this, enabled);
    }
}
