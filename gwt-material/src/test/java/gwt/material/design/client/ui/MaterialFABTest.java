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

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.Axis;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.FABType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.ListItem;
import org.junit.Test;

/**
 * Test case for FAB and FABList
 *
 * @author kevzlou7979
 */
public class MaterialFABTest extends MaterialWidgetTest {

    @Test
    public void testFAB() {
        MaterialFAB fab = new MaterialFAB();
        checkStructure(fab);
        checkTypes(fab);
        checkAxis(fab);
    }

    public <T extends MaterialFAB> void checkTypes(T widget) {
        widget.setType(FABType.CLICK_ONLY);
        assertEquals(widget.getType(), FABType.CLICK_ONLY);
        assertTrue(widget.getElement().hasClassName(FABType.CLICK_ONLY.getCssName()));
        widget.setType(FABType.HOVER);
        assertEquals(widget.getType(), FABType.HOVER);
        // Because Hover is empty by default
        assertTrue(widget.getType().getCssName().isEmpty());
    }

    public <T extends MaterialFAB> void checkAxis(T widget) {
        widget.setAxis(Axis.HORIZONTAL);
        assertEquals(widget.getAxis(), Axis.HORIZONTAL);
        assertTrue(widget.getElement().hasClassName(Axis.HORIZONTAL.getCssName()));
        widget.setAxis(Axis.VERTICAL);
        assertEquals(widget.getAxis(), Axis.VERTICAL);
        assertTrue(widget.getElement().hasClassName(Axis.VERTICAL.getCssName()));
    }

    public <T extends MaterialFAB> void checkStructure(T widget) {
        MaterialButton btnLarge = new MaterialButton();
        btnLarge.setType(ButtonType.FLOATING);
        MaterialFABList fabList = new MaterialFABList();
        for (int i = 1; i <= 5; i++) {
            MaterialButton button = new MaterialButton();
            button.setType(ButtonType.FLOATING);
            fabList.add(button);
        }
        widget.add(btnLarge);
        widget.add(fabList);

        assertEquals(widget.getChildren().size(), 2);
        assertTrue(widget.getWidget(0) instanceof MaterialButton);
        assertEquals(widget.getWidget(0), btnLarge);
        assertTrue(widget.getWidget(1) instanceof MaterialFABList);
        assertEquals(widget.getWidget(1), fabList);

        assertEquals(fabList.getChildren().size(), 5);
        for (Widget w : fabList.getChildren()) {
            assertTrue(w instanceof ListItem);
            ListItem item = (ListItem) w;
            assertNotNull(item.getWidget(0));
            assertTrue(item.getWidget(0) instanceof MaterialButton);
        }
    }
}