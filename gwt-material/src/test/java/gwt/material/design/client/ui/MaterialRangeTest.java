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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;
import gwt.material.design.client.ui.html.Paragraph;
import gwt.material.design.client.ui.html.Span;

/**
 * Test case for Range
 *
 * @author kevzlou7979
 */
public class MaterialRangeTest extends AbstractValueWidgetTest {

    public void init() {
        MaterialRange range = new MaterialRange();
        checkAbstractValueWidgetWoPlaceholder(range, range.getLblError());
        checkStructure(range);
        checkValues(range);
    }

    public <T extends MaterialRange> void checkStructure(T range) {
        assertEquals(range.getChildren().size(), 2);
        assertTrue(range.getWidget(0) instanceof Paragraph);
        Paragraph paragraph = (Paragraph) range.getWidget(0);
        assertTrue(paragraph.getElement().hasClassName(CssName.RANGE_FIELD));
        assertEquals(paragraph.getWidgetCount(), 2);
        assertTrue(paragraph.getWidget(0) instanceof MaterialInput);

        assertTrue(paragraph.getWidget(1) instanceof Span);
        Span thumb = (Span) paragraph.getWidget(1);
        assertTrue(thumb.getElement().hasClassName(CssName.THUMB));
        assertEquals(thumb.getWidgetCount(), 1);
        assertTrue(thumb.getWidget(0) instanceof Span);
        assertTrue(thumb.getWidget(0).getElement().hasClassName(CssName.VALUE));

        assertTrue(range.getWidget(1) instanceof MaterialLabel);
        assertEquals(range.getWidget(1), range.getLblError());
    }

    public <T extends MaterialRange> void checkValues(T range) {
        final Integer MIN = 20;
        final Integer MAX = 90;
        final Integer VALUE = 50;
        final Element inputElement = range.getRangeInputElement().getElement();

        // Make Sure to have not min / max / value set by default
        assertFalse(inputElement.hasAttribute("min"));
        assertFalse(inputElement.hasAttribute("max"));
        assertFalse(inputElement.hasAttribute("value"));

        range.setMin(MIN);
        assertTrue(inputElement.hasAttribute("min"));
        assertEquals(range.getMin(), MIN);
        assertEquals(inputElement.getAttribute("min"), String.valueOf(MIN));

        range.setMax(MAX);
        assertTrue(inputElement.hasAttribute("max"));
        assertEquals(range.getMax(), MAX);
        assertEquals(inputElement.getAttribute("max"), String.valueOf(MAX));

        range.setValue(VALUE);
        assertTrue(inputElement.hasAttribute("value"));
        assertEquals(range.getValue(), VALUE);
        assertEquals(inputElement.getAttribute("value"), String.valueOf(VALUE));
    }

    @Override
    protected <T extends MaterialWidget & HasError, H extends UIObject> void checkErrorSuccess(T widget, H target) {
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
