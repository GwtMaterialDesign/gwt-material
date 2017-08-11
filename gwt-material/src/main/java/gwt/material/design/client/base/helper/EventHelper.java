package gwt.material.design.client.base.helper;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

public final class EventHelper {

    public static void onAttachOnce(HasAttachHandlers has, AttachEvent.Handler handler) {
        HandlerRegistration[] reg = new HandlerRegistration[1];
        reg[0] = has.addAttachHandler(event -> {
            if(event.isAttached()) {
                handler.onAttachOrDetach(event);

                if (reg[0] != null) {
                    reg[0].removeHandler();
                }
            }
        });
    }
}
