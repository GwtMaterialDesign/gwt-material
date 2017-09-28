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
import gwt.material.design.client.constants.Axis;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.FABType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.ListItem;

/**
 * Test case for FAB and FABList
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialFABTest extends MaterialWidgetTest<MaterialFAB> {

    @Override
    protected MaterialFAB createWidget() {
        return new MaterialFAB();
    }

    public void testOpenAndCloseFAB() {
        // given
        MaterialFAB fab = getWidget();

        // when / then
        checkOpenHandler(fab);
        checkCloseHandler(fab);
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

        MaterialButton btnLarge = new MaterialButton();
        btnLarge.setType(ButtonType.FLOATING);
        MaterialFABList fabList = new MaterialFABList();
        for (int i = 1; i <= 5; i++) {
            MaterialButton button = new MaterialButton();
            button.setType(ButtonType.FLOATING);
            fabList.add(button);
        }

        // when / then
        fab.add(btnLarge);
        fab.add(fabList);

        assertEquals(2, fab.getChildren().size());
        assertTrue(fab.getWidget(0) instanceof MaterialButton);
        assertEquals(btnLarge, fab.getWidget(0));
        assertTrue(fab.getWidget(1) instanceof MaterialFABList);
        assertEquals(fabList, fab.getWidget(1));

        assertEquals(5, fabList.getChildren().size());
        for (Widget w : fabList.getChildren()) {
            assertTrue(w instanceof ListItem);
            ListItem item = (ListItem) w;
            assertNotNull(item.getWidget(0));
            assertTrue(item.getWidget(0) instanceof MaterialButton);
        }
    }
}