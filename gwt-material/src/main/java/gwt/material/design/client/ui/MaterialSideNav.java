/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.helper.DOMHelper;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Edge;
import gwt.material.design.client.constants.SideNavType;
import gwt.material.design.client.events.*;
import gwt.material.design.client.events.SideNavClosedEvent.SideNavClosedHandler;
import gwt.material.design.client.events.SideNavClosingEvent.SideNavClosingHandler;
import gwt.material.design.client.events.SideNavOpenedEvent.SideNavOpenedHandler;
import gwt.material.design.client.events.SideNavOpeningEvent.SideNavOpeningHandler;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.js.JsSideNavOptions;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.jquery.client.api.JQuery;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * SideNav is a material component that gives you a lists of menus and other navigation components.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialSideNav ui:field="sideNav" width="280" m:id="mysidebar"  type="OPEN" closeOnClick="false">
 *     <m:MaterialLink href="#about" iconPosition="LEFT" iconType="OUTLINE" text="About" textColor="BLUE"  />
 *     <m:MaterialLink href="#gettingStarted" iconPosition="LEFT" iconType="DOWNLOAD" text="Getting Started" textColor="BLUE"  >
 * </m:MaterialSideNav>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#sidenavs">Material SideNav</a>
 */
//@formatter:on
public class MaterialSideNav extends MaterialWidget implements HasType<SideNavType>, HasSelectables, HasSideNavHandlers {

    private int width = 240;
    private Edge edge = Edge.LEFT;
    private boolean closeOnClick = false;
    private boolean alwaysShowActivator = false;
    private boolean allowBodyScroll = false;
    private boolean open;
    private Boolean showOnAttach;

    private Element activator;

    private final CssTypeMixin<SideNavType, MaterialSideNav> typeMixin = new CssTypeMixin<>(this);
    private final ToggleStyleMixin<MaterialSideNav> fixedMixin = new ToggleStyleMixin<>(this, CssName.FIXED);

    private HandlerRegistration fixedResizeHandler;

    /**
     * Container for App Toolbar and App Sidebar , contains Material Links,
     * Icons or any other material components.
     */
    public MaterialSideNav() {
        super(Document.get().createULElement(), CssName.SIDE_NAV);

        typeMixin.setType(SideNavType.FIXED);
    }

    /**
     * Creates a list and adds the given widgets.
     */
    public MaterialSideNav(final Widget... widgets) {
        this();
        for (final Widget w : widgets) {
            add(w);
        }
    }

    @UiConstructor
    public MaterialSideNav(SideNavType type) {
        this();
        setType(type);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        // Initialize the side nav
        initialize();

        if(showOnAttach != null) {
            // Ensure the side nav starts closed
            $(activator).trigger("menu-in", null);

            if (showOnAttach) {
                Scheduler.get().scheduleDeferred(() -> {
                    // We are ignoring cases with mobile
                    if (Window.getClientWidth() > 960) {
                        show();
                    }
                });
            }
        } else {
            if(!getType().equals(SideNavType.CARD)) {
                setLeft(0);
            }
            $(activator).trigger("menu-out", null);
        }
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        $("#sidenav-overlay").remove();
        activator = null;
    }


    @Override
    public HandlerRegistration addOpeningHandler(SideNavOpeningHandler handler) {
        return addHandler(handler, SideNavOpeningEvent.TYPE);
    }

    @Override
    public HandlerRegistration addOpenedHandler(SideNavOpenedHandler handler) {
        return addHandler(handler, SideNavOpenedEvent.TYPE);
    }

    @Override
    public HandlerRegistration addClosingHandler(SideNavClosingHandler handler) {
        return addHandler(handler, SideNavClosingEvent.TYPE);
    }

    @Override
    public HandlerRegistration addClosedHandler(SideNavClosedHandler handler) {
        return addHandler(handler, SideNavClosedEvent.TYPE);
    }

