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
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTextBox extends AbstractMaterialTextBoxBase {

	private static MaterialTextBoxUiBinder uiBinder = GWT.create(MaterialTextBoxUiBinder.class);

	interface MaterialTextBoxUiBinder extends UiBinder<Widget, MaterialTextBox> {
	}

	@UiField
	protected CustomLabel customLabel;
	@UiField
	protected Label lblName;
	@UiField
	protected TextBox txtBox;
	@UiField
	protected CustomIcon iconPanel;

	@Override
	Widget getContentWidget() {
		return uiBinder.createAndBindUi(this);
	}

	@Override
	CustomLabel customLabel() {
		return customLabel;
	}

	@Override
	Label label() {
		return lblName;
	}

	@Override
	TextBoxBase textBox() {
		return txtBox;
	}

	@Override
	CustomIcon iconPanel() {
		return iconPanel;
	}

	@Override
	public void setType(String type) {
		super.setType(type);
		if (type.equals("number")) {
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
}
