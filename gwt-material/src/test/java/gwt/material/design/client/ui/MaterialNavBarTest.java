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

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.HideOn;
import gwt.material.design.client.constants.NavBarType;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.events.NavBarExpandEvent;
import gwt.material.design.client.events.NavBarShrinkEvent;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.Div;
import gwt.material.design.client.ui.html.ListItem;

/**
 * Test case for Nav Bar
 *
 * @author kevzlou7979
 */
public class MaterialNavBarTest extends MaterialWidgetTest {

    public void init() {
        MaterialHeader header = new MaterialHeader();
        MaterialNavBar navBar = new MaterialNavBar();
        header.add(navBar);
        RootPanel.get().add(header);
        assertTrue(navBar.getParent() instanceof MaterialHeader);
        checkWidget(navBar);
        checkNavBrand(navBar);
        checkNavSection(navBar);
        checkStructure(navBar);
        checkActivates(navBar);
        checkTypes();
        MaterialNavSection section = new MaterialNavSection();
        checkSelectionEvent(section);
    }

    public <T extends MaterialNavSection> void checkSelectionEvent(T widget) {
        final boolean[] isSelectionEventFired = {false};
        widget.addSelectionHandler(selectionEvent -> isSelectionEventFired[0] = true);
        widget.fireEvent(new GwtEvent<SelectionHandler<?>>() {
            @Override
            public Type<SelectionHandler<?>> getAssociatedType() {
                return SelectionEvent.getType();
            }

            @Override
            protected void dispatch(SelectionHandler eventHandler) {
                eventHandler.onSelection(null);
            }
        });

        assertEquals(isSelectionEventFired[0], true);
    }

    public <T extends MaterialNavBar> void checkTypes() {
        final int OFFSET = 100;
        MaterialNavBarShrink shrinkNavBar = new MaterialNavBarShrink();

        MaterialHeader header = new MaterialHeader();
        header.add(shrinkNavBar);
        RootPanel.get().add(header);

        assertTrue(shrinkNavBar.getStyleName().contains(NavBarType.SHRINK.getCssName()));

        shrinkNavBar.setOffset(OFFSET);
        assertEquals(shrinkNavBar.getOffset(), OFFSET);

        final boolean[] expandFired = {false};
        shrinkNavBar.addExpandHandler(event -> expandFired[0] = true);

        NavBarExpandEvent.fire(shrinkNavBar);
        assertTrue(expandFired[0]);

        final boolean[] shrinkFired = {false};
        shrinkNavBar.addShrinkHandler(event -> shrinkFired[0] = true);

        NavBarShrinkEvent.fire(shrinkNavBar);
        assertTrue(shrinkFired[0]);
    }

    public <T extends MaterialNavBar> void checkStructure(T navBar) {
        assertTrue(navBar.getWidget(0) instanceof Div);
        Div navWrapper = (Div) navBar.getWidget(0);
        assertEquals(navWrapper.getWidget(0), navBar.getNavMenu());
        if (!navBar.getActivates().isEmpty()) {
            assertTrue(navWrapper.getWidget(0) instanceof MaterialLink);
            MaterialLink navMenu = (MaterialLink) navWrapper.getWidget(0);
            assertTrue(navWrapper.getWidget(1) instanceof MaterialNavSection);
            assertEquals(navMenu, navBar.getNavMenu());
            assertTrue(navBar.getNavMenu().getElement().hasClassName(CssName.BUTTON_COLLAPSE));
        }
        assertTrue(navWrapper.getElement().hasClassName(CssName.NAV_WRAPPER));

        // NavBar Content extension
        MaterialNavContent navContent = new MaterialNavContent();
        navBar.add(navContent);
        assertTrue(navContent.getElement().hasClassName(CssName.NAV_CONTENT));
        MaterialLabel label = new MaterialLabel();
        navContent.add(label);

        assertEquals(navContent.getWidget(0), label);
        assertEquals(navWrapper.getWidget(3), navContent);

        navBar.clear();
        assertEquals(navWrapper.getChildren().size(), 0);
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
        navBar.add(navSection);
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
