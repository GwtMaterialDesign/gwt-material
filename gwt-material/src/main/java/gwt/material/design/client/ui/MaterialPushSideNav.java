package gwt.material.design.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasWithHeader;
import gwt.material.design.client.constants.SideNavType;
import gwt.material.design.jquery.client.api.JQuery;

import static gwt.material.design.client.js.JsMaterialElement.$;

public class MaterialPushSideNav extends MaterialSideNav implements HasWithHeader {

    private HandlerRegistration pushWithHeaderOpeningHandler;
    private HandlerRegistration pushWithHeaderClosingHandler;

    public MaterialPushSideNav() {
        super(SideNavType.PUSH);
    }

    @Override
    protected void build() {
        setWithHeader(false);
    }

    @Override
    public void setWithHeader(boolean withHeader) {
        if (withHeader) {
            applyPushWithHeader();
        } else {
            applyPushType();
        }
    }

    /**
     * Push the header, footer, and main to the right part when Close type is applied.
     */
    protected void applyPushType() {
        setType(SideNavType.PUSH);
        $(JQuery.window()).off("resize").resize((e, param1) -> {
            if (!isAlwaysShowActivator() && !isOpen() && gwt.material.design.client.js.Window.matchMedia("all and (min-width: 992px)")) {
                show();
            }
            pushElements(isOpen(), getWidth());
            return true;
        });
    }

    protected void applyPushWithHeader() {
        MaterialToast.fireToast("WITH HEADER");
        setType(SideNavType.PUSH_WITH_HEADER);
        applyTransition(getMain(), 200);
        applyTransition(getFooter(), 200);
        applyBodyScroll();
        if (isShowOnAttach()) {
            Scheduler.get().scheduleDeferred(() -> {
                pushElement(getHeader(), 0);
                pushElement(getMain(), getWidth());
                pushElementMargin(getFooter(), getWidth());
            });
        }

        if (pushWithHeaderOpeningHandler == null) {
            pushWithHeaderOpeningHandler = addOpeningHandler(event -> {
                pushElement(getMain(), getWidth());
                pushElementMargin(getFooter(), getWidth());
            });
        }

        if (pushWithHeaderClosingHandler == null) {
            pushWithHeaderClosingHandler = addClosingHandler(event -> {
                pushElement(getMain(), 0);
                pushElementMargin(getFooter(), 0);
            });
        }
    }

    @Override
    protected void onClosing() {
        super.onClosing();
        pushElements(false, getWidth());
    }

    @Override
    protected void onOpening() {
        super.onOpening();
        pushElements(true, getWidth());
    }
}
