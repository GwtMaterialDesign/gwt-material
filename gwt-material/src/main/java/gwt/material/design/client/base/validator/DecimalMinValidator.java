/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2015 GwtBootstrap3
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
package gwt.material.design.client.base.validator;

import gwt.material.design.client.base.validator.ValidationMessages.Keys;

/**
 * Decimal min validator. Checks to see if the decimal value is under the minimum value.
 *
 * @param <T> the generic type
 * @author Steven Jardine
 */
public class DecimalMinValidator<T extends Number> extends AbstractValidator<T> {

    private Number minValue;

    /**
     * Constructor.
     *
     * @param minValue the min value
     */
    public DecimalMinValidator(Number minValue) {
        super(Keys.DECIMAL_MIN, new Object[]{minValue.toString()});
        this.minValue = minValue;
    }

    /**
     * Constructor.
     *
     * @param minValue               the min value
     * @param invalidMessageOverride the invalid message override
     */
    public DecimalMinValidator(Number minValue, String invalidMessageOverride) {
        super(invalidMessageOverride);
        this.minValue = minValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPriority() {
        return Priority.MEDIUM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(T value) {
        return value == null || value.doubleValue() >= minValue.doubleValue();
    }
}
