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

//@formatter:off


/**
 * Material Integer Box is an input field that accepts any Float based string from user.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code <m:MaterialFloatBox placeholder="Your integer" step=100/>}
 * </pre>
 * 
 * The parsing and formatting of the number are done natively by the browser, using the
 * i18n settings from the user.
 * 
 * @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material FloatBox</a>
 * @author paulux84
 */
//@formatter:on
public class MaterialFloatBox extends MaterialNumberBox<Float> {

    public MaterialFloatBox() {
        setStep("any");
    }
    
    @Override
    public Float getValue() {
        double number = getValueAsNumber();
        if (Double.isNaN(number)){
            return null;
        }
        return Float.valueOf((float)number);
    }
    
}
