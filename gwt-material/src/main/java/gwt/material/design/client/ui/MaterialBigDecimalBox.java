/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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

import java.math.BigDecimal;

import static gwt.material.design.jquery.client.api.JQuery.$;

//@formatter:off

/**
 * MaterialBigDecimalBox is an input field that accepts any BigDecimal based string
 * from user. <h3>UiBinder Usage:</h3>
 * <p>
 * <pre> {@code <m:MaterialBigDecimalBox ui:field="txtBigDecimal" label="BigDecimal value"/>
 *
 * // Setting value in java
 * txtBigDecimal.setValue(123.3248834172371231243824823847374238482348327743284327483284327);}
 * </pre>
 * <p>
 * The parsing and formatting of the number are done natively by the browser,
 * using the i18n settings from the user.
 *
 * @author paulux84
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#textfields">Material
 * IntegerBox</a>
 * @see <a href="https://material.io/guidelines/components/text-fields.html#">Material Design Specification</a>
 */
// @formatter:on
public class MaterialBigDecimalBox extends MaterialNumberBox<BigDecimal> {

    /**
     * Will get the value as a string and return a BigDecimal value
     * for long precision instead of Double value.
     */
    public BigDecimal getValueAsBigDecimal() {
        String value = (String) $(valueBoxBase.getElement()).val();
        if (value != null && !value.isEmpty()) {
            return new BigDecimal(value);
        } else {
            return null;
        }
    }

    @Override
    protected BigDecimal parseNumber(double number) {
        return getValueAsBigDecimal();
    }
}
