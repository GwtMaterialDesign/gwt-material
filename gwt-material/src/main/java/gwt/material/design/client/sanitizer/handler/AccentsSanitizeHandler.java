package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class AccentsSanitizeHandler extends AbstractSanitizeHandler {

    public AccentsSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.ACCENTS, value, "Value must not contain accent symbols");
    }
}
