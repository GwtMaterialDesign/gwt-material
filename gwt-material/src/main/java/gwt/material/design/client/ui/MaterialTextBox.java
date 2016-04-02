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

import com.google.gwt.user.client.ui.TextBox;
import gwt.material.design.client.constants.InputType;

//@formatter:off

/**
* Material Text Box is an input field that accepts any text based string from user.
* <h3>UiBinder Usage:</h3>
* <pre>
*{@code <m:MaterialTextBox placeholder="First Name" />
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material TextBox</a>
* @author kevzlou7979
* @author Ben Dol
 * @author paulux84
*/
//@formatter:on
public class MaterialTextBox extends MaterialValueBox<String> {

    private final TextBox textBox;

    public MaterialTextBox() {
        this(new TextBox());
        setType(InputType.TEXT);
    }

    private MaterialTextBox(TextBox textBox) {
        super(textBox);
        this.textBox = textBox;
    }

    public int getMaxLength() {
        return textBox.getMaxLength();
    }

    public void setMaxLength(int length) {
        textBox.setMaxLength(length);
    }

    public int getVisibleLength() {
        return textBox.getVisibleLength();
    }

    public void setVisibleLength(int length) {
        textBox.setVisibleLength(length);
    }
}
