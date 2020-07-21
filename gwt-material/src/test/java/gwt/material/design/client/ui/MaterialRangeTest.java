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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;
import gwt.material.design.client.ui.html.Span;

/**
 * Test case for Range.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialRangeTest extends AbstractValueWidgetTest<MaterialRange> {

    static final Integer MIN = 20;
    static final Integer MAX = 100;
    static final Integer VALUE = 50;
    static final Integer SECOND_VALUE = 100;

    @Override
    protected MaterialRange createWidget() {
        return new MaterialRange();
    }

    public void testStructure() {
        // given
        MaterialRange range = getWidget();

        // when / then
        assertEquals(2, range.getChildren().size());
        assertTrue(range.getWidget(0) instanceof MaterialPanel);
        MaterialPanel container = (MaterialPanel) range.getWidget(0);
        assertTrue(container.getElement().hasClassName(CssName.RANGE_FIELD));
        assertEquals(3, container.getWidgetCount());
        assertTrue(container.getWidget(0) instanceof MaterialInput);

        assertTrue(container.getWidget(1) instanceof Span);
        Span thumb = (Span) container.getWidget(1);
        assertTrue(thumb.getElement().hasClassName(CssName.THUMB));
        assertEquals(1, thumb.getWidgetCount());
        assertTrue(thumb.getWidget(0) instanceof Span);
        assertTrue(thumb.getWidget(0).getElement().hasClassName(CssName.VALUE));

        assertTrue(container.getWidget(2) instanceof MaterialPanel);
        MaterialPanel progressWrapper = (MaterialPanel) container.getWidget(2);
        assertTrue(progressWrapper.getElement().hasClassName("range-progress-wrapper"));
        assertTrue(progressWrapper.getWidget(0) instanceof MaterialPanel);
        MaterialPanel progressFillContainer = (MaterialPanel) progressWrapper.getWidget(0);
        assertTrue(progressFillContainer.getElement().hasClassName("progress-container"));
        assertTrue(progressFillContainer.getWidget(0) instanceof MaterialPanel);
        MaterialPanel progress = (MaterialPanel) progressFillContainer.getWidget(0);
        assertTrue(progress.getElement().hasClassName("progress"));

        assertTrue(range.getWidget(1) instanceof MaterialLabel);
        assertEquals(range.getErrorLabel(), range.getWidget(1));
    }

    public void testValues() {
        // given
        MaterialRange range = getWidget();
        final Element inputElement = range.getRangeInputElement().getElement();

        // when / then
        // Make Sure to have not min / max / value set by default
        assertFalse(inputElement.hasAttribute("min"));
        assertFalse(inputElement.hasAttribute("max"));
        assertFalse(inputElement.hasAttribute("value"));

        range.setMin(MIN);
        assertTrue(inputElement.hasAttribute("min"));
        assertEquals(MIN, range.getMin());
        assertEquals(String.valueOf(MIN), inputElement.getAttribute("min"));

        range.setMax(MAX);
        assertTrue(inputElement.hasAttribute("max"));
        assertEquals(MAX, range.getMax());
        assertEquals(String.valueOf(MAX), inputElement.getAttribute("max"));

        range.setValue(VALUE);
        assertTrue(inputElement.hasAttribute("value"));
        assertEquals(VALUE, range.getValue());
        assertEquals(String.valueOf(VALUE), inputElement.getAttribute("value"));

        checkValueChangeEvent(range, VALUE, SECOND_VALUE);
    }

    public void testReset() {
        // given
        MaterialRange range = getWidget();

        // when / then
        range.setMin(MIN);
        range.setMax(MAX);
        range.setValue(VALUE);
        assertEquals(VALUE, range.getValue());
        range.setErrorText("some-error");
        range.reset();
        assertEquals(MIN, range.getValue());
        assertFalse(range.getElement().hasClassName(CssName.FIELD_ERROR));
    }

    public void testErrorSuccess() {
        // given
        MaterialRange range = getWidget();

        // when / then
        checkFieldErrorSuccess(range, range.getErrorLabel());
    }

    //TODO: Issue
    public void testChangeHandler() {
        /*MaterialRange range = getWidget();

        final boolean[] firedEvent = {false};
        range.addChangeHandler(changeEvent -> firedEvent[0] = true);
        ChangeEvent.fireNativeEvent(Document.get().createChangeEvent(), range.getRangeInputElement());
        assertTrue(firedEvent[0]);*/
    }
}
