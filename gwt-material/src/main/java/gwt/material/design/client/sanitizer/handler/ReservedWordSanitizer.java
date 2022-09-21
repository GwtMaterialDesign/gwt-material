package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Keywords;

public class ReservedWordSanitizer extends AbstractSanitizerHandler {

    public ReservedWordSanitizer() {
    }

    @Override
    public boolean sanitize(String value) {
        return contains(Keywords.RESERVED_WORDS, value, "Value must not contain reserved strings");
    }
}
