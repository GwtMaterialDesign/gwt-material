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

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.HideOn;
import gwt.material.design.client.constants.NavBarType;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.Div;
import gwt.material.design.client.ui.html.ListItem;
import org.junit.Test;

public class MaterialNavBarTest extends MaterialWidgetTest {

    @Test
    public void testNavBar() {
        MaterialHeader header = new MaterialHeader();
        MaterialNavBar navBar = new MaterialNavBar();
        header.add(navBar);
        assertTrue(navBar.getParent() instanceof MaterialHeader);
        checkWidget(navBar);
        checkNavBrand(navBar);
        checkNavSection(navBar);
        checkStructure(navBar);
        checkActivates(navBar);
        checkTypes(navBar);
    }

    public <T extends MaterialNavBar> void checkTypes(T navBar) {
        // Fixed
        navBar.setType(NavBarType.FIXED);
        assertTrue(navBar.getElement().hasClassName(NavBarType.FIXED.getCssName()));
        assertEquals(navBar.getType(), NavBarType.FIXED);
        // Shrink
        navBar.setType(NavBarType.SHRINK);
        assertTrue(navBar.getElement().hasClassName(NavBarType.SHRINK.getCssName()));
        assertEquals(navBar.getType(), NavBarType.SHRINK);
        // Tall
        navBar.setType(NavBarType.TALL);
        assertTrue(navBar.getElement().hasClassName(NavBarType.TALL.getCssName()));
        assertEquals(navBar.getType(), NavBarType.TALL);
    }

    public <T extends MaterialNavBar> void checkStructure(T navBar) {
        assertTrue(navBar.getWidget(0) instanceof Div);
        Div div = (Div) navBar.getWidget(0);
        assertTrue(div.getWidget(0) instanceof MaterialLink);
        assertTrue(div.getElement().hasClassName(CssName.NAV_WRAPPER));
        MaterialLink navMenu = (MaterialLink) div.getWidget(0);
        assertEquals(navMenu, navBar.getNavMenu());
        assertTrue(navBar.getNavMenu().getElement().hasClassName(CssName.BUTTON_COLLAPSE));
        navBar.clear();
        assertEquals(div.getChildren().size(), 0);
    }

    public <T extends MaterialNavBar> void checkNavBrand(T navBar) {
        MaterialNavBrand navBrand = new MaterialNavBrand();
        checkWidget(navBrand);
        navBar.add(navBrand);
        navBrand.setText("test");
        assertEquals(navBrand.getText(), "test");

        navBrand.setPosition(Position.RIGHT);
        assertTrue(navBrand.getElement().hasClassName(Position.RIGHT.getCssName()));
        navBrand.setPosition(Position.LEFT);
        assertTrue(navBrand.getElement().hasClassName(Position.LEFT.getCssName()));
    }

    public <T extends MaterialNavBar> void checkNavSection(T navBar) {
        MaterialNavSection navSection = new MaterialNavSection();
        checkWidget(navSection);
        for (int i = 1; i <= 5; i++) {
            navSection.add(new MaterialLink("Nav Link " + i));
        }
        for (Widget w : navSection.getChildren()) {
            assertTrue(w instanceof ListItem);
            ListItem item = (ListItem) w;
            assertTrue(item.getWidget(0) instanceof MaterialLink);
        }
        assertEquals(navSection.getChildren().size(), 5);
        navSection.setPosition(Position.LEFT);
        assertTrue(navSection.getElement().hasClassName(Position.LEFT.getCssName()));
        assertTrue(navSection.getElement().hasClassName(HideOn.HIDE_ON_MED.getCssName()));
    }

    public <T extends MaterialNavBar> void checkActivates(T navBar) {
        final String ACTIVATOR = "activator";
        navBar.setActivates(ACTIVATOR);
        assertNotNull(navBar.getActivates());
        assertTrue(navBar.getNavMenu().getElement().hasAttribute("data-activates"));
        assertEquals(navBar.getNavMenu().getElement().getAttribute("data-activates"), ACTIVATOR);
    }
}
