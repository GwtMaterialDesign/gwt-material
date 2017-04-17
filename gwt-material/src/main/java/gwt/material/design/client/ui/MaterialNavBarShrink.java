package gwt.material.design.client.ui;

import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasShrinkableNavBarHandlers;
import gwt.material.design.client.constants.NavBarType;
import gwt.material.design.client.events.NavBarExpandEvent;
import gwt.material.design.client.events.NavBarShrinkEvent;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class MaterialNavBarShrink extends MaterialNavBar implements HasShrinkableNavBarHandlers {

    private int offset = 300;

    public MaterialNavBarShrink() {
        super();
        setInitialClasses(NavBarType.SHRINK.getCssName());
    }

    @Override
    protected void build() {
        super.build();
        $("header").css("position", "fixed");
        $("header").css("width", "100%");
        final boolean[] fired = {false};
        window().off().on("scroll", (e, param1) -> {
            int distanceY = window().scrollTop();

            if (distanceY > offset) {
                $(getElement()).addClass("smaller");
                if (!fired[0]) {
                    NavBarExpandEvent.fire(this);
                    fired[0] = true;
                }
            } else {
                if ($(getElement()).hasClass("smaller")) {
                    $(getElement()).removeClass("smaller");
                    NavBarShrinkEvent.fire(this);
                    fired[0] = false;
                }
            }
            return true;
        });
    }

    @Override
    public HandlerRegistration addExpandHandler(NavBarExpandEvent.NavBarExpandHandler handler) {
        return addHandler(handler, NavBarExpandEvent.TYPE);
    }

    @Override
    public HandlerRegistration addShrinkHandler(NavBarShrinkEvent.NavBarShrinkHandler handler) {
        return addHandler(handler, NavBarShrinkEvent.TYPE);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
