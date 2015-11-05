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

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasActivates;
import gwt.material.design.client.base.HasLoader;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.mixin.ActivatesMixin;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.HideOn;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.NavBarType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.html.Div;
import gwt.material.design.client.ui.html.Nav;

//@formatter:off

/**
 * Material NavBar represents as a app tool bar, that contains NavBrand,
 * NavSection and initialize Material Sidenav.
 *
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialNavBar backgroundColor="blue" >
 * 	<m:MaterialNavBrand href="#Test" align="left">Title</m:MaterialNavBrand>
 * 	<m:MaterialNavSection align="right">
 * 		<m:MaterialLink  icon="mdi-action-account-circle" iconPosition="left" text="Account"  textColor="white" waves="light"/>
 * 		<m:MaterialLink  icon="mdi-action-autorenew" iconPosition="left" text="Refresh" textColor="white" waves="light"/>
 * 		<m:MaterialLink  icon="mdi-action-search" tooltip="Menu" textColor="white" waves="light"/>
 * 	 	<m:MaterialLink  icon="mdi-navigation-more-vert" tooltip="Starter" textColor="white" waves="light"/>
 * 	</m:MaterialNavSection>
 * </m:MaterialNavBar>
* }
*<pre>
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#navigations">Material Nav Bar</a>
*/
//@formatter:on
public class MaterialNavBar extends Nav implements HasActivates, HasLoader, HasType<NavBarType> {

	private Div div = new Div();

	private MaterialAnchorButton navMenu = new MaterialAnchorButton(IconType.MENU);
	private MaterialProgress progress = new MaterialProgress();

	private final CssTypeMixin<NavBarType, MaterialNavBar> typeMixin = new CssTypeMixin<>(this);
	private final ActivatesMixin<MaterialAnchorButton> activatesMixin = new ActivatesMixin<>(navMenu);
	
	public MaterialNavBar() {
		div.setStyleName("nav-wrapper container");
		div.add(navMenu);
		super.add(div);
		navMenu.setFontSize(2.7, Style.Unit.EM);
		navMenu.addStyleName("button-collapse");
		navMenu.setHideOn(HideOn.HIDE_ON_LARGE);
		navMenu.setCircle(true);
		navMenu.setWaves(WavesType.LIGHT);
		navMenu.setWidth("64px");
		navMenu.setTextAlign(TextAlign.CENTER);
		navMenu.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

			}
		});
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
		add(progress);
	}

	@Override
	public void hideLoader() {
		progress.removeFromParent();
	}

	@Override
	public void setActivates(String activates) {
		activatesMixin.setActivates(activates);
	}

	@Override
	public String getActivates() {
		return activatesMixin.getActivates();
	}
}
