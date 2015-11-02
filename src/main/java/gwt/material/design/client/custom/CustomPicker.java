package gwt.material.design.client.custom;

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

import gwt.material.design.client.ui.MaterialLabel;

import com.google.gwt.dom.client.Document;

public class CustomPicker extends ComplexWidget implements HasGrid, HasError {

	private MaterialLabel lblError = new MaterialLabel();
	
	public CustomPicker() {
		super(Document.get().createElement("input"));
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

	@Override
	public void setError(String error) {
		lblError.setText(error);
		lblError.addStyleName("field-error-label");
		lblError.removeStyleName("field-success-label");
		addStyleName("field-error-picker");
		removeStyleName("field-success-picker");
		lblError.setVisible(true);
	}

	@Override
	public void setSuccess(String success) {
		lblError.setText(success);
		lblError.addStyleName("field-success-label");
		lblError.removeStyleName("field-error-label");
		addStyleName("field-success-picker");
		removeStyleName("field-error-picker");
		lblError.setVisible(true);
	}
}
