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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasSelectables;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CollapsibleType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.events.ClearActiveEvent;
import gwt.material.design.client.events.ClearActiveEvent.ClearActiveHandler;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * Collapsible are accordion elements that expand when clicked on.
 * They allow you to hide content that is not immediately relevant
 * to the user.
 *
 * <h3>UiBinder Usage:</h3>
 *
 * <pre>
 *{@code
 * // Accordion
 * <m:MaterialCollapsible type="ACCORDION" grid="s12 m6 l8">
 *   <!-- ITEM 1 -->
 *   <m:MaterialCollapsibleItem>
 *     <m:MaterialCollapsibleHeader>
 *       <m:MaterialLink text="First" iconType="POLYMER" iconPosition="LEFT" textColor="black"/>
 *     </m:MaterialCollapsibleHeader>
 *     <m:MaterialCollapsibleBody>
 *       <m:MaterialLabel text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."/>
 *     </m:MaterialCollapsibleBody>
 *   </m:MaterialCollapsibleItem>
 * </m:MaterialCollapsible>
 *
 * // Expandable
 * <m:MaterialCollapsible type="EXPANDABLE" grid="s12 m6 l8">
 *   <!-- ITEM 1 -->
 *   <m:MaterialCollapsibleItem>
 *     <m:MaterialCollapsibleHeader>
 *       <m:MaterialLink text="First" iconType="POLYMER" iconPosition="LEFT" textColor="black"/>
 *     </m:MaterialCollapsibleHeader>
 *     <m:MaterialCollapsibleBody>
 *       <m:MaterialLabel text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."/>
 *     </m:MaterialCollapsibleBody>
 *   </m:MaterialCollapsibleItem>
 * </m:MaterialCollapsible>
 *
 * // Popout
 * <m:MaterialCollapsible type="POPOUT" grid="s12 m6 l8">
 *   <!-- ITEM 1 -->
 *     <m:MaterialCollapsibleItem>
 *     <m:MaterialCollapsibleHeader>
 *       <m:MaterialLink text="First" iconType="POLYMER" iconPosition="LEFT" textColor="black"/>
 *     </m:MaterialCollapsibleHeader>
 *     <m:MaterialCollapsibleBody>
 *       <m:MaterialLabel text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."/>
 *     </m:MaterialCollapsibleBody>
 *   </m:MaterialCollapsibleItem>
 * </m:MaterialCollapsible>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#!collapsible">Material Collapsibles</a>
 */
//@formatter:on
public class MaterialCollapsible extends MaterialWidget implements HasSelectables {

    protected interface HasCollapsibleParent {
        void setParent(MaterialCollapsible parent);
    }

    private boolean initialized;
    private boolean accordion = true;

    private int activeIndex = -1;
    private Widget activeWidget;

    /**
     * Creates an empty collapsible
     */
    public MaterialCollapsible() {
        super(Document.get().createULElement(), CssName.COLLAPSIBLE);

        // Items need to be added after the widget has loaded to avoid
        // premature configuration issues.
        enableFeature(Feature.ONLOAD_ADD_QUEUE, true);
    }

    /**
     *  Creates a list and adds the given widgets.
     */
    public MaterialCollapsible(final MaterialCollapsibleItem... widgets) {
        this();

        for(final MaterialCollapsibleItem item : widgets) {
            add(item);
        }
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        // Setup the expansion type
        getElement().setAttribute("data-collapsible", isAccordion() ? CssName.ACCORDION : CssName.EXPANDABLE);

        // Activate preset activation index
        if(activeIndex != -1 && activeWidget == null) {
            setActive(activeIndex);
        }

        // Initialize collapsible after all elements
        // are attached and marked as active, etc.
        collapsible(getElement(), accordion);
    }

    @Override
    public void add(final Widget child) {
        if(child instanceof MaterialCollapsibleItem) {
            ((MaterialCollapsibleItem) child).setParent(this);
        }
        super.add(child);
    }

    @Override
    public boolean remove(Widget w) {
        if(w instanceof MaterialCollapsibleItem) {
            ((MaterialCollapsibleItem) w).setParent(null);
        }
        w.removeStyleName(CssName.ACTIVE);

        return super.remove(w);
    }

    /**
     * Initialize the collapsible material component.
     */
    protected void collapsible() {
        collapsible(getElement(), isAccordion());
    }

    protected void collapsible(final Element e, boolean accordion) {
        $(e).collapsible(accordion);
        initialized = true;
    }

    public void setType(CollapsibleType type) {
        switch (type) {
            case POPOUT:
                addStyleName(type.getCssName());
                break;
            default:
                break;
        }
    }

    /**
     * Configure if you want this collapsible container to
     * accordion its child elements or use expandable.
     */
    public void setAccordion(boolean accordion) {
        this.accordion = accordion;

        if(initialized) {
            // Since we have attached already reinitialize collapsible.
            collapsible();
        }
    }

    /**
     * Is the collapsible an 'accordion' type.
     */
    public boolean isAccordion() {
        return accordion;
    }

    /**
     * Providing the one-based index of the
     * {@link MaterialCollapsibleItem} to mark as active.
     */
    public void setActive(int index) {
        clearActive();
        setActive(index, true);
    }

    /**
     * Providing the one-based index of the
     * {@link MaterialCollapsibleItem} to mark as active.
     */
    public void setActive(int index, boolean active) {
        activeIndex = index;
        if(isAttached()) {
            if(index <= getWidgetCount()) {
                activeWidget = getWidget(index - 1);
                if (activeWidget != null && activeWidget instanceof MaterialCollapsibleItem) {
                    ((MaterialCollapsibleItem) activeWidget).setActive(active);
                    if(initialized) {
                        collapsible();
                    }
                }
            }
        }
    }

    /**
     * Open the given collapsible item.
     * @param index the one-based collapsible item index.
     */
    public void open(int index) {
        setActive(index, true);
    }

    /**
     * Close the given collapsible item.
     * @param index the one-based collapsible item index.
     */
    public void close(int index) {
        setActive(index, false);
    }

    /**
     * Close all the collapsible items.
     */
    public void closeAll() {
        clearActive();
        if(initialized) {
            collapsible();
        }
    }

    public HandlerRegistration addClearActiveHandler(final ClearActiveHandler handler) {
        return addHandler(handler, ClearActiveEvent.TYPE);
    }

    @Override
    public void clearActive() {
        clearActiveClass(this);
        ClearActiveEvent.fire(this);
    }

    @Override
    public void setEnabled(boolean enabled) {
        getEnabledMixin().setEnabled(this, enabled);
    }
}
