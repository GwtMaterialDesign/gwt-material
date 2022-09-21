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
package gwt.material.design.client.sanitizer;

import gwt.material.design.client.sanitizer.handler.ValueSanitizerHandler;

public abstract class AbstractValueSanitizer implements ValueSanitizer {

    protected final ValueSanitizerHandler handler;
    protected boolean allowReservedString;
    protected boolean allowSpecial;
    protected boolean allowNumeric;
    protected boolean allowUnicode;
    protected boolean allowChinese;
    protected boolean allowEmoji;
    protected boolean allowRtl;
    protected boolean allowZalgo;

    public AbstractValueSanitizer(ValueSanitizerHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean sanitize(String value) throws ValueSanitizerException {
        if (!allowReservedString) {
            handler.sanitizeReservedString(value);
        }
        if (!allowSpecial) {
            handler.sanitizeSpecial(value);
        }
        if (!allowNumeric) {
            handler.sanitizeNumeric(value);
        }
        if (!allowUnicode) {
            handler.sanitizeUnicode(value);
        }
        if (!allowChinese) {
            handler.sanitizeChinese(value);
        }
        if (!allowEmoji) {
            handler.sanitizeEmoji(value);
        }
        if (!allowRtl) {
            handler.sanitizeRtl(value);
        }
        if (!allowZalgo) {
            handler.sanitizeZalgo(value);
        }
        return true;
    }

    @Override
    public ValueSanitizer reservedString(boolean allow) {
        this.allowReservedString = allow;
        return this;
    }

    @Override
    public ValueSanitizer special(boolean allow) {
        this.allowSpecial = allow;
        return this;
    }

    @Override
    public ValueSanitizer numeric(boolean allow) {
        this.allowNumeric = allow;
        return this;
    }

    @Override
    public ValueSanitizer unicode(boolean allow) {
        this.allowUnicode = allow;
        return this;
    }

    @Override
    public ValueSanitizer chinese(boolean allow) {
        this.allowChinese = allow;
        return this;
    }

    @Override
    public ValueSanitizer emoji(boolean allow) {
        this.allowEmoji = allow;
        return this;
    }

    @Override
    public ValueSanitizer rtl(boolean allow) {
        this.allowRtl = allow;
        return this;
    }

    @Override
    public ValueSanitizer zalgo(boolean allow) {
        this.allowZalgo = allow;
        return this;
    }
}
