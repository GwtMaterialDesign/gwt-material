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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasWaves;
import gwt.material.design.client.base.helper.DOMHelper;
import gwt.material.design.client.constants.Alignment;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;

//@formatter:off

/**
 * You can add dropdown easily by specifying it's item
 * content and add a UiHandler on it to implement any event.
 *
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code
 *<m:MaterialDropDown>
 *   <m:MaterialLink text="First" />
 *   <m:MaterialLink text="Second" />
 *   <m:MaterialLink text="Third" />
 * </m:MaterialDropDown>
 * }
 * </pre>
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#dropdowns">Material DropDowns</a>
 */
//@formatter:on
public class MaterialDropDown extends UnorderedList {

    private String activator;
    private Element activatorElem;

    // Options
    private int inDuration = 300;
    private int outDuration = 225;
    private boolean constrainWidth = true;
    private boolean hover = false;
    private boolean belowOrigin = false;
    private int gutter = 0;
    private String alignment = Alignment.LEFT.getCssName();

    public MaterialDropDown() {
        setStyleName("dropdown-content");
        setId(DOM.createUniqueId());
    }

    /**
     * Material Dropdown - adds a list item selection when button, link, icon button pressed.
     * @param activator - data-activates attribute name of your dropdown activator.
     */
    @UiConstructor
    public MaterialDropDown(String activator) {
        this();
        this.activator = activator;

        getElement().setId(this.activator);
    }

    public MaterialDropDown(Element activatorElem) {
        this();
        activatorElem.setAttribute("data-activates", getId());
        this.activatorElem = activatorElem;
    }

    public MaterialDropDown(UIObject activator) {
        this(activator.getElement());
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        initialize();
    }

    /**
     * The duration of the transition enter in milliseconds. Default: 300
     */
    public void setInDuration(int durationMillis) {
        this.inDuration = durationMillis;
    }

    public int getInDuration() {
        return inDuration;
    }

    /**
     * The duration of the transition out in milliseconds. Default: 225
     */
    public void setOutDuration(int durationMillis) {
        this.outDuration = durationMillis;
    }

    public int getOutDuration() {
        return outDuration;
    }

    /**
     * If true, constrainWidth to the size of the dropdown activator. Default: true
     */
    public void setConstrainWidth(boolean constrainWidth) {
        this.constrainWidth = constrainWidth;
    }

    public boolean isConstrainWidth() {
        return constrainWidth;
    }

    /**
     * If true, the dropdown will open on hover. Default: false
     */
    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isHover() {
        return hover;
    }

    /**
     * This defines the spacing from the aligned edge. Default: 0
     */
    public void setGutter(int gutter) {
        this.gutter = gutter;
    }

    public int getGutter() {
        return gutter;
    }

    /**
     * If true, the dropdown will show below the activator. Default: false
     */
    public void setBelowOrigin(boolean belowOrigin) {
        this.belowOrigin = belowOrigin;
    }

    public boolean isBelowOrigin() {
        return belowOrigin;
    }

    /**
     * Defines the edge the menu is aligned to. Default: 'left'
     */
    public void setAlignment(Alignment alignment) {
        this.alignment = alignment.getCssName();
    }

    public Alignment getAlignment() {
        return Alignment.fromStyleName(alignment);
    }

    @Override
    public void add(Widget child) {
        if(child instanceof ListItem) {
            child.getElement().getStyle().setDisplay(Style.Display.BLOCK);
            add(child, (Element) getElement());
        } else {
            ListItem li = new ListItem(child);
            if(child instanceof HasWaves) {
                li.setWaves(((HasWaves) child).getWaves());
                ((HasWaves) child).setWaves(null);
            }
            li.getElement().getStyle().setDisplay(Style.Display.BLOCK);
            add(li, (Element) getElement());
        }
    }

    protected void initialize() {
        if(activatorElem == null) {
            activatorElem = DOMHelper.getElementByAttribute("data-activates", activator);
            if (activatorElem == null) {
                throw new IllegalStateException("There is no activator element with id: '" + activator
                        + "' in the DOM, cannot instantiate MaterialDropDown without a data-activates.");
            }
        }
        initialize(activatorElem);
    }

    /**
     * Must be called after changing any options.
     */
    public void reinitialize() {
        remove(activatorElem);
        initialize(activatorElem);
    }

    /**
     * Initialize the dropdown components.
     */
    private native void initialize(Element activator)/*-{
        var that = this;
        $wnd.jQuery(document).ready(function(){
            $wnd.jQuery(activator).dropdown({
                inDuration: that.@gwt.material.design.client.ui.MaterialDropDown::inDuration,
                outDuration: that.@gwt.material.design.client.ui.MaterialDropDown::outDuration,
                constrain_width: that.@gwt.material.design.client.ui.MaterialDropDown::constrainWidth, // Does not change width of dropdown to that of the activator
                hover: that.@gwt.material.design.client.ui.MaterialDropDown::hover, // Activate on hover
                gutter: that.@gwt.material.design.client.ui.MaterialDropDown::gutter, // Spacing from edge
                belowOrigin: that.@gwt.material.design.client.ui.MaterialDropDown::belowOrigin, // Displays dropdown below the button
                alignment: that.@gwt.material.design.client.ui.MaterialDropDown::alignment // Displays dropdown with edge aligned to the left of button
            });
        });
    }-*/;

    private native void remove(Element activator)/*-{
        $wnd.jQuery(activator).dropdown("remove");
    }-*/;
}
