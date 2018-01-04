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
package gwt.material.design.client.ui;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ValueBoxBase;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;

/**
 * Test case for ValueBox.
 *
 * @author kevzlou7979
 */
public abstract class MaterialValueBoxTest<T extends MaterialValueBox> extends AbstractValueWidgetTest<T> {

    public abstract void testValue();

    public void testFieldErrorSuccess() {
        // given
        MaterialValueBox widget = getWidget();

        // when / then
        checkFieldErrorSuccess(widget, widget.getErrorLabel(), widget.getValueBoxBase(), widget.getLabel());
    }

    public void testPlaceholder() {
        // given
        T widget = getWidget();
        checkPlaceholder(widget);
    }

    public void testIcon() {
        // given
        T widget = getWidget();

        checkFieldIcon(widget);
    }

    public void testReadOnly() {
        // given
        T widget = getWidget();

        checkReadOnly(widget);
    }

    public void testValueReturnAsNull() {
        T widget = getWidget(false);

        if (widget.getValue() instanceof String) {
            checkValueReturnAsNull(widget);

            attachWidget();

            checkValueReturnAsNull(widget);
        }
    }

    protected void checkValueReturnAsNull(T widget) {
        assertTrue(widget.isBlank());
        assertFalse(widget.isReturnBlankAsNull());
        widget.setReturnBlankAsNull(true);
        assertTrue(widget.isReturnBlankAsNull());

        widget.setValue("");
        assertEquals(null, widget.getValue());

        widget.setReturnBlankAsNull(false);
        widget.setValue("");
        assertEquals("", widget.getValue());
        assertNotNull(widget.getValue());
    }

    @Override
    public void testTabIndex() {
        ValueBoxBase widget = getWidget().getValueBoxBase();
        final int INITIAL_TAB_INDEX = 0;
        final int FINAL_TAB_INDEX = 1;

        // when / then
        widget.setTabIndex(INITIAL_TAB_INDEX);
        assertEquals(INITIAL_TAB_INDEX, widget.getTabIndex());
        assertEquals(String.valueOf(INITIAL_TAB_INDEX), widget.getElement().getPropertyString("tabIndex"));

        // when / then
        widget.setTabIndex(FINAL_TAB_INDEX);
        assertEquals(FINAL_TAB_INDEX, widget.getTabIndex());
        assertEquals(String.valueOf(FINAL_TAB_INDEX), widget.getElement().getPropertyString("tabIndex"));
    }

    @Override
    public void fireBlurEvent(HasHandlers widget) {
        super.fireBlurEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireFocusEvent(HasHandlers widget) {
        super.fireFocusEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireClickEvent(HasHandlers widget) {
        super.fireClickEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireDoubleClickEvent(HasHandlers widget) {
        super.fireDoubleClickEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireKeyDownEvent(HasHandlers widget) {
        super.fireKeyDownEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireKeyUpEvent(HasHandlers widget) {
        super.fireKeyUpEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireKeyPressEvent(HasHandlers widget) {
        super.fireKeyPressEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireGestureStartEvent(HasHandlers widget) {
        super.fireGestureStartEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireGestureChangeEvent(HasHandlers widget) {
        super.fireGestureChangeEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireGestureEndEvent(HasHandlers widget) {
        super.fireGestureEndEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireTouchStartEvent(HasHandlers widget) {
        super.fireTouchStartEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireTouchMoveEvent(HasHandlers widget) {
        super.fireTouchMoveEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireTouchEndEvent(HasHandlers widget) {
        super.fireTouchEndEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireTouchCancelEvent(HasHandlers widget) {
        super.fireTouchCancelEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireMouseUpEvent(HasHandlers widget) {
        super.fireMouseUpEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireMouseDownEvent(HasHandlers widget) {
        super.fireMouseDownEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireMouseMoveEvent(HasHandlers widget) {
        super.fireMouseMoveEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public void fireMouseWheelEvent(HasHandlers widget) {
        super.fireMouseWheelEvent(((MaterialValueBox) widget).getValueBoxBase());
    }

    @Override
    public <H extends HasHandlers & IsWidget> void fireMouseOverEvent(H widget) {
        super.fireMouseOverEvent(((MaterialValueBox) widget).getValueBoxBase());
    }
}
