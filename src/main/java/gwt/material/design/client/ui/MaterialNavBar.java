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

import gwt.material.design.client.base.ComplexWidget;
import gwt.material.design.client.base.HasLoader;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.constants.HideOn;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.NavBarType;
import gwt.material.design.client.ui.html.Div;
import gwt.material.design.client.ui.html.Nav;

import com.google.gwt.dom.client.Document;
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
public class MaterialNavBar extends ComplexWidget implements HasLoader, HasType<NavBarType>{
	
	private Nav nav = new Nav();
	private Div div = new Div();

	private MaterialIcon navMenu = new MaterialIcon(IconType.MENU);
	private MaterialProgress progress = new MaterialProgress();

	private final CssTypeMixin<NavBarType, MaterialNavBar> typeMixin = new CssTypeMixin<>(this);
	
	public MaterialNavBar() {
		super(Document.get().createElement("header"));
		super.add(nav);
		div.setStyleName("nav-wrapper");
		nav.add(div);
		div.add(navMenu);
		navMenu.addStyleName("button-collapse");
		navMenu.setHideOn(HideOn.HIDE_ON_LARGE);
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		div.setBackgroundColor(bgColor);
	}
	
	@Override
	public void add(Widget child) {
		div.add(child);
	}
	
	@Override
	public void setType(NavBarType type) {
		typeMixin.setType(type);
	}

	@Override
	public NavBarType getType() {
		return typeMixin.getType();
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
