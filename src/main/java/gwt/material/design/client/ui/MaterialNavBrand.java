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
import gwt.material.design.client.custom.CustomDiv;
import gwt.material.design.client.custom.HasHref;

import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HasText;

public class MaterialNavBrand extends ComplexWidget implements HasText, HasHref {

	private CustomDiv div = new CustomDiv();

	/**
	 * Material NavBrand is a component wherein you can pass a text / logo branding of your app
	 */
	@UiConstructor
	public MaterialNavBrand() {
		super(Document.get().createElement("a"));
		this.addStyleName("brand-logo");

	}

	@Override
	public void setText(String text) {
		if(!div.isAttached()) {
			add(div);
		}
		div.getElement().setInnerText(text);
	}

	@Override
	public String getText() {
		return div.getElement().getInnerText();
	}

	@Override
	public String getHref() {
		return getElement().getAttribute("href");
	}

	@Override
	public void setHref(String href) {
		getElement().setAttribute("href", href);
	}

	@Override
	public String getTarget() {
		return getElement().getAttribute("target");
	}

	@Override
	public void setTarget(String target) {
		getElement().setAttribute("target", target);
	}
}
