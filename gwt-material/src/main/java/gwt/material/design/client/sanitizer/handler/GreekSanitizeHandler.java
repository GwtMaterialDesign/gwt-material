package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class GreekSanitizeHandler extends AbstractSanitizeHandler {

    public GreekSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.GREEK, value, "Value must not contain greek symbols");
    }
}
