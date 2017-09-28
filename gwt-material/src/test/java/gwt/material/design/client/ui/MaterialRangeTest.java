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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;
import gwt.material.design.client.ui.html.Paragraph;
import gwt.material.design.client.ui.html.Span;

/**
 * Test case for Range.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialRangeTest extends AbstractValueWidgetTest<MaterialRange> {

    @Override
    protected MaterialRange createWidget() {
        return new MaterialRange();
    }

    public void testStructure() {
        // given
        MaterialRange range = getWidget();

        // when / then
        assertEquals(2, range.getChildren().size());
        assertTrue(range.getWidget(0) instanceof Paragraph);
        Paragraph paragraph = (Paragraph) range.getWidget(0);
        assertTrue(paragraph.getElement().hasClassName(CssName.RANGE_FIELD));
        assertEquals(2, paragraph.getWidgetCount());
        assertTrue(paragraph.getWidget(0) instanceof MaterialInput);

        assertTrue(paragraph.getWidget(1) instanceof Span);
        Span thumb = (Span) paragraph.getWidget(1);
        assertTrue(thumb.getElement().hasClassName(CssName.THUMB));
        assertEquals(1, thumb.getWidgetCount());
        assertTrue(thumb.getWidget(0) instanceof Span);
        assertTrue(thumb.getWidget(0).getElement().hasClassName(CssName.VALUE));

        assertTrue(range.getWidget(1) instanceof MaterialLabel);
        assertEquals(range.getErrorLabel(), range.getWidget(1));
    }

    public void testValues() {
        // given
        MaterialRange range = getWidget();

        final Integer MIN = 20;
        final Integer MAX = 100;
        final Integer VALUE = 50;
        final Integer SECOND_VALUE = 100;

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

    public void testErrorSuccess() {
        // given
        MaterialRange range = getWidget();

        // when / then
        checkErrorSuccess(range, range.getErrorLabel());
    }

    @Override
    public <H extends HasError> void checkErrorSuccess(H widget, UIObject target) {
        widget.setError("Error");
        assertTrue(target.getElement().hasClassName(CssName.FIELD_ERROR_LABEL));
        widget.setSuccess("Success");
        assertTrue(target.getElement().hasClassName(CssName.FIELD_SUCCESS_LABEL));
        widget.setHelperText("Helper");
        assertTrue(target.getElement().hasClassName(CssName.FIELD_HELPER_LABEL));
        widget.clearErrorOrSuccess();
        assertFalse(target.getElement().hasClassName(CssName.FIELD_ERROR_LABEL));
        assertFalse(target.getElement().hasClassName(CssName.FIELD_SUCCESS_LABEL));
    }
}
