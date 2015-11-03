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

import gwt.material.design.client.constants.NavBarType;
import gwt.material.design.client.custom.CustomAnchor;
import gwt.material.design.client.custom.CustomHeader;
import gwt.material.design.client.custom.CustomNav;
import gwt.material.design.client.custom.HasLoader;
import gwt.material.design.client.custom.HasType;
import gwt.material.design.client.custom.MaterialWidget;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
* Material NavBar represents as a app tool bar, that contains NavBrand, NavSection and initialize 
* Material Sidenav
*
* <h3>UiBinder Usage:</h3>
* <pre>
* {@code

<m:MaterialNavBar backgroundColor="blue" >
	<m:MaterialNavBrand href="#Test" align="left">Title</m:MaterialNavBrand>
	<m:MaterialNavSection align="right">
		<m:MaterialLink  icon="mdi-action-account-circle" iconPosition="left" text="Account"  textColor="white" waves="light"/>
		<m:MaterialLink  icon="mdi-action-autorenew" iconPosition="left" text="Refresh" textColor="white" waves="light"/>
		<m:MaterialLink  icon="mdi-action-search" tooltip="Menu" textColor="white" waves="light"/>
	 	<m:MaterialLink  icon="mdi-navigation-more-vert" tooltip="Starter" textColor="white" waves="light"/>
	</m:MaterialNavSection>
</m:MaterialNavBar>

* }
*<pre>
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#navigations">Material Nav Bar</a>
*/
//@formatter:on
public class MaterialNavBar extends MaterialWidget implements HasWidgets, HasLoader, HasType<NavBarType> {

	private static NavBarUiBinder uiBinder = GWT.create(NavBarUiBinder.class);

	interface NavBarUiBinder extends UiBinder<Widget, MaterialNavBar> {
	}
	
	@UiField MaterialPanel panel;
	@UiField CustomAnchor navMenu;
	@UiField CustomNav nav;
	@UiField CustomHeader header;

	private MaterialProgress progress = new MaterialProgress();

	private NavBarType type;
	
	/**
	 * Nav Bar is a component which contains your app tool bar and app sidebar
	 */
	@UiConstructor
	public MaterialNavBar() {
		initWidget(uiBinder.createAndBindUi(this));

		setType(NavBarType.FIXED);
	}
	
	/**
	 * Set the sidebar name to activate it.
	 */
	public void setSideBar(String sideBar) {
		navMenu.addStyleName(sideBar);
		navMenu.getElement().setAttribute("data-activates", sideBar);
	}
	
	/**
	 * Will add a child widget into your nav bar
	 */
	@Override
	public void add(Widget w) {
		panel.add(w);
	}

	/**
	 * This will clear all the child of your nav bar
	 */
	@Override
	public void clear() {
		panel.clear();
	}

	@Override
	public Iterator<Widget> iterator() {
		return null;
	}

	@Override
	public boolean remove(Widget w) {
		return false;
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		panel.addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		panel.addStyleName(textColor + "-text");
	}

	@Override
	public NavBarType getType() {
		return type;
	}

	@Override
	public void setType(NavBarType type) {
		switch (type) {
		case FIXED:
			header.addStyleName(type.getCssName());
			break;
		case TALL:
			panel.addStyleName(type.getCssName());
			break;
		default:
			break;
		}
	}

	@Override
	public void showLoader() {
		nav.add(progress);
	}

	@Override
	public void hideLoader() {
		progress.removeFromParent();
	}
}
