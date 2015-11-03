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

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.html.Label;
import gwt.material.design.client.base.HasError;

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
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.base.HasPlaceholder;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ErrorMixin;

public class MaterialTextArea extends MaterialWidget implements HasText, HasKeyPressHandlers,
		HasKeyDownHandlers, HasKeyUpHandlers, HasChangeHandlers, HasError, HasIcon, HasPlaceholder {

	private static UiBinder uiBinder = GWT.create(UiBinder.class);

	interface UiBinder extends com.google.gwt.uibinder.client.UiBinder<Widget, MaterialTextArea> {
	}

	private String length;
	private String placeholder;
	private boolean isValid = true;

	private MaterialLabel lblError = new MaterialLabel();

	private final ErrorMixin<MaterialTextArea, MaterialLabel> errorMixin = new ErrorMixin<>(this, lblError, null);
	
	@UiField
	Label label;
	@UiField MaterialLabel lblName;
	@UiField TextArea txtBox;
	@UiField MaterialIcon icon;
	@UiField HTMLPanel panel;

	public MaterialTextArea() {
		initWidget(uiBinder.createAndBindUi(this));

		icon.setIconPrefix(true);
		txtBox.addStyleName("materialize-textarea");
		lblError.setVisible(false);
		panel.add(lblError);
	}

	public void setInvalid() {
		removeErrorModifiers();
		lblName.setStyleName("red-text");
		txtBox.getElement().addClassName("invalid");
		isValid = false;
	}

	public void setValid() {
		removeErrorModifiers();
		lblName.setStyleName("green-text");
		txtBox.getElement().addClassName("valid");
		isValid = true;
	}

	public void removeErrorModifiers() {
		txtBox.getElement().removeClassName("valid");
		txtBox.getElement().removeClassName("invalid");
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
	protected void onLoad() {
		super.onLoad();
		label.getElement().setAttribute("for", "field");
	}

	@Override
	public String getText() {
		return txtBox.getText();
	}

	@Override
	public void setText(String text) {
		txtBox.setText(text);
		label.addStyleName("active");
	}

	@Override
	public String getPlaceholder() {
		return placeholder;
	}

	@Override
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		lblName.setText(placeholder);
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
		errorMixin.setError(error);
		setInvalid();
	}

	@Override
	public void setSuccess(String success) {
		errorMixin.setSuccess(success);
		setValid();
	}

	@Override
	public MaterialIcon getIcon() {
		return icon;
	}

	@Override
	public void setIconType(IconType iconType) {
		icon.setIconType(iconType);
	}

	@Override
	public void setIconPosition(IconPosition position) {
		icon.setIconPosition(position);
	}

	@Override
	public void setIconSize(IconSize size) {
		icon.setIconSize(size);
	}

	@Override
	public void setIconFontSize(double size, Style.Unit unit) {
		icon.setIconFontSize(size, unit);
	}

	@Override
	public void setIconColor(String iconColor) {
		icon.setIconColor(iconColor);
	}

	@Override
	public void setIconPrefix(boolean prefix) {
		icon.setIconPrefix(prefix);
	}

	@Override
	public boolean isIconPrefix() {
		return icon.isIconPrefix();
	}
}
