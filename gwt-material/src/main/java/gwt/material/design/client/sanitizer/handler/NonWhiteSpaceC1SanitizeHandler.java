package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class NonWhiteSpaceC1SanitizeHandler extends AbstractSanitizeHandler {

    public NonWhiteSpaceC1SanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.NON_WHITESPACE_C1_CONTROLS, value, "Value must not contain non whitespace C1 controls");
    }
}
