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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasActiveParent;
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
 */
//@formatter:on
public class MaterialCollection extends MaterialWidget implements HasActiveParent {

    private Heading span = new Heading(HeadingSize.H4);
    private int index;

    /**
     * Creates an empty collection component.
     */
    public MaterialCollection() {
        super(Document.get().createULElement(), CssName.COLLECTION);
    }

    /**
     * Sets the header of the collection component.
     */
    public void setHeader(String header) {
        span.getElement().setInnerSafeHtml(SafeHtmlUtils.fromString(header));
        addStyleName(CssName.WITH_HEADER);
        ListItem item = new ListItem(span);
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
                    GWT.log("The active index must be a one-base index to mark as active.", new RuntimeException());
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

    @Override
    public void clearActive() {
        clearActiveClass(this);
    }

    public HandlerRegistration addClearActiveHandler(final ClearActiveEvent.ClearActiveHandler handler) {
        return addHandler(handler, ClearActiveEvent.TYPE);
    }
}
