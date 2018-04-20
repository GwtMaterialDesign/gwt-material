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
package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.AbstractSideNav;
import gwt.material.design.client.base.viewport.Resolution;
import gwt.material.design.client.base.viewport.WidthBoundary;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Edge;
import gwt.material.design.client.events.SideNavClosedEvent;
import gwt.material.design.client.events.SideNavClosingEvent;
import gwt.material.design.client.events.SideNavOpenedEvent;
import gwt.material.design.client.events.SideNavOpeningEvent;
import gwt.material.design.client.ui.*;
import gwt.material.design.client.ui.html.ListItem;

public abstract class AbstractSideNavTest<T extends AbstractSideNav> extends MaterialWidgetTest<T> {

    protected static MaterialNavBar navBar;
    protected static final String ACTIVATES = "activates";

    public void construct(T sideNav) {
        navBar = new MaterialNavBar();
        navBar.setActivates(ACTIVATES);
        sideNav.setId(ACTIVATES);
        sideNav.setWidth("300");
        assertNotNull(navBar.getNavMenu());
        assertEquals(ACTIVATES, navBar.getNavMenu().getElement().getAttribute("data-activates"));
        assertEquals(sideNav.getId(), navBar.getNavMenu().getElement().getAttribute("data-activates"));
        assertEquals(sideNav.getId(), navBar.getActivates());
        assertEquals(ACTIVATES, navBar.getActivates());
        assertEquals(300, sideNav.getWidth());
        // Attach the nav bar
        RootPanel.get().add(navBar);
        assertTrue(navBar.isAttached());
    }

