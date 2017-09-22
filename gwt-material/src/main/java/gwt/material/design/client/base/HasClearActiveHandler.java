package gwt.material.design.client.base;

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.events.ClearActiveEvent;

public interface HasClearActiveHandler {

    /**
     * Fires when active state has been cleared to all children.
     */
    HandlerRegistration addClearActiveHandler(ClearActiveEvent.ClearActiveHandler handler);
}
