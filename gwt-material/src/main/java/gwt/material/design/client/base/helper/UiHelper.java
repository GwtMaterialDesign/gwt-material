package gwt.material.design.client.base.helper;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 *  This static helper class is supposed to collect common methods used for multiple kind of UI classes.
 *  It is defined as abstract to prohibit 
 *
 */
public final class UiHelper {

    private static String PRESSED_CSS_STYLE_NAME = "pressed";

    /**
     * Adds a mouse pressed handler to a widget. Adds a CSS style ('pressed',
     * {@link #PRESSED_CSS_STYLE_NAME}) to the widget as long as the mouse is
     * pressed (or the user touches the widget). See
     * {@link #addMousePressedHandlers(Widget, String)}.
     *
     * @param widget The widget to which the "pressed" style musst be applied
     */
    public static void addMousePressedHandlers(final Widget widget) {
        addMousePressedHandlers(widget, PRESSED_CSS_STYLE_NAME);
    }

    /**
     * Adds a mouse pressed handler to a widget. Adds a CSS style to the widget
     * as long as the mouse is pressed (or the user touches the widget on mobile browser).
     *
     * @param widget The widget to which the style must be applied for mouse/touch event
     * @param cssStyleName CSS style name to be applied
     */
    public static void addMousePressedHandlers(final Widget widget, final String cssStyleName) {
        widget.sinkEvents(Event.ONMOUSEDOWN);
        widget.sinkEvents(Event.ONMOUSEUP);
        widget.sinkEvents(Event.ONMOUSEOUT);
        widget.sinkEvents(Event.TOUCHEVENTS);

        widget.addHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                widget.addStyleName(cssStyleName);
            }
        }, MouseDownEvent.getType());

        widget.addHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) {
                widget.removeStyleName(cssStyleName);
            }
        }, MouseUpEvent.getType());

        widget.addHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                widget.removeStyleName(cssStyleName);
            }
        }, MouseOutEvent.getType());

        // Touch Events
        widget.addHandler(new TouchStartHandler() {
            @Override
            public void onTouchStart(TouchStartEvent event) {
                widget.addStyleName(cssStyleName);
            }
        }, TouchStartEvent.getType());

        widget.addHandler(new TouchEndHandler() {
            @Override
            public void onTouchEnd(TouchEndEvent event) {
                widget.removeStyleName(cssStyleName);

            }
        }, TouchEndEvent.getType());

        widget.addHandler(new TouchCancelHandler() {
            @Override
            public void onTouchCancel(TouchCancelEvent event) {
                widget.removeStyleName(cssStyleName);
            }
        }, TouchCancelEvent.getType());
    }

    public static int calculateSpaceToBottom(Widget widget) {
        return Window.getClientHeight() - widget.getAbsoluteTop() - widget.getOffsetHeight();
    }
}
