package gwt.material.design.client.sanitizer.handler;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import gwt.material.design.client.sanitizer.Keywords;
import gwt.material.design.client.sanitizer.Patterns;
import gwt.material.design.client.sanitizer.ValueSanitizerException;

public class DefaultValueSanitizerHandler implements ValueSanitizerHandler {

    public DefaultValueSanitizerHandler() {
    }

    @Override
    public boolean sanitizeReservedString(String value) {
        return contains(Keywords.RESERVED_WORDS, value, "Value must not contain reserved strings");
    }

    @Override
    public boolean sanitizeSpecial(String value) {
        return matches(Patterns.SPECIAL, value, "Value must not contain special characters");
    }

    //TODO: Non Whitespace C0
    //TODO: Non Whitespace C1
    //TODO: Whitespace
    //TODO: Unicode additional control characters
    //TODO: Byte order marks

    @Override
    public boolean sanitizeNumeric(String value) {
        return false;
    }

    @Override
    public boolean sanitizeUnicode(String value) {
        return false;
    }

    @Override
    public boolean sanitizeChinese(String value) {
        return matches(Patterns.CHINESE, value, "Value must not contain chinese characters");
    }

    @Override
    public boolean sanitizeEmoji(String value) throws ValueSanitizerException {
        return matches(Patterns.EMOJI, value, "Value must not contain Emoji characters");
    }

    @Override
    public boolean sanitizeRtl(String value) throws ValueSanitizerException {
        char[] chars = value.toCharArray();
        for(char c: chars){
            if(c >= 0x600 && c <= 0x6ff){
                throw new ValueSanitizerException("Value must not contain right to left characters");
            }
        }
        return true;
    }

    public boolean matches(String pattern, String value, String sanitizeError) {
        RegExp regExp = RegExp.compile(pattern);
        MatchResult matcher = regExp.exec(value);
        if (matcher != null) {
            throw new ValueSanitizerException(sanitizeError);
        }
        return false;
    }

    public boolean contains(String[] strings, String value, String sanitizeError) {
        for (String string : strings) {
            if (value.equals(string)) {
                throw new ValueSanitizerException(sanitizeError);
            }
        }
        return false;
    }
}
