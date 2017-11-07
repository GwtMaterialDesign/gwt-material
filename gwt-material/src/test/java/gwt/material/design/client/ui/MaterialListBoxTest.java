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

import com.google.gwt.user.client.ui.ListBox;
import gwt.material.design.client.ui.html.Label;
import gwt.material.design.client.ui.html.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * Test case for List Value Box.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialListBoxTest extends MaterialListValueBoxTest<String> {

    @Override
    protected MaterialListBox createWidget() {
        return new MaterialListBox();
    }

    @Override
    public void testValues() {
        // given
        MaterialListBox listBox = (MaterialListBox) getWidget();

        List<String> users = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            users.add("user" + i);
        }

        // when / then
        checkValues(listBox, users);
    }

    public void testChildren() {
        // UiBinder
        // given
        MaterialListBox listBox = (MaterialListBox)getWidget();

        // when / then
        assertEquals(3, listBox.getChildren().size());
        assertTrue(listBox.getWidget(0) instanceof ListBox);
        assertTrue(listBox.getWidget(1) instanceof Label);
        assertTrue(listBox.getWidget(2) instanceof MaterialLabel);
    }

    public void testAllowBlanks() {
        // given
        MaterialListBox listBox = (MaterialListBox)getWidget();
        assertFalse(listBox.isAllowBlank());
        assertEquals(0, listBox.getItemCount());

        // when / then
        listBox.setValue(null);
        assertEquals("", listBox.getValue());

        listBox.setAllowBlank(true);
        listBox.addItem("user1");
        listBox.addItem("user2");
        Option option = new Option("user3");
        listBox.add(option);
        assertEquals(4, listBox.getItemCount());

        listBox.setValue("");
        assertEquals("", listBox.getValue());
        assertEquals(0, listBox.getSelectedIndex());

        listBox.setValue("user3");
        assertEquals("user3", listBox.getValue());
        assertEquals(3, listBox.getSelectedIndex());

        listBox.setValue(null);
        assertEquals("", listBox.getValue());
        assertEquals(0, listBox.getSelectedIndex());

        listBox.setValue("user4");
        assertEquals("", listBox.getValue());
        assertEquals(0, listBox.getSelectedIndex());

        listBox.setAllowBlank(false);
        assertEquals(3, listBox.getItemCount());
        assertEquals("user1", listBox.getValue());
        assertEquals(0, listBox.getSelectedIndex());
    }

}
