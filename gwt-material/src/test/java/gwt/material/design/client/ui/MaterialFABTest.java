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

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.ListItem;

/**
 * Test case for FAB and FABList
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialFABTest extends MaterialWidgetTest<MaterialFAB> {

    static final int FAB_ITEM_SIZE = 5;

    @Override
    protected MaterialFAB createWidget() {
        MaterialFAB fab = new MaterialFAB();
        MaterialButton btnLarge = new MaterialButton();
        btnLarge.setType(ButtonType.FLOATING);
        btnLarge.setSize(ButtonSize.LARGE);
        MaterialFABList fabList = new MaterialFABList();
        for (int i = 1; i <= FAB_ITEM_SIZE; i++) {
            MaterialButton button = new MaterialButton();
            button.setType(ButtonType.FLOATING);
            fabList.add(button);
        }
        fab.add(btnLarge);
        fab.add(fabList);
        return fab;
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.FIXED_ACTION_BTN);
    }

    public void testOpenAndCloseFAB() {
        // given
        MaterialFAB fab = getWidget();

        // when / then
        checkOpenHandler(fab);
        checkCloseHandler(fab);
    }

    public void testHoverableFAB() {
        // given
        final boolean[] opened = {false};
        MaterialFAB fab = getWidget();

        // when / then
        fab.setHoverable(true);
        fab.addOpenHandler(openEvent -> opened[0] = true);
        fab.addCloseHandler(closeEvent -> opened[0] = false);
        fireMouseOverEvent(fab);
        assertTrue(opened[0]);
        fireCloseHandler(fab);
        assertFalse(opened[0]);
    }

    public void testTypes() {
        // given
        MaterialFAB fab = getWidget();

        // when / then
        fab.setType(FABType.CLICK_ONLY);
        assertEquals(FABType.CLICK_ONLY, fab.getType());
        assertTrue(fab.getElement().hasClassName(FABType.CLICK_ONLY.getCssName()));
        fab.setType(FABType.HOVER);
        assertEquals(FABType.HOVER, fab.getType());
        // Because Hover is empty by default
        assertTrue(fab.getType().getCssName().isEmpty());
    }

    public void testAxis() {
        // given
        MaterialFAB fab = getWidget();

        // when / then
        fab.setAxis(Axis.HORIZONTAL);
        assertEquals(Axis.HORIZONTAL, fab.getAxis());
        assertTrue(fab.getElement().hasClassName(Axis.HORIZONTAL.getCssName()));
        fab.setAxis(Axis.VERTICAL);
        assertEquals(Axis.VERTICAL, fab.getAxis());
        assertTrue(fab.getElement().hasClassName(Axis.VERTICAL.getCssName()));
    }

    public void testStructure() {
        // given
        MaterialFAB fab = getWidget();
        MaterialButton btnLarge = (MaterialButton) fab.getWidget(0);
        MaterialFABList fabList = (MaterialFABList) fab.getWidget(1);

        // when / then
        assertEquals(ButtonSize.LARGE, btnLarge.getSize());
        assertEquals(ButtonType.FLOATING, btnLarge.getType());
        assertEquals(FAB_ITEM_SIZE, fabList.getWidgetCount());
    }

    public void testFABItems() {
        // given
        MaterialFAB fab = getWidget();

        MaterialFABList fabList = (MaterialFABList) fab.getWidget(1);
        for (Widget w : fabList.getChildren()) {
            // given
            assertTrue(w instanceof ListItem);
            ListItem item = (ListItem) w;

            // when / then
            assertNotNull(item.getWidget(0));
            assertTrue(item.getWidget(0) instanceof MaterialButton);
        }
    }
}