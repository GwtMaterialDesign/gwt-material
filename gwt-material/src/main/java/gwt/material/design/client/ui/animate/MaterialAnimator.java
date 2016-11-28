package gwt.material.design.client.ui.animate;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 GwtMaterial
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

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.jquery.client.api.Functions;

/**
 * Provides core and meaningful animation.
 *
 * @deprecated use {@link MaterialAnimation}.
 * @author kevzlou7979
 */
public class MaterialAnimator {

    public static void animate(final Transition transition, final Widget w, int delayMillis, Functions.Func callback) {
        animate(transition, w, delayMillis, 800, callback, false);
    }

    public static void animate(final Transition transition, final Widget w, int delayMillis, int durationMillis) {
        animate(transition, w, delayMillis, durationMillis, null, false);
    }

    public static void animate(final Transition transition, final Widget w, int delayMillis, boolean infinite) {
        animate(transition, w, delayMillis, 800, null, infinite);
    }

    public static void animate(final Transition transition, final Widget w, int delayMillis) {
        animate(transition, w, delayMillis, 800, null, false);
    }

    public static void animate(final Transition transition,
                               final Widget w,
                               int delayMillis,
                               final int durationMillis,
                               final Functions.Func callback,
                               final boolean infinite) {
        new MaterialAnimation()
                .transition(transition)
                .delayMillis(delayMillis)
                .durationMillis(durationMillis)
                .infinite(infinite)
                .animate(w, callback);
    }
}
