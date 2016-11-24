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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;
import gwt.material.design.jquery.client.api.Functions;

import static gwt.material.design.client.js.JsMaterialElement.$;

/**
 * Provides core and meaningful animation
 *
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

    public static void stopAnimation(Widget w) {
        w.removeStyleName(CssName.INFINITE);
    }

    public static void animate(final Transition transition,
                               final Widget w,
                               int delayMillis,
                               final int durationMillis,
                               final Functions.Func callback,
                               final boolean infinite) {
        final JsMaterialElement element = $(w.getElement());

        element.css("animation-duration", durationMillis + "ms");
        element.css("-webkit-animation-duration", durationMillis + "ms");

        switch (transition) {
            case SHOW_STAGGERED_LIST:
                if (w instanceof UnorderedList) {
                    UnorderedList ul = (UnorderedList) w;

                    for (Widget li : ul) {
                        if (li instanceof ListItem) {
                            li.getElement().getStyle().setOpacity(0);
                        }
                    }
                }
                break;
            case SHOW_GRID:
                w.getElement().getStyle().setOpacity(0);
                break;
            default:
                break;
        }

        new Timer() {
            @Override
            public void run() {
                switch (transition) {
                    case SHOW_STAGGERED_LIST:
                        JsMaterialElement.showStaggeredList(element);
                        break;
                    case FADE_IN_IMAGE:
                        JsMaterialElement.fadeInImage(element);
                        break;
                    case SHOW_GRID:
                        w.addStyleName(CssName.DISPLAY_ANIMATION);
                        JsMaterialElement.closeGrid(element);
                        break;
                    case CLOSE_GRID:
                        w.addStyleName(CssName.DISPLAY_ANIMATION);
                        JsMaterialElement.closeGrid(element);
                        break;
                    default:
                        // For core animation components
                        if (infinite) {
                            w.addStyleName(CssName.INFINITE);
                        }
                        w.addStyleName("animated " + transition.getCssName());
                        animationFinishedCallback(w.getElement(), "animated " + transition.getCssName(), durationMillis, callback);
                        break;
                }
            }
        }.schedule(delayMillis);

        w.removeStyleName(CssName.MATERIALIZE_CSS);
    }

    protected static void animationFinishedCallback(Element element, String oldClass, int durationMillis, Functions.Func callback) {
        new Timer() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.call();
                }
                $(element).removeClass(oldClass);
            }
        }.schedule(durationMillis);
    }
}
