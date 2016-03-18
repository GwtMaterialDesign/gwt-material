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

import gwt.material.design.client.base.NumberBox;
import gwt.material.design.client.constants.InputType;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiConstructor;

//@formatter:off

/**
 * Material Number Box is the base class for other numeric input boxes, such as {@link MaterialIntegerBox} and
 * {@link MaterialDoubleBox}.
 * 
 * @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material MaterialNumberBox</a>
 * @author paulux84
 */
//@formatter:on
public class MaterialNumberBox<T> extends MaterialValueBox<T> {

    @UiConstructor
    public MaterialNumberBox() {
        super(new NumberBox<T>());
        setType(InputType.NUMBER);
    }

    /**
     * Set step attribete to input element.
     * @param step "any" or number like for example 1 or 2.5 or 100, etc...
     */
    public void setStep(String step){
        valueBoxBase.getElement().setAttribute("step", step);
    }

    public String getStep(){
        return valueBoxBase.getElement().getAttribute("step");
    }

    public void setMin(String min){
        valueBoxBase.getElement().setAttribute("min", min);
    }

    public String getMin(){
        return valueBoxBase.getElement().getAttribute("min");
    }

    public void setMax(String max){
        valueBoxBase.getElement().setAttribute("max", max);
    }

    public String getMax(){
        return valueBoxBase.getElement().getAttribute("max");
    }

    @Override
    public String getText() {
        return valueBoxBase.getText();
    }
    
    /**
     * Returns the value parsed natively by the browser.
     * 
     * @return the value set on the component, or NaN if none is set
     */
    public double getValueAsNumber(){
        return getValueAsNumber(valueBoxBase.getElement());
    }
    
    /**
     * Native call to element.valueAsNumber.
     */
    protected native double getValueAsNumber(Element el)/*-{
        return el.valueAsNumber;
    }-*/;

}
