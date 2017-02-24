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

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.TabType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Tabs
 *
 * @author kevzlou7979
 */
public class MaterialTabTest extends MaterialWidgetTest {

    public void init() {
        MaterialRow row = new MaterialRow();
        MaterialTab tab = new MaterialTab();
        checkWidget(tab);
        generateTabItems(tab, row);
        checkEvents(tab);
        checkTypes(tab);
        row.add(tab);
    }

    public void checkEvents(MaterialTab widget) {
        final boolean[] isSelectionEventFired = {false};
        widget.addSelectionHandler(selectionEvent -> isSelectionEventFired[0] = true);
        widget.fireEvent(new GwtEvent<SelectionHandler<?>>() {
            @Override
            public Type<SelectionHandler<?>> getAssociatedType() {
                return SelectionEvent.getType();
            }

            @Override
            protected void dispatch(SelectionHandler eventHandler) {
                eventHandler.onSelection(null);
            }
        });
        
        assertEquals(isSelectionEventFired[0], true);
    }

    public void generateTabItems(MaterialTab tab, MaterialRow row) {

        for (int i = 0; i < 5; i++) {
            // Build Tab Items
            MaterialTabItem item = new MaterialTabItem();
            MaterialLink link = new MaterialLink("Item " + i);
            link.setHref("#item" + i);
            item.add(link);
            tab.add(item);
            assertTrue(item.getWidget(0) instanceof MaterialLink);
            assertEquals(tab.getWidget(i), item);
            // Build Panel
            MaterialPanel panel = new MaterialPanel();
            panel.setId("item" + i);
            row.add(panel);
        }

        assertEquals(tab.getChildren().size(), 5);
        for (Widget w : tab.getChildren()) {
            assertNotNull(w);
            assertTrue(w instanceof MaterialTabItem);
        }

        assertEquals(tab.getChildren().size(), 5);
    }

    public <T extends MaterialTab> void checkTypes(T tab) {
        tab.setType(TabType.DEFAULT);
        assertEquals(tab.getType(), TabType.DEFAULT);
        assertTrue(tab.getElement().getClassName().contains(TabType.DEFAULT.getCssName()));
        tab.setType(TabType.ICON);
        assertEquals(tab.getType(), TabType.ICON);
        assertTrue(tab.getElement().hasClassName(TabType.ICON.getCssName()));
    }
}
