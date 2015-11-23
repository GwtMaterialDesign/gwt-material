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

import com.google.gwt.dom.client.Document;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import gwt.material.design.client.base.HasColors;
import gwt.material.design.client.base.HasGrid;
import gwt.material.design.client.base.HasId;
import gwt.material.design.client.base.mixin.ColorsMixin;
import gwt.material.design.client.base.mixin.GridMixin;
import gwt.material.design.client.base.mixin.IdMixin;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;

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
 *    lstBox.addChangeHandler(handler);
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material ListBoxt</a>
 */
//@formatter:on
public class MaterialListBox extends ListBox implements HasId, HasGrid, HasColors {

    private final IdMixin<MaterialListBox> idMixin = new IdMixin<>(this);
    private final GridMixin<MaterialListBox> gridMixin = new GridMixin<>(this);
    private final ColorsMixin<MaterialListBox> colorsMixin = new ColorsMixin<>(this);
    private final ToggleStyleMixin<MaterialListBox> oldMixin = new ToggleStyleMixin<>(this, "browser-default input-field");

    public MaterialListBox() {
        setId(DOM.createUniqueId());
    }

    @Override
    public void setId(String id) {
        idMixin.setId(id);
    }

    @Override
    public String getId() {
        return idMixin.getId();
    }

    @Override
    public void setGrid(String grid) {
        gridMixin.setGrid(grid);
    }

    @Override
    public void setOffset(String offset) {
        gridMixin.setOffset(offset);
    }

    public boolean isOld() {
        return oldMixin.isOn();
    }

    public void setOld(boolean old) {
        oldMixin.setOn(old);
    }

    @Override
    public void setBackgroundColor(String bgColor) {
        colorsMixin.setBackgroundColor(bgColor);
    }

    @Override
    public String getBackgroundColor() {
        return colorsMixin.getBackgroundColor();
    }

    @Override
    public String getTextColor() {
        return colorsMixin.getTextColor();
    }

    @Override
    public void setTextColor(String textColor) {
        colorsMixin.setTextColor(textColor);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isOld()) {
            createInternalChangeHandler(getId());
        }
        initializeMaterial();
    }

    @Override
    public void insertItem(String item, Direction dir, String value, int index) {
        super.insertItem(item, dir, value, index);
        initializeMaterial();
    }

    @Override
    public void clear() {
        super.clear();
        initializeMaterial();
    }

    @Override
    public void setValue(int index, String value) {
        super.setValue(index, value);
        initializeMaterial();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        initializeMaterial();
    }

    @Override
    public void addItem(String item, Direction dir) {
        super.addItem(item, dir);
        initializeMaterial();
    }

    @Override
    public void addItem(String item, String value) {
        super.addItem(item, value);
        initializeMaterial();
    }

    @Override
    public void addItem(String item, Direction dir, String value) {
        super.addItem(item, dir, value);
        initializeMaterial();
    }

    @Override
    public void insertItem(String item, int index) {
        super.insertItem(item, index);
        initializeMaterial();
    }

    @Override
    public void insertItem(String item, Direction dir, int index) {
        super.insertItem(item, dir, index);
        initializeMaterial();
    }

    @Override
    public void insertItem(String item, String value, int index) {
        super.insertItem(item, value, index);
        initializeMaterial();
    }

    @Override
    public void setItemSelected(int index, boolean selected) {
        super.setItemSelected(index, selected);
        initializeMaterial();
    }

    @Override
    public void setItemText(int index, String text) {
        super.setItemText(index, text);
        initializeMaterial();
    }

    @Override
    public void setItemText(int index, String text, Direction dir) {
        super.setItemText(index, text, dir);
        initializeMaterial();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        initializeMaterial();
    }

    @Override
    public void setSelectedIndex(int index) {
        super.setSelectedIndex(index);
        initializeMaterial();
    }

    @Override
    public void setVisibleItemCount(int visibleItems) {
        super.setVisibleItemCount(visibleItems);
        initializeMaterial();
    }

    private void onChangeInternal() {
        Document.get().createChangeEvent();
    }

    public void initializeMaterial() {
        initializeMaterial(getId());
    }

    /**
     * Creates the internal change handler needed to trigger change events for
     * Materialize CSS change events.
     */
    protected native void createInternalChangeHandler(String id) /*-{
        var that = this;
        var callback = $entry(function() {
            that.@gwt.material.design.client.ui.MaterialListBox::onChangeInternal()();
        });

        $wnd.jQuery('#' + id).change(callback);
    }-*/;

    /**
     * Initializes the Materialize CSS list box. Should be
     * called every time the contents of the list box
     * changes, to keep the Materialize CSS design updated.
     */
    protected native void initializeMaterial(String id) /*-{
        $wnd.jQuery(document).ready(function() {
            $wnd.jQuery('#' + id).material_select();
        })
    }-*/;
}
