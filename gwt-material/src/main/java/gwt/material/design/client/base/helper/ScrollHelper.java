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
import gwt.material.design.client.constants.OffsetPosition;
import gwt.material.design.client.js.ScrollOption;
import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.jquery.client.api.JQueryElement;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * A set of scroll helper utils for GMD Widgets
 *
 * @author kevzlou7979@gmail.com
 */
public class ScrollHelper {

    private OffsetPosition offsetPosition = OffsetPosition.TOP;
    private Element containerElement;
    private Functions.Func completeCallback;
    private String easing = "swing";
    private int addedScrollOffset;
    private int duration = 400;
    private double offset;

    public ScrollHelper() {
        containerElement = getDefaultContainer();
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
        scrollTo(extractOffsetPosition(target));
    }

    /**
     * Will scroll to target offset.
     */
    public void scrollTo(double offset) {
        this.offset = offset;

        ScrollOption option = new ScrollOption();
        JQueryElement target = getContainerElement();
        if (containerElement != getDefaultContainer()) {
            offset = target.scrollTop() - target.offset().top + offset;
        } else {
            target = $("html, body");
        }
        option.scrollTop = offset - addedScrollOffset;

        target.animate(option, duration, easing, () -> {
            if (completeCallback != null) {
                completeCallback.call();
            }
        });
    }

    /**
     * Will detect if {@link #containerElement} has horizontal scrollbar.
     */
    public boolean hasHorizontalScrollbar() {
        return containerElement.getScrollWidth() > containerElement.getClientWidth();
    }

    /**
     * Will detect if {@link #containerElement} has vertical scrollbar.
     */
    public boolean hasVerticalScrollbar() {
        return containerElement.getScrollHeight() > containerElement.getClientHeight();
    }

    /**
     * Will recalculate the offset provided by the positioning based on the container's height
     * See {@link #setOffsetPosition(OffsetPosition)}
     */
    protected double extractOffsetPosition(Element target) {
        double offsetTop = $(target).offset().top;
        double containerHeight = $(containerElement).height();
        switch (getOffsetPosition()) {
            case MIDDLE:
                offsetTop = offsetTop - (containerHeight / 2) + ($(target).height() / 2);
                break;
            case BOTTOM:
                offsetTop = (offsetTop - containerHeight) + $(target).height();
                break;
        }

        return offsetTop;
    }

    /**
     * Will perform a detection whether the widget is in the View Port Scope or not, providing
     * the {@link #containerElement} as the wrapper or container of the target element.
     * <p/>
     * Note: By default if you didn't provide a container, the html or body element should be
     * applied as the target's container.
     *
     * @param widget The widget you are checking if it's inside the viewport scope.
     */
    public boolean isInViewPort(Widget widget) {
        return isInViewPort(widget.getElement());
    }

    public boolean isInViewPort(Widget widget, double addedHeight) {
        return isInViewPort(widget.getElement(), addedHeight);
    }

    /**
     * Will perform a detection whether the element is in the View Port Scope or not, providing
     * the {@link #containerElement} as the wrapper or container of the target element.
     * <p/>
     * Note: By default if you didn't provide a container, the html or body element should be
     * applied as the target's container.
     *
     * @param element The element you are checking if it's inside the viewport scope.
     */
    public boolean isInViewPort(Element element) {
        return isInViewPort(element, 0);
    }

    public boolean isInViewPort(Element element, double addedHeight) {
        double elementTop = getElementTop(element) + addedHeight;
        double elementBottom = elementTop + $(element).outerHeight() + addedHeight;

        JQueryElement target = getContainerElement();
        double viewportTop = target.scrollTop();

        if (target.asElement() != getDefaultContainer()) {
            viewportTop = getElementTop(target.asElement());
        }

        double viewportBottom = viewportTop + target.height();

        return elementBottom > viewportTop && elementTop < viewportBottom;
    }

    protected native double getElementTop(Element element) /*-{
        var rectObject = element.getBoundingClientRect();
        return rectObject.top;
    }-*/;

    protected Element getDefaultContainer() {
        return $("html, body").asElement();
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

    public int getAddedScrollOffset() {
        return addedScrollOffset;
    }

    /**
     * Additional offset height in pixels to be added on overall scroll offset once called {@link #scrollTo(double)}
     */
    public void setAddedScrollOffset(int addedScrollOffset) {
        this.addedScrollOffset = addedScrollOffset;
    }

    public JQueryElement getContainerElement() {
        if (containerElement == null) {
            return $("html, body");
        }
        return $(containerElement);
    }

    public OffsetPosition getOffsetPosition() {
        return offsetPosition;
    }

    /**
     * Will set the desired offset position of the target element.
     * Available options are TOP, MIDDLE and BOTTOM.
     */
    public void setOffsetPosition(OffsetPosition offsetPosition) {
        this.offsetPosition = offsetPosition;
    }
}
