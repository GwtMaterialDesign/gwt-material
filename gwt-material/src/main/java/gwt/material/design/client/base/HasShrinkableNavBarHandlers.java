package gwt.material.design.client.base;

import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.events.NavBarExpandEvent;
import gwt.material.design.client.events.NavBarShrinkEvent;

public interface HasShrinkableNavBarHandlers {

    HandlerRegistration addExpandHandler(NavBarExpandEvent.NavBarExpandHandler handler);

    HandlerRegistration addShrinkHandler(NavBarShrinkEvent.NavBarShrinkHandler handler);
}
