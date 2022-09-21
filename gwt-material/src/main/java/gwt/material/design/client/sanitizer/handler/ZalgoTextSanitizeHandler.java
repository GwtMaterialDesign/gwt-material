package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class ZalgoTextSanitizeHandler extends AbstractSanitizeHandler {

    public ZalgoTextSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.ZALGO, value, "Value must not contain Zalgo Characters");
    }
}
