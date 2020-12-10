package gwt.material.design.client.base;

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.events.RegexValidationEvent;

public interface HasRegexHandlers {

    HandlerRegistration addRegexValidationHandler(RegexValidationEvent.RegexValidationHandler handler);
}
