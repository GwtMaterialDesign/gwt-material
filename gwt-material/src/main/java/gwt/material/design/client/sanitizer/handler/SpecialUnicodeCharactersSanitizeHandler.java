package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class SpecialUnicodeCharactersSanitizeHandler extends AbstractSanitizeHandler {

    public SpecialUnicodeCharactersSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.SPECIAL_UNICODE_CHARACTERS, value, "Value must not contain special unicode characters");
    }
}
