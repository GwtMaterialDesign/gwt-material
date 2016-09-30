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
import org.junit.Test;

public class MaterialImageTest extends MaterialWidgetTest {

    @Test
    public void testImage() {
        MaterialImage image = new MaterialImage();
        checkWidget(image);
        checkTypes(image);
        checkCaption(image);
        checkUrl(image);
    }

    public <T extends MaterialImage> void checkTypes(T widget) {
        widget.setType(ImageType.CIRCLE);
        assertEquals(widget.getType(), ImageType.CIRCLE);
        assertTrue(widget.getElement().hasClassName(ImageType.CIRCLE.getCssName()));
        widget.setType(ImageType.MATERIALBOXED);
        assertEquals(widget.getType(), ImageType.MATERIALBOXED);
        assertTrue(widget.getElement().hasClassName(ImageType.MATERIALBOXED.getCssName()));
        widget.setType(ImageType.DEFAULT);
        assertEquals(widget.getType().getCssName(), "");
    }

    public <T extends MaterialImage> void checkCaption(T widget) {
        final String CAPTION = "Caption";
        widget.setCaption(CAPTION);
        assertEquals(widget.getCaption(), CAPTION);
        assertTrue(widget.getElement().hasAttribute("data-caption"));
        assertEquals(widget.getElement().getAttribute("data-caption"), CAPTION);
    }

    public <T extends MaterialImage> void checkUrl(T widget) {
        final String URL = "test.png";
        widget.setUrl(URL);
        assertEquals(widget.getUrl(), URL);
    }
}
