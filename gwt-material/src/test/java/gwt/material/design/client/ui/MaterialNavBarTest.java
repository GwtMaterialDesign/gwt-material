/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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

import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.NavBarType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.events.NavBarExpandEvent;
import gwt.material.design.client.events.NavBarShrinkEvent;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.Div;

/**
 * Test case for Nav Bar.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialNavBarTest extends MaterialWidgetTest<MaterialNavBar> {

    @Override
    protected MaterialNavBar createWidget() {
        return constructAndAttach();
    }

    public static MaterialNavBar constructAndAttach() {
        MaterialHeader header = new MaterialHeader();
        MaterialNavBar navBar = new MaterialNavBar();
        MaterialNavContent navContent = new MaterialNavContent();
        navBar.add(navContent);
        header.add(navBar);
        RootPanel.get().add(header);
        assertTrue(navBar.getParent() instanceof MaterialHeader);
        return navBar;
    }

    @Override
    protected void gwtTearDown() throws Exception {
        super.gwtTearDown();

        destroyWidget();
    }

    @Override
    public boolean neverAttach() {
        return true;
    }

    public void testTypes() {
        final int OFFSET = 100;
        MaterialNavBarShrink shrinkNavBar = new MaterialNavBarShrink();

        MaterialHeader header = new MaterialHeader();
        header.add(shrinkNavBar);
        RootPanel.get().add(header);

        assertTrue(shrinkNavBar.getStyleName().contains(NavBarType.SHRINK.getCssName()));

        shrinkNavBar.setOffset(OFFSET);
        assertEquals(OFFSET, shrinkNavBar.getOffset());

        final boolean[] expandFired = {false};
        shrinkNavBar.addExpandHandler(event -> expandFired[0] = true);

        NavBarExpandEvent.fire(shrinkNavBar);
        assertTrue(expandFired[0]);

        final boolean[] shrinkFired = {false};
        shrinkNavBar.addShrinkHandler(event -> shrinkFired[0] = true);

        NavBarShrinkEvent.fire(shrinkNavBar);
        assertTrue(shrinkFired[0]);
    }

    public void testStructure() {
        // given
        MaterialNavBar navBar = getWidget();
        Div navWrapper = navBar.getNavWrapper();
        MaterialLink navMenu = navBar.getNavMenu();
        MaterialNavContent navContent = (MaterialNavContent) navWrapper.getWidget(1);

        // when / then
        assertNotNull(navBar.getWidget(0));
        assertTrue(navBar.getWidget(0) instanceof Div);
        assertEquals(navBar.getNavWrapper(), navBar.getWidget(0));
        assertTrue(navWrapper.getElement().hasClassName(CssName.NAV_WRAPPER));
        assertEquals(navWrapper.getWidget(0), navBar.getNavMenu());
        assertTrue(navMenu.getElement().hasClassName(CssName.BUTTON_COLLAPSE));
        assertTrue(navMenu.isCircle());
        assertEquals("2.7em", navMenu.getFontSize());
        assertEquals(64, navMenu.getWidth());
        assertEquals(WavesType.LIGHT, navMenu.getWaves());
        assertEquals(TextAlign.CENTER, navMenu.getTextAlign());
        assertNotNull(navWrapper.getWidget(0));
        assertTrue(navWrapper.getWidget(1) instanceof MaterialNavContent);
        assertTrue(navContent.getElement().hasClassName(CssName.NAV_CONTENT));
        MaterialLabel label = new MaterialLabel();
        navContent.add(label);
        assertEquals(label, navContent.getWidget(0));
        navBar.clear();
        assertEquals(0, navWrapper.getChildren().size());
    }

    public void testActivates() {
        // given
        MaterialNavBar navBar = getWidget();

        // when / then
        final String ACTIVATOR = "activator";
        navBar.setActivates(ACTIVATOR);
        assertNotNull(navBar.getActivates());
        assertTrue(navBar.getNavMenu().getElement().hasAttribute("data-activates"));
        assertEquals(ACTIVATOR, navBar.getNavMenu().getElement().getAttribute("data-activates"));
    }

    // TODO Test ProgressBar
    public void testProgressBar() {}

    // TODO Test NavBar content
    public void testNavBarContent() {}
}
