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
}
