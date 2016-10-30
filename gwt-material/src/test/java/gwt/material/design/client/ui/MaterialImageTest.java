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

import gwt.material.design.client.constants.ImageType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Image
 *
 * @author kevzlou7979
 */
public class MaterialImageTest extends MaterialWidgetTest {

    public void init() {
        MaterialImage image = new MaterialImage();
        checkWidget(image);
        checkTypes(image);
        checkCaption(image);
        checkUrl(image);
    }

    public <T extends MaterialImage> void checkTypes(T image) {
        image.setType(ImageType.CIRCLE);
        assertEquals(image.getType(), ImageType.CIRCLE);
        assertTrue(image.getElement().hasClassName(ImageType.CIRCLE.getCssName()));
        image.setType(ImageType.MATERIALBOXED);
        assertEquals(image.getType(), ImageType.MATERIALBOXED);
        assertTrue(image.getElement().hasClassName(ImageType.MATERIALBOXED.getCssName()));
        image.setType(ImageType.DEFAULT);
        assertEquals(image.getType().getCssName(), "");
    }

    public <T extends MaterialImage> void checkCaption(T image) {
        final String CAPTION = "Caption";
        image.setCaption(CAPTION);
        assertEquals(image.getCaption(), CAPTION);
        assertTrue(image.getElement().hasAttribute("data-caption"));
        assertEquals(image.getElement().getAttribute("data-caption"), CAPTION);
    }

    public <T extends MaterialImage> void checkUrl(T image) {
        final String URL = "test.png";
        image.setUrl(URL);
        assertEquals(image.getUrl(), URL);
    }
}
