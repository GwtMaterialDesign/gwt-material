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

import com.google.gwt.user.client.ui.HasValue;
import gwt.material.design.client.base.validator.ValidationMessages.Keys;

/**
 * Validator for matching with another field.
 *
 * @param <T> the generic type
 * @author Steven Jardine
 */
public class FieldMatchValidator<T> extends AbstractValidator<T> {

    private final HasValue<T> verifyField;

    /**
     * Constructor.
     *
     * @param verifyField the verify field
     */
    public FieldMatchValidator(final HasValue<T> verifyField) {
        this(verifyField, new Object[0]);
    }

    /**
     * Constructor.
     *
     * @param verifyField        the verify field
     * @param invalidMessageArgs the invalid message args
     */
    public FieldMatchValidator(final HasValue<T> verifyField, final Object... invalidMessageArgs) {
        super(Keys.FIELD_MATCH, invalidMessageArgs);
        this.verifyField = verifyField;
        assert this.verifyField != null;
    }

    /**
     * Constructor.
     *
     * @param verifyField            the field to verify matches with this one.
     * @param invalidMessageOverride the invalid message override
     */
    public FieldMatchValidator(final HasValue<T> verifyField, final String invalidMessageOverride) {
        super(invalidMessageOverride);
        this.verifyField = verifyField;
        assert this.verifyField != null;
    }

    @Override
    public int getPriority() {
        return Priority.HIGH;
    }

    @Override
    public boolean isValid(T value) {
        T verifyValue = verifyField.getValue();
        return value == null ? verifyValue == null : value.equals(verifyValue);
    }

}
