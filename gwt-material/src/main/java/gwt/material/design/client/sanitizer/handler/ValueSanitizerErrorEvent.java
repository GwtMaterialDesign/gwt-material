package gwt.material.design.client.sanitizer.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ValueSanitizerErrorEvent extends GwtEvent<ValueSanitizerErrorEvent.ValueSanitizerErrorHandler> {

    protected String localizedMessage;

    public ValueSanitizerErrorEvent(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    public interface ValueSanitizerErrorHandler extends EventHandler {
        void onSideNavOpened(ValueSanitizerErrorEvent event);
    }

    public static final Type<ValueSanitizerErrorHandler> TYPE = new Type<>();

    public static void fire(HasHandlers source, String localizedMessage) {
        source.fireEvent(new ValueSanitizerErrorEvent(localizedMessage));
    }

    @Override
    public Type<ValueSanitizerErrorHandler> getAssociatedType() {
        return TYPE;
    }

    public String getLocalizedMessage() {
        return localizedMessage;
    }

    @Override
    protected void dispatch(ValueSanitizerErrorHandler handler) {
        handler.onSideNavOpened(this);
    }
}