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

import gwt.material.design.client.custom.HasGrid;

import com.google.gwt.dom.client.Document;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ListBox;

public class MaterialListBox extends ListBox implements HasGrid{

	private String id = "";
	private boolean old = false;
	private boolean disabled = false;
	private String textColor = "";

	public MaterialListBox() {
		id = DOM.createUniqueId();
		getElement().setId(id);
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

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		this.getElement().addClassName(textColor);
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		if (disabled) {
			this.getElement().setAttribute("disabled", "true");
		}
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		if (!old) {
			createInternalChangeHandler(id, this);
		}
		initializeMaterial(id);
	}

	@Override
	public void insertItem(String item, Direction dir, String value, int index) {
		super.insertItem(item, dir, value, index);
		initializeMaterial(id);
	}
	
	@Override
	public void clear() {
		super.clear();
		initializeMaterial(id);
	}
	
	private void onChangeInternal() {
		Document.get().createChangeEvent();
	}
	
	/**
	 * Creates the internal change handler needed to trigger change events for
	 * Materialize CSS change events.
	 * 
	 * @param id The ID of the internal {@code select} element.
	 * @param self The list box itself, used to trigger the callback.
	 */
	protected native void createInternalChangeHandler(String id, MaterialListBox self) /*-{
		var callback = $entry(function() {
			self.@gwt.material.design.client.ui.MaterialListBox::onChangeInternal()();
		});
		
		$wnd.jQuery('#' + id).change(callback);
	}-*/;
	
	/**
	 * Initializes the Materialize CSS list box. Should be
	 * called every time the contents of the list box
	 * changes, to keep the Materialize CSS design updated.
	 * 
	 * @param id The ID of the internal {@code select} element.
	 */
	protected native void initializeMaterial(String id) /*-{
		$wnd.jQuery( document ).ready(function(){
			$wnd.jQuery('#' + id).material_select();
		}) 
		
	}-*/;

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}
}