    @Override
    public void testId() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setId(ACTIVATES);
        assertNotNull(widget.getId());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setId(ACTIVATES);
        assertTrue(element.hasAttribute("id"));
        assertEquals(widget.getId(), element.getId());
    }

    @Override
    public void testScrollspy() {
        // Suppress this test since side nav does not support scroll spy
        // Both require unique id's.
    }

    public void testContent() {
        // given
        T sideNav = getWidget();

        // when / then
        assertEquals(0, sideNav.getChildren().size());
        MaterialSideNavContent content = new MaterialSideNavContent();
        sideNav.add(content);
        assertEquals(1, sideNav.getChildren().size());
        assertEquals(content, sideNav.getWidget(0));
    }

    public void testEdge() {
        // UiBinder
        // given
        T sideNav = getWidget(false);

        // when / then
        sideNav.setEdge(Edge.RIGHT);
        assertEquals(Edge.RIGHT, sideNav.getEdge());

        // when / then
        sideNav.setEdge(Edge.LEFT);
        assertEquals(Edge.LEFT, sideNav.getEdge());

        // Standard
        // given
        attachWidget();

        // when / then
        sideNav.setEdge(Edge.RIGHT);
        assertEquals(Edge.RIGHT, sideNav.getEdge());

        // when / then
        sideNav.setEdge(Edge.LEFT);
        assertEquals(Edge.LEFT, sideNav.getEdge());
    }

    public void testDuration() {
        // UiBinder
        // given
        T sideNav = getWidget(false);

        final int IN_DURATION = 500;
        final int OUT_DURATION = 800;

        // when / then
        // Check the default in duration (Expected 300ms)
        assertEquals(400, sideNav.getInDuration());
        // Check the default out duration (Expected 200ms)
        assertEquals(200, sideNav.getOutDuration());

        sideNav.setInDuration(IN_DURATION);
        assertEquals(IN_DURATION, sideNav.getInDuration());
        sideNav.setOutDuration(OUT_DURATION);
        assertEquals(OUT_DURATION, sideNav.getOutDuration());

        // Standard
        // given
        attachWidget();

        // when / then
        sideNav.setInDuration(IN_DURATION);
        assertEquals(IN_DURATION, sideNav.getInDuration());
        sideNav.setOutDuration(OUT_DURATION);
        assertEquals(OUT_DURATION, sideNav.getOutDuration());
    }

    public void testActivator() {
        // UiBinder
        // given
        T sideNav = getWidget(false);

        // when / then
        assertEquals(ACTIVATES, sideNav.getId());

        // Check Nav Menu
        assertNotNull(navBar.getNavMenu());

        // isAlwaysShowActivator() must be true by default
        assertTrue(sideNav.isAlwaysShowActivator());

        // Standard
        // given
        attachWidget();

        // when / then
        assertEquals(ACTIVATES, sideNav.getId());

        // Check Nav Menu
        assertNotNull(navBar.getNavMenu());
        final Element navMenuElement = navBar.getNavMenu().getElement();

        // isAlwaysShowActivator() must be true by default
        assertTrue(sideNav.isAlwaysShowActivator());
    }

    public void testBoolean() {
        // UiBinder
        // given
        T sideNav = getWidget(false);

        // when / then
        sideNav.setCloseOnClick(true);
        assertTrue(sideNav.isCloseOnClick());
        sideNav.setCloseOnClick(false);
        assertFalse(sideNav.isCloseOnClick());
        sideNav.setAlwaysShowActivator(true);
        assertTrue(sideNav.isAlwaysShowActivator());
        sideNav.setAlwaysShowActivator(false);
        assertFalse(sideNav.isAlwaysShowActivator());
        sideNav.setAllowBodyScroll(true);
        assertTrue(sideNav.isAllowBodyScroll());
        sideNav.setAllowBodyScroll(false);
        assertFalse(sideNav.isAllowBodyScroll());
        sideNav.setShowOnAttach(true);
        assertTrue(sideNav.isShowOnAttach());
        sideNav.setShowOnAttach(false);
        assertFalse(sideNav.isShowOnAttach());

        // Standard
        // given
        attachWidget();

        // when / then
        sideNav.setCloseOnClick(true);
        assertTrue(sideNav.isCloseOnClick());
        sideNav.setCloseOnClick(false);
        assertFalse(sideNav.isCloseOnClick());
        sideNav.setAlwaysShowActivator(true);
        assertFalse(navBar.getNavMenu().getElement().hasClassName(CssName.NAVMENU_PERMANENT));
        assertTrue(sideNav.isAlwaysShowActivator());
        sideNav.setAlwaysShowActivator(false);
        assertFalse(sideNav.isAlwaysShowActivator());
        sideNav.setAllowBodyScroll(true);
        assertTrue(sideNav.isAllowBodyScroll());
        sideNav.setAllowBodyScroll(false);
        assertFalse(sideNav.isAllowBodyScroll());
        sideNav.setShowOnAttach(true);
        assertTrue(sideNav.isShowOnAttach());
        sideNav.setShowOnAttach(false);
        assertFalse(sideNav.isShowOnAttach());
    }

    public void testSideNavItems() {
        // given
        T sideNav = getWidget();

        // when / then
        for (int i = 1; i <= 5; i++) {
            sideNav.add(new MaterialLink("Item " + i));
        }
        assertTrue(sideNav.getChildren().size() == 5);

        // Check if sidenav adds ListItem as parent widget of it's items
        for (Widget w : sideNav.getChildren()) {
            assertNotNull(w);
            assertTrue(w instanceof ListItem);
            ListItem item = (ListItem) w;
            assertTrue(item.getWidget(0) instanceof MaterialLink);
        }

        // Check active links
        ListItem link = (ListItem) sideNav.getWidget(0);
        link.addStyleName(CssName.ACTIVE);
        assertTrue(link.getElement().hasClassName(CssName.ACTIVE));

        // Clear all active side nav items
        sideNav.clearActive();
        assertFalse(link.getElement().hasClassName(CssName.ACTIVE));

        // Check Nested Sidenav items using Collapsible Component
        MaterialCollapsible collapsible = new MaterialCollapsible();
        MaterialCollapsibleItem item = new MaterialCollapsibleItem();
        MaterialCollapsibleHeader header = new MaterialCollapsibleHeader();
        MaterialLink parentLink = new MaterialLink("Parent");
        header.add(parentLink);

        MaterialCollapsibleBody body = new MaterialCollapsibleBody();
        for (int i = 1; i <= 5; i++) {
            body.add(new MaterialLink("SubItem " + i));
        }

        item.add(header);
        item.add(body);
        collapsible.add(item);
        sideNav.add(collapsible);

        assertNotNull(item);
        assertNotNull(collapsible);
        assertNotNull(header);
        assertNotNull(parentLink);
        assertNotNull(body);
        assertEquals(5, body.getChildren().size());
        assertTrue(sideNav.getChildren().get(5) instanceof MaterialCollapsible);
    }

    public void testOpenCloseHandlers() {
        T sideNav = getWidget();
        checkOpeningHandler(sideNav);
        checkClosingHandler(sideNav);
        checkOpenedHandler(sideNav);
        checkClosedHandler(sideNav);
    }

    public void testAutoHideOnResize() {
        T sideNav = getWidget();

        if (sideNav instanceof MaterialSideNavPush) {
            assertTrue(sideNav.isAutoHideOnResize());
        } else {
            assertFalse(sideNav.isAutoHideOnResize());
        }

        sideNav.setAutoHideOnResize(true);
        assertTrue(sideNav.isAutoHideOnResize());

        sideNav.setAutoHideOnResize(false);
        assertFalse(sideNav.isAutoHideOnResize());

        assertNotNull(sideNav.getClosingBoundary());

        WidthBoundary boundary = sideNav.getClosingBoundary();
        assertEquals(0, boundary.getMin());
        assertEquals(992, boundary.getMax());

        sideNav.setClosingBoundary(Resolution.ALL_MOBILE.getBoundary());
        assertEquals(Resolution.ALL_MOBILE.getBoundary(), sideNav.getClosingBoundary());
    }

    protected void checkOpeningHandler(T sideNav) {
        boolean[] openingEventFired = {false};
        sideNav.addOpeningHandler(event -> openingEventFired[0] = true);
        SideNavOpeningEvent.fire(sideNav);
        assertTrue(openingEventFired[0]);
    }

    protected void checkClosingHandler(T sideNav) {
        boolean[] closingEventFired = {false};
        sideNav.addClosingHandler(event -> closingEventFired[0] = true);
        SideNavClosingEvent.fire(sideNav);
        assertTrue(closingEventFired[0]);
    }

    protected void checkOpenedHandler(T sideNav) {
        boolean[] openedEventFired = {false};
        sideNav.addOpenedHandler(event -> openedEventFired[0] = true);
        SideNavOpenedEvent.fire(sideNav);
        assertTrue(openedEventFired[0]);
    }

    protected void checkClosedHandler(T sideNav) {
        boolean[] closedEventFired = {false};
        sideNav.addClosedHandler(event -> closedEventFired[0] = true);
        SideNavClosedEvent.fire(sideNav);
        assertTrue(closedEventFired[0]);
    }
}
