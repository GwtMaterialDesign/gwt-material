package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.helper.DOMHelper;
import gwt.material.design.client.base.helper.StyleHelper;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.Edge;
import gwt.material.design.client.constants.SideNavType;
import gwt.material.design.client.events.ClearActiveEvent;
import gwt.material.design.client.events.ClearActiveEvent.ClearActiveHandler;
import gwt.material.design.client.events.ObservedEvent;
import gwt.material.design.client.events.SideNavHiddenEvent;
import gwt.material.design.client.events.SideNavHiddenEvent.SideNavHiddenHandler;
import gwt.material.design.client.events.SideNavShownEvent;
import gwt.material.design.client.events.SideNavShownEvent.SideNavShownHandler;
import gwt.material.design.client.ui.html.ListItem;

//@formatter:off

/**
 * SideNav is a material component that gives you a lists of menus and other navigation components.
 *
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialSideNav ui:field="sideNav" width="280" m:id="mysidebar"  type="OPEN" closeOnClick="false">
 *     <m:MaterialLink href="#about" iconPosition="LEFT" iconType="OUTLINE" text="About" textColor="blue"  />
 *     <m:MaterialLink href="#gettingStarted" iconPosition="LEFT" iconType="DOWNLOAD" text="Getting Started" textColor="blue"  >
 * </m:MaterialSideNav>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#sidenav">Material SideNav</a>
 */
//@formatter:on
public class MaterialSideNav extends MaterialWidget implements HasType<SideNavType>, HasSelectables {

    private int width = 240;
    private Edge edge = Edge.LEFT;
    private boolean closeOnClick = false;
    private Element activator;
    private HandlerRegistration observedHandler;

    private StyleAttributeObserver observer = new StyleAttributeObserver(this, "left");

    private final CssTypeMixin<SideNavType, MaterialSideNav> typeMixin = new CssTypeMixin<>(this);
    private final ToggleStyleMixin<MaterialSideNav> fixedMixin = new ToggleStyleMixin<>(this, "fixed");

    /**
     * Container for App Toolbar and App Sidebar , contains Material Links,
     * Icons or any other material components.
     */
    public MaterialSideNav() {
        super(Document.get().createULElement());
        setStyleName("side-nav");
    }

    /**
     *  Creates a list and adds the given widgets.
     */
    public MaterialSideNav(final Widget... widgets){
        this();
        for (final Widget w : widgets) {
            add(w);
        }
    }

    @UiConstructor
    public MaterialSideNav(SideNavType type){
        this();
        setId("nav-mobile");
        setType(type);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        // Initialize the side nav
        initialize();
    }

    /**
     * This handler will be triggered when the side nav is shown.
     */
    public HandlerRegistration addShownHandler(SideNavShownHandler handler) {
        return addHandler(handler, SideNavShownEvent.TYPE);
    }

    /**
     * This handler will be triggered when the side nav is hidden.
     */
    public HandlerRegistration addHiddenHandler(SideNavHiddenHandler handler) {
        return addHandler(handler, SideNavHiddenEvent.TYPE);
    }

