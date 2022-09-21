package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class EmojiSanitizer extends AbstractSanitizerHandler {

    public EmojiSanitizer() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.EMOJI, value, "Value must not contain Emoji characters");
    }
}
