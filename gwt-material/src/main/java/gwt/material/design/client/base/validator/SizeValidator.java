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

import java.util.Collection;
import java.util.Map;

/**
 * Validator for checking the length of a map, array, collection, or string value. If type is not one of the
 * aformentioned, the {@link Object#toString()} method is called to get the string representation of the
 * object.
 *
 * @param <T> the generic type
 * @author Steven Jardine
 */
public class SizeValidator<T> extends AbstractValidator<T> {

    private Integer maxValue;

    private Integer minValue;

    public SizeValidator(Integer min, Integer max) {
        super(Keys.SIZE, new Object[]{min, max});
        setMin(min);
        setMax(max);
    }

    public SizeValidator(Integer min, Integer max, String invalidMessageOverride) {
        super(invalidMessageOverride);
        setMin(min);
        setMax(max);
    }

    @Override
    public int getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public boolean isValid(T value) {
        int length = 0;
        if (value instanceof Map<?, ?>) {
            length = ((Map<?, ?>) value).size();
        } else if (value instanceof Collection<?>) {
            length = ((Collection<?>) value).size();
        } else if (value instanceof Object[]) {
            length = ((Object[]) value).length;
        } else if (value != null) {
            length = value.toString().length();
        }
        return length >= minValue && length <= maxValue;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Integer max) {
        this.maxValue = max;
        assert maxValue > 0;
    }

    /**
     * @param min the min to set
     */
    public void setMin(Integer min) {
        minValue = min;
        if (minValue == null || minValue < 0) {
            minValue = 0;
        }
    }
}
