package gwt.material.design.client.sanitizer;

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.sanitizer.handler.ValueSanitizerErrorEvent;

public interface HasValueSanitizer {

    ValueSanitizer getValueSanitizer();

    void setValueSanitizer(ValueSanitizer valueSanitizer);

    HandlerRegistration addSanitizationErrorHandler(ValueSanitizerErrorEvent.ValueSanitizerErrorHandler handler);
}
