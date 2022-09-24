package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.Patterns;

//Reference https://www.fileformat.info/info/unicode/category/Nd/list.htm
public class UnicodeSanitizeHandler extends AbstractSanitizeHandler {

    public UnicodeSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return matches(Patterns.UNICODE, value, "Value must not contain unicode numbers");
    }
}
