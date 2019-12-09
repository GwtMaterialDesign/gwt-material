package gwt.material.design.client.base;

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.events.ToggleReadOnlyEvent;

public interface HasToggleReadOnlyHandler {

    HandlerRegistration addToggleReadOnlyHandler(ToggleReadOnlyEvent.ToggleReadOnlyHandler handler);
}
