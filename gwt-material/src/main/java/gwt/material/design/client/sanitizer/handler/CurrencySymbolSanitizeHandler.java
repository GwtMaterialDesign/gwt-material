package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class CurrencySymbolSanitizeHandler extends AbstractSanitizeHandler {

    public CurrencySymbolSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.CURRENCY_SYMBOLS, value, "Value must not contain currency symbols");
    }
}
