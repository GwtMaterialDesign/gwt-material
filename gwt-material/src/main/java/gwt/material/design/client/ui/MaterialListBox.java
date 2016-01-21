package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import gwt.material.design.client.base.HasColors;
import gwt.material.design.client.base.HasGrid;
import gwt.material.design.client.base.HasId;
import gwt.material.design.client.base.HasPlaceholder;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.ui.html.Label;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.ListBox;

//@formatter:off

/**
 * <p>Material ListBox is another dropdown component that will set / get the value depends on the selected index
 * <h3>UiBinder Usage:</h3>
 *
 * <pre>
 * {@code
 *    <m:MaterialListBox ui:field="lstBox" />
 * }
 * </pre>
 * <h3>Java Usage:</h3>
 *
 * <pre>
 * {@code
 *     // functions
 *    lstBox.setSelectedIndex(2);
 *    lstBox.getSelectedIndex();
 *    lstBox.addValueChangeHandler(handler);
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material ListBox</a>
 */
//@formatter:on
public class MaterialListBox extends MaterialWidget implements HasId, HasGrid, HasColors, HasValueChangeHandlers<String>, HasPlaceholder {

    private final ListBox listBox = new ListBox();
    private final Label lblName = new Label();

    private ToggleStyleMixin<MaterialListBox> toggleOldMixin = new ToggleStyleMixin<>(this, "browser-default");
    private boolean initialized;


    public MaterialListBox() {
        super(Document.get().createDivElement());
        addStyleName("input-field");
        add(listBox);
        add(lblName);
    }

    @Override
    public void onLoad() {
        if (!initialized) {
            initialized = true;
            createInternalChangeHandler(listBox.getElement());
            initializeMaterial(listBox.getElement());
        }
    }

    @Override
    public void setPlaceholder(String placeholder) {
        lblName.setText(placeholder);

        if (initialized && placeholder != null) {
            initializeMaterial(listBox.getElement());
        }
    }
    
    @Override
    public String getPlaceholder() {
        return lblName.getText();
    }

    public OptionElement getOptionElement(int index) {
        return  getSelectElement().getOptions().getItem(index);
    }


