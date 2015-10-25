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
import gwt.material.design.client.custom.HasHref;

import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;

public class MaterialNavBrand extends ComplexWidget implements HasText, HasHref {

	private String text;
	private HTML html = new HTML("");
	/**
	 * Material NavBrand is a component wherein you can pass a text / logo branding of your app
	 */
	@UiConstructor
	public MaterialNavBrand() {
		setElement(Document.get().createElement("a"));
		this.addStyleName("brand-logo");
	}

	@Override
	public void setText(String text) {
		this.text = text;
		html.setHTML(text);
		super.add(html);
	}

	@Override
	public void setHref(String href) {
		this.getElement().setAttribute("href", href);
	}

	public void setAlign(String align){
		addStyleName(align);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

	@Override
	public void setTarget(String target) {
		getElement().setAttribute("target", target);
	}
	
}
