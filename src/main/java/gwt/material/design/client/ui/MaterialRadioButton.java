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

import gwt.material.design.client.custom.HasGrid;

import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RadioButton;

public class MaterialRadioButton extends RadioButton implements HasGrid{

	private String type ="";
	private boolean disabled;
	
	public MaterialRadioButton() {
		// TODO Auto-generated constructor stub
		super("");
	}

	public MaterialRadioButton(String name, SafeHtml label, Direction dir) {
		super(name, label, dir);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, SafeHtml label, DirectionEstimator directionEstimator) {
		super(name, label, directionEstimator);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, SafeHtml label) {
		super(name, label);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, String label, boolean asHTML) {
		super(name, label, asHTML);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, String label, Direction dir) {
		super(name, label, dir);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, String label, DirectionEstimator directionEstimator) {
		super(name, label, directionEstimator);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, String label) {
		super(name, label);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name) {
		super(name);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(type.equals("gap")){
			Element e_cb = this.getElement(); 
	        Element e_input = DOM.getChild(e_cb, 0); 
	        e_input.setAttribute("class", "with-gap");
		}
		
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		setEnabled(!disabled);
	}

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}
	

	
}
