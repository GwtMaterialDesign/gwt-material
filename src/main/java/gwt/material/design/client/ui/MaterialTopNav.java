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

import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTopNav extends Composite {

	private static MaterialTopNavUiBinder uiBinder = GWT.create(MaterialTopNavUiBinder.class);

	interface MaterialTopNavUiBinder extends UiBinder<Widget, MaterialTopNav> {
	}

	@UiField
	Label lblTitle, lblDescription;
	@UiField
	HTMLPanel panel, customPanel, menuPanel;

	private String title = "";
	private String description = "";
	private String color = "blue";
	private String textColor = "white";
	private String fontSize ;
	private String padding = "";
	private ImageResource resource;
	private String url;

	public MaterialTopNav() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiChild(tagname = "child")
	public void addWidget(Widget item) {
		customPanel.add(item);
	}

	@UiChild(tagname = "menu")
	public void addMenuItem(Widget item){
		menuPanel.add(item);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		lblTitle.setText(title);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		lblDescription.setText(description);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		panel.addStyleName(color);
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		lblTitle.getElement().getStyle().setColor(textColor);
		lblDescription.getElement().getStyle().setColor(textColor);
	}

	public String getPadding() {
		return padding;
	}

	public void setPadding(String padding) {
		this.padding = padding;
		panel.getElement().getStyle().setPadding(Double.parseDouble(padding), Unit.PCT);
		panel.getElement().getStyle().setPaddingBottom(200, Unit.PX);
	}

	public ImageResource getResource() {
		return resource;
	}

	@SuppressWarnings("deprecation")
	public void setResource(ImageResource resource) {
		this.resource = resource;
		generateBackground(resource.getURL());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		generateBackground(url);
	}
	
	private void generateBackground(String url){
		panel.addStyleName(MaterialResources.INSTANCE.materialcss().fullBackground());
		panel.getElement().setAttribute("style", "background-image: url(" + url + ");" );
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		this.getElement().getStyle().setFontSize(Double.valueOf(fontSize), Unit.EM);
	}

}
