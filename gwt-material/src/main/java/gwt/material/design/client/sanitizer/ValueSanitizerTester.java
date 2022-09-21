package gwt.material.design.client.sanitizer;

public class ValueSanitizerTester {

    public static DefaultValueSanitizer sanitizer = new DefaultValueSanitizer();

    public static void main(String[] args) {
        try {
            System.out.println("Valid : " + sanitizer.sanitize("$"));
        } catch (Exception e) {
            System.out.println(e + "");
        }
    }
}