    @Override
    public void add(Widget child) {
        if(child instanceof MaterialImage) {
            child.getElement().getStyle().setProperty("border", "1px solid #e9e9e9");
            child.getElement().getStyle().setProperty("textAlign", "center");
        }

        boolean collapsible = child instanceof MaterialCollapsible;
        if(collapsible) {
            // Since the collapsible is separ
            ((MaterialCollapsible)child).addClearActiveHandler(new ClearActiveHandler() {
                @Override
                public void onClearActive(ClearActiveEvent event) {
                    clearActive();
                }
            });
        }

        if(!(child instanceof ListItem)) {
            // Direct list item not collapsible
            final ListItem listItem = new ListItem();
            if(child instanceof MaterialCollapsible) {
                listItem.getElement().getStyle().setBackgroundColor("transparent");
            }
            if(child instanceof HasWaves) {
                listItem.setWaves(((HasWaves) child).getWaves());
                ((HasWaves) child).setWaves(null);
            }
            listItem.add(child);

            child = listItem;
        }

        // Collapsible's should not be selectable
        final Widget finalChild = child;
        if(!collapsible) {
            // Active click handler
            finalChild.addDomHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    clearActive();
                    finalChild.addStyleName("active");
                }
            }, ClickEvent.getType());
        }
        child.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        super.add(child);
    }

    @Override
    public void setWidth(String width) {
        setWidth(Integer.parseInt(width));
    }

    /**
     * Set the menu's width in pixels.
     */
    public void setWidth(int width){
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
    public void setCloseOnClick(boolean closeOnClick){
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

    private void processType(SideNavType type) {
        if(activator != null && type != null) {
            switch (type) {
                case MINI:
                    setWidth(64);
                    break;
                case CARD:
                case FLOAT:
                    activator.addClassName("navmenu-permanent");
                    Timer t = new Timer() {
                        @Override
                        public void run() {
                            if(isSmall()){
                                show();
                            }
                        }
                    };
                    t.schedule(500);
                    break;
                case CLOSE:
                    applyCloseType(activator, width);
                    break;
            }
        }
    }

    private native boolean isSmall() /*-{
        var mq = $wnd.window.matchMedia('all and (max-width: 894px)');
        if(!mq.matches) {
            return true;
        }
        return false;
    }-*/;

    /**
     * Push the header, footer, and main to the right part when Close type is applied.
     * @param activator
     * @param width
     */
    private native void applyCloseType(Element activator, double width) /*-{
        var toggle;
        var _width;
        var _duration;

        $wnd.jQuery(activator).click(function (){

            var mq = $wnd.window.matchMedia('all and (max-width: 894px)');
            if(!mq.matches) {
                if(toggle){
                    _width = 0;
                    toggle = false;
                    _duration = 200;
                }else{
                    _width = width;
                    toggle = true;
                    _duration = 300;
                }
            }
            applyTransition($wnd.jQuery('header'), _width);
            applyTransition($wnd.jQuery('main'), _width);
            applyTransition($wnd.jQuery('footer'), _width);
        });
        function applyTransition(elem, _width){
            $wnd.jQuery(elem).css('transition', _duration + 'ms');
            $wnd.jQuery(elem).css('-moz-transition', _duration + 'ms');
            $wnd.jQuery(elem).css('-webkit-transition', _duration + 'ms');
            $wnd.jQuery(elem).css('margin-left', _width);
        }
    }-*/;

    @Override
    public void clearActive() {
        clearActive(this);

        ClearActiveEvent.fire(this);
    }

    private void clearActive(HasWidgets widget) {
        for(Widget child : widget) {
            Element element = child.getElement();
            if(StyleHelper.containsStyle(element.getClassName(), "active")) {
                element.removeClassName("active");
            }

            if(child instanceof HasWidgets) {
                clearActive((HasWidgets)child);
            }
        }
    }

    /**
     * reinitialize the side nav configurations when changing
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
        if(activator == null) {
            activator = DOMHelper.getElementByAttribute("data-activates", getId());
            if (activator != null && activator.getClassName().contains("button-collapse")) {
                SideNavType type = getType();
                processType(type);

                if(observedHandler != null) {
                    observedHandler.removeHandler();
                }
                observedHandler = observer.addObservedHandler(new ObservedEvent.ObservedHandler() {
                    @Override
                    public void onObserved(ObservedEvent event) {
                        if(event.getValue().equals("0px")) {
                            SideNavShownEvent.fire(MaterialSideNav.this);
                        } else if(event.getValue().equals("-"+getWidth()+"px")) {
                            SideNavHiddenEvent.fire(MaterialSideNav.this);
                        }
                    }
                });
                observer.observe(getElement());

                initialize(activator, width, closeOnClick, edge.getCssName());

                if(!isFixed()) {
                    String style = activator.getAttribute("style");
                    activator.setAttribute("style", style + "; display: block !important");
                }
            } else if(strict) {
                throw new RuntimeException("Cannot find an activator for the MaterialSideNav, " +
                        "please ensure you have a MaterialNavBar with an activator setup to match " +
                        "this widgets id.");
            }
        }
    }

    private static native void initialize(Element e, int width, boolean closeOnClick, String edge)/*-{
        $wnd.jQuery(e).ready(function() {
            $wnd.jQuery(e).sideNav({
                menuWidth: width,
                edge: edge,
                closeOnClick: closeOnClick
            });
        });
    }-*/;

    /**
     * Hide the overlay menu.
     */
    public native void hideOverlay()/*-{
        $wnd.jQuery(document).ready(function(){
            $wnd.jQuery('#sidenav-overlay').remove();
        })
    }-*/;

    /**
     * Show the sidenav.
     */
    public native void show(Element e)/*-{
        $wnd.jQuery(document).ready(function() {
            $wnd.jQuery(e).sideNav('show');
        });
    }-*/;

    /**
     * Hide the sidenav.
     */
    public native void hide(Element e)/*-{
        $wnd.jQuery(document).ready(function() {
            $wnd.jQuery(e).sideNav('hide');
        });
    }-*/;

    /**
     * Show the sidenav using the activator element
     */
    public void show() {
        show(activator);
    }

    /**
     * Hide the sidenav using the activator element
     */
    public void hide() {
        hide(activator);
    }
}
