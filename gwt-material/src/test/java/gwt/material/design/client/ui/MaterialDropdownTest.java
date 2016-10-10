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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.constants.Alignment;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.ListItem;

/**
 * Test case for Dropdown
 *
 * @author kevzlou7979
 */
public class MaterialDropdownTest extends MaterialWidgetTest {

    public void init() {
        MaterialDropDown dropDown = new MaterialDropDown();
        checkWidget(dropDown);
        checkOptions(dropDown);
        checkStructure(dropDown);
        checkSelection(dropDown);
    }

    public <T extends MaterialDropDown> void checkOptions(T dropdown) {
        dropdown.setBelowOrigin(true);
        assertTrue(dropdown.isBelowOrigin());
        dropdown.setBelowOrigin(false);
        assertFalse(dropdown.isBelowOrigin());

        dropdown.setConstrainWidth(true);
        assertTrue(dropdown.isConstrainWidth());
        dropdown.setConstrainWidth(false);
        assertFalse(dropdown.isConstrainWidth());

        dropdown.setGutter(20);
        assertEquals(dropdown.getGutter(), 20);

        dropdown.setInDuration(100);
        assertEquals(dropdown.getInDuration(), 100);
        dropdown.setOutDuration(100);
        assertEquals(dropdown.getOutDuration(), 100);

        dropdown.setHover(true);
        assertTrue(dropdown.isHover());
        dropdown.setHover(false);
        assertFalse(dropdown.isHover());

        dropdown.setAlignment(Alignment.CENTER);
        assertEquals(dropdown.getAlignment(), Alignment.CENTER);
        dropdown.setAlignment(Alignment.RIGHT);
        assertEquals(dropdown.getAlignment(), Alignment.RIGHT);
        dropdown.setAlignment(Alignment.LEFT);
        assertEquals(dropdown.getAlignment(), Alignment.LEFT);
        dropdown.setAlignment(Alignment.DEFAULT);
        assertEquals(dropdown.getAlignment(), Alignment.DEFAULT);
    }

    public <T extends MaterialDropDown> void checkStructure(T dropdown) {
        final String ACTIVATOR = "dpActivator";
        MaterialLink link = new MaterialLink();
        link.setActivates(ACTIVATOR);
        assertEquals(link.getActivates(), ACTIVATOR);
        assertTrue(link.getElement().hasAttribute("data-activates"));
        assertEquals(link.getElement().getAttribute("data-activates"), ACTIVATOR);
        RootPanel.get().add(link);

        dropdown.setActivator(ACTIVATOR);
        assertEquals(dropdown.getId(), ACTIVATOR);
        assertEquals(dropdown.getActivator(), ACTIVATOR);
        RootPanel.get().add(dropdown);
        assertNotNull(dropdown.getActivatorElement());
        assertEquals(dropdown.getActivatorElement(), link.getElement());

        for (int i = 1; i <= 5; i++) {
            MaterialLink item = new MaterialLink("Item" + i);
            dropdown.add(item);
            assertTrue(dropdown.getWidget(i - 1) instanceof ListItem);
            ListItem li = (ListItem) dropdown.getWidget(0);
            assertTrue(li.getWidget(0) instanceof MaterialLink);
        }

        assertEquals(dropdown.getChildren().size(), 5);
        dropdown.remove(0);
        assertEquals(dropdown.getChildren().size(), 4);
    }

    public <T extends MaterialDropDown> void checkSelection(T dropdown) {
        assertTrue(dropdown.getWidget(0) instanceof ListItem);
        ListItem item = (ListItem) dropdown.getWidget(0);
        assertTrue(item.getWidget(0) instanceof MaterialLink);
        MaterialLink link = (MaterialLink) item.getWidget(0);
        final boolean[] isSelectionFired = {false};
        dropdown.addSelectionHandler(selectionEvent -> {
            assertEquals(link, selectionEvent.getSelectedItem());
            isSelectionFired[0] = true;

        });

        link.fireEvent(new GwtEvent<ClickHandler>() {
            @Override
            public Type<ClickHandler> getAssociatedType() {
                return ClickEvent.getType();
            }

            @Override
            protected void dispatch(ClickHandler eventHandler) {
                eventHandler.onClick(null);
            }
        });
        assertTrue(isSelectionFired[0]);
    }
}
