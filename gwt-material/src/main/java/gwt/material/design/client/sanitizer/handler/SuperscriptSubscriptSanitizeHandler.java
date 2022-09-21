package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class SuperscriptSubscriptSanitizeHandler extends AbstractSanitizeHandler {

    public SuperscriptSubscriptSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.SUPERSCRIPT_SUBSCRIPT, value, "Value must not contain superscript and subscript characters");
    }
}
