package gwt.material.design.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class SideNavShownEvent extends GwtEvent<SideNavShownEvent.SideNavShownHandler> {

    public interface SideNavShownHandler extends EventHandler {
        void onSideNavShown(SideNavShownEvent event);
    }

    public static final Type<SideNavShownHandler> TYPE = new Type<>();

    public static void fire(HasHandlers source) {
        source.fireEvent(new SideNavShownEvent());
    }

    @Override
    public Type<SideNavShownHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SideNavShownHandler handler) {
        handler.onSideNavShown(this);
    }
}