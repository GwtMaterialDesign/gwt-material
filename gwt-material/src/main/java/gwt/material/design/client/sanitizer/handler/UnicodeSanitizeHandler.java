package gwt.material.design.client.sanitizer.handler;

public class UnicodeSanitizeHandler extends AbstractSanitizeHandler {

    public UnicodeSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        return true;
    }
}
