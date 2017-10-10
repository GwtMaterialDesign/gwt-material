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
