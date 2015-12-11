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

import com.google.gwt.user.client.ui.IntegerBox;
import gwt.material.design.client.constants.InputType;

//@formatter:off

/**
* Material Integer Box is an input field that accepts any number based string from user.
* <h3>UiBinder Usage:</h3>
* <pre>
*{@code <m:MaterialIntegerBox placeholder="First Name" />
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material IntegerBox</a>
* @author paulux84
*/
//@formatter:on
public class MaterialIntegerBox extends MaterialValueBox<Integer> {


    public MaterialIntegerBox() {
        super(new IntegerBox());
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

    @Override
    public String getText() {
        return valueBoxBase.getText();
    }

}
