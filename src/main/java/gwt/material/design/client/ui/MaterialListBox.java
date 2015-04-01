package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 GwtMaterial
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

import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ListBox;

@SuppressWarnings("deprecation")
public class MaterialListBox extends ListBox{

	private boolean old = false;
	private boolean disabled = false;
	
	private String textColor = "";
	
	public MaterialListBox() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		onInitMaterialDesign();
		
	}
	
	public static native void onInitMaterialDesign()/*-{
		$wnd.jQuery(document).ready(function(){
		  $wnd.jQuery('select').material_select();
		});
	}-*/;

	public boolean isOld() {
		return old;
	}

	public void setOld(boolean old) {
		this.old = old;
		if(old){
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

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		// TODO Auto-generated method stub
		return super.addChangeHandler(handler);
	}

	@Override
	public void addChangeListener(ChangeListener listener) {
		// TODO Auto-generated method stub
		super.addChangeListener(listener);
	}

	@Override
	public void addItem(String item) {
		// TODO Auto-generated method stub
		super.addItem(item);
	}

	@Override
	public void addItem(String item, Direction dir) {
		// TODO Auto-generated method stub
		super.addItem(item, dir);
	}

	@Override
	public void addItem(String item, String value) {
		// TODO Auto-generated method stub
		super.addItem(item, value);
	}

	@Override
	public void addItem(String item, Direction dir, String value) {
		// TODO Auto-generated method stub
		super.addItem(item, dir, value);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}

	@Override
	public DirectionEstimator getDirectionEstimator() {
		// TODO Auto-generated method stub
		return super.getDirectionEstimator();
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return super.getItemCount();
	}

	@Override
	public String getItemText(int index) {
		// TODO Auto-generated method stub
		return super.getItemText(index);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public int getSelectedIndex() {
		// TODO Auto-generated method stub
		return super.getSelectedIndex();
	}

	@Override
	public String getValue(int index) {
		// TODO Auto-generated method stub
		return super.getValue(index);
	}

	@Override
	public int getVisibleItemCount() {
		// TODO Auto-generated method stub
		return super.getVisibleItemCount();
	}

	@Override
	public void insertItem(String item, int index) {
		// TODO Auto-generated method stub
		super.insertItem(item, index);
	}

	@Override
	public void insertItem(String item, Direction dir, int index) {
		// TODO Auto-generated method stub
		super.insertItem(item, dir, index);
	}

	@Override
	public void insertItem(String item, String value, int index) {
		// TODO Auto-generated method stub
		super.insertItem(item, value, index);
	}

	@Override
	public void insertItem(String item, Direction dir, String value, int index) {
		// TODO Auto-generated method stub
		super.insertItem(item, dir, value, index);
	}

	@Override
	public boolean isItemSelected(int index) {
		// TODO Auto-generated method stub
		return super.isItemSelected(index);
	}

	@Override
	public boolean isMultipleSelect() {
		// TODO Auto-generated method stub
		return super.isMultipleSelect();
	}

	@Override
	public void removeChangeListener(ChangeListener listener) {
		// TODO Auto-generated method stub
		super.removeChangeListener(listener);
	}

	@Override
	public void removeItem(int index) {
		// TODO Auto-generated method stub
		super.removeItem(index);
	}

	@Override
	public void setDirectionEstimator(boolean enabled) {
		// TODO Auto-generated method stub
		super.setDirectionEstimator(enabled);
	}

	@Override
	public void setDirectionEstimator(DirectionEstimator directionEstimator) {
		// TODO Auto-generated method stub
		super.setDirectionEstimator(directionEstimator);
	}

	@Override
	public void setItemSelected(int index, boolean selected) {
		// TODO Auto-generated method stub
		super.setItemSelected(index, selected);
	}

	@Override
	public void setItemText(int index, String text) {
		// TODO Auto-generated method stub
		super.setItemText(index, text);
	}

	@Override
	public void setItemText(int index, String text, Direction dir) {
		// TODO Auto-generated method stub
		super.setItemText(index, text, dir);
	}

	@Override
	public void setMultipleSelect(boolean multiple) {
		// TODO Auto-generated method stub
		super.setMultipleSelect(multiple);
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	@Override
	public void setSelectedIndex(int index) {
		// TODO Auto-generated method stub
		super.setSelectedIndex(index);
	}

	@Override
	public void setValue(int index, String value) {
		// TODO Auto-generated method stub
		super.setValue(index, value);
	}

	@Override
	public void setVisibleItemCount(int visibleItems) {
		// TODO Auto-generated method stub
		super.setVisibleItemCount(visibleItems);
	}

	@Override
	protected String getOptionText(OptionElement option) {
		// TODO Auto-generated method stub
		return super.getOptionText(option);
	}

	@Override
	protected void onEnsureDebugId(String baseID) {
		// TODO Auto-generated method stub
		super.onEnsureDebugId(baseID);
	}

	@Override
	protected void setOptionText(OptionElement option, String text, Direction dir) {
		// TODO Auto-generated method stub
		super.setOptionText(option, text, dir);
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		if(disabled){
			this.getElement().setAttribute("disabled", "true");
		}
	}
	
	
	
}
