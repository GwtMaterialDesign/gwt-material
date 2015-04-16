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

import gwt.material.design.client.custom.CustomCheckBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSwitch extends Composite implements HasChangeHandlers {

	private static MaterialSwitchUiBinder uiBinder = GWT.create(MaterialSwitchUiBinder.class);

	interface MaterialSwitchUiBinder extends UiBinder<Widget, MaterialSwitch> {
	}
	
	@UiField CustomCheckBox cbBox;
	private Boolean value = false;
	private boolean disabled;
	public MaterialSwitch() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		cbBox.getElement().setId("switch");
	}

	public Boolean getValue() {
		if(value){
			value = false;
			return false;
		}
		else {
			value = true;
			return true	;
		}
	}
	
	/*public native String getCheckBoxValue()-{
		return $wnd.jQuery('#switch').val();
	}-;*/

	public void setValue(Boolean value) {
		this.value = value;
		if(value){
			cbBox.getElement().setAttribute("checked", ""+value);
		}else{
			cbBox.getElement().removeAttribute("checked");
		}
	}


	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		// TODO Auto-generated method stub
		return addDomHandler(handler, ChangeEvent.getType());
	}


	public boolean isDisabled() {
		return disabled;
	}


	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		cbBox.getElement().setAttribute("disabled", "true");
	}

	
	
}
