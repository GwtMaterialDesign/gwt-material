package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.ValueSanitizerException;

public interface ValueSanitizerHandler {

    boolean sanitizeReservedString(String value) throws ValueSanitizerException;

    boolean sanitizeSpecial(String value) throws ValueSanitizerException;

    boolean sanitizeNumeric(String value) throws ValueSanitizerException;

    boolean sanitizeUnicode(String value) throws ValueSanitizerException;

    boolean sanitizeChinese(String value) throws ValueSanitizerException;

    boolean sanitizeEmoji(String value) throws ValueSanitizerException;

    boolean sanitizeRtl(String value) throws ValueSanitizerException;
}
