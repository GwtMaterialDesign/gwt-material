package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.ValueSanitizerException;

public class RtlCharacterSanitizer extends AbstractSanitizerHandler {

    public RtlCharacterSanitizer() {
    }

    @Override
    public boolean sanitize(String value) {
        char[] chars = value.toCharArray();
        for(char c: chars){
            if(c >= 0x600 && c <= 0x6ff){
                throw new ValueSanitizerException("Value must not contain right to left characters");
            }
        }
        return true;
    }
}
