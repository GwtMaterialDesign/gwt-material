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
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasInputChangeHandler;
import gwt.material.design.client.base.HasStatusText;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.StatusTextMixin;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.events.InputChangeEvent;
import gwt.material.design.client.ui.html.Span;

import static gwt.material.design.jquery.client.api.JQuery.$;

//@formatter:off

/**
 * Material Range - a slider that initialize the minimum and maximum values.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code <m:MaterialRange value="2" min="20" max="50" value="25"/>}
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#range">Material Range</a>
 * @see <a href="https://material.io/guidelines/components/sliders.html">Material Design Specification</a>
 */
//@formatter:on
public class MaterialRange extends AbstractValueWidget<Integer>
    implements HasChangeHandlers, HasStatusText, HasInputChangeHandler {

    private static String VALUE = "value";
    private static String MAX = "max";
    private static String MIN = "min";

    private MaterialPanel progress = new MaterialPanel();
    private MaterialPanel progressWrapper = new MaterialPanel();
    private MaterialPanel progressFillContainer = new MaterialPanel();
    private MaterialPanel rangeContainer = new MaterialPanel();
    private MaterialInput rangeInputElement = new MaterialInput();
    private Span thumb = new Span();
    private Span value = new Span();
    private boolean autoBlur;

    private MaterialLabel errorLabel = new MaterialLabel();
    private StatusTextMixin<AbstractValueWidget, MaterialLabel> statusTextMixin;
    private ToggleStyleMixin<MaterialWidget> toggleThumbStyleMixin;

    /**
     * Creates a range
     */
    public MaterialRange() {
        super(Document.get().createDivElement());
    }

    /**
     * Creates a range with specified values
     *
     * @param min   - start min value
     * @param max   - end max value
     * @param value - default range value
     */
    public MaterialRange(Integer min, Integer max, Integer value) {
        this();
        setMin(min);
        setMax(max);
        setValue(value);
    }

    public void reset() {
        super.reset();

        setValue(getMin());
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        errorLabel.setVisible(false);
        rangeContainer.setStyleName(CssName.RANGE_FIELD);
        rangeInputElement.setType(InputType.RANGE);
        rangeContainer.add(rangeInputElement);

        thumb.getElement().setClassName(CssName.THUMB);
        value.getElement().setClassName(CssName.VALUE);
        thumb.add(value);
        rangeContainer.add(thumb);

        progressWrapper.getElement().setClassName("range-progress-wrapper");
        progress.getElement().setClassName(CssName.PROGRESS);
        progressFillContainer.getElement().setClassName("progress-container");
        progressFillContainer.add(progress);
        progressWrapper.add(progressFillContainer);
        rangeContainer.add(progressWrapper);

        add(rangeContainer);
        add(errorLabel);

        $(rangeInputElement.getElement()).on("input", (event, o) -> {
            InputChangeEvent.fire(this, getValue());
            updateProgressWidth(getValue());
            return true;
        });

        // Fixing IE Inconsistent event handling on Value Change event
        // https://www.impressivewebs.com/onchange-vs-oninput-for-range-sliders/
        registerHandler(addMouseUpHandler(event -> {
            if (isIE()) {
                setValue(getValue(), true);
            }
        }));

        registerHandler(addChangeHandler(changeEvent -> {
            // Fixing IE Inconsistent event handling on Input Change event
            // https://www.impressivewebs.com/onchange-vs-oninput-for-range-sliders/
            if (isIE()) {
                InputChangeEvent.fire(this, getValue());
            }
            setValue(getValue(), !isIE());
            if (isAutoBlur()) {
                $(rangeInputElement.getElement()).blur();
            }
        }));
    }

    protected void updateProgressWidth(int value) {
        double range = ((value - getMin()) * 100.0) / (getMax() - getMin());
        progress.setWidth(range + "%");
    }

    /**
     * Retrieve the Integer value from the given Attribute of the range element
     *
     * @param attribute The name of the attribute on the range element
     * @return The Integer vaulue read from the given attribute or null
     */
    protected Integer getIntFromRangeElement(String attribute) {
        Element ele = $(rangeInputElement).asElement();
        if (ele != null) {
            return ele.getPropertyInt(attribute);
        }
        return null;
    }

    /**
     * Set the given Integer value to the attribute of the range element.
     */
    protected void setIntToRangeElement(String attribute, Integer val) {
        Element ele = $(rangeInputElement).asElement();
        if (ele != null) {
            ele.setPropertyInt(attribute, val);
        }
    }

    /**
     * Read the current value
     *
     * @return The Integer value or null
     */
    @Override
    public Integer getValue() {
        return getIntFromRangeElement(VALUE);
    }

    @Override
    public void setValue(Integer value, boolean fireEvents) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null");
        }
        if (value < getMin()) {
            throw new IllegalArgumentException("Value must not be less than the minimum range value.");
        }
        if (value > getMax()) {
            throw new IllegalArgumentException("Value must not be greater than the maximum range value");
        }
        setIntToRangeElement(VALUE, value);
        updateProgressWidth(value);
        super.setValue(value, fireEvents);
    }

    /**
     * Read the min value
     *
     * @return The Integer or null
     */
    public Integer getMin() {
        return getIntFromRangeElement(MIN);
    }

    /**
     * Write the current min value
     *
     * @param min value must be &lt; max
     */
    public void setMin(Integer min) {
        setIntToRangeElement(MIN, min);
    }

    /**
     * Read the max value
     *
     * @return The Integer or null
     */
    public Integer getMax() {
        return getIntFromRangeElement(MAX);
    }

    public Integer getStep() {
        String step = getRangeInputElement().getElement().getAttribute("step");
        if (step != null && !step.isEmpty()) {
            return Integer.parseInt(step);
        }
        return null;
    }

    public void setStep(Integer step) {
        getRangeInputElement().getElement().setAttribute("step", step + "");
    }

    public boolean isEnableThumb() {
        return getToggleThumbStyleMixin().isOn();
    }

    public void setEnableThumb(boolean enableThumb) {
        getToggleThumbStyleMixin().setOn(!enableThumb);
    }

    public boolean isAutoBlur() {
        return autoBlur;
    }

    public void setAutoBlur(boolean autoBlur) {
        this.autoBlur = autoBlur;
    }

    /**
     * Write the current max value
     *
     * @param max value must be &gt; min
     */
    public void setMax(Integer max) {
        setIntToRangeElement(MAX, max);
    }

    public MaterialLabel getErrorLabel() {
        return errorLabel;
    }

    public MaterialInput getRangeInputElement() {
        return rangeInputElement;
    }

    public MaterialPanel getRangeContainer() {
        return rangeContainer;
    }

    public Span getThumb() {
        return thumb;
    }

    public boolean isIE() {
        return Window.Navigator.getUserAgent().indexOf("MSIE") > -1 || Window.Navigator.getUserAgent().indexOf("Trident/") > -1;
    }

    /**
     * Register the ChangeHandler to become notified if the user changes the slider.
     * The Handler is called when the user releases the mouse only at the end of the slide
     * operation.
     */
    @Override
    public HandlerRegistration addChangeHandler(final ChangeHandler handler) {
        return getRangeInputElement().addDomHandler(handler, ChangeEvent.getType());
    }

    @Override
    public HandlerRegistration addInputChangeHandler(InputChangeEvent.InputChangeHandler handler) {
        return addHandler(handler, InputChangeEvent.getType());
    }

    @Override
    public StatusTextMixin<AbstractValueWidget, MaterialLabel> getStatusTextMixin() {
        if (statusTextMixin == null) {
            statusTextMixin = new StatusTextMixin<>(this, errorLabel, null);
        }
        return statusTextMixin;
    }

    public ToggleStyleMixin<MaterialWidget> getToggleThumbStyleMixin() {
        if (toggleThumbStyleMixin == null) {
            toggleThumbStyleMixin = new ToggleStyleMixin<>(this, CssName.NO_THUMB);
        }
        return toggleThumbStyleMixin;
    }
}
