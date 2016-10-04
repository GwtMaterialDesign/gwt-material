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

import java.util.Date;

/**
 * Validator for checking if date is in the future.
 *
 * @author Steven Jardine
 */
public class FutureValidator extends AbstractValidator<Date> {

    /**
     * Constructor.
     */
    public FutureValidator() {
        super(Keys.FUTURE, new Object[0]);
    }

    /**
     * Constructor.
     *
     * @param invalidMessageOverride the invalid message override
     */
    public FutureValidator(String invalidMessageOverride) {
        super(invalidMessageOverride);
    }

    @Override
    public int getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public boolean isValid(Date value) {
        return value == null || value.after(new Date());
    }

}
