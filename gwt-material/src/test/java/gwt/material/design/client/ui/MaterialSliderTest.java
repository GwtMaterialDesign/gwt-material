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

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.UnorderedList;

/**
 * Test case for Slider.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialSliderTest extends MaterialWidgetTest<MaterialSlider> {

    @Override
    protected MaterialSlider createWidget() {
        MaterialSlider slider = new MaterialSlider();
        for (int i = 1; i <= 5; i++) {
            MaterialSlideItem item = new MaterialSlideItem();
            MaterialImage image = new MaterialImage();
            MaterialSlideCaption caption = new MaterialSlideCaption();
            item.add(image);
            item.add(caption);
            slider.add(item);
        }
        return slider;
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.SLIDER);
    }

    public void testTransitionDuration() {
        // given
        MaterialSlider slider = getWidget();

        // when / then
        final int UPDATED_DURATION = 0;
        final int UPDATED_INTERVAL = 0;
        slider.setDuration(UPDATED_DURATION);
        slider.setInterval(UPDATED_INTERVAL);
        assertEquals(UPDATED_DURATION, slider.getDuration());
        assertEquals(UPDATED_INTERVAL, slider.getInterval());
    }

    public void testSliderItems() {
        // given
        MaterialSlider slider = getWidget();

        // when / then
        assertNotNull(slider.getWidget(0));
        assertTrue(slider.getWidget(0) instanceof UnorderedList);
        UnorderedList ul = (UnorderedList) slider.getWidget(0);
        assertTrue(ul.getElement().hasClassName(CssName.SLIDES));

        // Check the children of Slides Container (Unordered List)
        assertEquals(5, ul.getChildren().size());
        for (Widget w : ul.getChildren()) {
            assertTrue(w instanceof MaterialSlideItem);
            MaterialSlideItem item = (MaterialSlideItem) w;
            assertTrue(item.getWidget(0) instanceof MaterialImage);
            assertTrue(item.getWidget(1) instanceof MaterialSlideCaption);
        }
    }

    public void testFullscreen() {
        MaterialSlider slider = getWidget();

        slider.setFullscreen(true);
        assertTrue(slider.isFullscreen());
        assertTrue(slider.getElement().hasClassName(CssName.FULLSCREEN));

        slider.setFullscreen(false);
        assertFalse(slider.isFullscreen());
        assertFalse(slider.getElement().hasClassName(CssName.FULLSCREEN));
    }

    public void testStartAndPause() {
        MaterialSlider slider = getWidget();

        slider.setDuration(0);
        slider.pause();


        slider.start();
    }
}
