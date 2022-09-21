package gwt.material.design.client.sanitizer;

public interface ValueSanitizer {

    boolean sanitize(String value) throws ValueSanitizerException;

    ValueSanitizer reservedString(boolean allow);

    ValueSanitizer special(boolean allow);

    ValueSanitizer numeric(boolean allow);

    ValueSanitizer unicode(boolean allow);

    ValueSanitizer chinese(boolean allow);

    ValueSanitizer emoji(boolean allow);
}
