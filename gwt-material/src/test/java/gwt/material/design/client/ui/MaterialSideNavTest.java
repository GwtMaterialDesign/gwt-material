package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.SideNavType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.ListItem;
import org.junit.Test;

/**
 * Test case for Sidenav
 *
 * @author kevzlou7979
 */
public class MaterialSideNavTest extends MaterialWidgetTest {

    @Test
    public void testSideNav() {
        MaterialSideNav sideNav = new MaterialSideNav();
        final String ACTIVATOR = "sidenav";
        MaterialNavBar navBar = new MaterialNavBar();
        navBar.setActivates(ACTIVATOR);
        sideNav.setId(ACTIVATOR);
        sideNav.setWidth("300");
        assertNotNull(navBar.getNavMenu());
        assertEquals(navBar.getNavMenu().getElement().getAttribute("data-activates"), ACTIVATOR);
        assertEquals(navBar.getNavMenu().getElement().getAttribute("data-activates"), sideNav.getId());
        assertEquals(navBar.getActivates(), sideNav.getId());
        assertEquals(navBar.getActivates(), ACTIVATOR);
        assertEquals(sideNav.getWidth(), 300);
        checkWidget(sideNav);
        checkTypes(sideNav);
        checkBoolean(sideNav, navBar);
        checkSideNavItems(sideNav);
    }

    public <T extends MaterialSideNav, H extends MaterialNavBar> void checkTypes(T sidenav) {
        final Element element = sidenav.getElement();
        // Fixed type
        sidenav.setType(SideNavType.FIXED);
        assertNotNull(sidenav.getId());
        assertTrue(element.hasClassName(SideNavType.FIXED.getCssName()));
        assertEquals(sidenav.getType(), SideNavType.FIXED);
        // Card Type
        sidenav.setType(SideNavType.CARD);
        assertTrue(element.hasClassName(SideNavType.CARD.getCssName()));
        assertEquals(sidenav.getType(), SideNavType.CARD);
        // Push Type
        sidenav.setType(SideNavType.PUSH);
        assertTrue(element.hasClassName(SideNavType.PUSH.getCssName()));
        assertEquals(sidenav.getType(), SideNavType.PUSH);
        // Mini
        sidenav.setType(SideNavType.MINI);
        assertTrue(element.hasClassName(SideNavType.MINI.getCssName()));
        assertEquals(sidenav.getType(), SideNavType.MINI);
    }

    public <T extends MaterialSideNav, H extends MaterialNavBar> void checkBoolean(T sideNav, H navBar) {
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

    public <T extends MaterialSideNav> void checkSideNavItems(T sidenav) {
        for (int i = 1; i <= 5; i++) {
            sidenav.add(new MaterialLink("Item " + i));
        }
        assertTrue(sidenav.getChildren().size() == 5);

        // Check if sidenav adds ListItem as parent widget of it's items
        for (Widget w : sidenav.getChildren()) {
            assertNotNull(w);
            assertTrue(w instanceof ListItem);
            ListItem item = (ListItem) w;
            assertTrue(item.getWidget(0) instanceof MaterialLink);
        }

        // Check active links
        ListItem link = (ListItem) sidenav.getWidget(0);
        link.addStyleName(CssName.ACTIVE);
        assertTrue(link.getElement().hasClassName(CssName.ACTIVE));

        // Clear all active side nav items
        sidenav.clearActive();
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
        sidenav.add(collapsible);

        assertNotNull(item);
        assertNotNull(collapsible);
        assertNotNull(header);
        assertNotNull(parentLink);
        assertNotNull(body);
        assertEquals(body.getChildren().size(), 5);
        assertTrue(sidenav.getChildren().get(5) instanceof ListItem);
        ListItem itemColaps = (ListItem) sidenav.getWidget(5);
        assertTrue(itemColaps.getWidget(0) instanceof MaterialCollapsible);
    }
}
