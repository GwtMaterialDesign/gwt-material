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

import com.google.gwt.dom.client.Element;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasGrid;

import com.google.gwt.dom.client.Document;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ListBox;
import gwt.material.design.client.custom.HasId;
import gwt.material.design.client.custom.mixin.ColorsMixin;
import gwt.material.design.client.custom.mixin.GridMixin;
import gwt.material.design.client.custom.mixin.IdMixin;

//@formatter:off
/**
 * <p>Material ListBox is another dropdown component that will set / get the value depends on the selected index
 * <h3>UiBinder Usage:</h3>
 *
 * <pre>
 * {@code
 *	<m:MaterialListBox ui:field="lstBox" />
 * }
 * </pre>
 * <h3>Java Usage:</h3>
 *
 * <pre>
 * {@code
 * 	// functions
 *	lstBox.setSelectedIndex(2);
 *	lstBox.getSelectedIndex();
 *	lstBox.addChangeHandler(handler);
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material ListBoxt</a>
 */
//@formatter:on
public class MaterialListBox extends ListBox implements HasId, HasGrid, HasColors {

	private final IdMixin<MaterialListBox> idMixin = new IdMixin<>(this);
	private final GridMixin<MaterialListBox> gridMixin = new GridMixin<>(this);
	private final ColorsMixin<MaterialListBox> colorsMixin = new ColorsMixin<>(this);

	private boolean old = false;

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
		return old;
	}

	public void setOld(boolean old) {
		this.old = old;
		if (old) {
			this.addStyleName("browser-default input-field");
		}
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
		if (!old) {
			createInternalChangeHandler(getElement());
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
	
	private void onChangeInternal() {
		Document.get().createChangeEvent();
	}

	public void initializeMaterial() {
		initializeMaterial(getElement());
	}
	
	/**
	 * Creates the internal change handler needed to trigger change events for
	 * Materialize CSS change events.
	 */
	protected native void createInternalChangeHandler(Element e) /*-{
		var that = this;
		var callback = $entry(function() {
            that.@gwt.material.design.client.ui.MaterialListBox::onChangeInternal()();
		});
		
		$wnd.jQuery(e).change(callback);
	}-*/;
	
	/**
	 * Initializes the Materialize CSS list box. Should be
	 * called every time the contents of the list box
	 * changes, to keep the Materialize CSS design updated.
	 */
	protected native void initializeMaterial(Element e) /*-{
		$wnd.jQuery(document).ready(function(){
			$wnd.jQuery(e).material_select();
		})
	}-*/;
}
