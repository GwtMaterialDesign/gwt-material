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
package gwt.material.design.client;

import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasColors;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import org.junit.Ignore;

@Ignore
public abstract class MaterialWidgetTestCase<T extends MaterialWidget> extends WidgetTestCase<T> {

    public void testFocusAndBlurEvents() {
        // given
        T widget = getWidget();

        // when / then
        checkFocusAndBlurEvents(widget);

        widget.setEnabled(false);
        checkFocusAndBlurEvents(widget);
    }

    protected <H extends HasAllFocusHandlers & HasEnabled> void checkFocusAndBlurEvents(H widget) {
        // Check Focus
        final boolean[] isFocusFired = {false};
        widget.addFocusHandler(focusEvent -> isFocusFired[0] = true);
        fireFocusEvent(widget);

        // Check Blur
        final boolean[] isBlurFired = {false};
        widget.addBlurHandler(blurEvent -> isBlurFired[0] = true);
        fireBlurEvent(widget);

        assertEquals(widget.isEnabled(), isFocusFired[0]);
        assertEquals(widget.isEnabled(), isBlurFired[0]);
    }

    public void fireFocusEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createFocusEvent(), widget);
    }

    public void fireBlurEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createBlurEvent(), widget);
    }

    public void testClickAndDoubleClickEvents() {
        // given
        T widget = getWidget();

        // when / then
        checkClickAndDoubleClickEvents(widget);

        widget.setEnabled(false);
        checkClickAndDoubleClickEvents(widget);
    }

    protected <H extends MaterialWidget> void checkClickAndDoubleClickEvents(H widget) {
        // Check Click Event
        final boolean[] isClickFired = {false};
        widget.addClickHandler(clickEvent -> isClickFired[0] = true);
        fireClickEvent(widget);

        // Check Double Click Event
        final boolean[] isDoubleClickFired = {false};
        widget.addDoubleClickHandler(doubleClickEvent -> isDoubleClickFired[0] = true);
        fireDoubleClickEvent(widget);

        assertEquals(widget.isEnabled(), isClickFired[0]);
        assertEquals(widget.isEnabled(), isDoubleClickFired[0]);
    }

    public void fireClickEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(
            Document.get().createClickEvent(1, 1, 1, 1, 1, false, false, false, false),
            widget
        );
    }

    public void fireDoubleClickEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(
            Document.get().createDblClickEvent(1, 1, 1, 1, 1, false, false, false, false),
            widget
        );
    }

    public void testKeyEvents() {
        // given
        T widget = getWidget();

        // when / then
        checkKeyEvents(widget);

        widget.setEnabled(false);
        checkKeyEvents(widget);
    }

    protected <H extends HasAllKeyHandlers & HasEnabled> void checkKeyEvents(H widget) {
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

        assertEquals(widget.isEnabled(), isKeyDownFired[0]);
        assertEquals(widget.isEnabled(), isKeyUpFired[0]);
        assertEquals(widget.isEnabled(), isKeyPressFired[0]);
    }

    public void fireKeyDownEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createKeyDownEvent(false, false, false, false, 1), widget);
    }

    public void fireKeyUpEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createKeyUpEvent(false, false, false, false, 1), widget);
    }

    public void fireKeyPressEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createKeyPressEvent(false, false, false, false, 1), widget);
    }

    public void testGestureEvents() {
        // given
        T widget = getWidget();

        // when / then
        checkGestureEvents(widget);

        widget.setEnabled(false);
        checkGestureEvents(widget);
    }

    protected <H extends MaterialWidget> void checkGestureEvents(H widget) {
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

        assertEquals(widget.isEnabled(), isGestureStartFired[0]);
        assertEquals(widget.isEnabled(), isGestureChangeFired[0]);
        assertEquals(widget.isEnabled(), isGestureEndFired[0]);
    }

    public void fireGestureStartEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createHtmlEvent(BrowserEvents.GESTURESTART, false, false), widget);
    }

    public void fireGestureChangeEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createHtmlEvent(BrowserEvents.GESTURECHANGE, false, false), widget);
    }

    public void fireGestureEndEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createHtmlEvent(BrowserEvents.GESTUREEND, false, false), widget);
    }

    public void testTouchEvents() {
        // given
        T widget = getWidget();

        // when / then
        checkTouchEvents(widget);

        widget.setEnabled(false);
        checkTouchEvents(widget);
    }

    protected <H extends MaterialWidget> void checkTouchEvents(H widget) {
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

        assertEquals(widget.isEnabled(), isTouchStartFired[0]);
        assertEquals(widget.isEnabled(), isTouchMoveFired[0]);
        assertEquals(widget.isEnabled(), isTouchEndFired[0]);
        assertEquals(widget.isEnabled(), isTouchCancelFired[0]);
    }

    public void fireTouchStartEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createHtmlEvent(BrowserEvents.TOUCHSTART, false, false), widget);
    }

    public void fireTouchMoveEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createHtmlEvent(BrowserEvents.TOUCHMOVE, false, false), widget);
    }

    public void fireTouchEndEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createHtmlEvent(BrowserEvents.TOUCHEND, false, false), widget);
    }

    public void fireTouchCancelEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createHtmlEvent(BrowserEvents.TOUCHCANCEL, false, false), widget);
    }

    public void testMouseEvents() {
        // given
        T widget = getWidget();

        // when / then
        checkMouseEvents(widget);

        widget.setEnabled(false);
        checkMouseEvents(widget);
    }

    protected <H extends MaterialWidget> void checkMouseEvents(H widget) {
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

    public void fireMouseUpEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(
            Document.get().createMouseUpEvent(1, 1, 1, 1, 1, false, false, false, false, 1),
            widget
        );
    }

    public void fireMouseDownEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(
            Document.get().createMouseDownEvent(1, 1, 1, 1, 1, false, false, false, false, 1),
            widget
        );
    }

    public void fireMouseMoveEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(
            Document.get().createMouseMoveEvent(1, 1, 1, 1, 1, false, false, false, false, 1),
            widget
        );
    }

    public void fireMouseWheelEvent(HasHandlers widget) {
        DomEvent.fireNativeEvent(Document.get().createHtmlEvent(BrowserEvents.MOUSEWHEEL, false, false), widget);
    }

    public <H extends HasHandlers & IsWidget> void fireMouseOverEvent(H widget) {
        DomEvent.fireNativeEvent(
            Document.get().createMouseOverEvent(1, 1, 1, 1, 1, false, false, false, false, 1, widget.asWidget().getElement()),
            widget
        );
    }

    protected <H extends UIObject & HasEnabled> void checkEnabled(HasEnabled widget, H target) {
        checkEnabled(widget, target, true);
    }

    protected <H extends UIObject & HasEnabled> void checkEnabled(HasEnabled widget, H target, boolean checkElement) {
        final Element element = target.getElement();
        if(checkElement) {
            assertFalse(element.hasClassName(CssName.DISABLED));
            assertFalse(element.hasAttribute(CssName.DISABLED));
        }
        widget.setEnabled(true);
        if(checkElement) {
            assertFalse(element.hasClassName(CssName.DISABLED));
            assertFalse(element.hasAttribute(CssName.DISABLED));
        }
        assertEquals(widget.isEnabled(), true);
        widget.setEnabled(false);
        if(checkElement) {
            assertTrue(element.hasClassName(CssName.DISABLED));
            assertTrue(element.hasAttribute(CssName.DISABLED));
        }
        assertEquals(target.isEnabled(), false);
    }

    // Generic Event Handling

    public <H extends MaterialWidget & HasOpenHandlers> void checkOpenHandler(H widget) {
        // Check Open Event
        final boolean[] fired = {false};
        widget.addOpenHandler(event -> {
            fired[0] = true;
        });
        fireOpenHandler(widget);

        assertTrue(fired[0]);
    }

    protected <H extends Widget & HasOpenHandlers> void fireOpenHandler(H widget) {
        OpenEvent.fire(widget, widget);
    }

    public <H extends Widget & HasCloseHandlers> void checkCloseHandler(H widget) {
        // Check Open Event
        final boolean[] fired = {false};
        widget.addCloseHandler(event -> fired[0] = true);
        fireCloseHandler(widget);

        assertTrue(fired[0]);
    }

    protected <H extends Widget & HasCloseHandlers> void fireCloseHandler(H widget) {
        CloseEvent.fire(widget, widget);
    }

    protected <H extends MaterialWidget & HasSelectionHandlers> void checkSelectionHandler(H widget, Object selectedItem) {
        // Check Open Event
        final boolean[] fired = {false};
        widget.addSelectionHandler(event -> {
            assertEquals(event.getSelectedItem(), selectedItem);
            fired[0] = true;
        });

        fireSelectionHandler(widget, selectedItem);
        assertTrue(fired[0]);
    }

    public <H extends MaterialWidget & HasSelectionHandlers> void fireSelectionHandler(H widget, Object index) {
        assertNotNull(widget);
        assertNotNull(index);
        SelectionEvent.fire(widget, index);
    }

    public static <H extends UIObject & HasColors> void checkColor(H hasColors) {
        final Element element = hasColors.getElement();
        hasColors.setTextColor(Color.WHITE);
        assertTrue(element.hasClassName(Color.WHITE.getCssName() + "-text"));
        hasColors.setBackgroundColor(Color.BLACK);
        assertTrue(element.hasClassName(Color.BLACK.getCssName()));
    }

    public <H extends AbstractValueWidget> void checkFieldErrorSuccess(H widget, UIObject errorLabel) {
        checkFieldErrorSuccess(widget, errorLabel, null, null);
    }

    public void checkFieldErrorSuccess(AbstractValueWidget widget, UIObject errorLabel, UIObject target, UIObject placeholder) {
        if (errorLabel != null) {
            widget.setErrorText("error");
            assertTrue(errorLabel.getElement().hasClassName(CssName.FIELD_ERROR_LABEL));
            assertFalse(errorLabel.getElement().hasClassName(CssName.FIELD_HELPER_LABEL));
            assertFalse(errorLabel.getElement().hasClassName(CssName.FIELD_SUCCESS_LABEL));
            errorLabel.setVisible(true);

            widget.setSuccessText("success");
            assertFalse(errorLabel.getElement().hasClassName(CssName.FIELD_ERROR_LABEL));
            assertFalse(errorLabel.getElement().hasClassName(CssName.FIELD_HELPER_LABEL));
            assertTrue(errorLabel.getElement().hasClassName(CssName.FIELD_SUCCESS_LABEL));

            widget.setHelperText("helper");
            assertFalse(errorLabel.getElement().hasClassName(CssName.FIELD_ERROR_LABEL));
            assertTrue(errorLabel.getElement().hasClassName(CssName.FIELD_HELPER_LABEL));
            assertFalse(errorLabel.getElement().hasClassName(CssName.FIELD_SUCCESS_LABEL));
        }
        if (target != null) {
            widget.setErrorText("error");
            assertTrue(target.getElement().hasClassName(CssName.FIELD_ERROR));
            assertFalse(target.getElement().hasClassName(CssName.FIELD_SUCCESS));

            widget.setSuccessText("success");
            assertFalse(target.getElement().hasClassName(CssName.FIELD_ERROR));
            assertTrue(target.getElement().hasClassName(CssName.FIELD_SUCCESS));

            widget.setHelperText("helper");
            assertFalse(target.getElement().hasClassName(CssName.FIELD_ERROR));
            assertFalse(target.getElement().hasClassName(CssName.FIELD_SUCCESS));
        }
        if (placeholder != null) {
            widget.setErrorText("error");
            assertFalse(placeholder.getElement().hasClassName("green-text"));
            assertTrue(placeholder.getElement().hasClassName("red-text"));

            widget.setSuccessText("success");
            assertTrue(placeholder.getElement().hasClassName("green-text"));
            assertFalse(placeholder.getElement().hasClassName("red-text"));

            widget.setHelperText("helper");
            assertFalse(placeholder.getElement().hasClassName("green-text"));
            assertFalse(placeholder.getElement().hasClassName("red-text"));
        }
    }
}
