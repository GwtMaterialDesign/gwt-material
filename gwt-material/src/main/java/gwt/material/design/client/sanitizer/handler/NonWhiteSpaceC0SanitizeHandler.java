package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class NonWhiteSpaceC0SanitizeHandler extends AbstractSanitizeHandler {

    public NonWhiteSpaceC0SanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.NON_WHITESPACE_C0_CONTROLS, value, "Value must not contain non white space C0 controls");
    }
}
