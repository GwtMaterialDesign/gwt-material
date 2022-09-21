package gwt.material.design.client.sanitizer.handler;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import gwt.material.design.client.sanitizer.ValueSanitizerException;

public abstract class AbstractSanitizerHandler implements ValueSanitizerHandler {

    protected boolean enabled;

    public AbstractSanitizerHandler() {

    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
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
