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
import gwt.material.design.client.custom.HasError;
import gwt.material.design.client.custom.HasGrid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTextArea extends Composite implements HasText, HasKeyPressHandlers,
		HasKeyDownHandlers, HasKeyUpHandlers, HasChangeHandlers, HasGrid, HasError {

	private static UiBinder uiBinder = GWT.create(UiBinder.class);

	interface UiBinder extends com.google.gwt.uibinder.client.UiBinder<Widget, MaterialTextArea> {
	}

	private String placeholder;
	private String type = "text";
	private String icon = "";
	private boolean isValid = true;
	private String length;
	private MaterialLabel lblError = new MaterialLabel();
	
	@UiField CustomLabel customLabel;
	@UiField Label lblName;
	@UiField TextArea txtBox;
	@UiField CustomIcon iconPanel;
	@UiField HTMLPanel panel;

	public MaterialTextArea() {
		initWidget(uiBinder.createAndBindUi(this));
		lblError.setVisible(false);
		panel.add(lblError);
	}

	public void setInvalid() {
		backToDefault();
		lblName.setStyleName("red-text");
		txtBox.getElement().addClassName("invalid");
		isValid = false;
	}

	public void setValid() {
		backToDefault();
		lblName.setStyleName("green-text");
		txtBox.getElement().addClassName("valid");
		isValid = true;
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
	
	@Override
	protected void onAttach() {
		super.onAttach();
		customLabel.getElement().setAttribute("for", "field");
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

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
		txtBox.getElement().setAttribute("length", length);
	}

	@Override
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return addDomHandler(handler, KeyDownEvent.getType());
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return addDomHandler(handler, KeyUpEvent.getType());
	}

	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return addDomHandler(handler, KeyPressEvent.getType());
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	@Override
	public void setError(String error) {
		lblError.setText(error);
		lblError.addStyleName("field-error-label");
		lblError.removeStyleName("field-success-label");
		lblError.setVisible(true);
		setInvalid();
	}

	@Override
	public void setSuccess(String success) {
		lblError.setText(success);
		lblError.addStyleName("field-success-label");
		lblError.removeStyleName("field-error-label");
		lblError.setVisible(true);
		setValid();
	}

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}
	
	@Override
	public void setOffset(String offset) {
		String cssName = "";
		for(String val : offset.split(" ")){
			cssName = cssName + " offset-" +  val;
		}
		this.addStyleName(cssName);
	}
}
