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
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.SideNavType;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;


//@formatter:off

/**
* SideNav is a material component that gives you a lists of menus and other navigation components.
*
* <h3>UiBinder Usage:</h3>
* <pre>
* {@code
* <m:MaterialSideNav ui:field="sideNav" width="280" name="mysidebar"  type="OPEN" closeOnClick="false">
* 	<m:MaterialLink href="#about" iconPosition="left" icon="info_outline" text="About" textColor="blue"  />
* 	<m:MaterialLink href="#gettingStarted" iconPosition="left" icon="cloud_download" text="Getting Started" textColor="blue"  >		
* </m:MaterialSideNav>
* }
* </pre>
* 
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#sidenav">Material SideNav</a>
*/
//@formatter:on
public class MaterialSideNav extends UnorderedList implements HasType<SideNavType> {

	private boolean closeOnClick;
	private int width;

	private final CssTypeMixin<SideNavType, MaterialSideNav> typeMixin = new CssTypeMixin<>(this);
	
	/**
	 * Container for App Toolbar and App Sidebar , contains Material Links,
	 * Icons or any other material components.
	 */
	public MaterialSideNav() {
		super();
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
		if(child instanceof MaterialImage) {
			child.getElement().getStyle().setProperty("border", "1px solid #e9e9e9");
			child.getElement().getStyle().setProperty("textAlign", "center");
		}

		if(!(child instanceof ListItem)) {
			ListItem listItem = new ListItem();
			if(child instanceof MaterialCollapsible) {
				listItem.getElement().getStyle().setBackgroundColor("transparent");
			}
			listItem.add(child);
			child = listItem;
		}
		child.getElement().getStyle().setDisplay(Style.Display.BLOCK);
		super.add(child);
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
