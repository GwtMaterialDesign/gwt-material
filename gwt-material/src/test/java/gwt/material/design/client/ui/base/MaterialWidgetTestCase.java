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
package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.*;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.MaterialPanel;
import org.junit.Ignore;

/**
 * Test case for MaterialWidget base.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
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
        assertTrue(widget.isEnabled());
        widget.setEnabled(false);
        if(checkElement) {
            assertTrue(element.hasClassName(CssName.DISABLED));
            assertTrue(element.hasAttribute(CssName.DISABLED));
        }
        assertFalse(target.isEnabled());
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

    public void testVerticalAlign() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        checkVerticalAlign(widget, false);

        // Standard
        // give
        attachWidget();

        // when / then
        checkVerticalAlign(widget, true);
    }

    protected void checkVerticalAlign(T widget, boolean checkElement) {
        checkVerticalAlign(widget, Style.VerticalAlign.MIDDLE, checkElement);
        checkVerticalAlign(widget, Style.VerticalAlign.TOP, checkElement);
        checkVerticalAlign(widget, Style.VerticalAlign.BOTTOM, checkElement);
        checkVerticalAlign(widget, Style.VerticalAlign.SUPER, checkElement);
        checkVerticalAlign(widget, Style.VerticalAlign.SUB, checkElement);
        checkVerticalAlign(widget, Style.VerticalAlign.TEXT_BOTTOM, checkElement);
        checkVerticalAlign(widget, Style.VerticalAlign.TEXT_TOP, checkElement);
        checkVerticalAlign(widget, Style.VerticalAlign.BASELINE, checkElement);
    }

    protected void checkVerticalAlign(MaterialWidget widget, Style.VerticalAlign value, boolean checkElement) {
        widget.getElement().setAttribute("style", "vertical-align: " + value.getCssName());
        assertEquals(value.getCssName(), widget.getVerticalAlign());
        if (checkElement) {
            assertEquals(widget.getVerticalAlign(), widget.getElement().getStyle().getVerticalAlign());
        }
    }

    public void testBorder() {
        // UiBinder
        // given
        T widget = getWidget(false);

        final String BORDER = "1px solid red";
        final String BORDER_LEFT = "10px dashed green";
        final String BORDER_RIGHT = "20px dotted";
        final String BORDER_BOTTOM = "10px solid";
        final String BORDER_TOP = "5px ridge";

        // when / then
        widget.setBorder(BORDER);
        assertEquals(BORDER, widget.getBorder());

        widget.setBorderLeft(BORDER_LEFT);
        assertEquals(BORDER_LEFT, widget.getBorderLeft());

        widget.setBorderRight(BORDER_RIGHT);
        assertEquals(BORDER_RIGHT, widget.getBorderRight());

        widget.setBorderBottom(BORDER_BOTTOM);
        assertEquals(BORDER_BOTTOM, widget.getBorderBottom());

        widget.setBorderTop(BORDER_TOP);
        assertEquals(BORDER_TOP, widget.getBorderTop());

        widget.setBorder("");
        assertEquals("", widget.getBorder());

        // Standard
        // given
        attachWidget();

        // when / then
        assertEquals("", widget.getElement().getStyle().getProperty("border"));
        assertEquals(BORDER_LEFT, widget.getElement().getStyle().getProperty("borderLeft"));
        assertEquals(BORDER_RIGHT, widget.getElement().getStyle().getProperty("borderRight"));
        assertEquals(BORDER_BOTTOM, widget.getElement().getStyle().getProperty("borderBottom"));
        assertEquals(BORDER_TOP, widget.getElement().getStyle().getProperty("borderTop"));
        assertEquals("", widget.getElement().getStyle().getProperty("border"));
    }

    public void testTransition() {
        // UiBinder
        // given
        T widget = getWidget();

        // when / then
        final int DURATION = 1000;
        final int DELAY = 0;
        final String PROPERTY = "all";
        final String TIMING_FUNCTION = "cubic-bezier(0, 0, 0.2, 1)";
        final String EXPECTED_RESULT = "all 1000ms cubic-bezier(0, 0, 0.2, 1)0ms";

        TransitionConfig config = new TransitionConfig();
        config.setDuration(DURATION);

        // when / then
        assertEquals(DURATION, config.getDuration());
        config.setDelay(DELAY);
        assertEquals(DELAY, config.getDelay());
        config.setProperty(PROPERTY);
        assertEquals(PROPERTY, config.getProperty());
        config.setTimingFunction(TIMING_FUNCTION);
        assertEquals(TIMING_FUNCTION, config.getTimingFunction());
        widget.setTransition(config);
    }

    public void testInitialClasses() {
        checkInitialClasses();
    }

    protected void checkInitialClasses(String... initialClasses) {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        if (widget.getInitialClasses() != null) {
            for (int i = 0; i < initialClasses.length; i++) {
                assertTrue(widget.getElement().hasClassName(initialClasses[i]));
            }
            assertNotNull(widget.getInitialClasses());
        }
    }

    public void testChildren() {
        // given
        T widget = getWidget();

        // when / then
        if (widget.getChildren().size() == 0) {
            int childCount = 3;
            for (int i = 1; i <= childCount; i++) {
                MaterialPanel panel = new MaterialPanel();
                widget.add(panel);
            }
            assertTrue(widget.getChildren().size() != 0);
            assertEquals(3, widget.getChildren().size());
            widget.getChildren().remove(0);
            assertEquals(2, widget.getChildren().size());
            widget.clear();
            assertEquals(0, widget.getChildren().size());
        }
    }

    public void testTruncate() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setTruncate(true);
        assertTrue(widget.isTruncate());
        widget.setTruncate(false);
        assertFalse(widget.isTruncate());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setTruncate(true);
        assertTrue(element.hasClassName(CssName.TRUNCATE));
        widget.setTruncate(false);
        assertFalse(element.hasClassName(CssName.TRUNCATE));
    }

    public void testFloat() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setFloat(Style.Float.LEFT);
        assertEquals(Style.Float.LEFT, widget.getFloat());

        widget.setFloat(Style.Float.RIGHT);
        assertEquals(Style.Float.RIGHT, widget.getFloat());

        widget.setFloat(Style.Float.NONE);
        assertEquals(Style.Float.NONE, widget.getFloat());

        // Standard
        // given
        attachWidget();
        final Element element = widget.getElement();

        // when / then
        widget.setFloat(Style.Float.LEFT);
        assertTrue(element.hasClassName(Style.Float.LEFT.getCssName()));

        widget.setFloat(Style.Float.RIGHT);
        assertTrue(element.hasClassName(Style.Float.RIGHT.getCssName()));

        widget.setFloat(Style.Float.NONE);
        assertTrue(element.hasClassName(Style.Float.NONE.getCssName()));
    }

    public void testFontSize() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setFontSize("12px");
        assertEquals("12px", widget.getFontSize());

        widget.setFontSize(14, Style.Unit.PX);
        assertEquals("14px", widget.getFontSize());

        // Standard
        // given
        attachWidget();

        // when / then
        widget.setFontSize("12px");
        assertEquals("12px", widget.getElement().getStyle().getFontSize());

        widget.setFontSize(14, Style.Unit.PX);
        assertEquals("14px", widget.getElement().getStyle().getFontSize());
    }

    public void testFontWeight() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setFontWeight(Style.FontWeight.BOLD);
        assertEquals(Style.FontWeight.BOLD.getCssName(), widget.getFontWeight());

        // Standard
        // given
        attachWidget();

        // when / then
        widget.setFontWeight(Style.FontWeight.BOLDER);
        assertEquals(Style.FontWeight.BOLDER.getCssName(), widget.getElement().getStyle().getFontWeight());
    }

    public void testHoverable() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setHoverable(true);
        assertTrue(widget.isHoverable());
        widget.setHoverable(false);
        assertFalse(widget.isHoverable());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setHoverable(true);
        assertTrue(element.hasClassName(CssName.HOVERABLE));
        widget.setHoverable(false);
        assertFalse(element.hasClassName(CssName.HOVERABLE));
    }

    public void testTooltip() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // test before attached (UiBinder test)
        widget.setTooltip("Tooltip");
        widget.setTooltipPosition(Position.RIGHT);
        widget.setTooltipDelayMs(300);
        attachWidget();

        // when / then
        widget.setTooltip("Tooltip");
        widget.setTooltipPosition(Position.RIGHT);
        widget.setTooltipDelayMs(300);
        assertEquals("Tooltip", widget.getTooltip());
        assertEquals(Position.RIGHT, widget.getTooltipPosition());
        assertEquals(300, widget.getTooltipDelayMs());
        widget.setTooltipHTML("<b>Tooltip</b>");
        assertEquals("<b>Tooltip</b>", widget.getTooltipHTML());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setTooltip("Tooltip");
        widget.setTooltipPosition(Position.RIGHT);
        widget.setTooltipDelayMs(300);
        assertTrue(element.hasAttribute("data-tooltip"));
        assertEquals("Tooltip", element.getAttribute("data-tooltip"));
        assertTrue(element.hasAttribute("data-position"));
        assertTrue(element.hasAttribute("data-delay"));
        assertEquals("300", element.getAttribute("data-delay"));
    }

    public void testWaves() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        checkWaves(widget, false);

        // Standard
        attachWidget();

        // given / when
        checkWaves(widget, true);
    }

    protected void checkWaves(T widget, boolean checkElement) {
        widget.setWaves(WavesType.YELLOW);
        assertEquals(WavesType.YELLOW, widget.getWaves());

        if (checkElement) {
            final Element element = widget.getElement();
            widget.setWaves(WavesType.YELLOW);
            assertTrue(element.hasClassName(WavesType.YELLOW.getCssName()));

            // Check Waves when the element is disabled
            widget.setEnabled(false);
            assertFalse(widget.getElement().hasClassName(CssName.WAVES_EFFECT));

            widget.setWaves(WavesType.YELLOW);
            assertFalse(widget.getElement().hasClassName(CssName.WAVES_EFFECT));

            // Check Waves when the element is enabled
            widget.setEnabled(true);
            assertTrue(widget.getElement().hasClassName(CssName.WAVES_EFFECT));
        }
    }

    public void testCircle() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setCircle(true);
        assertTrue(widget.isCircle());
        widget.setCircle(false);
        assertFalse(widget.isCircle());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setCircle(true);
        assertTrue(element.hasClassName(CssName.CIRCLE));
        widget.setCircle(false);
        assertFalse(element.hasClassName(CssName.CIRCLE));
    }

    public void testCenterOn() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setCenterOn(CenterOn.CENTER_ON_SMALL);
        assertEquals(CenterOn.CENTER_ON_SMALL, widget.getCenterOn());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setCenterOn(CenterOn.CENTER_ON_SMALL);
        assertTrue(element.hasClassName(CenterOn.CENTER_ON_SMALL.getCssName()));
    }

    public void testHideOnShowOn() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setHideOn(HideOn.HIDE_ON_MED);
        assertEquals(HideOn.HIDE_ON_MED, widget.getHideOn());
        widget.setShowOn(ShowOn.SHOW_ON_MED_UP);
        assertEquals(ShowOn.SHOW_ON_MED_UP, widget.getShowOn());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setHideOn(HideOn.HIDE_ON_MED);
        assertTrue(element.hasClassName(HideOn.HIDE_ON_MED.getCssName()));
        widget.setShowOn(ShowOn.SHOW_ON_MED_UP);
        assertTrue(element.hasClassName(ShowOn.SHOW_ON_MED_UP.getCssName()));
    }

    public void testScrollspy() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setScrollspy("scrollspy-1");
        assertEquals("scrollspy-1", widget.getId());
        assertEquals("scrollspy-1", widget.getScrollspy());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setScrollspy("scrollspy-1");
        assertTrue(element.hasClassName(CssName.SECTION + " " + CssName.SCROLLSPY));
    }

    public void testSeparator() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setSeparator(true);
        assertEquals(widget.isSeparator(), true);
        widget.setSeparator(false);
        assertFalse(widget.isSeparator());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setSeparator(true);
        assertEquals("1px solid #e9e9e9", element.getStyle().getProperty("borderBottom"));
        widget.setSeparator(false);
        assertEquals("", element.getStyle().getProperty("borderBottom"));
    }

    public void testShadow() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setShadow(1);
        assertEquals(1, widget.getShadow());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setShadow(1);
        assertTrue(element.hasClassName(CssName.Z_DEPTH_1));
    }

    public void testGrid() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when
        try {
            widget.setGrid("s12 m12 l12");
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setGrid("s12 m12 l12");
        assertTrue(element.hasClassName("col s12 m12 l12"));
        widget.setGrid("s12");
        assertTrue(element.hasClassName("col s12"));
        widget.setGrid("s12 l12");
        assertTrue(element.hasClassName("col s12 l12"));
        widget.setOffset("s12 m12 l12");
        assertTrue(element.hasClassName("offset-s12 offset-m12 offset-l12"));
    }

    public void testTextAlign() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        try {
            widget.setTextAlign(TextAlign.CENTER);
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setTextAlign(TextAlign.CENTER);
        assertTrue(element.hasClassName(TextAlign.CENTER.getCssName()));
        widget.setTextAlign(TextAlign.LEFT);
        assertTrue(element.hasClassName(TextAlign.LEFT.getCssName()));
        widget.setTextAlign(TextAlign.RIGHT);
        assertTrue(element.hasClassName(TextAlign.RIGHT.getCssName()));
    }

    public void testColor() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        try {
            widget.setTextColor(Color.BLACK);
            widget.setBackgroundColor(Color.BLACK);
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }

        // Standard
        // given
        attachWidget();

        // when / then
        checkColor(widget);
    }

    public void testEnabled() {
        // UiBinder
        // given
        T widget = getWidget(true);

        // when / then
        checkEnabled(widget, widget, true);
    }

    public void testMultiSetEnabledState() {
        // given
        T widget = getWidget();

        // when / then
        widget.setEnabled(false);
        assertFalse(false);

        // when / then
        widget.setEnabled(true);
        assertTrue(widget.isEnabled());

        // when / then
        widget.setEnabled(false);
        assertFalse(widget.isEnabled());

        // given
        attachWidget();

        // when / then
        assertFalse(widget.isEnabled());
        widget.setEnabled(true);
        assertTrue(widget.isEnabled());

        // when / then
        widget.setEnabled(false);
        assertFalse(widget.isEnabled());
    }

    public void testId() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setId("test");
        assertNotNull(widget.getId());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setId("test");
        assertTrue(element.hasAttribute("id"));
        assertEquals(widget.getId(), element.getId());
    }

    public void testDepth() {
        // UiBinder
        // given
        T widget = getWidget(false);

        final int DEPTH = 999;

        // when / then
        widget.setDepth(DEPTH);
        assertEquals(DEPTH, widget.getDepth());

        // Standard
        // given
        attachWidget();

        // when / then
        assertEquals(String.valueOf(DEPTH), widget.getElement().getStyle().getZIndex());
    }

    public void testDimensions() {
        // UiBinder
        // given
        T widget = getWidget(false);

        final String MIN_HEIGHT = "10px";
        final String MIN_WIDTH = "20px";

        final String MAX_HEIGHT = "30px";
        final String MAX_WIDTH = "50px";

        // when / then
        try {
            widget.setMinHeight(MIN_HEIGHT);
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }

        // Standard
        // given
        attachWidget();

        // when / then
        widget.setMinHeight(MIN_HEIGHT);
        assertEquals(MIN_HEIGHT, widget.getElement().getStyle().getProperty("minHeight"));

        widget.setMaxHeight(MAX_HEIGHT);
        assertEquals(MAX_HEIGHT, widget.getElement().getStyle().getProperty("maxHeight"));

        widget.setMinWidth(MIN_WIDTH);
        assertEquals(MIN_WIDTH, widget.getElement().getStyle().getProperty("minWidth"));

        widget.setMaxWidth(MAX_WIDTH);
        assertEquals(MAX_WIDTH, widget.getElement().getStyle().getProperty("maxWidth"));
    }

    public void testLayoutPosition() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setLayoutPosition(Style.Position.FIXED);
        assertEquals(Style.Position.FIXED.getCssName(), widget.getLayoutPosition());

        widget.setLayoutPosition(Style.Position.ABSOLUTE);
        assertEquals(Style.Position.ABSOLUTE.getCssName(), widget.getLayoutPosition());

        widget.setLayoutPosition(Style.Position.RELATIVE);
        assertEquals(Style.Position.RELATIVE.getCssName(), widget.getLayoutPosition());

        widget.setLayoutPosition(Style.Position.STATIC);
        assertEquals(Style.Position.STATIC.getCssName(), widget.getLayoutPosition());

        // Standard
        // given
        attachWidget();

        // when / then
        widget.setLayoutPosition(Style.Position.FIXED);
        assertEquals(Style.Position.FIXED.getCssName(), widget.getElement().getStyle().getPosition());

        widget.setLayoutPosition(Style.Position.ABSOLUTE);
        assertEquals(Style.Position.ABSOLUTE.getCssName(), widget.getElement().getStyle().getPosition());

        widget.setLayoutPosition(Style.Position.RELATIVE);
        assertEquals(Style.Position.RELATIVE.getCssName(), widget.getElement().getStyle().getPosition());

        widget.setLayoutPosition(Style.Position.STATIC);
        assertEquals(Style.Position.STATIC.getCssName(), widget.getElement().getStyle().getPosition());
    }

    public void testOpacity() {
        // UiBinder
        // given
        T widget = getWidget(false);

        final double INITIAL_OPACITY = 0.0;
        final double FINAL_OPACITY = 1.0;

        // when / then
        widget.setOpacity(INITIAL_OPACITY);
        assertEquals(INITIAL_OPACITY, widget.getOpacity());

        // when / then
        widget.setOpacity(FINAL_OPACITY);
        assertEquals(FINAL_OPACITY, widget.getOpacity());

        // Standard
        // given
        attachWidget();

        // when / then
        widget.setOpacity(INITIAL_OPACITY);
        assertEquals("0", widget.getElement().getStyle().getOpacity());

        // when / then
        widget.setOpacity(FINAL_OPACITY);
        assertEquals("1", widget.getElement().getStyle().getOpacity());
    }

    public void testOrientation() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setOrientation(Orientation.PORTRAIT);
        assertEquals(Orientation.PORTRAIT, widget.getOrientation());

        // when / then
        widget.setOrientation(Orientation.LANDSCAPE);
        assertEquals(Orientation.LANDSCAPE, widget.getOrientation());

        // Standard
        // given
        attachWidget();

        // when / then
        widget.setOrientation(Orientation.PORTRAIT);
        assertTrue(widget.getElement().hasClassName(Orientation.PORTRAIT.getCssName()));

        // when / then
        widget.setOrientation(Orientation.LANDSCAPE);
        assertTrue(widget.getElement().hasClassName(Orientation.LANDSCAPE.getCssName()));
    }

    public void testPerspective() {
        final String PERSPECTIVE = "500px";
        // given
        MaterialWidget widget = getWidget();

        // when
        widget.setPerspective(PERSPECTIVE);
        assertEquals(PERSPECTIVE, widget.getPerspective());
        assertEquals(PERSPECTIVE, widget.getElement().getStyle().getProperty("perspective"));
    }

    public void testTabIndex() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        checkTabIndex(widget, false);

        // Standard
        // given
        attachWidget();

        // when / then
        checkTabIndex(widget, true);
    }

    protected <W extends MaterialWidget> void checkTabIndex(W widget) {
        checkTabIndex(widget, true);
    }

    protected <W extends MaterialWidget> void checkTabIndex(W widget, boolean checkElement) {
        final int INITIAL_TAB_INDEX = 0;
        final int FINAL_TAB_INDEX = 1;

        // when / then
        widget.setTabIndex(INITIAL_TAB_INDEX);
        assertEquals(INITIAL_TAB_INDEX, widget.getTabIndex());
        if (checkElement) {
            assertEquals(String.valueOf(INITIAL_TAB_INDEX), widget.getElement().getPropertyString("tabIndex"));
        }

        // when / then
        widget.setTabIndex(FINAL_TAB_INDEX);
        assertEquals(FINAL_TAB_INDEX, widget.getTabIndex());
        if (checkElement) {
            assertEquals(String.valueOf(FINAL_TAB_INDEX), widget.getElement().getPropertyString("tabIndex"));
        }
    }

    public void testTransform() {
        // UiBinder
        // given
        T widget = getWidget(false);

        final String TRANSFORM_VALUE = "rotate(7deg)";
        final String FINAL_TRANSFORM_VALUE = "scale(0)";

        // when / then
        widget.setTransform(TRANSFORM_VALUE);
        assertEquals(TRANSFORM_VALUE, widget.getTransform());

        widget.setTransform(FINAL_TRANSFORM_VALUE);
        assertEquals(FINAL_TRANSFORM_VALUE, widget.getTransform());

        // Standard
        // given
        attachWidget();

        // when / then
        widget.setTransform(TRANSFORM_VALUE);
        assertEquals(TRANSFORM_VALUE, widget.getElement().getStyle().getProperty("transform"));

        widget.setTransform(FINAL_TRANSFORM_VALUE);
        assertEquals(FINAL_TRANSFORM_VALUE, widget.getElement().getStyle().getProperty("transform"));
    }

    public void testCursor() {
        // UiBinder
        // given
        T widget = getWidget(false);

        checkCursor(widget);

        // Standard
        // given
        attachWidget();

        // when / then
        checkCursor(widget);
    }

    public void testValignWrapper() {
        // UiBinder
        // given
        T widget = getWidget(false);

        checkCursor(widget);

        // Standard
        // given
        attachWidget();

        // when / then
        checkCursor(widget);
    }

    public void testZoom() {
        // UiBinder
        // given
        T widget = getWidget();

        checkZoom(widget);
    }

    protected <W extends MaterialWidget> void checkZoom(W widget) {
        String zoomProperty = "zoom";
        widget.setZoom(1.0);
        assertEquals("1", widget.getElement().getStyle().getProperty(zoomProperty));
        widget.setZoom(3.5);
        assertEquals("3.5", widget.getElement().getStyle().getProperty(zoomProperty));
        widget.setZoom(null);
        assertEquals("", widget.getElement().getStyle().getProperty(zoomProperty));
    }

    protected <W extends MaterialWidget> void checkValignWrapper(W widget) {
        assertFalse(widget.isValignWrapper());
        assertFalse(widget.getElement().hasClassName(CssName.VALIGN_WRAPPER));

        assertFalse(widget.isContainerEnabed());
        assertFalse(widget.getElement().hasClassName(CssName.CONTAINER));

        widget.setValignWrapper(true);
        assertTrue(widget.isValignWrapper());
        assertTrue(widget.getElement().hasClassName(CssName.VALIGN_WRAPPER));

        widget.setValignWrapper(false);
        assertFalse(widget.isValignWrapper());
        assertFalse(widget.getElement().hasClassName(CssName.VALIGN_WRAPPER));

        widget.setContainerEnabled(true);
        assertTrue(widget.isContainerEnabed());
        assertTrue(widget.getElement().hasClassName(CssName.CONTAINER));

        widget.setContainerEnabled(false);
        assertFalse(widget.isContainerEnabed());
        assertFalse(widget.getElement().hasClassName(CssName.CONTAINER));
    }

    protected <W extends MaterialWidget> void checkCursor(W widget) {
        widget.setCursor(Style.Cursor.POINTER);
        assertEquals(Style.Cursor.POINTER.getCssName().toLowerCase(), widget.getCursor());
        assertEquals(Style.Cursor.POINTER.getCssName().toLowerCase(), widget.getElement().getStyle().getCursor());

        widget.setCursor(Style.Cursor.DEFAULT);
        assertEquals(Style.Cursor.DEFAULT.getCssName().toLowerCase(), widget.getCursor());
        assertEquals(Style.Cursor.DEFAULT.getCssName().toLowerCase(), widget.getElement().getStyle().getCursor());

        widget.setCursor(Style.Cursor.AUTO);
        assertEquals(Style.Cursor.AUTO.getCssName().toLowerCase(), widget.getCursor());
        assertEquals(Style.Cursor.AUTO.getCssName().toLowerCase(), widget.getElement().getStyle().getCursor());

        widget.setCursor(Style.Cursor.HELP);
        assertEquals(Style.Cursor.HELP.getCssName().toLowerCase(), widget.getCursor());
        assertEquals(Style.Cursor.HELP.getCssName().toLowerCase(), widget.getElement().getStyle().getCursor());

        widget.setCursor(Style.Cursor.TEXT);
        assertEquals(Style.Cursor.TEXT.getCssName().toLowerCase(), widget.getCursor());
        assertEquals(Style.Cursor.TEXT.getCssName().toLowerCase(), widget.getElement().getStyle().getCursor());
    }

    protected <W extends HasPlaceholder> void checkPlaceholder(W widget) {
        // when / then
        widget.setPlaceholder("Placeholder");
        assertEquals("Placeholder", widget.getPlaceholder());
        widget.setPlaceholder("");
        assertEquals("", widget.getPlaceholder());
    }

    protected <W extends HasIcon> void checkFieldIcon(W widget) {
        // when / then
        Element iconElement = widget.getIcon().getElement();
        widget.setIconType(IconType.POLYMER);
        assertEquals(IconType.POLYMER.getCssName(), iconElement.getInnerHTML());
        widget.setIconColor(Color.RED);
        assertTrue(iconElement.hasClassName(Color.RED.getCssName() + "-text"));
        widget.setIconPosition(IconPosition.LEFT);
        assertTrue(iconElement.hasClassName(IconPosition.LEFT.getCssName()));
        widget.setIconPrefix(true);
        assertTrue(iconElement.hasClassName("prefix"));
        widget.setIconPrefix(false);
        assertFalse(iconElement.hasClassName("prefix"));
        widget.setIconSize(IconSize.LARGE);
        assertTrue(iconElement.hasClassName(IconSize.LARGE.getCssName()));
    }

    protected <W extends UIObject & HasReadOnly> void checkReadOnly(W widget, UIObject target) {
        checkReadOnly(widget, target, true);
    }

    protected <W extends UIObject & HasReadOnly> void checkReadOnly(W widget, UIObject target, boolean checkElement) {
        // given
        Element widgetElement = widget.getElement();
        Element targetElement = target.getElement();

        // when / then
        widget.setReadOnly(true);
        if (checkElement) {
            assertTrue(targetElement.hasAttribute("disabled"));
            assertTrue(widgetElement.hasClassName(CssName.READ_ONLY));
        }
        assertTrue(widget.isReadOnly());
        widget.setReadOnly(false);
        if (checkElement) {
            assertFalse(targetElement.hasAttribute("disabled"));
            assertFalse(widgetElement.hasClassName(CssName.READ_ONLY));
        }
        assertFalse(widget.isReadOnly());

        widget.setToggleReadOnly(true);
        if (checkElement) {
            assertTrue(widgetElement.hasClassName(CssName.READ_ONLY_TOGGLE));
        }
        assertTrue(widget.isToggleReadOnly());
        widget.setToggleReadOnly(false);
        if (checkElement) {
            assertFalse(widgetElement.hasClassName(CssName.READ_ONLY_TOGGLE));
        }
        assertFalse(widget.isToggleReadOnly());
    }

    protected <W extends UIObject & HasReadOnly> void checkReadOnly(W widget) {
        checkReadOnly(widget, true);
    }

    protected <W extends UIObject & HasReadOnly> void checkReadOnly(W widget, boolean checkElement) {
        checkReadOnly(widget, widget, checkElement);
    }
}
