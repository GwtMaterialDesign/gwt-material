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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.dto.User;
import gwt.material.design.client.ui.html.Label;

import java.util.ArrayList;
import java.util.List;

/**
 * Test case for List Value Box
 *
 * @author kevzlou7979
 */
public class MaterialListValueBoxTest extends MaterialWidgetTest {

    public void init() {
        MaterialListValueBox<User> valueBox = new MaterialListValueBox<>();
        checkWidget(valueBox);
        checkValues(valueBox);
        checkProperties(valueBox);
        MaterialListBox listBox = new MaterialListBox();
        checkValueAsListBox(listBox);
    }

    @Override
    protected <T extends MaterialWidget> void checkInteractionEvents(T widget, boolean enabled) {
        // TODO ListValueBox overrides the interaction event implementation Need to update this later
    }

    @Override
    protected <T extends MaterialWidget> void checkChildren(T widget) {
        MaterialListBox listbox = new MaterialListBox();
        RootPanel.get().add(listbox);
        assertEquals(listbox.getChildren().size(), 3);

        assertTrue(listbox.getWidget(0) instanceof ListBox);
        assertTrue(listbox.getWidget(1) instanceof Label);
        assertTrue(listbox.getWidget(2) instanceof MaterialLabel);
    }

    public <T extends MaterialListValueBox<User>> void checkValues(T listValueBox) {
        List<User> users = new ArrayList<>();
        // Add Item test
        for (int i = 1; i <= 5; i++) {
            User user = new User("user" + i, "User " + i);
            users.add(user);
            listValueBox.addItem(user);
            //widget.addItem(user, user.getEmail());
            //widget.add(user);
        }

        for (int i = 0; i <= 4; i++) {
            assertNotNull(listValueBox.getValue(i));
        }

        // Set selected item test
        listValueBox.setSelectedIndex(0);
        assertEquals(listValueBox.getSelectedIndex(), 0);
        assertNotNull(listValueBox.getSelectedValue());
        assertEquals(users.get(0), listValueBox.getSelectedValue());

        // Remove item test
        listValueBox.removeItem(0);
        users.remove(0);
        assertEquals(listValueBox.getItemCount(), users.size());
        assertEquals(users.get(0), listValueBox.getSelectedValue());

        // Insert item test
        User inserted = new User("inserted", "Inserted");
        listValueBox.insertItem(inserted, 1);
        assertNotNull(inserted);
        assertEquals(listValueBox.getValue(1), inserted);

        // Clear test
        listValueBox.clear();
        assertNull(listValueBox.getSelectedValue());
        assertEquals(listValueBox.getItemCount(), 0);
    }

    public <T extends MaterialListValueBox<String>> void checkValueAsListBox(T widget) {
        List<String> users = new ArrayList<>();
        // Add Item test
        for (int i = 1; i <= 5; i++) {
            users.add("Item" + i);
            widget.addItem("Item" + i);
            //widget.addItem("Item" + i, "value" + i);
            //widget.add("Item" + i);
        }

        for (int i = 0; i <= 4; i++) {
            assertNotNull(widget.getValue(i));
        }

        // Set selected item test
        widget.setSelectedIndex(0);
        assertEquals(widget.getSelectedIndex(), 0);
        assertNotNull(widget.getSelectedValue());
        assertEquals(users.get(0), widget.getSelectedValue());

        // Remove item test
        widget.removeItem(0);
        users.remove(0);
        assertEquals(widget.getItemCount(), users.size());
        assertEquals(users.get(0), widget.getSelectedValue());

        // Insert item test
        String inserted = new String("inserted");
        widget.insertItem(inserted, 1);
        assertNotNull(inserted);
        assertEquals(widget.getValue(1), inserted);

        // Clear test
        widget.clear();
        assertNull(widget.getSelectedValue());
        assertEquals(widget.getItemCount(), 0);
    }

    public <T extends MaterialListValueBox<User>> void checkProperties(T listValueBox) {
        listValueBox.setOld(true);
        assertTrue(listValueBox.isOld());
        listValueBox.setOld(false);
        assertFalse(listValueBox.isOld());
        listValueBox.setMultipleSelect(true);
        assertTrue(listValueBox.isMultipleSelect());
    }

    @Override
    public <T extends MaterialWidget & HasEnabled> void checkEnabled(T widget) {
        checkEnabled(new MaterialListValueBox<User>());
    }

    public <T extends MaterialListValueBox & HasEnabled, H extends UIObject> void checkEnabled(T listValueBox) {
        final Element element = listValueBox.getListBox().getElement();
        assertFalse(element.hasAttribute(CssName.DISABLED));
        listValueBox.setEnabled(true);
        assertFalse(element.hasAttribute(CssName.DISABLED));
        assertEquals(listValueBox.isEnabled(), true);
        listValueBox.setEnabled(false);
        assertTrue(element.hasAttribute(CssName.DISABLED));
        assertEquals(listValueBox.getListBox().isEnabled(), false);
    }
}
