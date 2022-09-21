package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class WhiteSpaceZsZlZpSanitizeHandler extends AbstractSanitizeHandler {

    public WhiteSpaceZsZlZpSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.WHITESPACE_ZSZlZP, value, "Value must not contain whitespace characters with category Zs Z1 or Zp");
    }
}
