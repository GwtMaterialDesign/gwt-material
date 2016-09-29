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
import org.junit.Test;

/**
 * Test case for Collapsibles
 *
 * @author kevzlou7979
 */
public class MaterialCollapsibleTest extends MaterialWidgetTest {

    @Test
    public void testCollapsible() {
        MaterialCollapsible collapsible = new MaterialCollapsible();
        checkWidget(collapsible);
        checkTypes(collapsible);
        checkPreSelection(collapsible);
        checkNested(collapsible);
        checkSecondaryItems(collapsible);
    }

    @Override
    protected <T extends MaterialWidget> void checkChildren(T widget) {
        checkStructure(new MaterialCollapsible());
    }

    protected <T extends MaterialCollapsible> void checkStructure(T widget) {
        MaterialCollapsibleItem item = new MaterialCollapsibleItem();

        MaterialCollapsibleHeader header = new MaterialCollapsibleHeader();
        item.add(header);

        MaterialCollapsibleBody body = new MaterialCollapsibleBody();
        item.add(body);

        assertEquals(item.getChildren().size(), 2);
        assertTrue(item.getWidget(0) instanceof MaterialCollapsibleHeader);
        assertTrue(item.getWidget(1) instanceof MaterialCollapsibleBody);
        widget.add(item);

        // isFeatureEnabled Default
        assertTrue(widget.isFeatureEnabled(MaterialWidget.Feature.ONLOAD_ADD_QUEUE));
        // Expected result -> 0 (Because feature is enabled)
        assertEquals(widget.getChildren().size(), 0);
        // isFeatureEnabled -> false
        widget.enableFeature(MaterialWidget.Feature.ONLOAD_ADD_QUEUE, false);
        assertFalse(widget.isFeatureEnabled(MaterialWidget.Feature.ONLOAD_ADD_QUEUE));
        widget.clear();
        widget.add(item);
        assertEquals(widget.getChildren().size(), 1);

        // Check Multiple collapsible items
        widget.clear();
        generateCollapsibleItems(widget);
        assertEquals(widget.getChildren().size(), 5);
        widget.remove(0);
        assertEquals(widget.getChildren().size(), 4);
        widget.insert(item, 1);
        assertEquals(widget.getWidget(1), item);
        assertEquals(widget.getChildren().size(), 5);
        widget.clear();
        assertEquals(widget.getChildren().size(), 0);
    }

    protected <T extends MaterialCollapsible> void checkTypes(T widget) {
        widget.enableFeature(MaterialWidget.Feature.ONLOAD_ADD_QUEUE, false);
        widget.setType(CollapsibleType.FLAT);
        assertEquals(widget.getType(), CollapsibleType.FLAT);
        assertTrue(widget.getElement().hasClassName(CollapsibleType.FLAT.getCssName()));
        widget.setType(CollapsibleType.POPOUT);
        assertEquals(widget.getType(), CollapsibleType.POPOUT);
        assertTrue(widget.getElement().hasClassName(CollapsibleType.POPOUT.getCssName()));
        widget.setAccordion(true);
        assertTrue(widget.isAccordion());
        widget.setAccordion(false);
        assertFalse(widget.isAccordion());
    }

    protected <T extends MaterialCollapsible> void checkPreSelection(T widget) {
        widget.enableFeature(MaterialWidget.Feature.ONLOAD_ADD_QUEUE, false);
        generateCollapsibleItems(widget);
        for (Widget w : widget.getChildren()) {
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

        widget.clearActive();
        for (Widget w : widget.getChildren()) {
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

    protected <T extends MaterialCollapsible> void checkNested(T widget) {
        generateCollapsibleItems(widget);
        for (Widget w : widget.getChildren()) {
            assertTrue(w instanceof MaterialCollapsibleItem);
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) w;
            generateCollapsibleItems(item.getBody());
            assertTrue(item.getBody().getChildren().get(0) instanceof MaterialCollapsibleItem);
        }
    }

    protected <T extends MaterialCollapsible> void checkSecondaryItems(T widget) {
        generateCollapsibleItems(widget);
        MaterialIcon icon1 = new MaterialIcon(IconType.DELETE);
        MaterialIcon icon2 = new MaterialIcon(IconType.PIN_DROP);
        for (Widget w : widget.getChildren()) {
            assertTrue(w instanceof MaterialCollapsibleItem);
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) w;
            item.getHeader().add(icon1);
            item.getHeader().add(icon2);
            assertEquals(item.getHeader().getWidget(0), icon1);
            assertEquals(item.getHeader().getWidget(1), icon2);
        }
    }
}
