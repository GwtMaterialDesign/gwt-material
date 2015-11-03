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

import gwt.material.design.client.constants.ImageType;
import gwt.material.design.client.custom.MaterialWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTopNav extends MaterialWidget {

	private static MaterialTopNavUiBinder uiBinder = GWT.create(MaterialTopNavUiBinder.class);

	interface MaterialTopNavUiBinder extends UiBinder<Widget, MaterialTopNav> {
	}

	@UiField Label lblTitle, lblDescription;
	@UiField HTMLPanel panel, customPanel;
	@UiField MaterialColumn menuPanel;

	private String type, url, fontSize = "";
	private ImageResource resource;
	
	private String profileImageUrl = "";
	private ImageResource profileImageResource;
	private String profileName = "";

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
		return lblTitle.getText();
	}

	public void setTitle(String title) {
		lblTitle.setText(title);
	}

	public String getDescription() {
		return lblDescription.getText();
	}

	public void setDescription(String description) {
		lblDescription.setText(description);
	}

	public String getColor() {
		return lblTitle.getElement().getStyle().getColor();
	}

	public void setColor(String textColor) {
		lblTitle.getElement().getStyle().setColor(textColor);
		lblDescription.getElement().getStyle().setColor(textColor);
	}

	@Override
	public void setPadding(double padding) {
		panel.getElement().getStyle().setPadding(padding, Unit.PCT);
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
	
	private void generateBackground(String url) {
		panel.addStyleName("fullBackground");
		panel.getElement().setAttribute("style", "background-image: url(" + url + "); background-size: 100%;" );
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		this.getElement().getStyle().setFontSize(Double.valueOf(fontSize), Unit.EM);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(type.equals("sidebar")){
			panel.addStyleName("sidebar");
		}
	}
	
	private void generateProfile(){
		panel.clear();
		MaterialImage profile = new MaterialImage();
		MaterialLink link = new MaterialLink(profileName);
		link.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		link.setTextColor("white");
		link.setText(profileName);
		profile.setType(ImageType.CIRCLE);
		profile.setUrl(profileImageUrl);
		profile.getElement().getStyle().setWidth(60, Unit.PX);
		profile.getElement().getStyle().setHeight(60, Unit.PX);
		panel.add(profile);
		panel.add(link);
		customPanel.getElement().getStyle().setMarginTop(20, Unit.PX);
		panel.add(customPanel);
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
		generateProfile();
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
		generateProfile();
	}

	/**
	 * @return the profileImageResource
	 */
	public ImageResource getProfileImageResource() {
		return profileImageResource;
	}

	/**
	 * @param profileImageResource the profileImageResource to set
	 */
	public void setProfileImageResource(ImageResource profileImageResource) {
		this.profileImageResource = profileImageResource;
	}
}
