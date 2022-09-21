package gwt.material.design.client.sanitizer.handler;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import gwt.material.design.client.sanitizer.Patterns;
import gwt.material.design.client.sanitizer.ValueSanitizerException;

public class StandardSanitizeHandler extends AbstractSanitizeHandler {

    public StandardSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {
        RegExp regExp = RegExp.compile(Patterns.STANDARD);
        MatchResult matcher = regExp.exec(value);
        if (value != null && !value.isEmpty() && matcher == null) {
            throw new ValueSanitizerException("Only Standard characters are allowed");
        }
        return true;
    }
}
