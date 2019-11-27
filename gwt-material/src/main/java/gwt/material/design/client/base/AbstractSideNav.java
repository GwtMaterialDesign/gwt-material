/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
package gwt.material.design.client.base;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.density.Density;
import gwt.material.design.client.base.helper.DOMHelper;
import gwt.material.design.client.base.mixin.DensityMixin;
import gwt.material.design.client.base.mixin.OverlayStyleMixin;
import gwt.material.design.client.base.mixin.StyleMixin;
import gwt.material.design.client.base.viewport.ViewPort;
import gwt.material.design.client.base.viewport.WidthBoundary;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.events.*;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.js.JsSideNavOptions;
import gwt.material.design.client.ui.*;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.jquery.client.api.JQueryElement;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * AbstractSideNav handles the creation and Ui logic for
 * different sidenavs, you can easily setup any kind of logic for
 * you sidenav behaviour in {@link AbstractSideNav#setup()}.
 *
 * @author kevzlou7979
 */
//@formatter:on
public abstract class AbstractSideNav extends MaterialWidget
        implements JsLoader, HasSelectables, HasInOutDurationTransition, HasSideNavHandlers, HasOverlayStyle, HasDensity {

    protected int width = 240;
    protected int inDuration = 400;
    protected int outDuration = 200;
    protected boolean open;
    protected boolean closeOnClick;
    protected boolean alwaysShowActivator = true;
    protected boolean allowBodyScroll = true;
    protected Edge edge = Edge.LEFT;
    protected Boolean showOnAttach;
    protected Element activator;
    protected WidthBoundary closingBoundary;
    protected ViewPort autoHideViewport;
    protected OverlayOption overlayOption = OverlayOption.create();

    private StyleMixin<MaterialSideNav> typeMixin;
    private OverlayStyleMixin<AbstractSideNav> overlayStyleMixin;
    private DensityMixin<AbstractSideNav> densityMixin;

    public AbstractSideNav() {
        super(Document.get().createULElement(), CssName.SIDE_NAV);
    }

    public AbstractSideNav(final Widget... widgets) {
        this();
        for (final Widget w : widgets) {
            add(w);
        }
    }

    public AbstractSideNav(SideNavType type) {
        this();
        setType(type);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        load();

        setupShowOnAttach();
    }

    protected void setupDefaultOverlayStyle() {
        overlayOption.setVisibility(Style.Visibility.HIDDEN);
        setOverlayOption(overlayOption);
    }

    protected void setupShowOnAttach() {
        if (showOnAttach != null) {
            // Ensure the side nav starts closed
            $(activator).trigger("menu-in", null);

            if (showOnAttach) {
                Scheduler.get().scheduleDeferred(() -> {
                    // We are ignoring cases with mobile
                    if (Window.getClientWidth() > 960) {
                        open();
                    }
                });
            }
        } else {
            if (Window.getClientWidth() > 960) {
                $(activator).trigger("menu-out", null);
            }
        }
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        unload();
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
                    if (child instanceof HasNoSideNavSelection) {
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
            if (child instanceof HasNoSideNavSelection) {
                super.add(child);
            } else {
                listItem.add(child);
                child = listItem;
            }
        }

        // Collapsible and Side Porfile should not be selectable
        final Widget finalChild = child;
        if (!isNotSelectable) {
            // Active click handler
            registerHandler(finalChild.addDomHandler(event -> {
                clearActive();
                finalChild.addStyleName(CssName.ACTIVE);
            }, ClickEvent.getType()));
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

    protected void pushElement(Element element, int value) {
        applyTransition($(element).asElement());
        if (getEdge() == Edge.RIGHT) {
            $(element).css("paddingRight", value + "px");
        } else {
            $(element).css("paddingLeft", value + "px");
        }

    }

    protected void pushElementMargin(Element element, int value) {
        applyTransition($(element).asElement());
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
            $("header").css("zIndex", "997");
            $(getElement()).css("position", "fixed");
        }
    }

    protected void applyTransition(Element element) {
        applyTransition(element, "all");
    }

    protected void applyTransition(Element element, String property) {
        int duration;
        if (isOpen()) {
            duration = inDuration;
        } else {
            duration = outDuration;
        }
        if (element != null) {
            setTransition(new TransitionConfig(element, duration, 0, property, "cubic-bezier(0, 0, 0.2, 1)"));
        }
    }

    @Override
    public void clearActive() {
        clearActiveClass(this);
        ClearActiveEvent.fire(this);
    }

    public void setActive(int index) {
        clearActive();
        getWidget(index).addStyleName(CssName.ACTIVE);
    }

    @Override
    public void load() {
        load(true);
    }

    @Override
    public void unload() {
        activator = null;
        $(".drag-target").remove();
    }

    /**
     * Reinitialize the side nav configurations when changing properties.
     */
    @Override
    public void reload() {
        unload();
        load(false);
    }

    protected void load(boolean strict) {
        try {
            activator = DOMHelper.getElementByAttribute("data-activates", getId());
            getNavMenu().setShowOn(ShowOn.SHOW_ON_MED_DOWN);
            if (alwaysShowActivator && !getTypeMixin().getStyle().equals(SideNavType.FIXED.getCssName())) {
                getNavMenu().setShowOn(ShowOn.SHOW_ON_LARGE);
            } else {
                getNavMenu().setHideOn(HideOn.HIDE_ON_LARGE);
            }
            getNavMenu().removeStyleName(CssName.NAVMENU_PERMANENT);
        } catch (Exception ex) {
            if (strict) {
                throw new IllegalArgumentException(
                        "Could not setup MaterialSideNav please ensure you have " +
                                "MaterialNavBar with an activator setup to match this widgets id.", ex);
            }
        }

        setup();
        setupDefaultOverlayStyle();

        JsSideNavOptions options = new JsSideNavOptions();
        options.menuWidth = width;
        options.edge = edge != null ? edge.getCssName() : null;
        options.closeOnClick = closeOnClick;

        JsMaterialElement element = $(activator);
        element.sideNav(options);

        element.off(SideNavEvents.SIDE_NAV_CLOSING);
        element.on(SideNavEvents.SIDE_NAV_CLOSING, e1 -> {
            onClosing();
            return true;
        });

        element.off(SideNavEvents.SIDE_NAV_CLOSED);
        element.on(SideNavEvents.SIDE_NAV_CLOSED, e1 -> {
            onClosed();
            return true;
        });

        element.off(SideNavEvents.SIDE_NAV_OPENING);
        element.on(SideNavEvents.SIDE_NAV_OPENING, e1 -> {
            onOpening();
            return true;
        });

        element.off(SideNavEvents.SIDE_NAV_OPENED);
        element.on(SideNavEvents.SIDE_NAV_OPENED, e1 -> {
            onOpened();
            return true;
        });

        element.off(SideNavEvents.SIDE_NAV_OVERLAY_ATTACHED);
        element.on(SideNavEvents.SIDE_NAV_OVERLAY_ATTACHED, e1 -> {
            onOverlayAttached();
            return true;
        });

        $(".collapsible-header").on("click", (e, param1) -> {
            //e.stopPropagation();

            return true;
        });
    }

    /**
     * Override the type of your sidenav.
     * Used by {@link MaterialSideNavDrawer}, {@link MaterialSideNavCard}, {@link MaterialSideNavMini}, {@link MaterialSideNavPush}
     */
    protected abstract void setup();

    @Override
    protected void onDetach() {
        super.onDetach();
        MaterialWidget navMenu = getNavMenu();
        if (navMenu != null) {
            navMenu.removeStyleName(ShowOn.SHOW_ON_LARGE.getCssName());
            navMenu.removeStyleName(ShowOn.SHOW_ON_MED_DOWN.getCssName());
        }
        pushElement(getHeader(), 0);
        pushElement(getMain(), 0);
        pushElementMargin(getFooter(), 0);
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

    @Override
    public void setWidth(String width) {
        setWidth(Integer.parseInt(width));
    }

    /**
     * Set the menu's width in pixels.
     */
    public void setWidth(int width) {
        this.width = width;
        getElement().getStyle().setWidth(width, Style.Unit.PX);
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

    protected void setType(SideNavType type) {
        getTypeMixin().setStyle(type.getCssName());
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

    protected void onClosing() {
        open = false;
        $("#sidenav-overlay").remove();
        SideNavClosingEvent.fire(this);

        resetOverlayStyle();
    }

    protected void onClosed() {
        SideNavClosedEvent.fire(this);
    }

    protected void onOpening() {
        open = true;

        $("#sidenav-overlay").each((param1, element) -> {
            if (element != null) {
                element.removeFromParent();
            }
        });

        SideNavOpeningEvent.fire(this);
    }

    protected void onOpened() {
        if (allowBodyScroll) {
            RootPanel.getBodyElement().getStyle().clearOverflow();
        }

        String overlayZIndex = $("#sidenav-overlay").css("zIndex");
        $(".drag-target").css("zIndex", (overlayZIndex != null ? Integer.parseInt(overlayZIndex) : 1) + "");
        SideNavOpenedEvent.fire(this);
    }

    protected void onOverlayAttached() {
        applyOverlayStyle(getOverlayElement());
    }

    /**
     * Hide the overlay menu.
     */
    public void hideOverlay() {
        $("#sidenav-overlay").remove();
    }

    /**
     * Replaced with {@link #open()}
     */
    @Deprecated
    public void show() {
        open();
    }

    /**
     * Show the sidenav using the activator element
     */
    public void open() {
        $("#sidenav-overlay").remove();
        $(activator).sideNav("show");
    }

    /**
     * Replaced with {@link #close()}
     */
    @Deprecated
    public void hide() {
        close();
    }

    /**
     * Hide the sidenav using the activator element
     */
    public void close() {
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

    public boolean isAutoHideOnResize() {
        return autoHideViewport != null;
    }

    /**
     * When enabled the sidenav will auto hide / collapse it durating browser resized.
     * Default true
     */
    public void setAutoHideOnResize(boolean autoHideOnResize) {
        if (autoHideOnResize) {
            autoHideViewport = ViewPort.when(getClosingBoundary()).then(param1 -> hide());
        } else {
            if (autoHideViewport != null) {
                autoHideViewport.destroy();
                autoHideViewport = null;
            }
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        getEnabledMixin().setEnabled(this, enabled);
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

    @Override
    public void setOverlayOption(OverlayOption overlayOption) {
        getOverlayStyleMixin().setOverlayOption(overlayOption);
    }

    @Override
    public OverlayOption getOverlayOption() {
        return getOverlayStyleMixin().getOverlayOption();
    }

    @Override
    public void applyOverlayStyle(JQueryElement overlayElement) {
        getOverlayStyleMixin().applyOverlayStyle(getOverlayElement());
    }

    @Override
    public void resetOverlayStyle() {
        getOverlayStyleMixin().resetOverlayStyle();
    }

    public void setClosingBoundary(WidthBoundary closingBoundary) {
        this.closingBoundary = closingBoundary;
    }

    public WidthBoundary getClosingBoundary() {
        if (closingBoundary == null) {
            closingBoundary = new WidthBoundary(0, 992);
        }
        return closingBoundary;
    }

    @Override
    public void setDensity(Density density) {
        getDensityMixin().setDensity(density);
    }

    @Override
    public Density getDensity() {
        return getDensityMixin().getDensity();
    }

    public Element getActivator() {
        return activator;
    }

    public JQueryElement getOverlayElement() {
        return $("#sidenav-overlay");
    }

    public JQueryElement getDragTargetElement() {
        return $(".drag-target");
    }

    @Override
    public HandlerRegistration addOpeningHandler(SideNavOpeningEvent.SideNavOpeningHandler handler) {
        return addHandler(handler, SideNavOpeningEvent.TYPE);
    }

    @Override
    public HandlerRegistration addOpenedHandler(SideNavOpenedEvent.SideNavOpenedHandler handler) {
        return addHandler(handler, SideNavOpenedEvent.TYPE);
    }

    @Override
    public HandlerRegistration addClosingHandler(SideNavClosingEvent.SideNavClosingHandler handler) {
        return addHandler(handler, SideNavClosingEvent.TYPE);
    }

    @Override
    public HandlerRegistration addClosedHandler(SideNavClosedEvent.SideNavClosedHandler handler) {
        return addHandler(handler, SideNavClosedEvent.TYPE);
    }

    protected StyleMixin<MaterialSideNav> getTypeMixin() {
        if (typeMixin == null) {
            typeMixin = new StyleMixin(this);
        }
        return typeMixin;
    }

    protected OverlayStyleMixin<AbstractSideNav> getOverlayStyleMixin() {
        if (overlayStyleMixin == null) {
            overlayStyleMixin = new OverlayStyleMixin<>(this);
        }
        return overlayStyleMixin;
    }

    protected DensityMixin<AbstractSideNav> getDensityMixin() {
        if (densityMixin == null) {
            densityMixin = new DensityMixin<>(this);
        }
        return densityMixin;
    }
}
