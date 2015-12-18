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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.ui.TextArea;

//@formatter:off

/**
* Material Text Area represents a multiple line textbox where users can define comment, detail and etc.
* <h3>UiBinder Usage:</h3>
* <pre>
*{@code <m:MaterialTextArea placeholder="Your Comment" />
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material TextArea</a>
* @author kevzlou7979
* @author Ben Dol
 * @author paulux84
*/
//@formatter:on
public class MaterialTextArea extends MaterialValueBox<String> {


    public MaterialTextArea() {
        super(new TextArea());
        valueBoxBase.setStyleName("materialize-textarea");
    }


    //OLD MaterialTextArea method
//    @Override
//    protected void onLoad() {
//        super.onLoad();
//        label.getElement().setAttribute("for", "field");
//    }


    @Override
    public void setValue(String value, boolean fireEvents) {
        super.setValue(value,fireEvents);

        if(fireEvents) {
            ValueChangeEvent.fire(this, value);
        }
    }


}