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
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.TabType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Tabs.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialTabTest extends MaterialWidgetTest<MaterialTab> {

    private MaterialRow row;

    @Override
    protected MaterialTab createWidget() {
        row = new MaterialRow();
        MaterialTab tab = new MaterialTab();
        row.add(tab);
        generateTabItems(tab, row);
        RootPanel.get().add(row);
        return tab;
    }

    @Override
    public boolean neverAttach() {
        // we will handle the attachment
        return true;
    }

    @Override
    protected void gwtTearDown() throws Exception {
        super.gwtTearDown();

        row = null;
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.TABS);
    }

    public void testDynamicTab() {
        // given
        MaterialTab tab = getWidget();

        // when / then
        // This will dynamically add new Tab Item
        MaterialTabItem item = newTabItem(tab, row, 1);

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

    public void testTabIndex() {
        // given
        MaterialTab tab = getWidget();

        // when / then
        // Expected default selected tab (0 index)
        assertEquals(0, tab.getTabIndex());

        tab.setTabIndex(1);
        // Expected index : 1
        assertEquals(1, tab.getTabIndex());

        tab.setTabIndex(2);
        // Expected index : 2
        assertEquals(2, tab.getTabIndex());
    }

    public void testSelection() {
        // given
        MaterialTab tab = getWidget();

        // when / then
        assertNotNull(tab.getWidget(0));
        checkSelectionHandler(tab, 0);
    }

    public void testTypes() {
        // given
        MaterialTab tab = getWidget();

        // when / then
        tab.setType(TabType.DEFAULT);
        assertEquals(TabType.DEFAULT, tab.getType());
        assertTrue(tab.getElement().getClassName().contains(TabType.DEFAULT.getCssName()));
        tab.setType(TabType.ICON);
        assertEquals(TabType.ICON, tab.getType());
        assertTrue(tab.getElement().hasClassName(TabType.ICON.getCssName()));
    }

    protected void generateTabItems(MaterialTab tab, MaterialRow row) {
        for (int i = 0; i < 5; i++) {
            newTabItem(tab, row, i);
        }

        assertEquals(5, tab.getChildren().size());
        for (Widget w : tab.getChildren()) {
            assertNotNull(w);
            assertTrue(w instanceof MaterialTabItem);
        }

        assertEquals(5, tab.getChildren().size());
    }

    protected MaterialTabItem newTabItem(MaterialTab tab, MaterialRow row, int index) {
        // Build Tab Items
        MaterialTabItem item = new MaterialTabItem();
        MaterialLink link = new MaterialLink("Item " + index);
        link.setHref("#item" + index);
        item.add(link);
        tab.add(item);
        assertTrue(item.getWidget(0) instanceof MaterialLink);
        /*assertEquals(item, tab.getWidget(index));*/
        // Build Panel
        MaterialPanel panel = new MaterialPanel();
        panel.setId("item" + index);
        row.add(panel);
        return item;
    }

    public void testTabItemSelect() {
        // given
        MaterialTab tab = getWidget(true);

        // when / then
        assertEquals(0, tab.getTabIndex());
        tab.selectTab("item1");
        assertEquals(1, tab.getTabIndex());
        tab.selectTab("item2");
        assertEquals(2, tab.getTabIndex());
    }
}
