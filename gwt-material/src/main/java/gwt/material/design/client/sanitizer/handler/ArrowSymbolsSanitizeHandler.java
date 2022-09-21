package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class ArrowSymbolsSanitizeHandler extends AbstractSanitizeHandler {

    public ArrowSymbolsSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.ARROW_SYMBOLS, value, "Value must not contain arrow symbols");
    }
}
