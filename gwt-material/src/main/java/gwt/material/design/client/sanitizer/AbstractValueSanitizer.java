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

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.sanitizer.handler.*;

public abstract class AbstractValueSanitizer implements ValueSanitizer {

    protected boolean enabled = true;

    public AbstractValueSanitizer() {
        //TODO: Self Register this
        register(new StandardSanitizeHandler());
        register(new ChineseSanitizeHandler());
        register(new JapaneseSanitizeHandler());
        register(new EmojiSanitizeHandler());
        register(new NumericSanitizeHandler());
        register(new ReservedWordSanitizeHandler());
        register(new RtlCharacterSanitizeHandler());
        register(new SpecialCharacterSanitizeHandler());
        register(new UnicodeSanitizeHandler());
        register(new ZalgoTextSanitizeHandler());
        register(new OghamSanitizeHandler());
        register(new SuperscriptSubscriptSanitizeHandler());
        register(new SymbolsSanitizeHandler());
        register(new MathOperatorsSanitizeHandler());
        register(new NumberFormsSanitizeHandler());
        register(new QuotationSanitizeHandler());
        register(new NonWhiteSpaceC0SanitizeHandler());
        register(new NonWhiteSpaceC1SanitizeHandler());
    }

    @Override
    public boolean sanitize(String value) throws ValueSanitizerException {
        if (enabled) {
            for (ValueSanitizeHandler enabledHandler : ValueSanitizerRegistry.getEnabledHandlers()) {
                enabledHandler.sanitize(value);
            }
        }
        return true;
    }

    public void register(ValueSanitizeHandler handler) {
        ValueSanitizerRegistry.register(handler);
    }

    @Override
    public void enabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public ValueSanitizer standard(boolean sanitize) {
        ValueSanitizerRegistry.enable(StandardSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer reservedString(boolean sanitize) {
        ValueSanitizerRegistry.enable(ReservedWordSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer special(boolean sanitize) {
        ValueSanitizerRegistry.enable(SpecialCharacterSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer numeric(boolean sanitize) {
        ValueSanitizerRegistry.enable(NumericSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer unicode(boolean sanitize) {
        ValueSanitizerRegistry.enable(UnicodeSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer chinese(boolean sanitize) {
        ValueSanitizerRegistry.enable(ChineseSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer japanese(boolean sanitize) {
        ValueSanitizerRegistry.enable(JapaneseSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer emoji(boolean sanitize) {
        ValueSanitizerRegistry.enable(EmojiSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer rtl(boolean sanitize) {
        ValueSanitizerRegistry.enable(RtlCharacterSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer zalgo(boolean sanitize) {
        ValueSanitizerRegistry.enable(ZalgoTextSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer ogham(boolean sanitize) {
        ValueSanitizerRegistry.enable(OghamSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer superscriptAndSubscript(boolean sanitize) {
        ValueSanitizerRegistry.enable(SuperscriptSubscriptSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer symbols(boolean sanitize) {
        ValueSanitizerRegistry.enable(SymbolsSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer numberForms(boolean sanitize) {
        ValueSanitizerRegistry.enable(NumberFormsSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer quotation(boolean sanitize) {
        ValueSanitizerRegistry.enable(QuotationSanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer nonWhiteSpaceC0Controls(boolean sanitize) {
        ValueSanitizerRegistry.enable(NonWhiteSpaceC0SanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer nonWhiteSpaceC1Controls(boolean sanitize) {
        ValueSanitizerRegistry.enable(NonWhiteSpaceC1SanitizeHandler.class, sanitize);
        return this;
    }

    @Override
    public ValueSanitizer mathOperators(boolean sanitize) {
        ValueSanitizerRegistry.enable(MathOperatorsSanitizeHandler.class, sanitize);
        return this;
    }
}