    public Widget wrap(Widget child) {
        if (child instanceof MaterialImage) {
            child.getElement().getStyle().setProperty("border", "1px solid #e9e9e9");
            child.getElement().getStyle().setProperty("textAlign", "center");
        }

        // Check whether the widget is not selectable by default
        boolean isNotSelectable = false;
        if (child instanceof MaterialWidget) {
            MaterialWidget widget = (MaterialWidget) child;
            if (widget.getInitialClasses() != null) {
                if (widget.getInitialClasses().length > 0) {
                    String initialClass = widget.getInitialClasses()[0];
                    if (initialClass.contains(CssName.SIDE_PROFILE) || initialClass.contains(CssName.COLLAPSIBLE)) {
                        isNotSelectable = true;
                    }
                }
            }
        }

        if (!(child instanceof ListItem)) {
            // Direct list item not collapsible
            final ListItem listItem = new ListItem();
            if (child instanceof MaterialCollapsible) {
                listItem.getElement().getStyle().setBackgroundColor("transparent");
            }
            if (child instanceof HasWaves) {
                listItem.setWaves(((HasWaves) child).getWaves());
                ((HasWaves) child).setWaves(null);
            }
            listItem.add(child);

            child = listItem;
        }

        // Collapsible and Side Porfile should not be selectable
        final Widget finalChild = child;
        if (!isNotSelectable) {
            // Active click handler
            finalChild.addDomHandler(event -> {
                clearActive();
                finalChild.addStyleName(CssName.ACTIVE);
            }, ClickEvent.getType());
        }
        child.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        return child;
    }

    @Override
    public void add(Widget child) {
        super.add(wrap(child));
    }

    @Override
    protected void insert(Widget child, com.google.gwt.user.client.Element container, int beforeIndex, boolean domInsert) {
        super.insert(wrap(child), container, beforeIndex, domInsert);
    }

    @Override
    public void setWidth(String width) {
        setWidth(Integer.parseInt(width));
    }

    /**
     * Set the menu's width in pixels.
     */
    public void setWidth(int width) {
        this.width = width;
        getElement().getStyle().setWidth(width, Unit.PX);
    }

    public int getWidth() {
        return width;
    }

    public boolean isCloseOnClick() {
        return closeOnClick;
    }

    /**
     * Close the side nav menu when an \<a\> tag is clicked
     * from inside it. Note that if you want this to work you
     * must wrap your item within a {@link MaterialLink}.
     */
    public void setCloseOnClick(boolean closeOnClick) {
        this.closeOnClick = closeOnClick;
    }

    public Edge getEdge() {
        return edge;
    }

    /**
     * Set which edge of the window the menu should attach to.
     */
    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    public boolean isFixed() {
        return fixedMixin.isOn();
    }

    /**
     * Fixed determines its display state on loading
     * (fixed being visible on load).
     */
    public void setFixed(boolean fixed) {
        fixedMixin.setOn(fixed);
    }

    /**
     * Define the menu's type specification.
     */
    public void setType(SideNavType type) {
        typeMixin.setType(type);
    }

    @Override
    public SideNavType getType() {
        return typeMixin.getType();
    }

    protected void processType(SideNavType type) {
        if (activator != null && type != null) {
            addStyleName(type.getCssName());
            switch (type) {
                case MINI:
                    setWidth(64);
                    break;
                case CARD:
                    new Timer() {
                        @Override
                        public void run() {
                            if (isSmall()) {
                                show();
                            }
                        }
                    }.schedule(500);
                    break;
                case PUSH:
                    applyPushType(width);
                    break;
            }
        }
    }

    protected boolean isSmall() {
        return !gwt.material.design.client.js.Window.matchMedia("all and (max-width: 992px)");
    }

    /**
     * Push the header, footer, and main to the right part when Close type is applied.
     */
    protected void applyPushType(int width) {
        $(JQuery.window()).off("resize").resize((e, param1) -> {
            pushElements(open, width);
            return true;
        });
    }

    protected void pushElements(boolean toggle, int width) {
        int w = 0;
        int dur = 200;
        if (!gwt.material.design.client.js.Window.matchMedia("all and (max-width: 992px)")) {
            if (toggle) {
                w = width;
                dur = 300;
            }
            applyTransition($("header").asElement(), w, dur);
            applyTransition($("main").asElement(), w, dur);
            applyTransition($("footer").asElement(), w, dur);
        }
        onPush(toggle, w, dur);
    }

    protected void applyTransition(Element elem, int width, int duration) {
        $(elem).css("transition", duration + "ms");
        $(elem).css("-moz-transition", duration + "ms");
        $(elem).css("-webkit-transition", duration + "ms");
        if(getEdge().equals(Edge.LEFT)){
            $(elem).css("margin-left", width + "px");
        }else{
            $(elem).css("margin-right", width + "px");
        }
    }

