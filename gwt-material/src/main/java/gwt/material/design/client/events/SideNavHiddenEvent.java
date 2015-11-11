package gwt.material.design.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class SideNavHiddenEvent extends GwtEvent<SideNavHiddenEvent.SideNavHiddenHandler> {

    public interface SideNavHiddenHandler extends EventHandler {
        void onSideNavHidden(SideNavHiddenEvent event);
    }

    public static final Type<SideNavHiddenHandler> TYPE = new Type<>();

    public static void fire(HasHandlers source) {
        source.fireEvent(new SideNavHiddenEvent());
    }

    @Override
    public Type<SideNavHiddenHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SideNavHiddenHandler handler) {
        handler.onSideNavHidden(this);
    }
}