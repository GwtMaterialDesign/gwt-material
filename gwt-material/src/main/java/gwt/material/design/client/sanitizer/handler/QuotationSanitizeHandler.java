package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class QuotationSanitizeHandler extends AbstractSanitizeHandler {

    public QuotationSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.QUOTATION, value, "Value must not contain quotation marks");
    }
}
