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
package gwt.material.design.client.ui.animate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.ui.animate.debugger.AnimationGlobalConfig;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;
import gwt.material.design.jquery.client.api.Functions;

import static gwt.material.design.client.js.JsMaterialElement.$;

/**
 * Stateful object holding animation details.
 * Default behaviour is a bounce transition for 800ms.
 */
public class MaterialAnimation implements Animation {

    private Widget widget;
    private Transition transition = Transition.BOUNCE;
    private int delay = 0;
    private int duration = 800;
    private boolean infinite;
    private Functions.Func startCallback, completeCallback;
    private Timer startTimer, endTimer;

    public MaterialAnimation() {
    }

    public MaterialAnimation(Widget widget) {
        this.widget = widget;
    }

    public MaterialAnimation transition(Transition transition) {
        setTransition(transition);
        return this;
    }

    public MaterialAnimation delay(int delay) {
        setDelay(delay);
        return this;
    }

    public MaterialAnimation duration(int duration) {
        setDuration(duration);
        return this;
    }

    public MaterialAnimation infinite(boolean infinite) {
        setInfinite(infinite);
        return this;
    }

    public MaterialAnimation completeCallback(Functions.Func completeCallback) {
        setCompleteCallback(completeCallback);
        return this;
    }

    public MaterialAnimation startCallback(Functions.Func startCallback) {
        setStartCallback(startCallback);
        return this;
    }

    public void animate(Widget widget) {
        animate(widget, completeCallback);
    }

    public void animate(Functions.Func callback) {
        animate(widget, callback);
    }

    public void animate(Widget widget, Functions.Func callback) {

        if (AnimationGlobalConfig.ENABLE_DEBUGGING) GWT.log(toString());

        if (widget != null) {
            this.widget = widget;
        } else {
            throw new NullPointerException("Cannot animate on a null widget.");
        }

        if (startTimer != null) {
            // Exit early since we are already animating.
            return;
        }

        final JsMaterialElement element = $(widget.getElement());

        element.css("animation-duration", duration + "ms");
        element.css("-webkit-animation-duration", duration + "ms");

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

                if (startCallback != null) {
                    startCallback.call();
                }

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
                        if (endTimer == null) {
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
                            endTimer.schedule(duration);
                        }
                        break;
                }
            }
        };
        if (delay < 1) {
            startTimer.run();
        } else {
            startTimer.schedule(delay);
        }

        widget.removeStyleName(CssName.MATERIALIZE_CSS);
    }

    /**
     * Stop an animation.
     */
    public void stopAnimation() {
        if (widget != null) {
            widget.removeStyleName("animated");
            widget.removeStyleName(transition.getCssName());
            widget.removeStyleName(CssName.INFINITE);
        }
    }

    @Override
    public void animate() {
        animate(widget);
    }

    @Override
    public Widget getWidget() {
        return widget;
    }

    @Override
    public void setDelay(int delay) {
        this.delay = (int) (delay * AnimationGlobalConfig.SPEED.getValue());
    }

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = (int) (duration * AnimationGlobalConfig.SPEED.getValue());
    }

    @Override
    public int getDuration() {
        return duration;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public boolean isInfinite() {
        return infinite;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
        stopAnimation();
    }

    @Override
    public Functions.Func getCompleteCallback() {
        return completeCallback;
    }

    @Override
    public void setStartCallback(Functions.Func startCallback) {
        this.startCallback = startCallback;
    }

    @Override
    public Functions.Func getStartCallback() {
        return startCallback;
    }

    @Override
    public void setCompleteCallback(Functions.Func completeCallback) {
        this.completeCallback = completeCallback;
    }

    @Override
    public String toString() {
        return "MaterialAnimation{" +
            "transition=" + transition +
            ", delay=" + delay +
            ", duration=" + duration +
            ", infinite=" + infinite +
            '}';
    }
}
