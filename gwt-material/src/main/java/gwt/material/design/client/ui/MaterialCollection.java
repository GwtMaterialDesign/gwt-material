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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasActiveParent;
import gwt.material.design.client.base.HasClearActiveHandler;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.helper.UiHelper;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.HeadingSize;
import gwt.material.design.client.events.ClearActiveEvent;
import gwt.material.design.client.ui.html.Heading;
import gwt.material.design.client.ui.html.ListItem;

//@formatter:off

/**
 * Collections allow you to group list objects together.
 * <p>
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * Simple
 * <m:MaterialCollection >
 * <m:MaterialCollectionItem><m:MaterialLabel text="Collecton 1"/></m:MaterialCollectionItem>
 * <m:MaterialCollectionItem><m:MaterialLabel text="Collecton 2"/></m:MaterialCollectionItem>
 * <m:MaterialCollectionItem><m:MaterialLabel text="Collecton 3"/></m:MaterialCollectionItem>
 * <m:MaterialCollectionItem><m:MaterialLabel text="Collecton 4"/></m:MaterialCollectionItem>
 * </m:MaterialCollection>
 *
 * Links
 * <m:MaterialCollection >
 * <m:MaterialCollectionItem><m:MaterialLink text="Collecton 1"/></m:MaterialCollectionItem>
 * <m:MaterialCollectionItem><m:MaterialLink text="Collecton 2"/></m:MaterialCollectionItem>
 * <m:MaterialCollectionItem><m:MaterialLink text="Collecton 3"/></m:MaterialCollectionItem>
 * <m:MaterialCollectionItem><m:MaterialLink text="Collecton 4"/></m:MaterialCollectionItem>
 * </m:MaterialCollection>
 *
 * Header
 * <m:MaterialCollection header="Header Title">
 * <m:MaterialCollectionItem><m:MaterialLink text="Collecton 1"/></m:MaterialCollectionItem>
 * <m:MaterialCollectionItem><m:MaterialLink text="Collecton 2"/></m:MaterialCollectionItem>
 * <m:MaterialCollectionItem><m:MaterialLink text="Collecton 3"/></m:MaterialCollectionItem>
 * <m:MaterialCollectionItem><m:MaterialLink text="Collecton 4"/></m:MaterialCollectionItem>
 * </m:MaterialCollection>
 *
 * Secondary Content
 * <m:MaterialCollection header="Header Title">
 * <m:MaterialCollectionItem>
 * <m:MaterialLink text="Collecton 1"/>
 * <m:MaterialSecondaryContent><m:MaterialIcon iconType="POLYMER" iconPosition="RIGHT" waves="DEFAULT"/></m:MaterialSecondaryContent>
 * </m:MaterialCollectionItem>
 * <m:MaterialCollectionItem>
 * <m:MaterialLink text="Collecton 2"/>
 * <m:MaterialSecondaryContent><m:MaterialIcon iconType="POLYMER" iconPosition="RIGHT" waves="DEFAULT"/></m:MaterialSecondaryContent>
 * </m:MaterialCollectionItem>
 * <m:MaterialCollectionItem>
 * <m:MaterialLink text="Collecton 3"/>
 * <m:MaterialSecondaryContent><m:MaterialIcon iconType="POLYMER" iconPosition="RIGHT" waves="DEFAULT"/></m:MaterialSecondaryContent>
 * </m:MaterialCollectionItem>
 * <m:MaterialCollectionItem>
 * <m:MaterialLink text="Collecton 4"/>
 * <m:MaterialSecondaryContent><m:MaterialIcon iconType="POLYMER" iconPosition="RIGHT" waves="DEFAULT"/></m:MaterialSecondaryContent>
 * </m:MaterialCollectionItem>
 * </m:MaterialCollection>
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#collections">Material Collections</a>
 * @see <a href="https://material.io/guidelines/components/lists-controls.html#lists-controls-types-of-menu-controls">Material Design Specification</a>
 */
//@formatter:on
public class MaterialCollection extends MaterialWidget
        implements HasActiveParent, HasClearActiveHandler, HasSelectionHandlers<MaterialCollectionItem> {

    private int index;
    private boolean selectable;
    private Heading headerLabel = new Heading(HeadingSize.H4);
    private HandlerRegistration selectHandler;

    /**
     * Creates an empty collection component.
     */
    public MaterialCollection() {
        super(Document.get().createULElement(), CssName.COLLECTION);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        if (selectable) {
            selectHandler = registerHandler(addSelectionHandler(selectionEvent -> {
                clearActive();
                selectionEvent.getSelectedItem().setActive(true);
            }));

            for (Widget child : getChildren()) {
                if (child instanceof MaterialCollectionItem) {
                    child.getElement().getStyle().setCursor(Style.Cursor.POINTER);
                    ((MaterialCollectionItem) child).addClickHandler(clickEvent -> SelectionEvent.fire(MaterialCollection.this, (MaterialCollectionItem) child));
                }
            }
        } else {
            removeHandler(selectHandler);
        }
    }

    @Override
    public void clearActive() {
        clearActiveClass(this);
    }

    /**
     * Sets the header of the collection component.
     */
    public void setHeader(String header) {
        headerLabel.getElement().setInnerSafeHtml(SafeHtmlUtils.fromString(header));
        addStyleName(CssName.WITH_HEADER);
        ListItem item = new ListItem(headerLabel);
        UiHelper.addMousePressedHandlers(item);
        item.setStyleName(CssName.COLLECTION_HEADER);
        insert(item, 0);
    }

    @Override
    public void setActive(int index) {
        setActive(index, true);
    }

    @Override
    public void setActive(int index, boolean value) {
        this.index = index;
        Widget activeWidget = getActive();
        if (activeWidget != null) {
            if (index <= getWidgetCount()) {
                if (index != 0) {
                    clearActiveClass(this);
                    if (activeWidget instanceof MaterialCollectionItem) {
                        ((MaterialCollectionItem) activeWidget).setActive(value);
                    }
                } else {
                    throw new IllegalArgumentException("The active index must be a one-base index to mark as active.");
                }
            }
        }
    }

    @Override
    public Widget getActive() {
        try {
            return getWidget(index - 1);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public Heading getHeaderLabel() {
        return headerLabel;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    @Override
    public HandlerRegistration addClearActiveHandler(final ClearActiveEvent.ClearActiveHandler handler) {
        return addHandler(handler, ClearActiveEvent.TYPE);
    }

    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<MaterialCollectionItem> selectionHandler) {
        return addHandler(selectionHandler, SelectionEvent.getType());
    }
}
