/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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
package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasActivates;
import gwt.material.design.client.base.HasProgress;
import gwt.material.design.client.base.HasShrinkableNavBarHandlers;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.mixin.ActivatesMixin;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.base.mixin.ProgressMixin;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.events.NavBarExpandEvent;
import gwt.material.design.client.events.NavBarShrinkEvent;
import gwt.material.design.client.ui.html.Div;
import gwt.material.design.client.ui.html.Nav;

import static gwt.material.design.jquery.client.api.JQuery.$;

//@formatter:off

/**
 * Material NavBar represents as a app tool bar, that contains NavBrand,
 * NavSection and initialize Material SideNav.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialNavBar backgroundColor="BLUE" >
 *     <m:MaterialNavBrand href="#Test" position="LEFT"  text="Title" />
 *     <m:MaterialNavSection position="RIGHT">
 *         <m:MaterialLink  iconType="ACCOUNT_CIRCLE" iconPosition="LEFT" text="Account"  textColor="WHITE" waves="LIGHT"/>
 *         <m:MaterialLink  iconType="AUTORENEW" iconPosition="LEFT" text="Refresh" textColor="WHITE" waves="LIGHT"/>
 *         <m:MaterialLink  iconType="SEARCH" tooltip="Menu" textColor="WHITE" waves="LIGHT"/>
 *          <m:MaterialLink  iconType="MORE_VERT" tooltip="Starter" textColor="WHITE" waves="LIGHT"/>
 *     </m:MaterialNavSection>
 * </m:MaterialNavBar>
 * }
 * <pre>
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#navbar">Material Nav Bar</a>
 * @see <a href="https://material.io/guidelines/components/toolbars.html#">Material Design Specification</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#navbar_default">Pattern Default</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#navbar_fixed">Pattern Fixed</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#navbar_tall">Pattern Tall</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#navbar_extend">Pattern Extend</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#navbar_tab">Pattern Tabs</a>
 */
//@formatter:on
public class MaterialNavBar extends Nav implements HasActivates, HasProgress {

    private Div navWrapper = new Div();

    private MaterialLink navMenu = new MaterialLink(IconType.MENU);

    private final ActivatesMixin<MaterialLink> activatesMixin = new ActivatesMixin<>(navMenu);
    private final ProgressMixin<MaterialNavBar> progressMixin = new ProgressMixin<>(this);

    public MaterialNavBar() {
        super();
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        build();
    }

    @Override
    protected void build() {
        navWrapper.setStyleName(CssName.NAV_WRAPPER);
        navWrapper.insert(navMenu,0);
        super.add(navWrapper);
        navMenu.setFontSize(2.7, Style.Unit.EM);
        navMenu.addStyleName(CssName.BUTTON_COLLAPSE);
        navMenu.getElement().getStyle().clearDisplay();
        navMenu.setCircle(true);
        navMenu.setWaves(WavesType.LIGHT);
        navMenu.setWidth("64px");
        navMenu.setTextAlign(TextAlign.CENTER);
        navMenu.setIconPosition(IconPosition.NONE);

        // Check whether the SideNav is attached or not. If not attached Hide the NavMenu
        Element sideNavElement = $("#" + activatesMixin.getActivates()).asElement();

        if (sideNavElement == null) {
            navMenu.setVisibility(Style.Visibility.HIDDEN);
        } else {
            navMenu.setVisibility(Style.Visibility.VISIBLE);
        }
    }

    @Override
    public void add(Widget child) {
        navWrapper.add(child);
    }

    @Override
    public void clear() {
        navWrapper.clear();
    }

    @Override
    public void showProgress(ProgressType type) {
        progressMixin.showProgress(type);
    }

    @Override
    public void setPercent(double percent) {
        progressMixin.setPercent(percent);
    }

    @Override
    public void hideProgress() {
        progressMixin.hideProgress();
    }

    @Override
    public void setActivates(String activates) {
        activatesMixin.setActivates(activates);
    }

    @Override
    public String getActivates() {
        return activatesMixin.getActivates();
    }

    public MaterialLink getNavMenu() {
        return navMenu;
    }

    public Div getNavWrapper() {
        return navWrapper;
    }
}
