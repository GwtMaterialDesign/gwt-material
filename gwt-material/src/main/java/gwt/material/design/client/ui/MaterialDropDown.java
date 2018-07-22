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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.helper.DOMHelper;
import gwt.material.design.client.constants.Alignment;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.js.JsDropdownOptions;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;

import java.util.ArrayList;
import java.util.List;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * You can add dropdown easily by specifying it's item
 * content and add a UiHandler on it to implement any event.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialDropDown>
 *   <m:MaterialLink text="First" />
 *   <m:MaterialLink text="Second" />
 *   <m:MaterialLink text="Third" />
 * </m:MaterialDropDown>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#dropdown">Material DropDown</a>
 * @see <a href="https://material.io/guidelines/components/menus.html#">Material Design Specification</a>
 */
//@formatter:on
public class MaterialDropDown extends UnorderedList implements JsLoader, HasSelectionHandlers<Widget>, HasInOutDurationTransition {

    private String activator;
    private Element activatorElement;
    private List<HandlerRegistration> handlers = new ArrayList<>();
    private JsDropdownOptions options = new JsDropdownOptions();

    public MaterialDropDown() {
        setInitialClasses(CssName.DROPDOWN_CONTENT);
        setId(DOM.createUniqueId());
    }

    /**
     * Add a list item selection when button, link, icon button pressed.
     *
     * @param activator data-activates attribute name of your dropdown activator.
     */
    @UiConstructor
    public MaterialDropDown(String activator) {
        this();
        this.activator = activator;
        getElement().setId(this.activator);
    }

    public MaterialDropDown(Element activatorElement) {
        this();
        activatorElement.setAttribute("data-activates", getId());
        this.activatorElement = activatorElement;
    }

    public MaterialDropDown(UIObject activator) {
        this(activator.getElement());
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        load();
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        unload();
    }

    @Override
    public void load() {
        Widget parent = getParent();
        if (parent instanceof HasActivates) {
            String uid = DOM.createUniqueId();
            ((HasActivates) parent).setActivates(uid);
            setId(uid);
            activatorElement = parent.getElement();
        } else if (activatorElement == null) {
            activatorElement = DOMHelper.getElementByAttribute("data-activates", activator);
        }

        $(activatorElement).dropdown(options);

    }

    @Override
    public void unload() {
        for (HandlerRegistration handler : handlers) {
            handler.removeHandler();
        }

        // Hook for materialize bug on dropdown for not having closed once detach
        if (getElement() != null && isAttached()) {
            getElement().getStyle().setDisplay(Style.Display.NONE);
        }
        $(activatorElement).dropdown("remove");
    }

    @Override
    public void reload() {
        unload();
        load();
    }

    @Override
    public void add(final Widget child) {
        String tagName = child.getElement().getTagName();
        if (child instanceof ListItem || tagName.toLowerCase().startsWith("li")) {
            child.getElement().getStyle().setDisplay(Style.Display.BLOCK);
            add(child, (Element) getElement());
        } else {
            ListItem li = new ListItem(child);
            // Checks if there are sub dropdown components
            if (child instanceof MaterialLink) {
                MaterialLink link = (MaterialLink) child;
                handlers.add(link.addClickHandler(event -> SelectionEvent.fire(MaterialDropDown.this, child)));
                for (int i = 0; i < link.getWidgetCount(); i++) {
                    if (link.getWidget(i) instanceof MaterialDropDown) {
                        registerHandler(link.addClickHandler(DomEvent::stopPropagation));
                        link.stopTouchStartEvent();
                    }
                }
            }

            if (child instanceof HasWaves) {
                li.setWaves(((HasWaves) child).getWaves());
                ((HasWaves) child).setWaves(null);
            }
            li.getElement().getStyle().setDisplay(Style.Display.BLOCK);
            add(li, (Element) getElement());
        }
    }

    @Override
    public void setInDuration(int durationMillis) {
        options.inDuration = durationMillis;
    }

    @Override
    public int getInDuration() {
        return options.inDuration;
    }

    @Override
    public void setOutDuration(int durationMillis) {
        options.outDuration = durationMillis;
    }

    @Override
    public int getOutDuration() {
        return options.outDuration;
    }

    /**
     * If true, constrainWidth to the size of the dropdown activator. Default: true
     */
    public void setConstrainWidth(boolean constrainWidth) {
        options.constrain_width = constrainWidth;
    }

    public boolean isConstrainWidth() {
        return options.constrain_width;
    }

    /**
     * If true, the dropdown will open on hover. Default: false
     */
    public void setHover(boolean hover) {
        options.hover = hover;
    }

    public boolean isHover() {
        return options.hover;
    }

    /**
     * This defines the spacing from the aligned edge. Default: 0
     */
    public void setGutter(int gutter) {
        options.gutter = gutter;
    }

    public int getGutter() {
        return options.gutter;
    }

    /**
     * If true, the dropdown will show below the activator. Default: false
     */
    public void setBelowOrigin(boolean belowOrigin) {
        options.belowOrigin = belowOrigin;
    }

    public boolean isBelowOrigin() {
        return options.belowOrigin;
    }

    /**
     * Defines the edge the menu is aligned to. Default: 'left'
     */
    public void setAlignment(Alignment alignment) {
        options.alignment = alignment.getCssName();
    }

    public Alignment getAlignment() {
        return Alignment.fromStyleName(options.alignment);
    }

    /**
     * Get the unique activator set by material widget e.g links, icons, buttons to trigger the dropdown.
     */
    public String getActivator() {
        return activator;
    }

    /**
     * Set the unique activator of each dropdown component and it must be unique
     */
    public void setActivator(String activator) {
        this.activator = activator;
        setId(activator);
    }

    public List<Widget> getItems() {
        return getChildrenList();
    }

    public Element getActivatorElement() {
        return activatorElement;
    }

    @Override
    public HandlerRegistration addSelectionHandler(final SelectionHandler<Widget> handler) {
        return addHandler((SelectionHandler<Widget>) event -> {
            Widget widget = event.getSelectedItem();
            if (widget instanceof HasEnabled && ((HasEnabled) widget).isEnabled() && isEnabled()) {
                handler.onSelection(event);
            }
        }, SelectionEvent.getType());
    }
}
