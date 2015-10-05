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
import gwt.material.design.client.custom.HasType;
import gwt.material.design.client.custom.HasWaves;
import gwt.material.design.client.custom.SideNavType;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Widget;

/**
 * SideNav is a material component that gives you a lists of menus and other navigation components.
 * @param width
 * @param name
 */
public class MaterialSideNav extends ComplexWidget implements HasType, HasWaves{

	
	
	
	private String name;
	
	/**
	 * Container for App Toolbar and App Sidebar , contains Material Links, Icons or any other material components
	 */
	public MaterialSideNav() {
		setElement(Document.get().createULElement());
		setStyleName("side-nav");
	}
	
	/**
	 *  Creates a list and adds the given widgets.
	 */
	public MaterialSideNav(final Widget... widgets){
		this();
		for (final Widget w : widgets) {
			ListItem item = new ListItem(w);
			if(w instanceof MaterialImage){
				item.getElement().getStyle().setProperty("border", "1px solid #e9e9e9");
				item.getElement().getStyle().setProperty("textAlign", "center");
			}
			
            add(item);
        }
	}
	
	@UiConstructor
	public MaterialSideNav(int width, String name, boolean closeOnClick) {
		this();
		super.getElement().setId(name);
		getElement().getStyle().setWidth(width, Unit.PX);
		setName(name);
		initNavBar(width, name, closeOnClick);
	}
	
	
	private static native void initNavBar(int width, String name, boolean closeOnClick)/*-{
		$wnd.jQuery( document ).ready(function(){
			$wnd.jQuery(".button-collapse").sideNav({
				menuWidth: width,
				closeOnClick: closeOnClick
			});
		}) 
	}-*/;

	/**
	 * Set the type of the sideBar
	 * OPEN, CLOSE, MINI, CLIP 
	 */
	@Override
	public void setType(String type) {
		SideNavType type2 = SideNavType.fromString(type);
		switch (type2) {
		case OPEN:
			addStyleName("fixed open");
			break;
		case CLIP:
			addStyleName("fixed clip");
			break;
		case MINI:
			addStyleName("fixed mini");
			showNavMenu(name);
			break;
		case FLOAT:
			addStyleName("fixed clip float");
			break;
		case CARD:
			addStyleName("fixed clip card");
			break;
		case CLOSE:
		default:
			addStyleName("close");
			showNavMenu(name);
			break;
		}
	}

	/**
	 * Show the NavMenu
	 * @param sideNav
	 */
	public native void showNavMenu(String sideNav)/*-{
		$wnd.jQuery( document ).ready(function(){
			$wnd.jQuery("." + sideNav).addClass('show-on-large');
		})
	}-*/;
	
	/**
	 * Hide the overlay menu
	 */
	public native void hideOverlay(String sideNav)/*-{
		$wnd.jQuery( document ).ready(function(){
			$wnd.jQuery('#sidenav-overlay').css('z-index', '994');
		})
	}-*/;
	
	/**
	 * Shoe the sidenav
	 * @param sidenav
	 */
	public native void show(String sideNav)/*-{
		$wnd.jQuery("." + sideNav).sideNav('show');
	}-*/;
	
	/**
	 * Hide the sidenav
	 * @param sideNav - the name of your sidenav
	 */
	public native void hide(String sideNav)/*-{
		$wnd.jQuery("." + sideNav).sideNav('hide');
	}-*/;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void setWaves(String waves) {
		for(Widget w : this){
			if(w instanceof ListItem){
				w.addStyleName(waves + " waves-effect");
			}
		}
	}
	
	@Override
	public native void initWaves() /*-{
	    $wnd.Waves.displayEffect();
	}-*/;
}
