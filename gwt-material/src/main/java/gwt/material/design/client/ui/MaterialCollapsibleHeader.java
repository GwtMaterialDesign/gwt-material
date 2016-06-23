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
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;

//@formatter:off

/**
 * CollapsibleItem element to define the header
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#!collapsible">Material Collapsibles</a>
 */
//@formatter:on
public class MaterialCollapsibleHeader extends MaterialWidget implements HasAllMouseHandlers, HasClickHandlers {

    /**
     * Creates empty collapsible header.
     */
    public MaterialCollapsibleHeader() {
        super(Document.get().createDivElement(), "collapsible-header");
    }

    /**
     * Adds other components as header.
     */
    public MaterialCollapsibleHeader(final Widget... widgets) {
        this();
        for(Widget w : widgets) {
            add(w);
        }
    }

    @Override
    public void add(Widget child) {
        if(child instanceof UnorderedList) {
            for(Widget w : (UnorderedList) child) {
                if(w instanceof ListItem) {
                    w.getElement().getStyle().setDisplay(Style.Display.BLOCK);
                }
            }
        } else if(child instanceof ListItem) {
            child.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        }
        super.add(child);
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()) {
                handler.onClick(event);
            }
        }, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()) {
                handler.onMouseDown(event);
            }
        }, MouseDownEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()) {
                handler.onMouseMove(event);
            }
        }, MouseMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()) {
                handler.onMouseOut(event);
            }
        }, MouseOutEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()) {
                handler.onMouseOver(event);
            }
        }, MouseOverEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()) {
                handler.onMouseUp(event);
            }
        }, MouseUpEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(final MouseWheelHandler handler) {
        return addDomHandler(event -> {
            if(isEnabled()) {
                handler.onMouseWheel(event);
            }
        }, MouseWheelEvent.getType());
    }
}
