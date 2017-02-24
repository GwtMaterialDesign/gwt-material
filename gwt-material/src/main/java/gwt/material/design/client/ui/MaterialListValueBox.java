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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.user.client.ui.ListBox;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.base.mixin.ReadOnlyMixin;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.html.Label;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static gwt.material.design.client.js.JsMaterialElement.$;
//@formatter:off

/**
 * <p>Material ListBox is another dropdown component that will set / get the value depends on the selected index
 * <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code
 *    <m:MaterialListBox ui:field="lstBox" />
 * }
 * </pre>
 * <h3>Java Usage:</h3>
 * <p>
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
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#forms">Material ListBox</a>
 */
//@formatter:on
public class MaterialListValueBox<T> extends AbstractValueWidget<T> implements HasPlaceholder,
        HasConstrainedValue<T>, HasReadOnly {

    private final ListBox listBox = new ListBox();
    private final Label lblName = new Label();

    private boolean initialized;

    // By default the key is generated using toString
    private KeyFactory<T, String> keyFactory = Object::toString;

    protected final List<T> values = new ArrayList<>();

    private ToggleStyleMixin<ListBox> toggleOldMixin;
    private ReadOnlyMixin<MaterialListValueBox<T>, ListBox> readOnlyMixin;
    private HandlerRegistration valueChangeHandler;

    private MaterialLabel lblError = new MaterialLabel();

    public MaterialListValueBox() {
        super(Document.get().createDivElement(), CssName.INPUT_FIELD);
        toggleOldMixin = new ToggleStyleMixin<>(listBox, "browser-default");
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        if (!initialized) {
            $(listBox.getElement()).change((e, param) -> {
                try {
                    ValueChangeEvent.fire(this, getValue());
                } catch (IndexOutOfBoundsException ex) {
                    GWT.log("ListBox value change handler threw an exception.", ex);
                }
                return true;
            });
            valueChangeHandler = addValueChangeHandler(valueChangeEvent -> {
                if (isToggleReadOnly()) {
                    setReadOnly(true);
                }
            });
            add(listBox);
            add(lblName);
            add(lblError);
            initialize();
        }
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        if (valueChangeHandler != null) {
            valueChangeHandler.removeHandler();
            valueChangeHandler = null;
        }
        $(listBox.getElement()).off("change");
        $(listBox.getElement()).material_select("destroy");
        initialized = false;
    }

    @Override
    public void setPlaceholder(String placeholder) {
        lblName.setText(placeholder);

        if (placeholder != null) {
            reinitialize();
        }
    }

    @Override
    public String getPlaceholder() {
        return lblName.getText();
    }

    public OptionElement getOptionElement(int index) {
        return getSelectElement().getOptions().getItem(index);
    }

    @Override
    public void reset() {
        super.reset();
        clear();
    }

    /**
     * Removes all items from the list box.
     */
    @Override
    public void clear() {
        values.clear();
        listBox.clear();
        reinitialize();
    }

    protected SelectElement getSelectElement() {
        return listBox.getElement().cast();
    }

    /**
     * Initializes the Materialize CSS list box. Should be
     * called every time the contents of the list box
     * changes, to keep the Materialize CSS design updated.
     */
    protected void initialize() {
        $(listBox.getElement()).material_select();
        initialized = true;
    }

    /**
     * Initialize if we have already initialized before.
     */
    protected void reinitialize() {
        if (initialized) {
            initialize();
        }
    }

    /**
     * Sets whether this list allows multiple selections.
     *
     * @param multipleSelect <code>true</code> to allow multiple selections
     */
    public void setMultipleSelect(boolean multipleSelect) {
        listBox.setMultipleSelect(multipleSelect);
        reinitialize();
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

        reinitialize();
    }

    @Override
    public void setAcceptableValues(Collection<T> values) {
        this.values.clear();
        clear();
        values.forEach(this::addItem);
    }


    @Override
    public T getValue() {
        if (getSelectedIndex() != -1) {
            return values.get(getSelectedIndex());
        }
        return null;
    }

    @Override
    public void setValue(T value) {
        setValue(value, true);
    }

    @Override
    public void setValue(T value, boolean fireEvents) {
        int index = values.indexOf(value);
        if (index >= 0) {
            T before = getValue();
            setSelectedIndex(index);

            if (fireEvents) {
                ValueChangeEvent.fireIfNotEqual(this, before, value);
            }
        }
    }

    public boolean isOld() {
        return toggleOldMixin.isOn();
    }

    public void setOld(boolean old) {
        toggleOldMixin.setOn(old);
    }

    // delegate methods

    public void add(T value) {
        addItem(value);
    }

    /**
     * Inserts an item into the list box, specifying its direction and an
     * initial value for the item. If the index is less than zero, or greater
     * than or equal to the length of the list, then the item will be appended
     * to the end of the list.
     *
     * @param item  the text of the item to be inserted
     * @param dir   the item's direction. If {@code null}, the item is displayed
     *              in the widget's overall direction, or, if a direction
     *              estimator has been set, in the item's estimated direction.
     * @param value the item's value, to be submitted if it is part of a
     *              {@link FormPanel}.
     * @param index the index at which to insert it
     */
    public void insertItem(T item, Direction dir, String value, int index) {
        values.add(index, item);
        listBox.insertItem(keyFactory.generateKey(item), dir, value, index);
        reinitialize();
    }

    /**
     * Sets the value associated with the item at a given index. This value can
     * be used for any purpose, but is also what is passed to the server when
     * the list box is submitted as part of a {@link FormPanel}.
     *
     * @param index the index of the item to be set
     * @param value the item's new value; cannot be <code>null</code>
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void setValue(int index, String value) {
        listBox.setValue(index, value);
        reinitialize();
    }

    @Override
    public void setTitle(String title) {
        listBox.setTitle(title);
        reinitialize();
    }

    /**
     * Adds an item to the list box, specifying its direction. This method has
     * the same effect as
     * <p>
     * <pre>
     * addItem(item, dir, item)
     * </pre>
     *
     * @param item the text of the item to be added
     * @param dir  the item's direction
     */
    public void addItem(T item, Direction dir) {
        values.add(item);
        listBox.addItem(keyFactory.generateKey(item), dir);
        reinitialize();
    }

    /**
     * Adds an item to the list box. This method has the same effect as
     * <p>
     * <pre>
     * addItem(item, item)
     * </pre>
     *
     * @param item the text of the item to be added
     */
    public void addItem(T item) {
        values.add(item);
        listBox.addItem(keyFactory.generateKey(item));
        reinitialize();
    }

    /**
     * Adds an item to the list box, specifying an initial value for the item.
     *
     * @param item  the text of the item to be added
     * @param value the item's value, to be submitted if it is part of a
     *              {@link FormPanel}; cannot be <code>null</code>
     */
    public void addItem(T item, String value) {
        values.add(item);
        listBox.addItem(value, keyFactory.generateKey(item));
        reinitialize();
    }

    /**
     * Adds an item to the list box, specifying its direction and an initial
     * value for the item.
     *
     * @param item  the text of the item to be added
     * @param dir   the item's direction
     * @param value the item's value, to be submitted if it is part of a
     *              {@link FormPanel}; cannot be <code>null</code>
     */
    public void addItem(T item, Direction dir, String value) {
        values.add(item);
        listBox.addItem(value, dir, keyFactory.generateKey(item));
        reinitialize();
    }

    /**
     * Inserts an item into the list box. Has the same effect as
     * <p>
     * <pre>
     * insertItem(item, item, index)
     * </pre>
     *
     * @param item  the text of the item to be inserted
     * @param index the index at which to insert it
     */
    public void insertItem(T item, int index) {
        values.add(index, item);
        listBox.insertItem(keyFactory.generateKey(item), index);
        reinitialize();
    }

    /**
     * Inserts an item into the list box, specifying its direction. Has the same
     * effect as
     * <p>
     * <pre>
     * insertItem(item, dir, item, index)
     * </pre>
     *
     * @param item  the text of the item to be inserted
     * @param dir   the item's direction
     * @param index the index at which to insert it
     */
    public void insertItem(T item, Direction dir, int index) {
        values.add(index, item);
        listBox.insertItem(keyFactory.generateKey(item), dir, index);
        reinitialize();
    }

    /**
     * Inserts an item into the list box, specifying an initial value for the
     * item. Has the same effect as
     * <p>
     * <pre>
     * insertItem(item, null, value, index)
     * </pre>
     *
     * @param item  the text of the item to be inserted
     * @param value the item's value, to be submitted if it is part of a
     *              {@link FormPanel}.
     * @param index the index at which to insert it
     */
    public void insertItem(T item, String value, int index) {
        values.add(index, item);
        listBox.insertItem(value, keyFactory.generateKey(item), index);
        reinitialize();
    }

    /**
     * Sets whether an individual list item is selected.
     *
     * @param index    the index of the item to be selected or unselected
     * @param selected <code>true</code> to select the item
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void setItemSelected(int index, boolean selected) {
        listBox.setItemSelected(index, selected);
        reinitialize();
    }

    /**
     * Sets the text associated with the item at a given index.
     *
     * @param index the index of the item to be set
     * @param text  the item's new text
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void setItemText(int index, String text) {
        listBox.setItemText(index, text);
        reinitialize();
    }

    /**
     * Sets the text associated with the item at a given index.
     *
     * @param index the index of the item to be set
     * @param text  the item's new text
     * @param dir   the item's direction.
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void setItemText(int index, String text, Direction dir) {
        listBox.setItemText(index, text, dir);
        reinitialize();
    }

    public void setName(String name) {
        listBox.setName(name);
        reinitialize();
    }

    /**
     * Sets the currently selected index.
     * <p>
     * After calling this method, only the specified item in the list will
     * remain selected. For a ListBox with multiple selection enabled, see
     * {@link #setItemSelected(int, boolean)} to select multiple items at a
     * time.
     *
     * @param index the index of the item to be selected
     */
    public void setSelectedIndex(int index) {
        listBox.setSelectedIndex(index);
        reinitialize();
    }

    /**
     * Sets the number of items that are visible. If only one item is visible,
     * then the box will be displayed as a drop-down list.
     *
     * @param visibleItems the visible item count
     */
    public void setVisibleItemCount(int visibleItems) {
        listBox.setVisibleItemCount(visibleItems);
        reinitialize();
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
     * @param index the index of the item whose text is to be retrieved
     * @return the text associated with the item
     * @throws IndexOutOfBoundsException if the index is out of range
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
     * @param index the index of the item to be retrieved
     * @return the item's associated value
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T getValue(int index) {
        return values.get(index);
    }

    /**
     * Gets the value for currently selected item. If multiple items are
     * selected, this method will return the value of the first selected item.
     *
     * @return the value for selected item, or {@code null} if none is selected
     */
    public T getSelectedValue() {
        try {
            return values.get(getSelectedIndex());
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
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
     * @param index the index of the item to be tested
     * @return <code>true</code> if the item is selected
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public boolean isItemSelected(int index) {
        return listBox.isItemSelected(index);
    }

    /**
     * Removes the item at the specified index.
     *
     * @param index the index of the item to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void removeItem(int index) {
        values.remove(index);
        listBox.removeItem(index);
        reinitialize();
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
     * <p>
     * After calling this method, only the specified item in the list will
     * remain selected. For a ListBox with multiple selection enabled, see
     * {@link #setValueSelected(String, boolean)} to select multiple items at a
     * time.
     *
     * @param value the value of the item to be selected
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
     * @param value the value of the item to be found
     * @return the index of the value
     */
    public int getIndex(String value) {
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            if (getValue(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets whether an individual list value is selected.
     *
     * @param value    the value of the item to be selected or unselected
     * @param selected <code>true</code> to select the item
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
     * @param value the value to be removed from the list
     */
    public void removeValue(String value) {
        int idx = getIndex(value);
        if (idx >= 0) {
            removeItem(idx);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        listBox.setEnabled(enabled);
        reinitialize();
    }

    @Override
    public boolean isEnabled() {
        return listBox.isEnabled();
    }

    /**
     * Use your own key factory for value keys.
     */
    public void setKeyFactory(KeyFactory<T, String> keyFactory) {
        this.keyFactory = keyFactory;
    }

    public ReadOnlyMixin<MaterialListValueBox<T>, ListBox> getReadOnlyMixin() {
        if (readOnlyMixin == null) {
            readOnlyMixin = new ReadOnlyMixin<>(this, listBox);
        }
        return readOnlyMixin;
    }

    @Override
    public void setReadOnly(boolean value) {
        getReadOnlyMixin().setReadOnly(value);
        if (!value) {
            $(listBox.getElement()).material_select("destroy");
            $(listBox.getElement()).material_select();
        }
    }

    @Override
    public boolean isReadOnly() {
        return getReadOnlyMixin().isReadOnly();
    }

    @Override
    public void setToggleReadOnly(boolean toggle) {
        getReadOnlyMixin().setToggleReadOnly(toggle);
    }

    @Override
    public boolean isToggleReadOnly() {
        return getReadOnlyMixin().isToggleReadOnly();
    }

    public ListBox getListBox() {
        return listBox;
    }

    @Override
    public ErrorMixin<AbstractValueWidget, MaterialLabel> getErrorMixin() {
        MaterialWidget target = new MaterialWidget($(getElement()).find(".select-dropdown"));
        return new ErrorMixin<>(this, lblError, target, lblName);
    }
}