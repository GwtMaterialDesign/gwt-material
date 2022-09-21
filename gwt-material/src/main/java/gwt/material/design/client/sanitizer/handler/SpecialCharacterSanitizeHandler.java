package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class SpecialCharacterSanitizeHandler extends AbstractSanitizeHandler {

    public SpecialCharacterSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.SPECIAL, value, "Value must not contain special characters");
    }
}
