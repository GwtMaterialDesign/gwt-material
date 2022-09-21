package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class JapaneseSanitizeHandler extends AbstractSanitizeHandler {

    public JapaneseSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.JAPANESE, value, "Value must not contain Japanese characters");
    }
}
