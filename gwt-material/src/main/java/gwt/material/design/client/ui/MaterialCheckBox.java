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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import gwt.material.design.client.async.AsyncWidgetCallback;
import gwt.material.design.client.async.IsAsyncWidget;
import gwt.material.design.client.async.loader.AsyncDisplayLoader;
import gwt.material.design.client.async.loader.DefaultCheckBoxLoader;
import gwt.material.design.client.async.mixin.AsyncWidgetMixin;
import gwt.material.design.client.base.BaseCheckBox;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CheckBoxType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.events.DefaultHandlerRegistry;
import gwt.material.design.client.events.HandlerRegistry;

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
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#checkbox">CheckBox</a>
 * @see <a href="https://material.io/guidelines/components/selection-controls.html#selection-controls-checkbox">Material Design Specification</a>
 */
public class MaterialCheckBox extends BaseCheckBox implements IsAsyncWidget<MaterialCheckBox, Boolean> {

    private Object object;

    private HandlerRegistry handlerRegistry = new DefaultHandlerRegistry(this);
    private ToggleStyleMixin<MaterialCheckBox> toggleOldMixin;
    private AsyncWidgetMixin<MaterialCheckBox, Boolean> asyncWidgetMixin;

    private CheckBoxType type;

    public MaterialCheckBox() {
        super(DOM.createDiv());
        setAsyncDisplayLoader(new DefaultCheckBoxLoader(this));
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

    @Override
    protected void onLoad() {
        super.onLoad();

        getElement().getStyle().setDisplay(isVisible() ? Display.BLOCK : Display.NONE);
        handlerRegistry.registerHandler(addClickHandler(event -> {
            if (isAsynchronous()) {
                load(getAsyncCallback());
                event.preventDefault();
                event.stopPropagation();
            }
        }));
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        handlerRegistry.clearHandlers();
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * Used the old checkbox.
     */
    public void setOld(boolean old) {
        getToggleOldMixin().setOn(old);
    }

    public boolean isOld() {
        return getToggleOldMixin().isOn();
    }

    /**
     * Setting the type of Checkbox.
     */
    public void setType(CheckBoxType type) {
        this.type = type;
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

    public CheckBoxType getType() {
        return type;
    }

    public ToggleStyleMixin<MaterialCheckBox> getToggleOldMixin() {
        if (toggleOldMixin == null) {
            toggleOldMixin = new ToggleStyleMixin<>(this, CssName.OLD_CHECKBOX);
        }
        return toggleOldMixin;
    }

    @Override
    public void setAsynchronous(boolean asynchronous) {
        getAsyncWidgetMixin().setAsynchronous(asynchronous);
    }

    @Override
    public boolean isAsynchronous() {
        return getAsyncWidgetMixin().isAsynchronous();
    }

    @Override
    public void load(AsyncWidgetCallback<MaterialCheckBox, Boolean> asyncCallback) {
        getAsyncWidgetMixin().load(asyncCallback);
    }

    @Override
    public void setLoaded(boolean loaded) {
        getAsyncWidgetMixin().setLoaded(loaded);
    }

    @Override
    public boolean isLoaded() {
        return getAsyncWidgetMixin().isLoaded();
    }

    @Override
    public void setAsyncCallback(AsyncWidgetCallback<MaterialCheckBox, Boolean> asyncCallback) {
        getAsyncWidgetMixin().setAsyncCallback(asyncCallback);
    }

    @Override
    public AsyncWidgetCallback<MaterialCheckBox, Boolean> getAsyncCallback() {
        return getAsyncWidgetMixin().getAsyncCallback();
    }

    @Override
    public void setAsyncDisplayLoader(AsyncDisplayLoader displayLoader) {
        getAsyncWidgetMixin().setAsyncDisplayLoader(displayLoader);
    }

    @Override
    public AsyncDisplayLoader getAsyncDisplayLoader() {
        return getAsyncWidgetMixin().getAsyncDisplayLoader();
    }

    public AsyncWidgetMixin<MaterialCheckBox, Boolean> getAsyncWidgetMixin() {
        if (asyncWidgetMixin == null) {
            asyncWidgetMixin = new AsyncWidgetMixin<>(this);
        }
        return asyncWidgetMixin;
    }
}
