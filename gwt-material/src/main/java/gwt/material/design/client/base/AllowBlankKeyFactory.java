package gwt.material.design.client.base;

public class AllowBlankKeyFactory<T> implements KeyFactory<T, String> {
    public static final String BLANK_VALUE_TEXT = "";

    @Override
    public String generateKey(T object) {
        if (object == null) {
            return BLANK_VALUE_TEXT;
        } else {
            return object.toString();
        }
    }
}