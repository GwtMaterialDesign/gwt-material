package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class ChineseSanitizeHandler extends AbstractSanitizeHandler {

    public ChineseSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.CHINESE, value, "Value must not contain chinese characters");
    }
}
