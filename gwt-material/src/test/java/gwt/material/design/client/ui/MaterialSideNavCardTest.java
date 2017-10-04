package gwt.material.design.client.ui;

import gwt.material.design.client.constants.SideNavType;
import gwt.material.design.client.ui.base.AbstractSideNavTest;

public class MaterialSideNavCardTest extends AbstractSideNavTest<MaterialSideNavCard> {

    @Override
    protected MaterialSideNavCard createWidget() {
        MaterialSideNavCard sideNav = new MaterialSideNavCard();
        construct(sideNav);
        return sideNav;
    }

    @Override
    public void testInitialClasses() {
        super.testInitialClasses();
        checkInitialClasses(SideNavType.CARD.getCssName());
    }
}
