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
import gwt.material.design.client.constants.Alignment;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.ListItem;

/**
 * Test case for Dropdown.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialDropdownTest extends MaterialWidgetTest<MaterialDropDown> {

    @Override
    protected MaterialDropDown createWidget() {
        return new MaterialDropDown();
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.DROPDOWN_CONTENT);
    }

    public void testOptions() {
        // given
        MaterialDropDown dropdown = getWidget();

        // when / then
        dropdown.setBelowOrigin(true);
        assertTrue(dropdown.isBelowOrigin());
        dropdown.setBelowOrigin(false);
        assertFalse(dropdown.isBelowOrigin());

        dropdown.setConstrainWidth(true);
        assertTrue(dropdown.isConstrainWidth());
        dropdown.setConstrainWidth(false);
        assertFalse(dropdown.isConstrainWidth());

        dropdown.setGutter(20);
        assertEquals(20, dropdown.getGutter());

        dropdown.setInDuration(100);
        assertEquals(100, dropdown.getInDuration());
        dropdown.setOutDuration(100);
        assertEquals(100, dropdown.getOutDuration());

        dropdown.setHover(true);
        assertTrue(dropdown.isHover());
        dropdown.setHover(false);
        assertFalse(dropdown.isHover());

        dropdown.setAlignment(Alignment.CENTER);
        assertEquals(Alignment.CENTER, dropdown.getAlignment());
        dropdown.setAlignment(Alignment.RIGHT);
        assertEquals(Alignment.RIGHT, dropdown.getAlignment());
        dropdown.setAlignment(Alignment.LEFT);
        assertEquals(Alignment.LEFT, dropdown.getAlignment());
        dropdown.setAlignment(Alignment.DEFAULT);
        assertEquals(Alignment.DEFAULT, dropdown.getAlignment());
    }

    public void testStructure() {
        // given
        MaterialDropDown dropdown = getWidget();

        // when / then
        final String ACTIVATOR = "dpActivator";
        MaterialLink link = new MaterialLink();
        link.setActivates(ACTIVATOR);
        assertEquals(ACTIVATOR, link.getActivates());
        assertTrue(link.getElement().hasAttribute("data-activates"));
        assertEquals(ACTIVATOR, link.getElement().getAttribute("data-activates"));
        RootPanel.get().add(link);

        dropdown.setActivator(ACTIVATOR);
        assertEquals(ACTIVATOR, dropdown.getId());
        assertEquals(ACTIVATOR, dropdown.getActivator());
        link.add(dropdown);
        assertNotNull(dropdown.getActivatorElement());
        assertEquals(link.getElement(), dropdown.getActivatorElement());

        populateDropDown(dropdown);

        // Smart check for parent Enabled / Disabled property
        dropdown.setEnabled(true);
        assertTrue(link.isEnabled());
        dropdown.setEnabled(false);
        assertTrue(link.isAttached());
        assertEquals(link, dropdown.getParent());
        dropdown.setEnabled(true);

        assertEquals(5, dropdown.getChildren().size());
        dropdown.remove(0);
        assertEquals(4, dropdown.getChildren().size());
    }

    public void testSelection() {
        // given
        MaterialDropDown dropdown = populateDropDown(getWidget());

        // when / then
        assertTrue(dropdown.getWidget(0) instanceof ListItem);
        ListItem item = (ListItem) dropdown.getWidget(0);
        assertTrue(item.getWidget(0) instanceof MaterialLink);
        MaterialLink link = (MaterialLink) item.getWidget(0);
        checkSelectionHandler(dropdown, link);
    }

    protected MaterialDropDown populateDropDown(MaterialDropDown dropdown) {
        for (int i = 1; i <= 5; i++) {
            MaterialLink item = new MaterialLink("Item" + i);
            dropdown.add(item);
            assertTrue(dropdown.getWidget(i - 1) instanceof ListItem);
            ListItem li = (ListItem) dropdown.getWidget(0);
            assertTrue(li.getWidget(0) instanceof MaterialLink);
        }
        return dropdown;
    }

    public void testEmptyItems() {
        MaterialDropDown dropdown = getWidget(false);

        dropdown.clear();
        dropdown.add(new ListItem());
        RootPanel.get().add(dropdown);
    }
}
