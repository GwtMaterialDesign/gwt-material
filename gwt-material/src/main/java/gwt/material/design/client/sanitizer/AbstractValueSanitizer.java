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

import gwt.material.design.client.sanitizer.handler.*;

public abstract class AbstractValueSanitizer implements ValueSanitizer {


    public AbstractValueSanitizer() {
        register(new ChineseSanitizer());
        register(new EmojiSanitizer());
        register(new NumericSanitizer());
        register(new ReservedWordSanitizer());
        register(new RtlCharacterSanitizer());
        register(new SpecialCharacterSanitizer());
        register(new UnicodeSanitizer());
        register(new ZalgoTextSanitizer());
    }

    @Override
    public boolean sanitize(String value) throws ValueSanitizerException {
        for (ValueSanitizerHandler enabledHandler : ValueSanitizerRegistry.getEnabledHandlers()) {
            enabledHandler.sanitize(value);
        }
        return true;
    }

    public void register(ValueSanitizerHandler handler) {
        ValueSanitizerRegistry.register(handler);
    }

    @Override
    public ValueSanitizer reservedString(boolean sanitize) {
        ValueSanitizerRegistry.enable(ReservedWordSanitizer.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer special(boolean sanitize) {
        ValueSanitizerRegistry.enable(SpecialCharacterSanitizer.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer numeric(boolean sanitize) {
        ValueSanitizerRegistry.enable(NumericSanitizer.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer unicode(boolean sanitize) {
        ValueSanitizerRegistry.enable(UnicodeSanitizer.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer chinese(boolean sanitize) {
        ValueSanitizerRegistry.enable(ChineseSanitizer.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer emoji(boolean sanitize) {
        ValueSanitizerRegistry.enable(EmojiSanitizer.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer rtl(boolean sanitize) {
        ValueSanitizerRegistry.enable(RtlCharacterSanitizer.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer zalgo(boolean sanitize) {
        ValueSanitizerRegistry.enable(ZalgoTextSanitizer.class, sanitize);
        return this;
    }
}
