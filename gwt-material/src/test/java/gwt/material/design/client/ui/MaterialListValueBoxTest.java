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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;
import gwt.material.design.client.ui.dto.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Test case for List Value Box.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialListValueBoxTest<T> extends AbstractValueWidgetTest<MaterialListValueBox<T>> {

    private MaterialListValueBox<User> listValueBox;

    @Override
    protected MaterialListValueBox<T> createWidget() {
        return new MaterialListValueBox<>();
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.INPUT_FIELD);
    }

    public <M> void checkValues(MaterialListValueBox<M> listValueBox, List<M> models) {
        for (M model : models) {
            listValueBox.addItem(model);
        }

        for (int i = 0; i <= 4; i++) {
            assertNotNull(listValueBox.getValue(i));
        }

        // Set selected item test
        listValueBox.setSelectedIndex(0);
        assertEquals(0, listValueBox.getSelectedIndex());
        assertNotNull(listValueBox.getSelectedValue());
        assertEquals(listValueBox.getSelectedValue(), models.get(0));

        // Check ValueChange Event
        checkValueChangeEvent(listValueBox, models.get(0), models.get(1));

        // Remove item test
        listValueBox.removeItem(0);
        models.remove(0);
        assertEquals(models.size(), listValueBox.getItemCount());
        assertEquals(listValueBox.getSelectedValue(), models.get(0));

        // Insert item test
        M model = models.get(2);
        listValueBox.insertItem(model, 1);
        assertNotNull(model);
        assertEquals(model, listValueBox.getValue(1));

        // Clear test
        listValueBox.clear();
        assertNull(listValueBox.getSelectedValue());
        assertEquals(0, listValueBox.getItemCount());
    }

    public void testValues() {
        // given
        listValueBox = new MaterialListValueBox<>();
        RootPanel.get().add(listValueBox);

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            User user = new User("user" + i, "User " + i);
            users.add(user);
        }

        // when / then
        checkValues(listValueBox, users);
    }

    public void testProperties() {
        // UiBinder
        // given
        MaterialListValueBox<T> listValueBox = getWidget(false);

        // when / then
        checkProperties(listValueBox);

        attachWidget();

        // Standard
        // given
        checkProperties(listValueBox);
    }

    protected void checkProperties(MaterialListValueBox listValueBox) {
        // when / then
        listValueBox.setMultipleSelect(true);
        assertTrue(listValueBox.isMultipleSelect());
    }

    public void testBrowserDefault() {
        // UiBinder
        // given
        MaterialListValueBox<T> listValueBox = getWidget(false);

        // when / then
        checkBrowserDefault(listValueBox, false);

        // Standard
        // given
        attachWidget();

        // when / then
        checkBrowserDefault(listValueBox, true);
    }

    protected void checkBrowserDefault(MaterialListValueBox listValueBox, boolean checkElement) {
        listValueBox.setOld(true);
        assertTrue(listValueBox.isOld());
        if (checkElement) {
            assertTrue(listValueBox.getListBox().getElement().hasClassName("browser-default"));
        }

        listValueBox.setOld(false);
        assertFalse(listValueBox.isOld());
        if (checkElement) {
            assertFalse(listValueBox.getListBox().getElement().hasClassName("browser-default"));
        }
    }

    public void testEnabled() {
        // given
        MaterialListValueBox<T> listValueBox = getWidget();

        // when / then
        final Element element = listValueBox.getListBox().getElement();
        assertFalse(element.hasAttribute(CssName.DISABLED));
        listValueBox.setEnabled(true);
        assertFalse(element.hasAttribute(CssName.DISABLED));
        assertTrue(listValueBox.isEnabled());
        listValueBox.setEnabled(false);
        assertTrue(element.hasAttribute(CssName.DISABLED));
        assertFalse(listValueBox.getListBox().isEnabled());
    }

    public void testReadOnly() {
        // given
        MaterialListValueBox<T> valueBox = getWidget();

        // when / then
        checkReadOnly(valueBox, valueBox.getListBox());
    }

    public void testErrorSuccess() {
        // given
        MaterialListValueBox<T> valueBox = getWidget();

        // when / then
        checkFieldErrorSuccess(valueBox, valueBox.getErrorLabel(), valueBox, valueBox.getLabel());
    }

    public void testPlaceholder() {
        // UiBinder
        // given
        MaterialListValueBox<T> valueBox = getWidget(false);

        // when / then
        checkPlaceholder(valueBox);

        // Standard
        // given
        attachWidget();

        // when / then
        checkPlaceholder(valueBox);
    }

    public void testAllowBlanks() {
        // given
        MaterialListValueBox<Integer> valueBox = (MaterialListValueBox<Integer>) getWidget();
        assertFalse(valueBox.isAllowBlank());
        assertEquals(0, valueBox.getItemCount());

        // when / then
        valueBox.setAllowBlank(true);
        assertEquals(1, valueBox.getItemCount());

        valueBox.addItem(Integer.valueOf(1));
        assertEquals(2, valueBox.getItemCount());
        valueBox.addItem(Integer.valueOf(2));
        valueBox.addItem(Integer.valueOf(3));
        assertTrue(valueBox.isAllowBlank());
        valueBox.setSelectedIndex(-1);
        assertNull(valueBox.getSelectedValue());
        assertNull(valueBox.getValue());

        valueBox.setValue(null);
        assertEquals(0, valueBox.getSelectedIndex());
        assertNull(valueBox.getValue());
        valueBox.setValue(Integer.valueOf(2));
        assertEquals(2, valueBox.getSelectedIndex());
        assertNotNull(valueBox.getValue());
        valueBox.setValue(null);
        assertEquals(0, valueBox.getSelectedIndex());
        assertNull(valueBox.getValue());

        valueBox.setAllowBlank(false);
        assertEquals(0, valueBox.getSelectedIndex());
        assertNotNull(valueBox.getValue());

        valueBox.setAllowBlank(true);
        valueBox.setValue(null);
        assertNull(valueBox.getValue());

    }

    public void testFocusAndBlurHandlers() {
        MaterialListValueBox<T> valueBox = getWidget();
        checkFocusAndBlurEvents(valueBox);
    }

    public void testEmptyPlaceHolder() {
        // Given
        final String EMPTY_PLACEHOLDER = "select-item";
        MaterialListValueBox<Integer> valueBox = new MaterialListValueBox<>();
        for (int i = 0; i <= 10; i++) {
            valueBox.addItem(i, "Item " + i);
        }
        RootPanel.get().add(valueBox);

        // when / then
        valueBox.setEmptyPlaceHolder(EMPTY_PLACEHOLDER);
        assertEquals(EMPTY_PLACEHOLDER, valueBox.getEmptyPlaceHolder());
        assertEquals(valueBox.getListBox().getItemText(0), EMPTY_PLACEHOLDER);
        assertTrue(valueBox.getOptionElement(0).isDisabled());

        valueBox.setEmptyPlaceHolder(null);
        assertNotSame(EMPTY_PLACEHOLDER, valueBox.getEmptyPlaceHolder());
        assertNotSame(EMPTY_PLACEHOLDER, valueBox.getOptionElement(0));
        assertNotNull(valueBox.getValue());
    }


    @Override
    protected void checkWaves(MaterialListValueBox<T> widget, boolean checkElement) {
        super.checkWaves(getWidget(), false);
    }
}
