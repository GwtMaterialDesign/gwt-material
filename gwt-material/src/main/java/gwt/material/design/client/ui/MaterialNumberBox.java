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

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.ValueBoxBase;
import gwt.material.design.client.constants.InputType;

//@formatter:off

/**
 * Material Number Box is an input field that accepts a typed number from user.
 * <h3>UiBinder Usage:</h3>
 * <h4>Java side:</h4>
 * <pre>
 * {@code
 *  @code @UiField(provided = true)
 *  MaterialNumberBox<Long> numberBox;
 *
 *  public Constructor(){
 *      numberBox=new MaterialNumberBox<>(new LongBox());
 *      initWidget(ourUiBinder.createAndBindUi(this));
 *  }
 *  }
 * <h4>Ui.xml side:</h4>
 *{@code   <m:MaterialNumberBox step="1" type="NUMBER"  placeholder="my long" ui:field="numberBox" />}
 * </pre>
 * @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material MaterialNumberBox</a>
 * @author paulux84
 */
//@formatter:on
public class MaterialNumberBox<T> extends MaterialValueBox<T> {

    @UiConstructor
    public MaterialNumberBox(ValueBoxBase<T> valueBox) {
        super(valueBox);
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
