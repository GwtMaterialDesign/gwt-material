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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.helper.DOMHelper;
import gwt.material.design.client.base.mixin.StyleMixin;
import gwt.material.design.client.constants.*;
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
public class MaterialSideNav extends MaterialWidget implements HasSelectables, HasInOutDurationTransition, HasSideNavHandlers {

    private int width = 240;
    private Edge edge = Edge.LEFT;
    private boolean closeOnClick = false;
    private boolean alwaysShowActivator = true;
    private boolean allowBodyScroll = false;
    private boolean open;
    private Boolean showOnAttach;
    private Element activator;
    private int inDuration = 400;
    private int outDuration = 200;

    private final StyleMixin<MaterialSideNav> typeMixin = new StyleMixin<>(this);

    /**
     * Container for App Toolbar and App Sidebar , contains Material Links,
     * Icons or any other material components.
     */
    public MaterialSideNav() {
        super(Document.get().createULElement(), CssName.SIDE_NAV);
        setType(SideNavType.FIXED);
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

    public MaterialSideNav(SideNavType type) {
        this();
        setType(type);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        // Initialize the side nav
        initialize();

        if (showOnAttach != null) {
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

    protected void build() {
        applyFixedType();
    }

    protected void setType(SideNavType type) {
        typeMixin.setStyle(type.getCssName());
    }

    protected boolean isSmall() {
        return !gwt.material.design.client.js.Window.matchMedia("all and (max-width: 992px)");
    }

    protected MaterialWidget getNavMenu() {
        Element navMenuElement = DOMHelper.getElementByAttribute("data-activates", getId());
        if (navMenuElement != null) {
            return new MaterialWidget(navMenuElement);
        }
        return null;
    }

    /**
     * Provides a Fixed type sidenav which by default on desktop - activator will notbe visible
     * but you can configure it by setting the property setAlwaysShowActivator() to true
     */
    protected void applyFixedType() {
        setType(SideNavType.FIXED);
        applyBodyScroll();
        $(JQuery.window()).off("resize").resize((e, param1) -> {
            if (gwt.material.design.client.js.Window.matchMedia("all and (min-width: 992px)")) {
                Scheduler.get().scheduleDeferred(() -> show());
            }
            return true;
        });

        Scheduler.get().scheduleDeferred(() -> {
            pushElement(getHeader(), this.width);
            pushElement(getMain(), this.width);
            pushElement(getFooter(), this.width);
        });
    }

    protected void pushElement(Element element, int value) {
        if (getEdge() == Edge.RIGHT) {
            $(element).css("paddingRight", value + "px");
        } else {
            $(element).css("paddingLeft", value + "px");
        }

    }

    protected void pushElementMargin(Element element, int value) {
        if (getEdge() == Edge.LEFT) {
            $(element).css("margin-left", value + "px");
        } else {
            $(element).css("margin-right", value + "px");
        }
    }

    protected void applyBodyScroll() {
        if (isAllowBodyScroll()) {
            $("header").css("width", "100%");
            $("header").css("position", "fixed");
            $("header").css("zIndex", "999");
            $(getElement()).css("position", "fixed");
        }
    }

    protected Element getMain() {
        return $("main").asElement();
    }

    protected Element getHeader() {
        return $("header").asElement();
    }

    protected Element getFooter() {
        return $("footer").asElement();
    }

    protected void pushElements(boolean toggle, int width) {
        int w = 0;
        if (!gwt.material.design.client.js.Window.matchMedia("all and (max-width: 992px)")) {
            if (toggle) {
                w = width;
            }

            applyTransition(getHeader());
            pushElementMargin(getHeader(), w);

            applyTransition(getMain());
            pushElementMargin(getMain(), w);

            applyTransition(getFooter());
            pushElementMargin(getFooter(), w);
        }
        onPush(toggle, w);
    }

    protected void applyTransition(Element element) {
        int duration;
        if (isOpen()) {
            duration = inDuration;
        } else {
            duration = outDuration;
        }
        setTransition(new TransitionProperty(element, duration, "all"));
    }

    protected void onPush(boolean toggle, int width) {
        SideNavPushEvent.fire(this, getElement(), activator, toggle, width);
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
        try {
            activator = DOMHelper.getElementByAttribute("data-activates", getId());
            getNavMenu().setShowOn(ShowOn.SHOW_ON_MED_DOWN);
            if (alwaysShowActivator && !typeMixin.getStyle().equals(SideNavType.FIXED.getCssName())) {
                getNavMenu().setShowOn(ShowOn.SHOW_ON_LARGE);
            } else {
                getNavMenu().setHideOn(HideOn.HIDE_ON_LARGE);
            }
            getNavMenu().removeStyleName(CssName.NAVMENU_PERMANENT);
        } catch (Exception ex) {
            if (strict) {
                throw new IllegalArgumentException("Could not setup MaterialSideNav please ensure you have MaterialNavBar with an activator setup to match this widgets id.");
            }
        }

        applyTransition(getElement());
        build();

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
        SideNavClosingEvent.fire(this);
    }

    protected void onClosed() {
        SideNavClosedEvent.fire(this);
    }

    protected void onOpening() {
        open = true;
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

    @Override
    protected void onDetach() {
        super.onDetach();
        getNavMenu().setVisibility(Style.Visibility.HIDDEN);
        getNavMenu().removeStyleName(ShowOn.SHOW_ON_LARGE.getCssName());
        getNavMenu().removeStyleName(ShowOn.SHOW_ON_MED_DOWN.getCssName());
        pushElement(getHeader(), 0);
        pushElement(getMain(), 0);
        pushElementMargin(getFooter(), 0);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        getNavMenu().setVisibility(Style.Visibility.VISIBLE);
    }


    @Override
    public void setInDuration(int inDuration) {
        this.inDuration = inDuration;
    }

    @Override
    public int getInDuration() {
        return inDuration;
    }

    @Override
    public void setOutDuration(int outDuration) {
        this.outDuration = outDuration;
    }

    @Override
    public int getOutDuration() {
        return outDuration;
    }
}