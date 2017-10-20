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
import gwt.material.design.client.constants.ImageType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Image.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialImageTest extends MaterialWidgetTest<MaterialImage> {

    @Override
    protected MaterialImage createWidget() {
        return new MaterialImage();
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.RESPONSIVE_IMG);
    }

    public void testTypes() {
        // given
        MaterialImage image = getWidget();

        // when / then
        image.setType(ImageType.CIRCLE);
        assertEquals(ImageType.CIRCLE, image.getType());
        assertTrue(image.getElement().hasClassName(ImageType.CIRCLE.getCssName()));
        image.setType(ImageType.MATERIALBOXED);
        assertEquals(ImageType.MATERIALBOXED, image.getType());
        assertTrue(image.getElement().hasClassName(ImageType.MATERIALBOXED.getCssName()));
        image.setType(ImageType.DEFAULT);
        assertEquals("", image.getType().getCssName());
    }

    public void testCaption() {
        // given
        MaterialImage image = getWidget();

        // when / then
        final String CAPTION = "Caption";
        image.setCaption(CAPTION);
        assertEquals(CAPTION, image.getCaption());
        assertTrue(image.getElement().hasAttribute("data-caption"));
        assertEquals(CAPTION, image.getElement().getAttribute("data-caption"));
    }

    public void testUrl() {
        // given
        MaterialImage image = getWidget();

        // when / then
        final String URL = "test.png";
        image.setUrl(URL);
        assertEquals(URL, image.getUrl());
    }
}
