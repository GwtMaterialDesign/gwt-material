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

import gwt.material.design.client.base.NumberBox;
import gwt.material.design.client.base.NumberBox.NumberHandler;
import gwt.material.design.client.constants.InputType;

import static gwt.material.design.jquery.client.api.JQuery.$;

//@formatter:off

/**
 * Material Number Box is the base class for other numeric input boxes, such as {@link MaterialIntegerBox} and
 * {@link MaterialDoubleBox}.
 *
 * @author paulux84
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#forms">Material MaterialNumberBox</a>
 */
//@formatter:on
public abstract class MaterialNumberBox<T> extends MaterialValueBox<T> {

    protected MaterialNumberBox() {
        initValueBox(new NumberBox<>(new NumberHandler<>(this)));
        setType(InputType.NUMBER);
    }

    /**
     * Set step attribute to input element.
     *
     * @param step "any" or number like for example 1 or 2.5 or 100, etc...
     */
    public void setStep(String step) {
        valueBoxBase.getElement().setAttribute("step", step);
    }

    public String getStep() {
        return valueBoxBase.getElement().getAttribute("step");
    }

    public void setMin(String min) {
        valueBoxBase.getElement().setAttribute("min", min);
    }

    public String getMin() {
        return valueBoxBase.getElement().getAttribute("min");
    }

    public void setMax(String max) {
        valueBoxBase.getElement().setAttribute("max", max);
    }

    public String getMax() {
        return valueBoxBase.getElement().getAttribute("max");
    }

    @Override
    public T getValue() {
        if (getValueAsNumber() != null) {
            return parseNumber(getValueAsNumber());
        }
        return null;
    }

    protected abstract T parseNumber(double number);

    /**
     * Returns the value parsed natively by the browser.
     *
     * @return the value set on the component, or NaN if none is set
     */
    public Double getValueAsNumber() {
        String value = (String) $(valueBoxBase.getElement()).val();
        if (value != null && !value.isEmpty()) {
            return Double.parseDouble(value);
        } else {
            return null;
        }
    }
}
