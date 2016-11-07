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
package gwt.material.design.client.ui.base;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.GwtEvent;
import gwt.material.design.client.base.HasSearchHandlers;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.events.SearchFinishEvent;
import gwt.material.design.client.events.SearchNoResultEvent;
import gwt.material.design.client.ui.MaterialSearch;
import junit.framework.TestCase;

/**
 * Test case for GMD Events
 *
 * @author kevzlou7979
 */
public class BaseEventTest extends TestCase {

    protected <T extends MaterialWidget> void checkInteractionEvents(T widget, boolean enabled) {
        checkClickAndDoubleClickEvent(widget, enabled);
        checkAllMouseEvent(widget, enabled);
        checkAllTouchEvent(widget, enabled);
        checkAllGestureEvent(widget, enabled);
        checkAllKeyEvent(widget, enabled);
        checkFocusEvent(widget, enabled);
    }

    protected <T extends MaterialWidget> void checkFocusEvent(T widget, boolean enabled) {
        widget.setEnabled(enabled);
        // Check Focus
        final boolean[] isFocusFired = {false};
        widget.addFocusHandler(focusEvent -> isFocusFired[0] = true);
        fireFocusEvent(widget);

        // Check Blur
        final boolean[] isBlurFired = {false};
        widget.addBlurHandler(blurEvent -> isBlurFired[0] = true);
        fireBlurEvent(widget);

        assertEquals(isFocusFired[0], enabled);
        assertEquals(isBlurFired[0], enabled);
    }

