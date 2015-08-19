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
import gwt.material.design.client.resources.Orientation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTimePicker extends Composite {

	private static MaterialTimePickerUiBinder uiBinder = GWT
			.create(MaterialTimePickerUiBinder.class);

	interface MaterialTimePickerUiBinder extends
			UiBinder<Widget, MaterialTimePicker> {
	}
	
	

	@UiField CustomInput inputElement;
	private String time;
	private String placeholder;
	private Orientation orientation = Orientation.PORTRAIT;
	
	public MaterialTimePicker() {
		initWidget(uiBinder.createAndBindUi(this));
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

	
	
}
