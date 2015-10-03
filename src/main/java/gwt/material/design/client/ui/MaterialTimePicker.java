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


import gwt.material.design.client.custom.CustomInput;
import gwt.material.design.client.custom.HasError;
import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.resources.Orientation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTimePicker extends Composite implements HasGrid, HasError{

	private static MaterialTimePickerUiBinder uiBinder = GWT
			.create(MaterialTimePickerUiBinder.class);

	interface MaterialTimePickerUiBinder extends
			UiBinder<Widget, MaterialTimePicker> {
	}
	
	
	@UiField HTMLPanel panel;
	@UiField CustomInput inputElement;
	private String time;
	private String placeholder;
	private Orientation orientation = Orientation.PORTRAIT;
	private MaterialLabel lblError = new MaterialLabel();
	
	public MaterialTimePicker() {
		initWidget(uiBinder.createAndBindUi(this));
		lblError.setVisible(false);
		panel.add(lblError);
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		String genId = DOM.createUniqueId();
		inputElement.getElement().setAttribute("type", "text");
		inputElement.getElement().setAttribute("id", genId);
		initTimePicker(genId, getOrientation().getValue());
	}

	public native void initTimePicker(String id, String orientation)/*-{
		$wnd.jQuery('#' + id).lolliclock({autoclose:false, orientation: orientation});
		$wnd.jQuery('#' + id).blur();
	}-*/;

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
		inputElement.getElement().setAttribute("value", time.toUpperCase());
	}

	/**
	 * @return the placeholder
	 */
	public String getPlaceholder() {
		return placeholder;
	}

	/**
	 * @param placeholder the placeholder to set
	 */
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		inputElement.getElement().setAttribute("placeholder", "Time");
	}

	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation the orientation to set : can be Horizontal or Vertical
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	@Override
	public void setError(String error) {
		lblError.setText(error);
		lblError.addStyleName("field-error-label");
		lblError.removeStyleName("field-success-label");
		panel.addStyleName("field-error-picker");
		panel.removeStyleName("field-success-picker");
		lblError.setVisible(true);
	}

	@Override
	public void setSuccess(String success) {
		lblError.setText(success);
		lblError.addStyleName("field-success-label");
		lblError.removeStyleName("field-error-label");
		panel.addStyleName("field-success-picker");
		panel.removeStyleName("field-error-picker");
		lblError.setVisible(true);
	}

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}

	@Override
	public void setOffset(String offset) {
		String tobeadded = "";
		String[] vals = offset.split(" ");
		for(String val : vals){
			tobeadded = tobeadded + " offset-" +  val;
		}
		this.addStyleName(tobeadded);
	}
	
}