    /**
     * Removes all items from the list box.
     */
    @Override
    public void clear() {
        listBox.clear();
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    protected SelectElement getSelectElement() {
        return listBox.getElement().cast();
    }

    private void onChangeInternal() {
        ValueChangeEvent.fire(this, getSelectElement().getValue());
    }

    /**
     * Creates the internal change handler needed to trigger change events for
     * Materialize CSS change events.
     */
    protected native void createInternalChangeHandler(Element element) /*-{
        var that = this;
        var callback = $entry(function() {
            that.@gwt.material.design.client.ui.MaterialListBox::onChangeInternal()();
        });

        $wnd.jQuery(element).change(callback);
    }-*/;

    /**
     * Initializes the Materialize CSS list box. Should be
     * called every time the contents of the list box
     * changes, to keep the Materialize CSS design updated.
     */
    protected native void initializeMaterial(Element element) /*-{
        $wnd.jQuery(element).material_select();
    }-*/;

    /**
     * Sets whether this list allows multiple selections.
     *
     * @param multiple <code>true</code> to allow multiple selections
     */
    public void setMultipleSelect(boolean multipleSelect) {
        listBox.setMultipleSelect(multipleSelect);
        if (initialized) {
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Gets whether this list allows multiple selection.
     * 
     * @return <code>true</code> if multiple selection is allowed
     */
    public boolean isMultipleSelect() {
        return listBox.isMultipleSelect();
    }

    public void setEmptyPlaceHolder(String value) {
        listBox.insertItem(value, 0);

        getOptionElement(0).setDisabled(true);

        if (initialized) {
            initializeMaterial(listBox.getElement());
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    public boolean isOld() {
        return toggleOldMixin.isOn();
    }

    public void setOld(boolean old) {
        toggleOldMixin.setOn(old);
    }

    // delegate methods
    
    /**
     * Inserts an item into the list box, specifying its direction and an
     * initial value for the item. If the index is less than zero, or greater
     * than or equal to the length of the list, then the item will be appended
     * to the end of the list.
     * 
     * @param item
     *            the text of the item to be inserted
     * @param dir
     *            the item's direction. If {@code null}, the item is displayed
     *            in the widget's overall direction, or, if a direction
     *            estimator has been set, in the item's estimated direction.
     * @param value
     *            the item's value, to be submitted if it is part of a
     *            {@link FormPanel}.
     * @param index
     *            the index at which to insert it
     */
    public void insertItem(String item, Direction dir, String value, int index) {
        listBox.insertItem(item, dir, value, index);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Sets the value associated with the item at a given index. This value can
     * be used for any purpose, but is also what is passed to the server when
     * the list box is submitted as part of a {@link FormPanel}.
     * 
     * @param index
     *            the index of the item to be set
     * @param value
     *            the item's new value; cannot be <code>null</code>
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     */
    public void setValue(int index, String value) {
        listBox.setValue(index, value);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    @Override
    public void setTitle(String title) {
        listBox.setTitle(title);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Adds an item to the list box, specifying its direction. This method has
     * the same effect as
     * 
     * <pre>
     * addItem(item, dir, item)
     * </pre>
     * 
     * @param item
     *            the text of the item to be added
     * @param dir
     *            the item's direction
     */
    public void addItem(String item, Direction dir) {
        listBox.addItem(item, dir);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Adds an item to the list box. This method has the same effect as
     * 
     * <pre>
     * addItem(item, item)
     * </pre>
     * 
     * @param item
     *            the text of the item to be added
     */
    public void addItem(String item) {
        listBox.addItem(item);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Adds an item to the list box, specifying an initial value for the item.
     * 
     * @param item
     *            the text of the item to be added
     * @param value
     *            the item's value, to be submitted if it is part of a
     *            {@link FormPanel}; cannot be <code>null</code>
     */
    public void addItem(String item, String value) {
        listBox.addItem(item, value);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Adds an item to the list box, specifying its direction and an initial
     * value for the item.
     * 
     * @param item
     *            the text of the item to be added
     * @param dir
     *            the item's direction
     * @param value
     *            the item's value, to be submitted if it is part of a
     *            {@link FormPanel}; cannot be <code>null</code>
     */
    public void addItem(String item, Direction dir, String value) {
        listBox.addItem(item, dir, value);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Inserts an item into the list box. Has the same effect as
     * 
     * <pre>
     * insertItem(item, item, index)
     * </pre>
     * 
     * @param item
     *            the text of the item to be inserted
     * @param index
     *            the index at which to insert it
     */
    public void insertItem(String item, int index) {
        listBox.insertItem(item, index);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Inserts an item into the list box, specifying its direction. Has the same
     * effect as
     * 
     * <pre>
     * insertItem(item, dir, item, index)
     * </pre>
     * 
     * @param item
     *            the text of the item to be inserted
     * @param dir
     *            the item's direction
     * @param index
     *            the index at which to insert it
     */
    public void insertItem(String item, Direction dir, int index) {
        listBox.insertItem(item, dir, index);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Inserts an item into the list box, specifying an initial value for the
     * item. Has the same effect as
     *
     * <pre>
     * insertItem(item, null, value, index)
     * </pre>
     * 
     * @param item
     *            the text of the item to be inserted
     * @param value
     *            the item's value, to be submitted if it is part of a
     *            {@link FormPanel}.
     * @param index
     *            the index at which to insert it
     */
    public void insertItem(String item, String value, int index) {
        listBox.insertItem(item, value, index);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Sets whether an individual list item is selected.
     * 
     * @param index
     *            the index of the item to be selected or unselected
     * @param selected
     *            <code>true</code> to select the item
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     */
    public void setItemSelected(int index, boolean selected) {
        listBox.setItemSelected(index, selected);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Sets the text associated with the item at a given index.
     * 
     * @param index
     *            the index of the item to be set
     * @param text
     *            the item's new text
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     */
    public void setItemText(int index, String text) {
        listBox.setItemText(index, text);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Sets the text associated with the item at a given index.
     * 
     * @param index
     *            the index of the item to be set
     * @param text
     *            the item's new text
     * @param dir
     *            the item's direction.
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     */
    public void setItemText(int index, String text, Direction dir) {
        listBox.setItemText(index, text, dir);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    public void setName(String name) {
        listBox.setName(name);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Sets the currently selected index.
     * 
     * After calling this method, only the specified item in the list will
     * remain selected. For a ListBox with multiple selection enabled, see
     * {@link #setItemSelected(int, boolean)} to select multiple items at a
     * time.
     *
     * @param index
     *            the index of the item to be selected
     */
    public void setSelectedIndex(int index) {
        listBox.setSelectedIndex(index);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Sets the number of items that are visible. If only one item is visible,
     * then the box will be displayed as a drop-down list.
     * 
     * @param visibleItems
     *            the visible item count
     */
    public void setVisibleItemCount(int visibleItems) {
        listBox.setVisibleItemCount(visibleItems);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    /**
     * Gets the number of items present in the list box.
     * 
     * @return the number of items
     */
    public int getItemCount() {
        return listBox.getItemCount();
    }

    /**
     * Gets the text associated with the item at the specified index.
     * 
     * @param index
     *            the index of the item whose text is to be retrieved
     * @return the text associated with the item
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     */
    public String getItemText(int index) {
        return listBox.getItemText(index);
    }

    /**
     * Gets the text for currently selected item. If multiple items are
     * selected, this method will return the text of the first selected item.
     *
     * @return the text for selected item, or {@code null} if none is selected
     */
    public String getSelectedItemText() {
        return listBox.getSelectedItemText();
    }

    public String getName() {
        return listBox.getName();
    }

    /**
     * Gets the currently-selected item. If multiple items are selected, this
     * method will return the first selected item ({@link #isItemSelected(int)}
     * can be used to query individual items).
     * 
     * @return the selected index, or <code>-1</code> if none is selected
     */
    public int getSelectedIndex() {
        return listBox.getSelectedIndex();
    }

    /**
     * Gets the value associated with the item at a given index.
     * 
     * @param index
     *            the index of the item to be retrieved
     * @return the item's associated value
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     */
    public String getValue(int index) {
        return listBox.getValue(index);
    }

    /**
     * Gets the value for currently selected item. If multiple items are
     * selected, this method will return the value of the first selected item.
     *
     * @return the value for selected item, or {@code null} if none is selected
     */
    public String getSelectedValue() {
        return listBox.getSelectedValue();
    }

    /**
     * Gets the number of items that are visible. If only one item is visible,
     * then the box will be displayed as a drop-down list.
     * 
     * @return the visible item count
     */
    public int getVisibleItemCount() {
        return listBox.getVisibleItemCount();
    }

    /**
     * Determines whether an individual list item is selected.
     * 
     * @param index
     *            the index of the item to be tested
     * @return <code>true</code> if the item is selected
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     */
    public boolean isItemSelected(int index) {
        return listBox.isItemSelected(index);
    }

    /**
     * Removes the item at the specified index.
     *
     * @param index
     *            the index of the item to be removed
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     */
    public void removeItem(int index) {
        listBox.removeItem(index);
        if (initialized) {
            initializeMaterial(listBox.getElement());
        }
    }

    // utility methods

    /**
     * Returns all selected values of the list box, or empty array if none.
     * 
     * @return the selected values of the list box
     */
    public String[] getItemsSelected() {
        List<String> selected = new LinkedList<>();
        for (int i = 0; i < listBox.getItemCount(); i++) {
            if (listBox.isItemSelected(i)) {
                selected.add(listBox.getValue(i));
            }
        }
        return selected.toArray(new String[selected.size()]);
    }

    /**
     * Sets the currently selected value.
     * 
     * After calling this method, only the specified item in the list will
     * remain selected. For a ListBox with multiple selection enabled, see
     * {@link #setValueSelected(String, boolean)} to select multiple items at a
     * time.
     *
     * @param value
     *            the value of the item to be selected
     */
    public void setSelectedValue(String value) {
        int idx = getIndex(value);
        if (idx >= 0) {
            setSelectedIndex(idx);
        }
    }

    /**
     * Gets the index of the specified value.
     * 
     * @param value
     *            the value of the item to be found
     * @return the index of the value
     */
    public int getIndex(String value) {
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            String v = getValue(i);
            if (v.equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets whether an individual list value is selected.
     * 
     * @param value
     *            the value of the item to be selected or unselected
     * @param selected
     *            <code>true</code> to select the item
     */
    public void setValueSelected(String value, boolean selected) {
        int idx = getIndex(value);
        if (idx >= 0) {
            setItemSelected(idx, selected);
        }
    }

    /**
     * Removes a value from the list box. Nothing is done if the value isn't on
     * the list box.
     * 
     * @param value
     *            the value to be removed from the list
     */
    public void removeValue(String value) {
        int idx = getIndex(value);
        if (idx >= 0) {
            removeItem(idx);
        }
    }

}

