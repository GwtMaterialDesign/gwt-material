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

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.RootPanel;
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
        checkTabIndex(tab);
        checkDynamicTab(tab);
        row.add(tab);
    }

    public void checkDynamicTab(MaterialTab tab) {
        MaterialRow row = new MaterialRow();

        // This will dynamically add new Tab Item
        MaterialTabItem item = newTabItem(tab, row, 1);

        row.add(tab);
        RootPanel.get().add(row);

        boolean[] selectionEventFired = new boolean[]{false};
        tab.addSelectionHandler(selectionEvent -> selectionEventFired[0] = true);

        // This will trigger the selection event of the tab once tab item fired MouseDownEvent
        item.fireEvent(new GwtEvent<MouseDownHandler>() {
            @Override
            public Type<MouseDownHandler> getAssociatedType() {
                return MouseDownEvent.getType();
            }

            @Override
            protected void dispatch(MouseDownHandler eventHandler) {
                eventHandler.onMouseDown(null);
            }
        });

        // Expected : true
        assertTrue(selectionEventFired[0]);
    }

    public void checkTabIndex(MaterialTab tab) {
        // Expected default selected tab (0 index)
        assertEquals(tab.getTabIndex(), 0);

        tab.setTabIndex(1);
        // Expected index : 1
        assertEquals(tab.getTabIndex(), 1);

        tab.setTabIndex(2);
        // Expected index : 2
        assertEquals(tab.getTabIndex(), 2);
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
            newTabItem(tab, row, i);
        }

        assertEquals(tab.getChildren().size(), 5);
        for (Widget w : tab.getChildren()) {
            assertNotNull(w);
            assertTrue(w instanceof MaterialTabItem);
        }

        assertEquals(tab.getChildren().size(), 5);
    }

    protected MaterialTabItem newTabItem(MaterialTab tab, MaterialRow row, int index) {
        // Build Tab Items
        MaterialTabItem item = new MaterialTabItem();
        MaterialLink link = new MaterialLink("Item " + index);
        link.setHref("#item" + index);
        item.add(link);
        tab.add(item);
        assertTrue(item.getWidget(0) instanceof MaterialLink);
        /*assertEquals(tab.getWidget(index), item);*/
        // Build Panel
        MaterialPanel panel = new MaterialPanel();
        panel.setId("item" + index);
        row.add(panel);
        return item;
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
