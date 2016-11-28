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

//@formatter:off

/**
 * Material Integer Box is an input field that accepts any Long based string
 * from user. <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code <m:MaterialLongBox placeholder="Your Long" step="100"/>}
 * </pre>
 * <p>
 * The parsing and formatting of the number are done natively by the browser,
 * using the i18n settings from the user.
 *
 * @author paulux84
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#forms">Material
 * IntegerBox</a>
 */
// @formatter:on
public class MaterialLongBox extends MaterialNumberBox<Long> {

    public MaterialLongBox() {
        setStep("1");
    }

    public MaterialLongBox(String placeholder) {
        this();
        setPlaceholder(placeholder);
    }

    public MaterialLongBox(String placeholder, long value) {
        this(placeholder);
        setValue(value);
    }

    @Override
    protected Long parseNumber(double number) {
        if (Double.isNaN(number)) {
            return null;
        }
        return (long) Math.rint(number);
    }
}
