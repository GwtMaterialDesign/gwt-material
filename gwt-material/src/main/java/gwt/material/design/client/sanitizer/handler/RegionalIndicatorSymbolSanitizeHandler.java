package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class RegionalIndicatorSymbolSanitizeHandler extends AbstractSanitizeHandler {

    public RegionalIndicatorSymbolSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.REGIONAL_INDICATOR_SYMBOLS, value, "Value must not contain regional indicator symbols");
    }
}
