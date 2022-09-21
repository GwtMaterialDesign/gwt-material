package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.ValueSanitizerException;
import gwt.material.design.client.sanitizer.utils.EmojiUtil;

public class EmojiSanitizeHandler extends AbstractSanitizeHandler {

    public EmojiSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        if (EmojiUtil.containsEmoji(value)) {
            throw new ValueSanitizerException("Value must not contain reserved emoji characters");
        }
        return true;
    }
}
