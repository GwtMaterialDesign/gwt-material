package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

public class OghamSanitizeHandler extends AbstractSanitizeHandler {

    public OghamSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.OGHAM, value, "Value must not contain Ogham characters");
    }
}
