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

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasDisabled;
import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasWidgets;
import gwt.material.design.client.custom.Waves;

public class MaterialTabItem extends ComplexWidget implements HasWidgets, HasColors, HasWaves, HasGrid, HasDisabled {

	public MaterialTabItem() {
		setElement(Document.get().createLIElement());
		setStyleName("tab");
	}
	
	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}

	@Override
	public void setOffset(String offset) {
		String cssName = "";
		for(String val : offset.split(" ")) {
			cssName = cssName + " offset-" +  val;
		}
		this.addStyleName(cssName);
	}

	@Override
	public void setWaves(String waves) {
		addStyleName("waves-"+waves + " waves-effect");
		Waves.detectAndApply(this);
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor+"-text");
	}

	@Override
	public void setDisabled(boolean disabled) {
		removeStyleName("disabled");
		if(disabled) {
			addStyleName("disabled");
		}
	}

	@Override
	public boolean isDisabled() {
		return getStyleName().contains("disabled");
	}
}
