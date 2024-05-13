/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2022 GwtMaterialDesign
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
package gwt.material.design.client.sanitizer.handler;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import gwt.material.design.client.sanitizer.ValueSanitizerException;

public abstract class AbstractSanitizeHandler implements ValueSanitizeHandler {

    protected boolean enabled;

    public AbstractSanitizeHandler() {

    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public boolean matches(String pattern, String value, String sanitizeError) {
        RegExp regExp = RegExp.compile(pattern);
        MatchResult matcher = regExp.exec(value);
        if (matcher != null) {
            throw new ValueSanitizerException(sanitizeError);
        }
        return false;
    }

    public boolean contains(String[] strings, String value, String sanitizeError) {
        for (String string : strings) {
            if (value.equals(string)) {
                throw new ValueSanitizerException(sanitizeError);
            }
        }
        return false;
    }
}
