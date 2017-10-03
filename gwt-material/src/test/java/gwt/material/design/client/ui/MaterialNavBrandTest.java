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

import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Nav Brand.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialNavBrandTest extends MaterialWidgetTest<MaterialNavBrand> {

    @Override
    protected MaterialNavBrand createWidget() {
        return new MaterialNavBrand();
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.BRAND_LOGO);
    }

    public void testText() {
        // given
        MaterialNavBrand navBrand = getWidget();
        MaterialNavBar navBar = MaterialNavBarTest.constructAndAttach();
        navBar.add(navBrand);

        // when
        navBrand.setText("test");

        // then
        assertEquals("test", navBrand.getText());
    }

    public void testPositions() {
        // given
        MaterialNavBrand navBrand = getWidget();

        // when / then
        navBrand.setPosition(Position.RIGHT);
        assertTrue(navBrand.getElement().hasClassName(Position.RIGHT.getCssName()));
        navBrand.setPosition(Position.LEFT);
        assertTrue(navBrand.getElement().hasClassName(Position.LEFT.getCssName()));
    }
}
