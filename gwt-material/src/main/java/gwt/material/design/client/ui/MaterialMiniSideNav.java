package gwt.material.design.client.ui;

import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.SideNavType;

import static gwt.material.design.client.js.JsMaterialElement.$;

public class MaterialMiniSideNav extends MaterialSideNav {

    private boolean expandable;
    private boolean expandOnClick;
    private HandlerRegistration miniWithOpeningExpandHandler;
    private HandlerRegistration miniWithClosingExpandHandler;

    public MaterialMiniSideNav() {
        super(SideNavType.MINI);
    }

    @Override
    protected void build() {
        applyBodyScroll();
        if (isExpandable()) {
            setType(SideNavType.MINI_WITH_EXPAND);
            applyTransition(getMain(), 400);
            applyTransition(getFooter(), 400);

            int originalWidth = getWidth();
            int miniWidth = 64;
            pushElement(getMain(), miniWidth);
            pushElementMargin(getFooter(), miniWidth);
            setShowOnAttach(false);
            setWidth(miniWidth);

            if (miniWithOpeningExpandHandler == null) {
                miniWithOpeningExpandHandler = addOpeningHandler(event -> expand(originalWidth));
            }

            if (miniWithClosingExpandHandler == null) {
                miniWithClosingExpandHandler = addClosingHandler(event -> collapse(miniWidth));
            }

            // Add Opening when sidenav link is clicked by default
            for (Widget w : getChildren()) {
                if (w instanceof MaterialWidget && isExpandOnClick()) {
                    $(w.getElement()).off("click").on("click", (e, param1) -> {
                        if (!getElement().hasClassName("expanded")) {
                            show();
                        }
                        return true;
                    });
                }
            }
        } else {
            setType(SideNavType.MINI);
            setWidth(64);
        }
    }

    protected void expand(int width) {
        addStyleName("expanded");
        setWidth(width);
        pushElement(getMain(), width);
        pushElementMargin(getFooter(), width);
    }

    protected void collapse(int width) {
        removeStyleName("expanded");
        setWidth(width);
        pushElement(getMain(), width);
        pushElementMargin(getFooter(), width);
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public boolean isExpandOnClick() {
        return expandOnClick;
    }

    public void setExpandOnClick(boolean expandOnClick) {
        this.expandOnClick = expandOnClick;
    }
}
