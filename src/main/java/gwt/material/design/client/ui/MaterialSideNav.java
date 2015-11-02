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

import com.google.gwt.dom.client.Element;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.custom.ComplexNav;
import gwt.material.design.client.custom.HasType;
import gwt.material.design.client.custom.HasWaves;
import gwt.material.design.client.constants.SideNavType;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.mixin.CssTypeMixin;
import gwt.material.design.client.custom.mixin.WavesMixin;

/**
 * SideNav is a material component that gives you a lists
 * of menus and other navigation components.
 *
 * @author Ben Dol
 */
public class MaterialSideNav extends ComplexNav implements HasWaves, HasType<SideNavType> {

	private boolean closeOnClick;
	private int width;

	private final CssTypeMixin<SideNavType, MaterialSideNav> typeMixin = new CssTypeMixin<>(this);
	private final WavesMixin<MaterialSideNav> wavesMixin = new WavesMixin<>(this);
	
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
            add(w);
        }
	}
	
	@UiConstructor
	public MaterialSideNav(int width, boolean closeOnClick, SideNavType type){
		this();
		setId("nav-mobile");
		setWidth(width);
		setCloseOnClick(closeOnClick);
		setType(type);
	}
	
	@Override
	public void onLoad() {
		super.onLoad();

		// Initialize the side nav
		initNavBar();
	}

	@Override
	public void add(Widget child) {
		ListItem item = new ListItem(child);
		if(child instanceof MaterialImage) {
			item.getElement().getStyle().setProperty("border", "1px solid #e9e9e9");
			item.getElement().getStyle().setProperty("textAlign", "center");
		}
		super.add(child);
	}

	@Override
	public void setWaves(WavesType waves) {
		wavesMixin.setWaves(waves);
	}

	@Override
	public WavesType getWaves() {
		return wavesMixin.getWaves();
	}

	public void setWidth(int width){
		this.width = width;
		getElement().getStyle().setWidth(width, Unit.PX);
	}
	
	public void setCloseOnClick(boolean closeOnClick){
		this.closeOnClick = closeOnClick;
	}

	public void setType(SideNavType type) {
		typeMixin.setType(type);
	}

	@Override
	public SideNavType getType() {
		return typeMixin.getType();
	}

	public void initNavBar() {
		initNavBar(getElement(), width, closeOnClick);
	}

	private native void initNavBar(Element e, int width, boolean closeOnClick)/*-{
        $wnd.jQuery(document).ready(function() {
            $wnd.jQuery(e).find(".button-collapse").sideNav({
                menuWidth: width,
                closeOnClick: closeOnClick
            });
        })
    }-*/;
	
	/**
	 * Hide the overlay menu
	 */
	public native void hideOverlay()/*-{
		$wnd.jQuery(document).ready(function(){
			$wnd.jQuery('#sidenav-overlay').css('z-index', '994');
		})
	}-*/;
	
	/**
	 * Shoe the sidenav.
	 */
	public native void show(Element e)/*-{
		$wnd.jQuery(e).sideNav('show');
	}-*/;
	
	/**
	 * Hide the sidenav.
	 */
	public native void hide(Element e)/*-{
		$wnd.jQuery(e).sideNav('hide');
	}-*/;
}
