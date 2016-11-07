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
package gwt.material.design.client.ui.animation;

import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Core Animations
 *
 * @author kevzlou7979
 */
public class AnimationTest extends MaterialWidgetTest {

    public void init() {
        checkAnimation();
    }

    protected void checkAnimation() {
        MaterialPanel panel = new MaterialPanel();
        RootPanel.get().add(panel);
        MaterialAnimation animation = new MaterialAnimation();
        animation.delayMillis(0);
        assertEquals(animation.getDelayMillis(), 0);
        animation.infinite(true);
        assertTrue(animation.isInfinite());
        animation.infinite(false);
        assertFalse(animation.isInfinite());
        animation.durationMillis(20);
        assertEquals(animation.getDurationMillis(), 20);
        animation.transition(Transition.FADEIN);
        assertEquals(animation.getTransition(), Transition.FADEIN);
        animation.animate(panel);
        assertEquals(animation.getWidget(), panel);
        // Check Advance Logic
        String WEBKIT_ANIMATION_DURATION = panel.getElement().getStyle().getProperty("WebkitAnimationDuration");
        assertEquals(WEBKIT_ANIMATION_DURATION, animation.getDurationMillis() + "ms");
    }
}
