package gwt.material.design.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ObservedEvent extends GwtEvent<ObservedEvent.ObservedHandler> {

    public interface ObservedHandler extends EventHandler {
        void onObserved(ObservedEvent event);
    }

    public static final Type<ObservedHandler> TYPE = new Type<>();

    private final String old;
    private final String value;

    public ObservedEvent(String old, String value) {
        this.old = old;
        this.value = value;
    }

    public static void fire(HasHandlers source, String old, String value) {
        source.fireEvent(new ObservedEvent(old, value));
    }

    public String getOld() {
        return old;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Type<ObservedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ObservedHandler handler) {
        handler.onObserved(this);
    }
}