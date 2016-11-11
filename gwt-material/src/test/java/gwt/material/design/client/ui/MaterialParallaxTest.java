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

import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.Div;

/**
 * Test case for Parallax
 *
 * @author kevzlou7979
 */
public class MaterialParallaxTest extends MaterialWidgetTest {

    public void init() {
        MaterialParallax parallax = new MaterialParallax();
        checkWidget(parallax);
        checkStructure(parallax);
        generateParallaxItems(parallax);
    }

    public <T extends MaterialParallax> void checkStructure(T parallax) {
        assertNotNull(parallax.getWidget(0));
        assertTrue(parallax.getWidget(0) instanceof Div);
        assertTrue(parallax.getWidget(0).getElement().hasClassName(CssName.PARALLAX));
    }

    protected void generateParallaxItems(MaterialParallax parallax) {
        for (int i = 1; i <= 5; i++) {
            MaterialImage image = new MaterialImage();
            parallax.add(image);
            assertTrue(parallax.getWidget(0) instanceof Div);
            Div div = (Div) parallax.getWidget(0);
            assertNotNull(div.getWidget(i - 1));
            assertTrue(div.getWidget(i - 1) instanceof MaterialImage);
        }
    }
}