    protected void onPush(boolean toggle, int width, int duration) {
        SideNavPushEvent.fire(this, getElement(), activator, toggle, width, duration);
    }

    @Override
    public void clearActive() {
        clearActiveClass(this);
        ClearActiveEvent.fire(this);
    }

    /**
     * Reinitialize the side nav configurations when changing
     * properties.
     */
    public void reinitialize() {
        activator = null;
        initialize(false);
    }

    protected void initialize() {
        initialize(true);
    }

    protected void initialize(boolean strict) {
        if (activator == null) {
            activator = DOMHelper.getElementByAttribute("data-activates", getId());
            if (activator != null) {
                if (alwaysShowActivator || !isFixed()) {
                    String style = activator.getAttribute("style");
                    activator.setAttribute("style", style + "; display: block !important");
                    activator.removeClassName(CssName.NAVMENU_PERMANENT);
                }
            } else if (strict) {
                throw new RuntimeException("Cannot find an activator for the MaterialSideNav, " +
                        "please ensure you have a MaterialNavBar with an activator setup to match " +
                        "this widgets id.");
            }
        }

        SideNavType type = getType();
        processType(type);

        JsSideNavOptions options = new JsSideNavOptions();
        options.menuWidth = width;
        options.edge = edge != null ? edge.getCssName() : null;
        options.closeOnClick = closeOnClick;

        JsMaterialElement element = $(activator);
        element.sideNav(options);

        element.off("side-nav-closing");
        element.on("side-nav-closing", e1 -> {
            onClosing();
            return true;
        });

        element.off("side-nav-closed");
        element.on("side-nav-closed", e1 -> {
            onClosed();
            return true;
        });

        element.off("side-nav-opening");
        element.on("side-nav-opening", e1 -> {
            onOpening();
            return true;
        });

        element.off("side-nav-opened");
        element.on("side-nav-opened", e1 -> {
            onOpened();
            return true;
        });
    }

    protected void onClosing() {
        open = false;
        if (getType().equals(SideNavType.PUSH)) {
            pushElements(false, width);
        }

        SideNavClosingEvent.fire(this);
    }

    protected void onClosed() {
        SideNavClosedEvent.fire(this);
    }

    protected void onOpening() {
        open = true;
        if (getType().equals(SideNavType.PUSH)) {
            pushElements(true, width);
        }

        SideNavOpeningEvent.fire(this);
    }

    protected void onOpened() {
        if (allowBodyScroll) {
            RootPanel.getBodyElement().getStyle().clearOverflow();
        }

        SideNavOpenedEvent.fire(this);
    }

    /**
     * Hide the overlay menu.
     */
    public void hideOverlay() {
        $("#sidenav-overlay").remove();
    }

    /**
     * Show the sidenav using the activator element
     */
    public void show() {
        $(activator).sideNav("show");
    }

    /**
     * Hide the sidenav using the activator element
     */
    public void hide() {
        $(activator).sideNav("hide");
    }

    public boolean isOpen() {
        return open;
    }

    /**
     * Will the body have scroll capability
     * while the menu is open.
     */
    public boolean isAllowBodyScroll() {
        return allowBodyScroll;
    }

    /**
     * Allow the body to maintain its scroll capability
     * while the menu is visible.
     */
    public void setAllowBodyScroll(boolean allowBodyScroll) {
        this.allowBodyScroll = allowBodyScroll;
    }

    /**
     * Will the activator always be shown.
     */
    public boolean isAlwaysShowActivator() {
        return alwaysShowActivator;
    }

    /**
     * Disable the hiding of your activator element.
     */
    public void setAlwaysShowActivator(boolean alwaysShowActivator) {
        this.alwaysShowActivator = alwaysShowActivator;
    }

    /**
     * Will the menu forcefully show on attachment.
     */
    public boolean isShowOnAttach() {
        return showOnAttach != null && showOnAttach;
    }

    /**
     * Show the menu upon attachment.<br>
     * Note that you shouldn't apply this setting if you want your side nav to appear static.
     * otherwise when set to <code>true</code> will slide in from the left.
     */
    public void setShowOnAttach(boolean showOnAttach) {
        this.showOnAttach = showOnAttach;
    }

    @Override
    public void setEnabled(boolean enabled) {
        getEnabledMixin().setEnabled(this, enabled);
    }
}
