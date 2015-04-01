package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 GwtMaterial
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

import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class MaterialBadge  extends ComplexPanel {
	
	private String type;
	private String text;
	private String color;
	private Label label;
	
	public MaterialBadge() {
		setElement(DOM.createElement("SPAN"));
		this.addStyleName("badge");
		this.addStyleName(MaterialResources.INSTANCE.materialcss().badge());
	}

	@UiChild(tagname = "child")
	public void addWidget(final Widget item) {
		add(item);
	}
	
	public void add(Widget w) {
		super.add(w, getElement());
	}

	public void insert(Widget w, int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.addStyleName(type);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.label = new Label(text);
		add(label);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		this.addStyleName(color);
	}

}