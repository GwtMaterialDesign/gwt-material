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


import gwt.material.design.client.custom.CustomAnchor;
import gwt.material.design.client.custom.HasHref;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MaterialNavBrand extends CustomAnchor implements HasText, HasWidgets, HasHref {

	/**
	 * Material NavBrand is a component wherein you can pass a text / logo branding of your app
	 */
	@UiConstructor
	public MaterialNavBrand() {
		this.addStyleName("brand-logo");
	}

	/* (non-Javadoc)
	 * @see gwt.material.design.client.custom.CustomAnchor#add(com.google.gwt.user.client.ui.Widget)
	 */
	@Override
	public void add(Widget w) {
		// TODO Auto-generated method stub
		super.add(w);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		super.add(new HTML("" + text));
	}

	@Override
	public void setHref(String href) {
		this.getElement().setAttribute("href", href);
	}

	public void setAlign(String align){
		addStyleName(align);
	}
	
}