    public <T extends MaterialWidget> void fireFocusEvent(T widget) {
        widget.fireEvent(new GwtEvent<FocusHandler>() {
            @Override
            public Type<FocusHandler> getAssociatedType() {
                return FocusEvent.getType();
            }

            @Override
            protected void dispatch(FocusHandler eventHandler) {
                eventHandler.onFocus(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireBlurEvent(T widget) {
        widget.fireEvent(new GwtEvent<BlurHandler>() {
            @Override
            public Type<BlurHandler> getAssociatedType() {
                return BlurEvent.getType();
            }

            @Override
            protected void dispatch(BlurHandler eventHandler) {
                eventHandler.onBlur(null);
            }
        });
    }

    protected <T extends MaterialWidget> void checkClickAndDoubleClickEvent(T widget, boolean enabled) {
        widget.setEnabled(enabled);
        // Check Click Event
        final boolean[] isClickFired = {false};
        widget.addClickHandler(clickEvent -> isClickFired[0] = true);
        fireClickEvent(widget);

        // Check Double Click Event
        final boolean[] isDoubleClickFired = {false};
        widget.addDoubleClickHandler(doubleClickEvent -> isDoubleClickFired[0] = true);
        fireDoubleClickEvent(widget);

        assertEquals(isClickFired[0], enabled);
        assertEquals(isDoubleClickFired[0], enabled);
    }

    public <T extends MaterialWidget> void fireClickEvent(T widget) {
        widget.fireEvent(new GwtEvent<ClickHandler>() {
            @Override
            public Type<ClickHandler> getAssociatedType() {
                return ClickEvent.getType();
            }

            @Override
            protected void dispatch(ClickHandler eventHandler) {
                eventHandler.onClick(null);
            }
        });
    }


    public <T extends MaterialWidget> void fireDoubleClickEvent(T widget) {
        widget.fireEvent(new GwtEvent<DoubleClickHandler>() {
            @Override
            public Type<DoubleClickHandler> getAssociatedType() {
                return DoubleClickEvent.getType();
            }

            @Override
            protected void dispatch(DoubleClickHandler eventHandler) {
                eventHandler.onDoubleClick(null);
            }
        });
    }

    protected <T extends MaterialWidget> void checkAllKeyEvent(T widget, boolean enabled) {
        widget.setEnabled(enabled);
        // Key Down
        final boolean[] isKeyDownFired = {false};
        widget.addKeyDownHandler(keyDownEvent -> isKeyDownFired[0] = true);
        fireKeyDownEvent(widget);

        // Key Up
        final boolean[] isKeyUpFired = {false};
        widget.addKeyUpHandler(keyUpEvent -> isKeyUpFired[0] = true);
        fireKeyUpEvent(widget);

        // Key Press
        final boolean[] isKeyPressFired = {false};
        widget.addKeyPressHandler(keyPressEvent -> isKeyPressFired[0] = true);
        fireKeyPressEvent(widget);

        assertEquals(isKeyDownFired[0], enabled);
        assertEquals(isKeyUpFired[0], enabled);
        assertEquals(isKeyPressFired[0], enabled);
    }

    public <T extends MaterialWidget> void fireKeyDownEvent(T widget) {
        widget.fireEvent(new GwtEvent<KeyDownHandler>() {
            @Override
            public Type<KeyDownHandler> getAssociatedType() {
                return KeyDownEvent.getType();
            }

            @Override
            protected void dispatch(KeyDownHandler eventHandler) {
                eventHandler.onKeyDown(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireKeyUpEvent(T widget) {
        widget.fireEvent(new GwtEvent<KeyUpHandler>() {
            @Override
            public Type<KeyUpHandler> getAssociatedType() {
                return KeyUpEvent.getType();
            }

            @Override
            protected void dispatch(KeyUpHandler eventHandler) {
                eventHandler.onKeyUp(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireKeyPressEvent(T widget) {
        widget.fireEvent(new GwtEvent<KeyPressHandler>() {
            @Override
            public Type<KeyPressHandler> getAssociatedType() {
                return KeyPressEvent.getType();
            }

            @Override
            protected void dispatch(KeyPressHandler eventHandler) {
                eventHandler.onKeyPress(null);
            }
        });
    }

    protected <T extends MaterialWidget> void checkAllGestureEvent(T widget, boolean enabled) {
        widget.setEnabled(enabled);
        // Gesture Start
        final boolean[] isGestureStartFired = {false};
        widget.addGestureStartHandler(gestureStartEvent -> isGestureStartFired[0] = true);
        fireGestureStartEvent(widget);

        // Gesture End
        final boolean[] isGestureEndFired = {false};
        widget.addGestureEndHandler(gestureEndEvent -> isGestureEndFired[0] = true);
        fireGestureEndEvent(widget);

        // Gesture Change
        final boolean[] isGestureChangeFired = {false};
        widget.addGestureChangeHandler(gestureChangeEvent -> isGestureChangeFired[0] = true);
        fireGestureChangeEvent(widget);

        assertEquals(isGestureStartFired[0], enabled);
        assertEquals(isGestureChangeFired[0], enabled);
        assertEquals(isGestureEndFired[0], enabled);
    }

    public <T extends MaterialWidget> void fireGestureStartEvent(T widget) {
        widget.fireEvent(new GwtEvent<GestureStartHandler>() {
            @Override
            public Type<GestureStartHandler> getAssociatedType() {
                return GestureStartEvent.getType();
            }

            @Override
            protected void dispatch(GestureStartHandler eventHandler) {
                eventHandler.onGestureStart(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireGestureChangeEvent(T widget) {
        widget.fireEvent(new GwtEvent<GestureChangeHandler>() {
            @Override
            public Type<GestureChangeHandler> getAssociatedType() {
                return GestureChangeEvent.getType();
            }

            @Override
            protected void dispatch(GestureChangeHandler eventHandler) {
                eventHandler.onGestureChange(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireGestureEndEvent(T widget) {
        widget.fireEvent(new GwtEvent<GestureEndHandler>() {
            @Override
            public Type<GestureEndHandler> getAssociatedType() {
                return GestureEndEvent.getType();
            }

            @Override
            protected void dispatch(GestureEndHandler eventHandler) {
                eventHandler.onGestureEnd(null);
            }
        });
    }

    protected <T extends MaterialWidget> void checkAllTouchEvent(T widget, boolean enabled) {
        widget.setEnabled(enabled);
        // Touch Start
        final boolean[] isTouchStartFired = {false};
        widget.addTouchStartHandler(touchStartEvent -> isTouchStartFired[0] = true);
        fireTouchStartEvent(widget);

        // Touch Move
        final boolean[] isTouchMoveFired = {false};
        widget.addTouchMoveHandler(touchMoveEvent -> isTouchMoveFired[0] = true);
        fireTouchMoveEvent(widget);

        // Touch End
        final boolean[] isTouchEndFired = {false};
        widget.addTouchEndHandler(touchEndEvent -> isTouchEndFired[0] = true);
        fireTouchEndEvent(widget);

        // Touch Cancel
        final boolean[] isTouchCancelFired = {false};
        widget.addTouchCancelHandler(touchCancelEvent -> isTouchCancelFired[0] = true);
        fireTouchCancelEvent(widget);

        assertEquals(isTouchStartFired[0], enabled);
        assertEquals(isTouchMoveFired[0], enabled);
        assertEquals(isTouchEndFired[0], enabled);
        assertEquals(isTouchCancelFired[0], enabled);
    }

    public <T extends MaterialWidget> void fireTouchStartEvent(T widget) {
        widget.fireEvent(new GwtEvent<TouchStartHandler>() {
            @Override
            public Type<TouchStartHandler> getAssociatedType() {
                return TouchStartEvent.getType();
            }

            @Override
            protected void dispatch(TouchStartHandler eventHandler) {
                eventHandler.onTouchStart(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireTouchMoveEvent(T widget) {
        widget.fireEvent(new GwtEvent<TouchMoveHandler>() {
            @Override
            public Type<TouchMoveHandler> getAssociatedType() {
                return TouchMoveEvent.getType();
            }

            @Override
            protected void dispatch(TouchMoveHandler eventHandler) {
                eventHandler.onTouchMove(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireTouchEndEvent(T widget) {
        widget.fireEvent(new GwtEvent<TouchEndHandler>() {
            @Override
            public Type<TouchEndHandler> getAssociatedType() {
                return TouchEndEvent.getType();
            }

            @Override
            protected void dispatch(TouchEndHandler eventHandler) {
                eventHandler.onTouchEnd(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireTouchCancelEvent(T widget) {
        widget.fireEvent(new GwtEvent<TouchCancelHandler>() {
            @Override
            public Type<TouchCancelHandler> getAssociatedType() {
                return TouchCancelEvent.getType();
            }

            @Override
            protected void dispatch(TouchCancelHandler eventHandler) {
                eventHandler.onTouchCancel(null);
            }
        });
    }

    protected <T extends MaterialWidget> void checkAllMouseEvent(T widget, boolean enabled) {
        widget.setEnabled(enabled);
        // Check Mouse Down Event
        final boolean[] isMouseDownFired = {false};
        widget.addMouseDownHandler(mouseDownEvent -> isMouseDownFired[0] = true);
        fireMouseDownEvent(widget);

        // Check Mouse Up Event
        final boolean[] isMouseUpFired = {false};
        widget.addMouseUpHandler(mouseUpEvent -> isMouseUpFired[0] = true);
        fireMouseUpEvent(widget);

        // Check Mouse Over
        final boolean[] isMouseOverFired = {false};
        widget.addMouseOverHandler(mouseOverEvent -> isMouseOverFired[0] = true);
        fireMouseOverEvent(widget);

        // Check Mouse Move
        final boolean[] isMouseMoveFired = {false};
        widget.addMouseMoveHandler(mouseMoveEvent -> isMouseMoveFired[0] = true);
        fireMouseMoveEvent(widget);

        // Check Mouse Wheel
        final boolean[] isMouseWheelFired = {false};
        widget.addMouseWheelHandler(mouseWheelEvent -> isMouseWheelFired[0] = true);
        fireMouseWheelEvent(widget);
        assertEquals(isMouseUpFired[0], widget.isEnabled());
        assertEquals(isMouseDownFired[0], widget.isEnabled());
        assertEquals(isMouseMoveFired[0], widget.isEnabled());
        assertEquals(isMouseWheelFired[0], widget.isEnabled());
        assertEquals(isMouseOverFired[0], widget.isEnabled());
    }

    public <T extends MaterialWidget> void fireMouseUpEvent(T widget) {
        widget.fireEvent(new GwtEvent<MouseUpHandler>() {
            @Override
            public Type<MouseUpHandler> getAssociatedType() {
                return MouseUpEvent.getType();
            }

            @Override
            protected void dispatch(MouseUpHandler eventHandler) {
                eventHandler.onMouseUp(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireMouseDownEvent(T widget) {
        widget.fireEvent(new GwtEvent<MouseDownHandler>() {
            @Override
            public Type<MouseDownHandler> getAssociatedType() {
                return MouseDownEvent.getType();
            }

            @Override
            protected void dispatch(MouseDownHandler eventHandler) {
                eventHandler.onMouseDown(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireMouseMoveEvent(T widget) {
        widget.fireEvent(new GwtEvent<MouseMoveHandler>() {
            @Override
            public Type<MouseMoveHandler> getAssociatedType() {
                return MouseMoveEvent.getType();
            }

            @Override
            protected void dispatch(MouseMoveHandler eventHandler) {
                eventHandler.onMouseMove(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireMouseWheelEvent(T widget) {
        widget.fireEvent(new GwtEvent<MouseWheelHandler>() {
            @Override
            public Type<MouseWheelHandler> getAssociatedType() {
                return MouseWheelEvent.getType();
            }

            @Override
            protected void dispatch(MouseWheelHandler eventHandler) {
                eventHandler.onMouseWheel(null);
            }
        });
    }

    public <T extends MaterialWidget> void fireMouseOverEvent(T widget) {
        widget.fireEvent(new GwtEvent<MouseOverHandler>() {
            @Override
            public Type<MouseOverHandler> getAssociatedType() {
                return MouseOverEvent.getType();
            }

            @Override
            protected void dispatch(MouseOverHandler eventHandler) {
                eventHandler.onMouseOver(null);
            }
        });
    }

    public <T extends MaterialSearch & HasSearchHandlers> void checkSearchEvents(T widget) {
        // Check Search Finish Event
        final boolean[] isSearchFinishEvent = {false};
        widget.addSearchFinishHandler(event -> {
            isSearchFinishEvent[0] = true;
        });
        fireSearchFinishHandler(widget);

        // Check Search No Result
        final boolean[] isSearchNoResultEvent = {false};
        widget.addSearchNoResultHandler(event -> {
            isSearchNoResultEvent[0] = true;
        });
        fireSearchNoResultHandler(widget);

        assertTrue(isSearchNoResultEvent[0]);
        assertTrue(isSearchFinishEvent[0]);
    }

    protected <T extends MaterialSearch & HasSearchHandlers> void fireSearchNoResultHandler(T widget) {
        widget.fireEvent(new GwtEvent<SearchNoResultEvent.SearchNoResultHandler>() {
            @Override
            public Type<SearchNoResultEvent.SearchNoResultHandler> getAssociatedType() {
                return SearchNoResultEvent.TYPE;
            }

            @Override
            protected void dispatch(SearchNoResultEvent.SearchNoResultHandler eventHandler) {
                eventHandler.onSearchNoResult(null);
            }
        });
    }

    protected <T extends MaterialSearch & HasSearchHandlers> void fireSearchFinishHandler(T widget) {
        widget.fireEvent(new GwtEvent<SearchFinishEvent.SearchFinishHandler>() {
            @Override
            public Type<SearchFinishEvent.SearchFinishHandler> getAssociatedType() {
                return SearchFinishEvent.TYPE;
            }

            @Override
            protected void dispatch(SearchFinishEvent.SearchFinishHandler eventHandler) {
                eventHandler.onSearchFinish(null);
            }
        });
    }

    public <T extends MaterialWidget & HasCloseHandlers> void checkCloseHandler(T widget) {
        // Check Close Event
        final boolean[] isCloseEvent = {false};
        widget.addCloseHandler(event -> {
            isCloseEvent[0] = true;
        });
        fireCloseHandler(widget);

        assertTrue(isCloseEvent[0]);
    }

    protected <T extends MaterialWidget & HasCloseHandlers> void fireCloseHandler(T widget) {
        widget.fireEvent(new GwtEvent<CloseHandler<?>>() {
            @Override
            public Type<CloseHandler<?>> getAssociatedType() {
                return CloseEvent.getType();
            }

            @Override
            protected void dispatch(CloseHandler<?> eventHandler) {
                eventHandler.onClose(null);
            }
        });
    }

    public <T extends MaterialWidget & HasOpenHandlers> void checkOpenHandler(T widget) {
        // Check Open Event
        final boolean[] isOpenEvent = {false};
        widget.addOpenHandler(event -> {
            isOpenEvent[0] = true;
        });
        fireOpenHandler(widget);

        assertTrue(isOpenEvent[0]);
    }

    protected <T extends MaterialWidget & HasOpenHandlers> void fireOpenHandler(T widget) {
        widget.fireEvent(new GwtEvent<OpenHandler<?>>() {
            @Override
            public Type<OpenHandler<?>> getAssociatedType() {
                return OpenEvent.getType();
            }

            @Override
            protected void dispatch(OpenHandler<?> eventHandler) {
                eventHandler.onOpen(null);
            }
        });
    }
}
