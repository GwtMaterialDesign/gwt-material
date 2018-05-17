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

import gwt.material.design.client.base.validator.DecimalMaxValidator;
import gwt.material.design.client.base.validator.DecimalMinValidator;

import java.math.BigDecimal;

/**
 * Test case for BigDecimal Box.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialBigDecimalBoxTest extends MaterialValueBoxTest<MaterialBigDecimalBox> {

    @Override
    protected MaterialBigDecimalBox createWidget() {
        return new MaterialBigDecimalBox();
    }

    @Override
    public void testValue() {
        BigDecimal bigDecimal = new BigDecimal(11.1111111111111111111111111111111111111111111111111111111111111111111111111111111);
        BigDecimal maxValue = new BigDecimal(11.11111111111111111111111111111111111111111111111111111111111111111111111111111112);
        BigDecimal minValue = new BigDecimal(0.3333333333333333333333333333333333333333333333333333333333333333333333);
        MaterialBigDecimalBox bigDecimalBox = getWidget();

        bigDecimalBox.setValue(bigDecimal);
        assertEquals(bigDecimal, bigDecimalBox.getValue());
        assertEquals(bigDecimal.toString(), bigDecimalBox.getText());

        DecimalMaxValidator<BigDecimal> decimalMaxValidator = new DecimalMaxValidator<>(maxValue);
        bigDecimalBox.addValidator(decimalMaxValidator);

        DecimalMinValidator<BigDecimal> decimalMinValidator = new DecimalMinValidator<>(minValue);
        bigDecimalBox.addValidator(decimalMinValidator);

        assertTrue(bigDecimalBox.validate());

        bigDecimalBox.setValue(new BigDecimal(11.2));
        assertFalse(bigDecimalBox.validate());

        bigDecimalBox.setValue(new BigDecimal(0.2));
        assertFalse(bigDecimalBox.validate());
    }
}
