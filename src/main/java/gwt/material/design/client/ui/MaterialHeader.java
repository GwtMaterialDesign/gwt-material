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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.MaterialWidget;

public class MaterialHeader extends MaterialWidget {

	private static MaterialHeaderUiBinder uiBinder = GWT.create(MaterialHeaderUiBinder.class);

	interface MaterialHeaderUiBinder extends UiBinder<Widget, MaterialHeader> {
	}
	
	@UiField Image logo;
	@UiField HTMLPanel navigation, panel;

	private ImageResource logoResource;

	public MaterialHeader() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiChild( tagname = "item" )
	public void addWidget(Widget item) {
		navigation.add(item);
	} 

	public void addNavItem(Widget w){
		navigation.add(w);
	}

	public String getColor() {
		return panel.getElement().getStyle().getColor();
	}

	public void setColor(String textColor) {
		panel.getElement().getStyle().setColor(textColor);
	}

	public ImageResource getLogoResource() {
		return logoResource;
	}

	public void setLogoResource(ImageResource logoResource) {
		this.logoResource = logoResource;
		logo.setResource(logoResource);
	}

	public String getUrl() {
		return logo.getUrl();
	}

	public void setUrl(String url) {
		logo.setUrl(url);
	}

	public Image getLogo() {
		return logo;
	}
}
