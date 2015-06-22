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

import gwt.material.design.client.custom.CustomIcon;
import gwt.material.design.client.custom.CustomLabel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTextBox extends Composite implements HasText,HasKeyUpHandlers{

	private static MaterialTextBoxUiBinder uiBinder = GWT.create(MaterialTextBoxUiBinder.class);

	interface MaterialTextBoxUiBinder extends UiBinder<Widget, MaterialTextBox> {
	}

	private String placeholder;
	private String type = "text";
	private String icon = "";
	private boolean isValid = true;
	private boolean enabled;
	private String length;

	@UiField
	CustomLabel 
	customLabel;
	@UiField
	Label lblName;
	@UiField
	TextBox txtBox;
	@UiField
	CustomIcon iconPanel;

	public MaterialTextBox() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		String name = String.valueOf(hashCode());
		txtBox.getElement().setId(name);
		customLabel.getElement().setAttribute("for", name);
	}

	public void setInvalid() {
		backToDefault();
		txtBox.getElement().addClassName("invalid");
		isValid = false;
	}

	public void setValid() {
		backToDefault();
		txtBox.getElement().addClassName("valid");
		isValid = true;
	}
	
	/**
	 * Resets the textbox by removing its content and resetting visual state.
	 */
	public void clear() {
		txtBox.setText("");
		backToDefault();
		customLabel.removeStyleName("active");
	}

	public void backToDefault() {
		txtBox.getElement().removeClassName("valid");
		txtBox.getElement().removeClassName("invalid");
	}

	public String getText() {
		return txtBox.getText();
	}

	public void setText(String text) {
		txtBox.setText(text);
		customLabel.addStyleName("active");
	}

	public String getPlaceholder() {
		return placeholder;

	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		lblName.setText(placeholder);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		txtBox.getElement().setAttribute("type", type);
		if(type.equals("number")){
			txtBox.addKeyPressHandler(new KeyPressHandler() {
				
				@Override
				public void onKeyPress(KeyPressEvent event) {
					 if (!Character.isDigit(event.getCharCode()) 
			                    && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_TAB 
			                    && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_BACKSPACE
			                    && event.getNativeEvent().getKeyCode() != 190){
			                ((TextBox) event.getSource()).cancelKey();
			         }
				}
			});
		}
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		iconPanel.addStyleName(icon + " prefix");
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		txtBox.setEnabled(enabled);
		
	}



	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return addDomHandler(handler, KeyUpEvent.getType());
	}



	public String getLength() {
		return length;
	}



	public void setLength(String length) {
		this.length = length;
		txtBox.getElement().setAttribute("length", length);
	}
	
}
