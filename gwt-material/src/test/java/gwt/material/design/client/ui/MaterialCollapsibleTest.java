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
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CollapsibleType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Collapsibles
 *
 * @author kevzlou7979
 */
public class MaterialCollapsibleTest extends MaterialWidgetTest {

    public void init() {
        MaterialCollapsible collapsible = new MaterialCollapsible();
        checkWidget(collapsible);
        checkTypes(collapsible);
        checkPreSelection(collapsible);
        checkNested(collapsible);
        checkSecondaryItems(collapsible);
    }

    @Override
    protected <T extends MaterialWidget> void checkChildren(T collapsible) {
        checkStructure(new MaterialCollapsible());
    }

    protected <T extends MaterialCollapsible> void checkStructure(T collapsible) {
        MaterialCollapsibleItem item = new MaterialCollapsibleItem();

        MaterialCollapsibleHeader header = new MaterialCollapsibleHeader();
        item.add(header);

        MaterialCollapsibleBody body = new MaterialCollapsibleBody();
        item.add(body);

        assertEquals(item.getChildren().size(), 2);
        assertTrue(item.getWidget(0) instanceof MaterialCollapsibleHeader);
        assertTrue(item.getWidget(1) instanceof MaterialCollapsibleBody);
        collapsible.add(item);

        // isFeatureEnabled Default
        assertTrue(collapsible.isFeatureEnabled(MaterialWidget.Feature.ONLOAD_ADD_QUEUE));
        // Expected result -> 0 (Because feature is enabled)
        assertEquals(collapsible.getChildren().size(), 0);
        // isFeatureEnabled -> false
        collapsible.enableFeature(MaterialWidget.Feature.ONLOAD_ADD_QUEUE, false);
        assertFalse(collapsible.isFeatureEnabled(MaterialWidget.Feature.ONLOAD_ADD_QUEUE));
        collapsible.clear();
        collapsible.add(item);
        assertEquals(collapsible.getChildren().size(), 1);

        // Check Multiple collapsible items
        collapsible.clear();
        generateCollapsibleItems(collapsible);
        assertEquals(collapsible.getChildren().size(), 5);
        collapsible.remove(0);
        assertEquals(collapsible.getChildren().size(), 4);
        collapsible.insert(item, 1);
        assertEquals(collapsible.getWidget(1), item);
        assertEquals(collapsible.getChildren().size(), 5);
        collapsible.clear();
        assertEquals(collapsible.getChildren().size(), 0);
    }

    protected <T extends MaterialCollapsible> void checkTypes(T collapsible) {
        collapsible.enableFeature(MaterialWidget.Feature.ONLOAD_ADD_QUEUE, false);
        collapsible.setType(CollapsibleType.FLAT);
        assertEquals(collapsible.getType(), CollapsibleType.FLAT);
        assertTrue(collapsible.getElement().hasClassName(CollapsibleType.FLAT.getCssName()));
        collapsible.setType(CollapsibleType.POPOUT);
        assertEquals(collapsible.getType(), CollapsibleType.POPOUT);
        assertTrue(collapsible.getElement().hasClassName(CollapsibleType.POPOUT.getCssName()));
        collapsible.setAccordion(true);
        assertTrue(collapsible.isAccordion());
        collapsible.setAccordion(false);
        assertFalse(collapsible.isAccordion());
    }

    protected <T extends MaterialCollapsible> void checkPreSelection(T collapsible) {
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

    protected <T extends MaterialCollapsible> void checkNested(T collapsible) {
        generateCollapsibleItems(collapsible);
        for (Widget w : collapsible.getChildren()) {
            assertTrue(w instanceof MaterialCollapsibleItem);
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) w;
            generateCollapsibleItems(item.getBody());
            assertTrue(item.getBody().getChildren().get(0) instanceof MaterialCollapsibleItem);
        }
    }

    protected <T extends MaterialCollapsible> void checkSecondaryItems(T collapsible) {
        generateCollapsibleItems(collapsible);
        MaterialIcon icon1 = new MaterialIcon(IconType.DELETE);
        MaterialIcon icon2 = new MaterialIcon(IconType.PIN_DROP);
        for (Widget w : collapsible.getChildren()) {
            assertTrue(w instanceof MaterialCollapsibleItem);
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) w;
            item.getHeader().add(icon1);
            item.getHeader().add(icon2);
            assertEquals(item.getHeader().getWidget(0), icon1);
            assertEquals(item.getHeader().getWidget(1), icon2);
        }
    }
}
