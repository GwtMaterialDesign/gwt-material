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

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CollapsibleType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Collapsibles.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialCollapsibleTest extends MaterialWidgetTest<MaterialCollapsible> {

    @Override
    protected MaterialCollapsible createWidget() {
        return new MaterialCollapsible();
    }

    public void testStructure() {
        // given
        MaterialCollapsible collapsible = getWidget(false);

        // when / then
        MaterialCollapsibleItem item = new MaterialCollapsibleItem();

        MaterialCollapsibleHeader header = new MaterialCollapsibleHeader();
        item.add(header);

        MaterialCollapsibleBody body = new MaterialCollapsibleBody();
        item.add(body);

        assertEquals(2, item.getChildren().size());
        assertTrue(item.getWidget(0) instanceof MaterialCollapsibleHeader);
        assertTrue(item.getWidget(1) instanceof MaterialCollapsibleBody);
        collapsible.add(item);

        // isFeatureEnabled Default
        assertTrue(collapsible.isFeatureEnabled(MaterialWidget.Feature.ONLOAD_ADD_QUEUE));
        // Expected result -> 0 (Because feature is enabled)
        assertEquals(0, collapsible.getChildren().size());
        RootPanel.get().add(collapsible);
        assertEquals(1, collapsible.getChildren().size());
        // isFeatureEnabled -> false
        collapsible.enableFeature(MaterialWidget.Feature.ONLOAD_ADD_QUEUE, false);
        assertFalse(collapsible.isFeatureEnabled(MaterialWidget.Feature.ONLOAD_ADD_QUEUE));
        collapsible.clear();
        collapsible.add(item);
        assertEquals(1, collapsible.getChildren().size());

        // Check Multiple collapsible items
        collapsible.clear();
        generateCollapsibleItems(collapsible);
        assertEquals(5, collapsible.getChildren().size());
        collapsible.remove(0);
        assertEquals(4, collapsible.getChildren().size());
        collapsible.insert(item, 1);
        assertEquals(item, collapsible.getWidget(1));
        assertEquals(5, collapsible.getChildren().size());
        collapsible.clear();
        assertEquals(0, collapsible.getChildren().size());
    }

    public void testTypes() {
        // given
        MaterialCollapsible collapsible = getWidget();

        // when / then
        collapsible.enableFeature(MaterialWidget.Feature.ONLOAD_ADD_QUEUE, false);
        collapsible.setType(CollapsibleType.FLAT);
        assertEquals(CollapsibleType.FLAT, collapsible.getType());
        assertTrue(collapsible.getElement().hasClassName(CollapsibleType.FLAT.getCssName()));
        collapsible.setType(CollapsibleType.POPOUT);
        assertEquals(CollapsibleType.POPOUT, collapsible.getType());
        assertTrue(collapsible.getElement().hasClassName(CollapsibleType.POPOUT.getCssName()));
        collapsible.setAccordion(true);
        assertTrue(collapsible.isAccordion());
        collapsible.setAccordion(false);
        assertFalse(collapsible.isAccordion());
    }

    public void testPreSelection() {
        // given
        MaterialCollapsible collapsible = getWidget();

        // when / then
        collapsible.enableFeature(MaterialWidget.Feature.ONLOAD_ADD_QUEUE, false);
        generateCollapsibleItems(collapsible);
        for (Widget w : collapsible.getChildren()) {
            assertTrue(w instanceof MaterialCollapsibleItem);
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) w;
            assertNotNull(item.getBody());
            assertNotNull(item.getHeader());

            item.setActive(true);
            assertTrue(item.isActive());
            assertTrue(item.getElement().hasClassName(CssName.ACTIVE));
            assertTrue(item.getHeader().getElement().hasClassName(CssName.ACTIVE));

            item.setActive(false);
            assertFalse(item.isActive());
            assertFalse(item.getElement().hasClassName(CssName.ACTIVE));
            assertFalse(item.getHeader().getElement().hasClassName(CssName.ACTIVE));
        }

        collapsible.clearActive();
        for (Widget w : collapsible.getChildren()) {
            assertTrue(w instanceof MaterialCollapsibleItem);
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) w;
            assertFalse(item.getElement().hasClassName(CssName.ACTIVE));
        }
    }

    protected void generateCollapsibleItems(MaterialWidget parent) {
        for (int i = 1; i <= 5; i++) {
            MaterialCollapsibleItem item = new MaterialCollapsibleItem();
            MaterialCollapsibleHeader header = new MaterialCollapsibleHeader();
            MaterialCollapsibleBody body = new MaterialCollapsibleBody();
            item.add(header);
            item.add(body);
            parent.add(item);
        }
    }

    public void testNested() {
        // given
        MaterialCollapsible collapsible = getWidget();

        // when / then
        generateCollapsibleItems(collapsible);
        for (Widget w : collapsible.getChildren()) {
            assertTrue(w instanceof MaterialCollapsibleItem);
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) w;
            generateCollapsibleItems(item.getBody());
            assertTrue(item.getBody().getChildren().get(0) instanceof MaterialCollapsibleItem);
        }
    }

    public void testSecondaryItems() {
        // given
        MaterialCollapsible collapsible = getWidget();

        // when / then
        generateCollapsibleItems(collapsible);
        MaterialIcon icon1 = new MaterialIcon(IconType.DELETE);
        MaterialIcon icon2 = new MaterialIcon(IconType.PIN_DROP);
        for (Widget w : collapsible.getChildren()) {
            assertTrue(w instanceof MaterialCollapsibleItem);
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) w;
            item.getHeader().add(icon1);
            item.getHeader().add(icon2);
            assertEquals(icon1, item.getHeader().getWidget(0));
            assertEquals(icon2, item.getHeader().getWidget(1));
        }
    }
}
