package gwt.material.design.client.sanitizer.handler;

public class UnicodeSanitizer extends AbstractSanitizerHandler {

    public UnicodeSanitizer() {
    }

    @Override
    public boolean sanitize(String value) {
        return true;
    }
}
