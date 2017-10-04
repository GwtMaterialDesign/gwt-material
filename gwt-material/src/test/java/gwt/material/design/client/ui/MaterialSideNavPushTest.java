package gwt.material.design.client.ui;

import gwt.material.design.client.constants.SideNavType;
import gwt.material.design.client.ui.base.AbstractSideNavTest;

public class MaterialSideNavPushTest extends AbstractSideNavTest<MaterialSideNavPush> {

    @Override
    protected MaterialSideNavPush createWidget() {
        MaterialSideNavPush sideNav = new MaterialSideNavPush();
        construct(sideNav);
        return sideNav;
    }

    @Override
    public void testInitialClasses() {
        super.testInitialClasses();
        checkInitialClasses(SideNavType.PUSH.getCssName());
    }

    public void testWithHeader() {
        MaterialSideNavPush sideNavPush = getWidget();

        sideNavPush.setWithHeader(true);
        sideNavPush.reload();
        assertTrue(sideNavPush.isWithHeader());
        assertTrue(sideNavPush.getElement().hasClassName(SideNavType.PUSH_WITH_HEADER.getCssName()));

        sideNavPush.setWithHeader(false);
        sideNavPush.reload();
        assertFalse(sideNavPush.isWithHeader());
        assertFalse(sideNavPush.getElement().hasClassName(SideNavType.PUSH_WITH_HEADER.getCssName()));
    }
}
