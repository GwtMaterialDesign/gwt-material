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

import com.google.gwt.regexp.shared.RegExp;
import gwt.material.design.client.base.validator.ValidationMessages.Keys;

/**
 * Validator for checking value matches a regular expression.
 *
 * @author Steven Jardine
 */
public class RegExValidator extends AbstractValidator<String> {

    private RegExp regex;

    public RegExValidator(String pattern) {
        super(Keys.REGEX, new Object[0]);
        regex = RegExp.compile(pattern);
    }

    public RegExValidator(String pattern, String invalidMessageOverride) {
        super(invalidMessageOverride);
        regex = RegExp.compile(pattern);
    }

    @Override
    public int getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public boolean isValid(String value) {
        return value == null || regex.test(value);
    }

}
