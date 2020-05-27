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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import gwt.material.design.client.async.AsyncWidgetCallback;
import gwt.material.design.client.async.IsAsyncWidget;
import gwt.material.design.client.async.loader.AsyncDisplayLoader;
import gwt.material.design.client.async.loader.DefaultSwitchLoader;
import gwt.material.design.client.async.mixin.AsyncWidgetMixin;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.mixin.StatusTextMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.constants.SwitchType;
import gwt.material.design.client.ui.html.Label;
import gwt.material.design.client.ui.html.Span;

//@formatter:off

/**
 * Material Switch or other call it toggle - used for an alternative for checkbox
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code <m:MaterialSwitch value="true"/>
 * <m:MaterialSwitch value="true" disabled="true"/>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#switches">Material Switch</a>
 * @see <a href="https://material.io/guidelines/components/selection-controls.html#selection-controls-switch">Material Design Specification</a>
 */
//@formatter:on
public class MaterialSwitch extends AbstractValueWidget<Boolean>
    implements HasValue<Boolean>, IsAsyncWidget<MaterialSwitch, Boolean> {

    private final MaterialLabel errorLabel = new MaterialLabel();
    private final Span onLabel = new Span();
    private final Span offLabel = new Span();
    private MaterialInput input = new MaterialInput();
    private Label label = new Label();
    private Span span = new Span();
    private SwitchType type = SwitchType.DEFAULT;

    private StatusTextMixin<AbstractValueWidget, MaterialLabel> statusTextMixin;
    private AsyncWidgetMixin<MaterialSwitch, Boolean> asyncWidgetMixin;

    /**
     * Creates a switch element
     */
    public MaterialSwitch() {
        super(Document.get().createDivElement(), CssName.SWITCH);
        span.setStyleName(CssName.LEVER);
        input.setType(InputType.CHECKBOX);
        setAsyncDisplayLoader(new DefaultSwitchLoader(this));
    }

    public MaterialSwitch(String onLabel, String offLabel) {
        this();
        setOnLabel(onLabel);
        setOffLabel(offLabel);
    }

    public MaterialSwitch(String onLabel, String offLabel, Boolean value) {
        this(onLabel, offLabel);
        setValue(value);
    }

    /**
     * Creates a material switch with default value.
     */
    public MaterialSwitch(boolean value) {
        this();
        setValue(value);
    }

    public MaterialSwitch(SwitchType switchType) {
        this();
        setType(switchType);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        label.add(offLabel);
        label.add(input);
        label.add(span);
        add(label);
        add(errorLabel);
        errorLabel.getElement().getStyle().setMarginTop(16, Unit.PX);
        label.add(onLabel);

        // Register click handler here in order to have it at first position
        // and therefore it will deal with clicks as first and setup the value
        // right before others get notified.
        registerHandler(addClickHandler(event -> {
            if (type.equals(SwitchType.DEFAULT)) {
                if (isAsynchronous()) {
                    load(getAsyncCallback());
                } else {
                    setValue(!getValue(), true);
                }
            }

            event.preventDefault();
            event.stopPropagation();
        }));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        span.setEnabled(enabled);
        input.setEnabled(enabled);
    }

    /**
     * Set the value of switch component.
     */
    @Override
    public void setValue(Boolean value, boolean fireEvents) {
        boolean oldValue = getValue();
        if (value) {
            input.getElement().setAttribute("checked", "true");
        } else {
            input.getElement().removeAttribute("checked");
        }

        if (fireEvents && oldValue != value) {
            ValueChangeEvent.fire(this, getValue());
        }
    }

    @Override
    public void setValue(Boolean value) {
        setValue(value, false);
    }

    /**
     * Gets the value of switch component.
     */
    @Override
    public Boolean getValue() {
        return input.getElement().hasAttribute("checked");
    }

    @Override
    public void reset() {
        super.reset();

        setValue(false);
    }

    /**
     * @return the input
     */
    public MaterialInput getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(MaterialInput input) {
        this.input = input;
    }

    /**
     * @return the span
     */
    public Span getSpan() {
        return span;
    }

    /**
     * @param span the span to set
     */
    public void setSpan(Span span) {
        this.span = span;
    }

    /**
     * @return the label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    @Deprecated
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * Set the On State Label of the switch component
     */
    public void setOnLabel(String label) {
        onLabel.setText(label);
    }

    /**
     * Set the Off State Label of the switch component
     */
    public void setOffLabel(String label) {
        offLabel.setText(label);
    }

    public MaterialLabel getErrorLabel() {
        return errorLabel;
    }

    public Span getOnLabel() {
        return onLabel;
    }

    public Span getOffLabel() {
        return offLabel;
    }

    public SwitchType getType() {
        return type;
    }

    public void setType(SwitchType type) {
        this.type = type;
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
    public void load(AsyncWidgetCallback<MaterialSwitch, Boolean> asyncCallback) {
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
    public void setAsyncCallback(AsyncWidgetCallback<MaterialSwitch, Boolean> asyncCallback) {
        getAsyncWidgetMixin().setAsyncCallback(asyncCallback);
    }

    @Override
    public AsyncWidgetCallback<MaterialSwitch, Boolean> getAsyncCallback() {
        return getAsyncWidgetMixin().getAsyncCallback();
    }

    @Override
    public void setAsyncDisplayLoader(AsyncDisplayLoader<Boolean> displayLoader) {
        getAsyncWidgetMixin().setAsyncDisplayLoader(displayLoader);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Boolean> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    protected StatusTextMixin<AbstractValueWidget, MaterialLabel> getStatusTextMixin() {
        if (statusTextMixin == null) {
            statusTextMixin = new StatusTextMixin<>(this, errorLabel, null);
        }
        return statusTextMixin;
    }

    @Override
    public AsyncDisplayLoader<Boolean> getAsyncDisplayLoader() {
        return getAsyncWidgetMixin().getAsyncDisplayLoader();
    }

    protected AsyncWidgetMixin<MaterialSwitch, Boolean> getAsyncWidgetMixin() {
        if (asyncWidgetMixin == null) {
            asyncWidgetMixin = new AsyncWidgetMixin<>(this);
        }
        return asyncWidgetMixin;
    }
}
