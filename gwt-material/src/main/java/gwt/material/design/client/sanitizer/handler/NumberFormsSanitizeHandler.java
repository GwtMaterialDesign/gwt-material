package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class NumberFormsSanitizeHandler extends AbstractSanitizeHandler {

    public NumberFormsSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.NUMBER_FORMS, value, "Value must not contain fraction, roman numerals and other number forms");
    }
}
