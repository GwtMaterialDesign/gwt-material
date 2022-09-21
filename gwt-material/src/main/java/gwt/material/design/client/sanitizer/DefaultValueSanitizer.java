package gwt.material.design.client.sanitizer;

import gwt.material.design.client.sanitizer.handler.DefaultValueSanitizerHandler;

//TODO: Check
public class DefaultValueSanitizer extends AbstractValueSanitizer {

    public DefaultValueSanitizer() {
        super(new DefaultValueSanitizerHandler());

        reservedString(false);
        special(false);
        numeric(false);
        unicode(false);
        chinese(false);
        emoji(false);
        rtl(false);
    }
}
