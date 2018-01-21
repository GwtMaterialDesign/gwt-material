/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gwt.material.design.client.base.helper;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.js.ScrollOption;
import gwt.material.design.jquery.client.api.Functions;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * A set of scroll helper utils for GMD Widgets
 *
 * @author kevzlou7979@gmail.com
 */
public class ScrollHelper {

    private Element containerElement;
    private Functions.Func completeCallback;
    private String easing = "swing";
    private int duration = 400;
    private double offset;

    public Element getContainerElement() {
        return containerElement;
    }

    /**
     * The container element of which the scrolling feature will be applied.
     */
    public void setContainerElement(Element containerElement) {
        this.containerElement = containerElement;
    }

    /**
     * The container of which the scrolling feature will be applied.
     */
    public void setContainer(Widget widget) {
        this.containerElement = widget.getElement();
    }

    public Functions.Func getCompleteCallback() {
        return completeCallback;
    }

    /**
     * A function to call once the scrolling is complete, called once per matched element.
     */
    public void setCompleteCallback(Functions.Func completeCallback) {
        this.completeCallback = completeCallback;
    }

    public String getEasing() {
        return easing;
    }

    /**
     * An easing function specifies the speed at which the animation progresses at different points within the animation.
     * The only easing implementations in the jQuery library are the default, called swing, and one that progresses at a
     * constant pace, called linear.
     */
    public void setEasing(String easing) {
        this.easing = easing;
    }

    public double getOffset() {
        return offset;
    }

    public int getDuration() {
        return duration;
    }

    /**
     * Determines how long the animation will run in milliseconds.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Will scroll target widget.
     */
    public void scrollTo(Widget widget) {
        scrollTo(widget.getElement());
    }

    /**
     * Will scroll to target element.
     */
    public void scrollTo(Element target) {
        scrollTo($(target).offset().top);
    }

    /**
     * Will scroll to target offset.
     */
    public void scrollTo(double offset) {
        this.offset = offset;
        ScrollOption option = new ScrollOption();
        option.scrollTop = offset;

        if (containerElement == null) {
            containerElement = $("html, body").asElement();
        }

        $(containerElement).animate(option, duration, easing, () -> {
            if (completeCallback != null) {
                completeCallback.call();
            }
        });
    }
}
