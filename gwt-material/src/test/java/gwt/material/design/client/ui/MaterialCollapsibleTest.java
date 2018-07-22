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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Collapsibles.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialCollapsibleTest extends MaterialWidgetTest<MaterialCollapsible> {

    static final int FIRST_ITEM = 1;
    static final int SECOND_ITEM = 2;

    @Override
    protected MaterialCollapsible createWidget() {
        MaterialCollapsible collapsible = new MaterialCollapsible();
        generateCollapsibleItems(collapsible);
        return collapsible;
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.COLLAPSIBLE);
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

    public void testStructure() {
        // given
        MaterialCollapsible collapsible = getWidget();

        // when / then
        assertTrue(collapsible.getChildren().size() > 0);
        collapsible.getChildren().forEach(widget -> {
            assertTrue(widget instanceof MaterialCollapsibleItem);

            // given
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) widget;

            // when / then
            assertEquals(item.getBody(), item.getWidget(1));

            // given
            MaterialCollapsibleHeader header = item.getHeader();
            MaterialCollapsibleBody body = item.getBody();

            // when / then
            assertTrue(header.getElement().hasClassName(CssName.COLLAPSIBLE_HEADER));
            assertTrue(body.getElement().hasClassName(CssName.COLLAPSIBLE_BODY));
        });
    }

    public void testExpansion() {
        // given
        MaterialCollapsible collapsible = getWidget();

        // when
        collapsible.setAccordion(true);
        collapsible.open(FIRST_ITEM);

        // then
        assertTrue(collapsible.getActive() instanceof MaterialCollapsibleItem);
        MaterialCollapsibleItem item = (MaterialCollapsibleItem) collapsible.getActive();
        assertTrue(item.getElement().hasClassName(CssName.ACTIVE));
        assertTrue(item.getHeader().getElement().hasClassName(CssName.ACTIVE));

        // when
        collapsible.close(FIRST_ITEM);

        // then
        assertFalse(item.getElement().hasClassName(CssName.ACTIVE));
        assertFalse(item.getHeader().getElement().hasClassName(CssName.ACTIVE));

        // when
        collapsible.open(SECOND_ITEM);

        // then
        assertTrue(collapsible.getActive() instanceof MaterialCollapsibleItem);
        MaterialCollapsibleItem item2 = (MaterialCollapsibleItem) collapsible.getActive();
        assertTrue(item2.getElement().hasClassName(CssName.ACTIVE));
        assertTrue(item2.getHeader().getElement().hasClassName(CssName.ACTIVE));
        assertFalse(item.getHeader().getElement().hasClassName(CssName.ACTIVE));

        // when
        collapsible.close(SECOND_ITEM);

        // then
        assertFalse(item2.getElement().hasClassName(CssName.ACTIVE));
        assertFalse(item2.getHeader().getElement().hasClassName(CssName.ACTIVE));
    }

    public void testMultipleExpansion() {
        // given
        MaterialCollapsible collapsible = getWidget();

        // when / then
        collapsible.setAccordion(false);
        assertTrue(collapsible.getWidget(FIRST_ITEM) instanceof MaterialCollapsibleItem);
        assertTrue(collapsible.getWidget(SECOND_ITEM) instanceof MaterialCollapsibleItem);

        // given
        MaterialCollapsibleItem item1 = (MaterialCollapsibleItem) collapsible.getWidget(FIRST_ITEM);
        MaterialCollapsibleItem item2 = (MaterialCollapsibleItem) collapsible.getWidget(SECOND_ITEM);

        // when / then
        item1.expand();
        assertTrue(item1.getElement().hasClassName(CssName.ACTIVE));
        item1.collapse();
        assertFalse(item1.getElement().hasClassName(CssName.ACTIVE));
        item2.expand();
        assertTrue(item2.getElement().hasClassName(CssName.ACTIVE));
        item2.collapse();
        assertFalse(item2.getElement().hasClassName(CssName.ACTIVE));
    }

    public void testProgress() {
        // given
        MaterialCollapsible collapsible = getWidget();
        MaterialCollapsibleItem item1 = (MaterialCollapsibleItem) collapsible.getWidget(FIRST_ITEM);

        // when / then
        item1.expand();
        item1.showProgress(ProgressType.INDETERMINATE);
        assertEquals(3, item1.getWidgetCount());
        assertTrue(item1.getWidget(2) instanceof MaterialProgress);
        assertEquals(Display.NONE.getCssName(), item1.getBody().getElement().getStyle().getDisplay());

        // when / then
        item1.hideProgress();
        assertEquals(Display.BLOCK.getCssName(), item1.getBody().getElement().getStyle().getDisplay());
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
        for (Widget w : collapsible.getChildren()) {
            assertTrue(w instanceof MaterialCollapsibleItem);
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) w;
            assertNotNull(item.getBody());
            assertNotNull(item.getHeader());

            item.setActive(true);
            assertTrue(item.isActive());
            assertTrue(item.getElement().hasClassName(CssName.ACTIVE));
            assertTrue(item.getHeader().getElement().hasClassName(CssName.ACTIVE));
            assertEquals(Display.BLOCK.getCssName(), item.getBody().getElement().getStyle().getDisplay());

            item.setActive(false);
            assertFalse(item.isActive());
            assertFalse(item.getElement().hasClassName(CssName.ACTIVE));
            assertFalse(item.getHeader().getElement().hasClassName(CssName.ACTIVE));
            assertEquals(Display.NONE.getCssName(), item.getBody().getElement().getStyle().getDisplay());
        }

        collapsible.clearActive();
        for (Widget w : collapsible.getChildren()) {
            assertTrue(w instanceof MaterialCollapsibleItem);
            MaterialCollapsibleItem item = (MaterialCollapsibleItem) w;
            assertFalse(item.getElement().hasClassName(CssName.ACTIVE));
        }
    }

    public void testNested() {
        // given
        MaterialCollapsible collapsible = getWidget();

        // when / then
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

    public void testCollapsibleHandlers() {
        MaterialCollapsible collapsible = getWidget();

        for (Widget widget : collapsible.getChildren()) {
            if (widget instanceof MaterialCollapsibleItem) {
                checkCollapsibleItemEvent(collapsible, (MaterialCollapsibleItem) widget);
            }
        }
    }

    protected void checkCollapsibleItemEvent(MaterialCollapsible collapsible, MaterialCollapsibleItem item) {
        final boolean[] collapseEventFired = {false};
        final boolean[] expandEventFired = {false};

        collapsible.addCollapseHandler(event -> collapseEventFired[0] = true);
        collapsible.addExpandHandler(event -> expandEventFired[0] = true);

        item.setActive(true);
        assertTrue(expandEventFired[0]);

        item.setActive(false);
        assertTrue(collapseEventFired[0]);

        fireClickEvent(item.getHeader());
        assertTrue(expandEventFired[0]);

        fireClickEvent(item.getHeader());
        assertTrue(collapseEventFired[0]);
    }
}
