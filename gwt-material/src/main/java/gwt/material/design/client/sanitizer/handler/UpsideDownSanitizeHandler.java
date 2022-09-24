package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;
//https://www.upsidedowntext.com/unicode
public class UpsideDownSanitizeHandler extends AbstractSanitizeHandler {

    public UpsideDownSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.UPSIDE_DOWN, value, "Value must not contain upside down characters");
    }
}
