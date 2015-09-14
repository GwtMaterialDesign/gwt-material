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
import gwt.material.design.client.custom.CustomHeader;
import gwt.material.design.client.custom.CustomNav;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
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
	CustomHeader navBar;
	@UiField
	CustomAnchor anchor, navMenu;
	@UiField
	MaterialLink lblLogo;
	@UiField
	CustomNav customNav;
	
	private String color = "";
	private String textColor = "";
	private String type = "";
	private String align = "";
	private String wave = "";
	private String sideBar = "";
	private String sideBarWidth = "";

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
	private String sideNav = String.valueOf(hashCode());
	private MaterialProgress progress = new MaterialProgress();
	
	@Override
	protected void onAttach() {
		super.onAttach();
		String name = String.valueOf(hashCode());
		navMenu.addStyleName(sideNav);
		navMenu.getElement().setAttribute("data-activates", name);
		mobileNav.getElement().setId(name);
		initNavBar(Integer.parseInt(getSideBarWidth()), sideNav);
	}
	
	

	@UiChild(tagname = "nav")
	public void addWidget(final Widget item) {
		navigation.add(new ListItem(item));
	}
	
	@UiChild(tagname = "sidebaritem")
	public void addWidgetSideNav(final Widget item) {
		ListItem listItem = new ListItem(item);
		if(item instanceof MaterialCollapsible){
			listItem.getElement().getStyle().setBackgroundColor("transparent");
		}
		
		mobileNav.add(listItem);
	}

	/**
	 * Adding progress indicator on the Nav Bar
	 * @param isProgress True to show the progress indicator False otherwise
	 */
	public void showProgress(boolean isProgress){
		if(isProgress){
			customNav.add(progress);
		}else{
			progress.removeFromParent();
		}
	}
	
	public static native void initNavBar(int width, String sideNav)/*-{
		$wnd.jQuery("." + sideNav).sideNav({
				menuWidth: width
			}
		);
	}-*/;

	public static native void hideNav(String sideNav)/*-{
		$wnd.jQuery("." + sideNav).sideNav('hide');
	}-*/;
	
	public void showSideBar(){
		onShowSideBar(sideNav);
	}
	
	public static native void onShowSideBar(String sideNav)/*-{
		$wnd.jQuery("." + sideNav).sideNav('show');
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
		hideNav(sideNav);
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
		
		if(type.contains("no-padding")){
			wrapper.removeStyleName("container");
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
				ListItem item = (ListItem) w;
				for(Widget child : item){
					if(!(child instanceof MaterialCollapsible)){
						w.addStyleName("waves-effect waves-" + wave);
						w.getElement().getStyle().setDisplay(Display.BLOCK);
					}else{
						MaterialCollapsible col = (MaterialCollapsible) child;
						for(Widget colItem : col){
							if(colItem instanceof MaterialCollapsibleItem){
								((MaterialCollapsibleItem) colItem).getHeader().addStyleName("waves-effect waves-" + wave);
							}
						}
					}
					
				}
				
			}
		}
	}

	public String getSideBar() {
		return sideBar;
	}

	public void setSideBar(String sideBar) {
		this.sideBar = sideBar;
		mobileNav.addStyleName(sideBar);
		if(sideBar.equals("hidden"))
		{
			mobileNav.getElement().getStyle().setPaddingLeft(0, Unit.PX);
			navMenu.getElement().getStyle().setDisplay(Display.BLOCK);
		}
	}

	public String getSideBarWidth() {
		return sideBarWidth;
	}

	public void setSideBarWidth(String sideBarWidth) {
		this.sideBarWidth = sideBarWidth;
		mobileNav.setWidth(sideBarWidth + "px");
		if(getSideBar().equals("fixed")){
			navBar.getElement().getStyle().setPaddingLeft(Double.parseDouble(sideBarWidth), Unit.PX);
		}
	}

	public CustomHeader getNavBar() {
		return navBar;
	}

	public void setNavBar(CustomHeader navBar) {
		this.navBar = navBar;
	}

	public MaterialLink getNavBarTextLogo() {
	    return lblLogo;
	}
	
    public void setNavBarTextLogo(MaterialLink textLogo) {
        this.text = lblLogo.getText();
        this.lblLogo = textLogo;
        imgLogo.removeFromParent();
    }



	/**
	 * @return the textColor
	 */
	public String getTextColor() {
		return textColor;
	}



	/**
	 * @param textColor the textColor to set
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
		lblLogo.setTextColor(textColor);
	}
    
    
	
}
