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
import gwt.material.design.client.custom.CustomNav;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class MaterialNavBar extends Composite {

	private static MaterialNavBarUiBinder uiBinder = GWT
			.create(MaterialNavBarUiBinder.class);

	interface MaterialNavBarUiBinder extends UiBinder<Widget, MaterialNavBar> {
	}

	@UiField
	HTMLPanel navBar;
	@UiField
	CustomAnchor anchor, navMenu;
	@UiField
	MaterialLink lblLogo;
	@UiField
	CustomNav customNav;
	
	private String color = "";
	private String type = "";
	private String align = "";
	private String wave = "";
	private String sideBar = "";

	public MaterialNavBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	UnorderedList navigation, mobileNav;
	@UiField
	HTMLPanel wrapper;
	@UiField
	Image imgLogo;
	
	private String logoUrl;
	private ImageResource logoResource;
	private String text;

	@Override
	protected void onAttach() {
		super.onAttach();
		String name = String.valueOf(hashCode());
		navMenu.getElement().setAttribute("data-activates", name);
		mobileNav.getElement().setId(name);
		initNavBar();
		
	}

	@UiChild(tagname = "nav")
	public void addWidget(final Widget item) {
		navigation.add(new ListItem(item));
	}
	
	@UiChild(tagname = "sidebaritem")
	public void addWidgetSideNav(final Widget item) {
		mobileNav.add(new ListItem(item));
	}

	
	public static native void initNavBar()/*-{
		$wnd.jQuery(".button-collapse").sideNav();
	}-*/;

	public static native void hideNav()/*-{
		$wnd.jQuery(".button-collapse").sideNav('hide');
	}-*/;
	
	public void showSideBar(){
		onShowSideBar();
	}
	
	public static native void onShowSideBar()/*-{
		$wnd.jQuery(".button-collapse").sideNav('show');
	}-*/;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		customNav.addStyleName(color);
	}

	public ImageResource getLogoResource() {
		return logoResource;
	}

	public void setLogoResource(ImageResource logoResource) {
		this.logoResource = logoResource;
		imgLogo.setResource(logoResource);
		lblLogo.removeFromParent();
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
		imgLogo.setUrl(logoUrl);
		lblLogo.removeFromParent();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		imgLogo.removeFromParent();
		lblLogo.setText(text);
		
	}

	public void hide() {
		hideNav();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		switch (type) {
		case "fixed":
			navBar.addStyleName("navbar-" + type);
			break;

		default:
			navBar.addStyleName("navbar-fixed");
			anchor.addStyleName("left");
			navigation.addStyleName("right");
			break;
		}

	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
		switch (align) {

		case "right":
			anchor.addStyleName("left");
			navigation.addStyleName("right");
			break;
		case "left":
			anchor.addStyleName("right");
			navigation.addStyleName("left");
			break;
		default:
			navBar.addStyleName("navbar-fixed");
			anchor.addStyleName("left");
			navigation.addStyleName("right");
			break;
		}
	}

	public String getWave() {
		return wave;
	}

	public void setWave(String wave) {
		this.wave = wave;
		for(Widget w : navigation){
			if(w instanceof ListItem){
				w.addStyleName("waves-effect waves-" + wave);
			}
		}
		for(Widget w : mobileNav){
			if(w instanceof ListItem){
				w.addStyleName("waves-effect waves-" + wave);
				w.getElement().getStyle().setDisplay(Display.BLOCK);
			}
		}
	}

	public String getSideBar() {
		return sideBar;
	}

	public void setSideBar(String sideBar) {
		this.sideBar = sideBar;
		mobileNav.addStyleName(sideBar);
	}

	
}
