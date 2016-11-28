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

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasAxis;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.Axis;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.FABType;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * Floating action buttons are used for a promoted action. They
 * are distinguished by a circled icon floating above the UI and
 * have motion behaviors that include morphing, launching, and a
 * transferring anchor point.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialFAB>
 *   <m:MaterialButton type="FLOATING" backgroundColor="BLUE" iconType="POLYMER" size="LARGE"/>
 *   <m:MaterialFABList>
 *     <m:MaterialButton type="FLOATING" backgroundColor="RED" iconType="POLYMER"/>
 *     <m:MaterialButton type="FLOATING" backgroundColor="ORANGE" iconType="POLYMER"/>
 *     <m:MaterialButton type="FLOATING" backgroundColor="WHITE" iconType="POLYMER" iconColor="BLACK"/>
 *   </m:MaterialFABList>
 * </m:MaterialFAB>}
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#buttons">Material FAB</a>
 */
//@formatter:on
public class MaterialFAB extends MaterialWidget implements HasType<FABType>, HasAxis, HasCloseHandlers<MaterialFAB>,
        HasOpenHandlers<MaterialFAB> {

    private final CssTypeMixin<FABType, MaterialFAB> typeMixin = new CssTypeMixin<>(this);
    private final CssNameMixin<MaterialFAB, Axis> axisMixin = new CssNameMixin<>(this);

    private HandlerRegistration clickHandler;
    private HandlerRegistration mouseOverHandler;
    private HandlerRegistration mouseOutHandler;

    public MaterialFAB() {
        super(Document.get().createDivElement(), CssName.FIXED_ACTION_BTN);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        if (getType() == FABType.CLICK_ONLY) {
            clickHandler = addClickHandler(clickEvent -> {
                if(isEnabled()) {
                    if (!isOpen()) {
                        open();
                    } else {
                        close();
                    }
                }
            });
        } else {
            mouseOverHandler = addMouseOverHandler(mouseOverEvent -> {
                OpenEvent.fire(this, this);
            });
            mouseOutHandler = addMouseOutHandler(mouseOutEvent -> {
                CloseEvent.fire(this, this);
            });
        }
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        if (clickHandler != null) {
            clickHandler.removeHandler();
            clickHandler = null;
        }
        if (mouseOverHandler != null) {
            mouseOverHandler.removeHandler();
            mouseOverHandler = null;
        }
        if (mouseOutHandler != null) {
            mouseOutHandler.removeHandler();
            mouseOutHandler = null;
        }
    }

    @Override
    public void setType(FABType type) {
        typeMixin.setType(type);
    }

    @Override
    public FABType getType() {
        return typeMixin.getType();
    }

    @Override
    public void setAxis(Axis axis) {
        axisMixin.setCssName(axis);
    }

    @Override
    public Axis getAxis() {
        return axisMixin.getCssName();
    }

    /**
     * Open the FAB programmatically
     */
    public void open() {
        open(true);
    }

    /**
     * Open the FAB programmatically
     *
     * @param fireEvent flag whether this component fires Open Event
     */
    public void open(boolean fireEvent) {
        if (fireEvent) {
            OpenEvent.fire(this, this);
        }
        $(getElement()).openFAB();
    }

    /**
     * Close the FAB programmatically
     */
    public void close() {
        close(true);
    }

    /**
     * Close the FAB programmatically
     *
     * @param fireEvent flag whether this component fires Close Event
     */
    public void close(boolean fireEvent) {
        if (fireEvent) {
            CloseEvent.fire(this, this);
        }
        $(getElement()).closeFAB();
    }

    public boolean isOpen() {
        return getElement().hasClassName(CssName.ACTIVE);
    }

    @Override
    public HandlerRegistration addCloseHandler(CloseHandler<MaterialFAB> handler) {
        return addHandler(handler, CloseEvent.getType());
    }

    @Override
    public HandlerRegistration addOpenHandler(OpenHandler handler) {
        return addHandler(handler, OpenEvent.getType());
    }
}