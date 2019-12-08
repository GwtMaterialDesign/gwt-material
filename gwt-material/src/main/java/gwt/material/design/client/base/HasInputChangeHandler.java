package gwt.material.design.client.base;

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.events.InputChangeEvent;

public interface HasInputChangeHandler {

    HandlerRegistration addInputChangeHandler(InputChangeEvent.InputChangeHandler handler);
}
