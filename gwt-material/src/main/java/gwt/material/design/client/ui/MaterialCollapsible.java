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
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.HasSelectables;
import gwt.material.design.client.base.helper.StyleHelper;
import gwt.material.design.client.constants.CollapsibleType;
import gwt.material.design.client.events.ClearActiveEvent;
import gwt.material.design.client.events.ClearActiveEvent.ClearActiveHandler;

//@formatter:off

/**
 * Collapsibles are accordion elements that expand when clicked on.
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
 * @see <a href="http://gwt-material-demo.herokuapp.com/#collapsibles">Material Collapsibles</a>
 */
//@formatter:on
public class MaterialCollapsible extends MaterialWidget implements HasSelectables {

    protected interface HasCollapsibleParent {
        void setParent(MaterialCollapsible parent);
    }

    /**
     * Creates an empty collapsible
     */
    public MaterialCollapsible() {
        super(Document.get().createULElement());
        setStyleName("collapsible");
    }

    /**
     *  Creates a list and adds the given widgets.
     */
    public MaterialCollapsible(final MaterialCollapsibleItem... widgets){
        this();
        for (final MaterialCollapsibleItem item : widgets) {
            add(item);
        }
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        onInitCollapsible(getElement());
    }

    @Override
    public void add(final Widget child) {
        if(child instanceof MaterialCollapsibleItem) {
            ((MaterialCollapsibleItem) child).setParent(this);
        }

        onInitCollapsible(getElement());

        super.add(child);
    }

    @Override
    public boolean remove(Widget w) {
        if(w instanceof MaterialCollapsibleItem) {
            ((MaterialCollapsibleItem) w).setParent(null);
        }
        w.removeStyleName("active");

        return super.remove(w);
    }

    /**
     * Initialize the collapsible material component.
     */
    protected native void onInitCollapsible(final Element e) /*-{
        $wnd.jQuery(document).ready(function(){
            $wnd.jQuery(e).collapsible();
        })
    }-*/;

    public void setType(CollapsibleType type) {
        switch (type) {
            case POPOUT:
                this.getElement().setAttribute("data-collapsible", "accordion");
                this.addStyleName(type.getCssName());
                break;
            default:
                getElement().setAttribute("data-collapsible", type.getCssName());
                break;
        }
    }

    public void setActive(int index) {
        clearActive();
        Widget activeWidget = getWidget(index);
        if(activeWidget != null) {
            activeWidget.addStyleName("active");
        }
    }

    public HandlerRegistration addClearActiveHandler(ClearActiveHandler handler) {
        return addHandler(handler, ClearActiveEvent.TYPE);
    }

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
}
