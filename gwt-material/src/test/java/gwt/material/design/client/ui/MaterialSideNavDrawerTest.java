package gwt.material.design.client.ui;

import gwt.material.design.client.constants.SideNavType;
import gwt.material.design.client.ui.base.AbstractSideNavTest;

public class MaterialSideNavDrawerTest extends AbstractSideNavTest<MaterialSideNavDrawer> {

    @Override
    protected MaterialSideNavDrawer createWidget() {
        MaterialSideNavDrawer sideNav = new MaterialSideNavDrawer();
        construct(sideNav);
        return sideNav;
    }

    @Override
    public void testInitialClasses() {
        super.testInitialClasses();
        checkInitialClasses(SideNavType.DRAWER.getCssName());
    }

    public void testWithHeader() {
        MaterialSideNavDrawer sideNavPush = getWidget();

        sideNavPush.setWithHeader(true);
        sideNavPush.reload();
        assertTrue(sideNavPush.isWithHeader());
        assertTrue(sideNavPush.getElement().hasClassName(SideNavType.DRAWER_WITH_HEADER.getCssName()));

        sideNavPush.setWithHeader(false);
        sideNavPush.reload();
        assertFalse(sideNavPush.isWithHeader());
        assertFalse(sideNavPush.getElement().hasClassName(SideNavType.DRAWER_WITH_HEADER.getCssName()));
    }
}
