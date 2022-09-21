package gwt.material.design.client.sanitizer;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class StandardValueSanitizer extends AbstractValueSanitizer {

    public StandardValueSanitizer() {
    }

    @Override
    public boolean sanitize(String value) throws ValueSanitizerException {
        RegExp regExp = RegExp.compile(Patterns.STANDARD);
        MatchResult matcher = regExp.exec(value);
        if (matcher == null) {
            throw new ValueSanitizerException("Only Standard characters are allowed");
        }
        return false;
    }
}
