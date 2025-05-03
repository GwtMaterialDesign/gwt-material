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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.CollapsibleType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.events.ClearActiveEvent;
import gwt.material.design.client.events.ClearActiveEvent.ClearActiveHandler;
import gwt.material.design.client.events.CollapseEvent;
import gwt.material.design.client.events.ExpandEvent;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * Collapsible are accordion elements that expand when clicked on.
 * They allow you to hide content that is not immediately relevant
 * to the user.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code
 * // Accordion
 * <m:MaterialCollapsible accordion="true" grid="s12 m6 l8">
 *   <!-- ITEM 1 -->
 *   <m:MaterialCollapsibleItem>
 *     <m:MaterialCollapsibleHeader>
 *       <m:MaterialLink text="First" iconType="POLYMER" iconPosition="LEFT" textColor="BLACK"/>
 *     </m:MaterialCollapsibleHeader>
 *     <m:MaterialCollapsibleBody>
 *       <m:MaterialLabel text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."/>
 *     </m:MaterialCollapsibleBody>
 *   </m:MaterialCollapsibleItem>
 * </m:MaterialCollapsible>
 *
 * // Expandable
 * <m:MaterialCollapsible accordion="false" grid="s12 m6 l8">
 *   <!-- ITEM 1 -->
 *   <m:MaterialCollapsibleItem>
 *     <m:MaterialCollapsibleHeader>
 *       <m:MaterialLink text="First" iconType="POLYMER" iconPosition="LEFT" textColor="BLACK"/>
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
 *       <m:MaterialLink text="First" iconType="POLYMER" iconPosition="LEFT" textColor="BLACK"/>
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
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#collapsible">Material Collapsibles</a>
 * @see <a href="https://material.io/guidelines/components/expansion-panels.html#expansion-panels-behavior">Material Design Specification</a>
 */
//@formatter:on
public class MaterialCollapsible extends MaterialWidget
        implements JsLoader, HasType<CollapsibleType>, HasActiveParent, HasNoSideNavSelection, HasCollapsibleHandlers<MaterialCollapsibleItem> {

    protected interface HasCollapsibleParent {
        void setParent(MaterialCollapsible parent);
    }

    private boolean accordion = true;
    private int activeIndex = -1;
    private Widget activeWidget;

    private CssTypeMixin<CollapsibleType, MaterialCollapsible> typeMixin;

    public MaterialCollapsible() {
        super(Document.get().createULElement(), CssName.COLLAPSIBLE);

        // Items need to be added after the widget has loaded to avoid
        // premature configuration issues.
        enableFeature(Feature.ONLOAD_ADD_QUEUE, true);
    }

    public MaterialCollapsible(final MaterialCollapsibleItem... widgets) {
        this();

        for (final MaterialCollapsibleItem item : widgets) {
            add(item);
        }
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        if (activeIndex != -1 && activeWidget == null) {
            setActive(activeIndex);
        }

        load();
    }

    @Override
    public void load() {
        collapsible(getElement());
    }

    @Override
    public void unload() {
    }

    @Override
    public void reload() {
        unload();
        load();
    }

    @Override
    public void add(final Widget child) {
        if (child instanceof MaterialCollapsibleItem) {
            ((MaterialCollapsibleItem) child).setParent(this);
        }
        super.add(child);
    }

    @Override
    public boolean remove(Widget w) {
        if (w instanceof MaterialCollapsibleItem) {
            ((MaterialCollapsibleItem) w).setParent(null);
        }
        w.removeStyleName(CssName.ACTIVE);

        return super.remove(w);
    }

    @Override
    public void clearActive() {
        clearActiveClass(this);
        ClearActiveEvent.fire(this);
    }

    @Override
    protected void clearActiveClass(HasWidgets widget) {
        super.clearActiveClass(widget);

        for (Widget child : widget) {
            if (child instanceof MaterialCollapsibleBody) {
                ((MaterialCollapsibleBody) child).setDisplay(Display.NONE);
            }
        }
    }

    /**
     * Open the given collapsible item.
     */
    public void open(int index) {
        setActive(index);
    }

    /**
     * Close the given collapsible item.
     */
    public void close(int index) {
        setActive(index, false);
    }

    /**
     * Close all the collapsible items.
     */
    public void closeAll() {
        clearActive();
        reload();
    }

    @Override
    public void setEnabled(boolean enabled) {
        getEnabledMixin().setEnabled(this, enabled);
    }

    @Override
    public void setType(CollapsibleType type) {
        getTypeMixin().setType(type);
    }

    @Override
    public CollapsibleType getType() {
        return getTypeMixin().getType();
    }

    protected void collapsible(final Element e) {
        $(e).collapsible(isAccordion());
    }

    /**
     * Configure if you want this collapsible container to
     * accordion its child elements or use expandable.
     */
    public void setAccordion(boolean accordion) {
        getElement().setAttribute("data-collapsible", accordion ? CssName.ACCORDION : CssName.EXPANDABLE);
        reload();
    }

    /**
     * Is the collapsible an 'accordion' type.
     */
    public boolean isAccordion() {
        return getElement().getAttribute("data-collapsible").equals(CssName.ACCORDION);
    }

    @Override
    public void setActive(int index) {
        if (isAccordion()) {
            clearActive();
        }
        setActive(index, true);
    }

    @Override
    public void setActive(int index, boolean active) {
        activeIndex = index;
        if (isAttached()) {
            if (index <= getWidgetCount()) {
                if (index > 0) {
                    activeWidget = getWidget(index - 1);
                    if (activeWidget != null && activeWidget instanceof MaterialCollapsibleItem) {
                        ((MaterialCollapsibleItem) activeWidget).setActive(active);
                        reload();
                    }
                } else {
                    GWT.log("The active index must be a one-base index to mark as active.", new IndexOutOfBoundsException());
                }
            }
        }
    }

    @Override
    public Widget getActive() {
        try {
            return activeWidget;
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    public HandlerRegistration addClearActiveHandler(final ClearActiveHandler handler) {
        return addHandler(handler, ClearActiveEvent.TYPE);
    }

    @Override
    public HandlerRegistration addCollapseHandler(CollapseEvent.CollapseHandler<MaterialCollapsibleItem> handler) {
        return addHandler(handler, CollapseEvent.getType());
    }

    @Override
    public HandlerRegistration addExpandHandler(ExpandEvent.ExpandHandler<MaterialCollapsibleItem> handler) {
        return addHandler(handler, ExpandEvent.getType());
    }


    protected CssTypeMixin<CollapsibleType, MaterialCollapsible> getTypeMixin() {
        if (typeMixin == null) {
            typeMixin = new CssTypeMixin<>(this);
        }
        return typeMixin;
    }
}
