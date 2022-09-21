package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class ChineseSanitizer extends AbstractSanitizerHandler {

    public ChineseSanitizer() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.CHINESE, value, "Value must not contain chinese characters");
    }
}
