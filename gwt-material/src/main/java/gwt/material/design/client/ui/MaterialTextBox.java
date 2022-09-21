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

import com.google.gwt.user.client.ui.TextBox;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.sanitizer.ValueSanitizer;
import gwt.material.design.client.sanitizer.ValueSanitizerException;

//@formatter:off

/**
 * Material Text Box is an input field that accepts any text based string from user.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code <m:MaterialTextBox placeholder="First Name" /> }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @author paulux84
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#textfields">Material TextBox</a>
 * @see <a href="https://material.io/guidelines/components/text-fields.html#">Material Design Specification</a>
 */
//@formatter:on
public class MaterialTextBox extends MaterialValueBox<String> {

    protected ValueSanitizer valueSanitizer;

    public MaterialTextBox() {
        super(new TextBox());
        setType(InputType.TEXT);
    }

    public MaterialTextBox(String placeholder) {
        this();
        setPlaceholder(placeholder);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        if (valueSanitizer != null) {
            registerHandler(addValueChangeHandler(valueChangeEvent -> {
                try {
                    clearErrorText();
                    valueSanitizer.sanitize(valueChangeEvent.getValue());
                } catch (Exception e) {
                    if (e instanceof ValueSanitizerException) {
                        setErrorText(e.getLocalizedMessage());
                    } else {
                        setErrorText("Invalid input value");
                    }
                }
            }));
        }
    }

    public void setMaxLength(int length) {
        asTextBox().setMaxLength(length);
    }

    public int getMaxLength() {
        return asTextBox().getMaxLength();
    }

    public void setVisibleLength(int length) {
        asTextBox().setVisibleLength(length);
    }

    public int getVisibleLength() {
        return asTextBox().getVisibleLength();
    }

    public ValueSanitizer getValueSanitizer() {
        return valueSanitizer;
    }

    public void setValueSanitizer(ValueSanitizer valueSanitizer) {
        this.valueSanitizer = valueSanitizer;
    }

    @Ignore
    public TextBox asTextBox() {
        return (TextBox) valueBoxBase;
    }
}
