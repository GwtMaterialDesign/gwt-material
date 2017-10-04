package gwt.material.design.client.ui;

import gwt.material.design.client.constants.SideNavType;
import gwt.material.design.client.ui.base.AbstractSideNavTest;

public class MaterialSideNavMiniTest extends AbstractSideNavTest<MaterialSideNavMini> {

    @Override
    protected MaterialSideNavMini createWidget() {
        MaterialSideNavMini sideNav = new MaterialSideNavMini();
        construct(sideNav);
        return sideNav;
    }

    @Override
    public void testInitialClasses() {
        super.testInitialClasses();
        checkInitialClasses(SideNavType.MINI.getCssName());
    }
}
