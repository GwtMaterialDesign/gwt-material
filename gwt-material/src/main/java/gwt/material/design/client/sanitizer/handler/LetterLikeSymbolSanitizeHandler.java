package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class LetterLikeSymbolSanitizeHandler extends AbstractSanitizeHandler {

    public LetterLikeSymbolSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.LETTER_LIKE_SYMBOLS, value, "Value must not contain letter like symbols");
    }
}
