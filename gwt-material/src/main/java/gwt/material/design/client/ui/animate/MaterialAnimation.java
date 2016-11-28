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
package gwt.material.design.client.ui.animate;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;
import gwt.material.design.jquery.client.api.Functions;

import static gwt.material.design.client.js.JsMaterialElement.$;

/**
 * Stateful object holding animation details.
 * Default behaviour is a bounce transition for 800ms.
 */
public class MaterialAnimation {

    private Widget widget;
    private Transition transition = Transition.BOUNCE;
    private int delayMillis = 0;
    private int durationMillis = 800;
    private boolean infinite;

    private Timer startTimer, endTimer;

    public MaterialAnimation() {
    }

    public MaterialAnimation(Widget widget) {
        this.widget = widget;
    }

    public MaterialAnimation transition(Transition transition) {
        this.transition = transition;
        return this;
    }

    public MaterialAnimation delayMillis(int delayMillis) {
        this.delayMillis = delayMillis;
        return this;
    }


    public MaterialAnimation durationMillis(int durationMillis) {
        this.durationMillis = durationMillis;
        return this;
    }

    public MaterialAnimation infinite(boolean infinite) {
        this.infinite = infinite;
        return this;
    }

    public void animate() {
        animate(widget);
    }

    public void animate(Widget widget) {
        animate(widget, null);
    }

    public void animate(Functions.Func callback) {
        animate(widget, callback);
    }

    public void animate(Widget widget, Functions.Func callback) {
        if(widget != null) {
            this.widget = widget;
        } else {
            throw new NullPointerException("Cannot animate on a null widget.");
        }

        if(startTimer != null) {
            // Exit early since we are already animating.
            return;
        }

        final JsMaterialElement element = $(widget.getElement());

        element.css("animation-duration", durationMillis + "ms");
        element.css("-webkit-animation-duration", durationMillis + "ms");

        switch (transition) {
            case SHOW_STAGGERED_LIST:
                if (widget instanceof UnorderedList) {
                    UnorderedList ul = (UnorderedList) widget;

                    for (Widget li : ul) {
                        if (li instanceof ListItem) {
                            li.getElement().getStyle().setOpacity(0);
                        }
                    }
                }
                break;
            case SHOW_GRID:
                widget.getElement().getStyle().setOpacity(0);
                break;
            default:
                break;
        }

        startTimer = new Timer() {
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
                    widget.addStyleName(CssName.DISPLAY_ANIMATION);
                    JsMaterialElement.showGrid(element);
                    break;
                case CLOSE_GRID:
                    widget.addStyleName(CssName.DISPLAY_ANIMATION);
                    JsMaterialElement.closeGrid(element);
                    break;
                default:
                    // For core animation components
                    if (infinite) {
                        widget.addStyleName(CssName.INFINITE);
                    }
                    widget.addStyleName("animated " + transition.getCssName());

                    // Only start the end timer if its not already active.
                    if(endTimer == null) {
                        endTimer = new Timer() {
                            @Override
                            public void run() {
                                if (callback != null) {
                                    callback.call();
                                }
                                if (!infinite) {
                                    $(element).removeClass("animated " + transition.getCssName());
                                }

                                endTimer = null;
                                startTimer = null;
                            }
                        };
                        endTimer.schedule(durationMillis);
                    }
                    break;
                }
            }
        };
        startTimer.schedule(delayMillis);

        widget.removeStyleName(CssName.MATERIALIZE_CSS);
    }

    /**
     * Stop an animation.
     */
    public void stopAnimation() {
        if(widget != null) {
            widget.removeStyleName("animated");
            widget.removeStyleName(transition.getCssName());
            widget.removeStyleName(CssName.INFINITE);
        }
    }

    public Widget getWidget() {
        return widget;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public int getDelayMillis() {
        return delayMillis;
    }

    public void setDelayMillis(int delayMillis) {
        this.delayMillis = delayMillis;
    }

    public int getDurationMillis() {
        return durationMillis;
    }

    public void setDurationMillis(int durationMillis) {
        this.durationMillis = durationMillis;
    }

    public boolean isInfinite() {
        return infinite;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
        stopAnimation();
    }
}
