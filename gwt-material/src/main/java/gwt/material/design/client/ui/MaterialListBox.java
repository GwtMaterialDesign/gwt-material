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
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import gwt.material.design.client.base.HasColors;
import gwt.material.design.client.base.HasGrid;
import gwt.material.design.client.base.HasId;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ColorsMixin;
import gwt.material.design.client.base.mixin.GridMixin;
import gwt.material.design.client.base.mixin.IdMixin;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.ui.html.Label;

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
public class MaterialListBox extends MaterialWidget implements HasId, HasGrid, HasColors {

    private final ListBox listBox = new ListBox();
    private final Label lblName = new Label();

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
            initializeMaterial(listBox.getElement());
        }
    }

    public void setPlaceholder(String placeholder) {
        lblName.setText(placeholder);

        if (initialized && placeholder != null) {
            initializeMaterial(listBox.getElement());
        }
    }

    public OptionElement getOptionElement(int index) {
        return  getSelectElement().getOptions().getItem(index);
    }


    public void insertItem(String item, Direction dir, String value, int index) {
        listBox.insertItem(item, dir, value, index);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    @Override
    public void clear() {
        listBox.clear();
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }


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

    public void addItem(String item, Direction dir) {
        listBox.addItem(item, dir);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }


    public void addItem(String item, String value) {
        listBox.addItem(item, value);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    public void addItem(String item, Direction dir, String value) {
        listBox.addItem(item, dir, value);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    public void insertItem(String item, int index) {
        listBox.insertItem(item, index);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    public void insertItem(String item, Direction dir, int index) {
        listBox.insertItem(item, dir, index);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    public void insertItem(String item, String value, int index) {
        listBox.insertItem(item, value, index);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    public void setItemSelected(int index, boolean selected) {
        listBox.setItemSelected(index, selected);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    public void setItemText(int index, String text) {
        listBox.setItemText(index, text);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

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

    public void setSelectedIndex(int index) {
        listBox.setSelectedIndex(index);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    public void setVisibleItemCount(int visibleItems) {
        listBox.setVisibleItemCount(visibleItems);
        if (initialized) {
            // reinitialize
            initializeMaterial(listBox.getElement());
        }
    }

    protected SelectElement getSelectElement() {
        return listBox.getElement().cast();
    }

    private void onChangeInternal() {
        Document.get().createChangeEvent();
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
    protected native void initializeMaterial(Element element) /*-{
        $wnd.jQuery(element).material_select();
    }-*/;

    public void setMultipleSelect(boolean multipleSelect) {
        listBox.setMultipleSelect(multipleSelect);
        if (initialized) {
            initializeMaterial(listBox.getElement());
        }
    }

    public boolean isMultipleSelect() {
        return listBox.isMultipleSelect();
    }

    public void setEmptyPlaceHolder(String value) {
        listBox.insertItem(value, 0);
        listBox.setItemSelected(0, true);
        getOptionElement(0).setDisabled(true);

        if (initialized) {
            initializeMaterial(listBox.getElement());
        }
    }
}
