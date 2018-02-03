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

import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.DatePickerContainer;
import gwt.material.design.client.constants.DatePickerLanguage;
import gwt.material.design.client.constants.Orientation;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;

import java.util.Date;

import static gwt.material.design.client.js.JsMaterialElement.$;

/**
 * Test case for Datepicker.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialDatePickerTest extends AbstractValueWidgetTest<MaterialDatePicker> {

    final static Date DATE = new Date(116, 2, 5);
    final static Date SECOND_DATE = new Date(116, 1, 0);

    @Override
    protected MaterialDatePicker createWidget() {
        return new MaterialDatePicker();
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.INPUT_FIELD);
    }

    public void testStructure() {
        // given
        MaterialDatePicker picker = getWidget();

        // when / then
        assertEquals(CssName.INPUT_FIELD, picker.getInitialClasses()[0]);
        assertEquals(3, picker.getWidgetCount());
        assertEquals(picker.getDateInput(), picker.getWidget(0));
        assertEquals(picker.getLabel(), picker.getWidget(1));
        assertEquals(picker.getPlaceholderLabel(), picker.getLabel().getWidget(0));
        assertEquals(picker.getErrorLabel(), picker.getWidget(2));
    }

    public void testPlaceholder() {
        // UiBinder
        // given
        MaterialDatePicker datePicker = getWidget(false);

        // when / then
        datePicker.setPlaceholder("Placeholder");
        assertEquals("Placeholder", datePicker.getPlaceholder());
        datePicker.setPlaceholder("");
        assertEquals("", datePicker.getPlaceholder());

        // Standard
        // given
        attachWidget();

        // when / then
        datePicker.setPlaceholder("Placeholder");
        assertEquals("Placeholder", datePicker.getPlaceholder());
        datePicker.setPlaceholder("");
        assertEquals("", datePicker.getPlaceholder());
    }

    public void testErrorSuccess() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        checkFieldErrorSuccess(datePicker, datePicker.getErrorLabel(), datePicker.getDateInput(), datePicker.getPlaceholderLabel());
    }

    public void testReadOnly() {
        // UiBinder
        // given
        MaterialDatePicker datePicker = getWidget(false);

        // when / then
        checkReadOnly(datePicker, false);

        // Standard
        // given
        attachWidget();

        // when / then
        checkReadOnly(datePicker, true);
    }

    public void testFieldIcon() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        checkFieldIcon(datePicker);
    }

    public void testContainer() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        // Check the default container - SELF
        assertEquals(DatePickerContainer.SELF, datePicker.getContainer());

        datePicker.setContainer(DatePickerContainer.BODY);
        assertEquals(DatePickerContainer.BODY, datePicker.getContainer());

        datePicker.setContainer(DatePickerContainer.SELF);
        assertEquals(DatePickerContainer.SELF, datePicker.getContainer());
    }

    public void testAutoClose() {
        // UiBinder
        // given
        MaterialDatePicker datePicker = getWidget(false);

        // when / then
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, false);
        // Test multiple set to autoclose (leaks checking)
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, false);
        checkAutoClose(datePicker, false);

        // Standard
        // given
        attachWidget();

        // when / then
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, false);
        // Test multiple set to autoclose (leaks checking)
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, false);
        checkAutoClose(datePicker, false);
    }

    public void testClear() {
        // UiBinder
        // given
        MaterialDatePicker picker = getWidget(false);

        // when / then
        checkClear(picker);

        // Standard
        // given
        attachWidget();

        // when / then
        checkClear(picker);
    }

    protected void checkClear(MaterialDatePicker picker) {
        picker.setValue(DATE);
        picker.setError("error");
        picker.clear();

        assertEquals("", picker.getDateInput().getElement().getInnerText());
        assertFalse(picker.getLabel().getElement().hasClassName(CssName.ACTIVE));
        assertFalse(picker.getDateInput().getElement().hasClassName(CssName.VALID));
        assertFalse(picker.getDateInput().getElement().hasClassName(CssName.INVALID));

        assertFalse(picker.getErrorLabel().getElement().hasClassName(CssName.FIELD_ERROR_LABEL));
        assertFalse(picker.getDateInput().getElement().hasClassName(CssName.FIELD_ERROR));
        assertNull(picker.getValue());
    }

    protected void checkAutoClose(MaterialDatePicker datePicker, boolean autoClose) {
        datePicker.setAutoClose(autoClose);
        if (autoClose) {
            assertTrue(datePicker.isAutoClose());
        } else {
            assertFalse(datePicker.isAutoClose());
        }
    }

    public void testOpenCloseControl() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        datePicker.setEnabled(true);
        assertTrue(datePicker.isEnabled());
        checkOpenHandler(datePicker);
        checkCloseHandler(datePicker);
    }

    public void testFormat() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        final String FORMAT = "mm/dd/yyyy";
        datePicker.setFormat(FORMAT);
        assertEquals(FORMAT, datePicker.getFormat());
    }

    @Override
    public void testEnabled() {
        super.testEnabled();

        destroyWidget();

        // UiBinder
        // given
        MaterialDatePicker datePicker = getWidget(false);

        // when / then
        datePicker.setEnabled(true);
        datePicker.setEnabled(false);

        // Standard
        // given
        attachWidget();

        // when / then
        datePicker.setEnabled(true);
        // Check Initial Tab index value for Root Picker Element - tabindex = 0
        assertEquals("0", $(datePicker.getPickerRootElement()).attr("tabindex"));
        // Expected when datepicker is disabled - tabindex = -1
        datePicker.setEnabled(false);
        assertEquals("-1", $(datePicker.getPickerRootElement()).attr("tabindex"));
    }

    public void testDateMinAndMax() {
        // UiBinder
        // given
        MaterialDatePicker datePicker = getWidget(false);

        final Date DATE_MIN = new Date(116, 2, 2);
        final Date DATE_MAX = new Date(116, 2, 20);

        // when / then
        datePicker.setDateMin(DATE_MIN);
        assertEquals(DATE_MIN, datePicker.getDateMin());
        datePicker.setDateMax(DATE_MAX);
        assertEquals(DATE_MAX, datePicker.getDateMax());

        // Standard
        // given
        attachWidget();

        // when / then
        datePicker.setDateMin(DATE_MIN);
        assertEquals(DATE_MIN, datePicker.getDateMin());
        datePicker.setDateMax(DATE_MAX);
        assertEquals(DATE_MAX, datePicker.getDateMax());
    }

    public void testDateValue() {
        // UiBinder
        // given
        MaterialDatePicker datePicker = getWidget(false);

        // when / then
        datePicker.setValue(DATE);
        assertEquals(datePicker.getValue(), DATE);

        datePicker.setValue(null);
        assertEquals(datePicker.getValue(), null);

        // Standard
        // given
        attachWidget();

        boolean[] isValueChanged = {false};
        datePicker.addValueChangeHandler(event -> isValueChanged[0] = true);

        datePicker.setDate(DATE);
        assertEquals(DATE, datePicker.getDate());

        assertEquals(DATE, datePicker.getValue());

        datePicker.setEnabled(true);

        datePicker.setValue(DATE);
        assertEquals(datePicker.getValue(), DATE);

        datePicker.setValue(null);
        assertEquals(datePicker.getValue(), null);

        datePicker.setValue(DATE);
        datePicker.setValue(DATE, false);
        assertEquals(DATE, datePicker.getValue());
        assertFalse(isValueChanged[0]);

        datePicker.setValue(SECOND_DATE, true);
        assertTrue(isValueChanged[0]);
    }

    public void testLanguage() {
        // UiBinder
        // given
        MaterialDatePicker datePicker = getWidget(false);

        final DatePickerLanguage DANISH = DatePickerLanguage.DA;

        // when / then
        datePicker.setLanguage(DANISH);
        assertEquals(DANISH, datePicker.getLanguage());

        // Standard
        // given
        attachWidget();

        // when / then
        datePicker.setLanguage(DANISH);
        assertEquals(DANISH, datePicker.getLanguage());
    }

    public void testOrientation() {
        // UiBinder
        // given
        MaterialDatePicker datePicker = getWidget(false);

        // when / then
        datePicker.setOrientation(Orientation.LANDSCAPE);
        assertEquals(Orientation.LANDSCAPE, datePicker.getOrientation());
        datePicker.setOrientation(Orientation.PORTRAIT);
        assertEquals(Orientation.PORTRAIT, datePicker.getOrientation());

        // Standard
        // given
        attachWidget();

        // when / then
        JsMaterialElement element = $(datePicker.pickatizedDateInput).pickadate("picker");
        datePicker.setOrientation(Orientation.LANDSCAPE);
        assertTrue(element.root.hasClass(Orientation.LANDSCAPE.getCssName()));
        datePicker.setOrientation(Orientation.PORTRAIT);
        assertTrue(element.root.hasClass(Orientation.PORTRAIT.getCssName()));
    }

    public void testSelectionType() {
        // UiBinder
        // given
        MaterialDatePicker datePicker = getWidget(false);

        // when / then
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.DAY);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.DAY, datePicker.getSelectionType());
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR_MONTH_DAY);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.YEAR_MONTH_DAY, datePicker.getSelectionType());
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.MONTH_DAY);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.MONTH_DAY, datePicker.getSelectionType());
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.YEAR, datePicker.getSelectionType());

        // Standard
        // given
        attachWidget();

        // when / then
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.DAY);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.DAY, datePicker.getSelectionType());
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR_MONTH_DAY);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.YEAR_MONTH_DAY, datePicker.getSelectionType());
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.MONTH_DAY);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.MONTH_DAY, datePicker.getSelectionType());
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.YEAR, datePicker.getSelectionType());
    }

    public void testYearsToDisplay() {
        // UiBinder
        // given
        MaterialDatePicker picker = getWidget(false);

        // when / then
        picker.setYearsToDisplay(10);
        assertEquals(10, picker.getYearsToDisplay());
        picker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.DAY, 20);
        assertEquals(20, picker.getYearsToDisplay());

        // Standard
        // given
        attachWidget();

        // when / then
        picker.setYearsToDisplay(10);
        assertEquals(10, picker.getYearsToDisplay());
        picker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.DAY, 20);
        assertEquals(20, picker.getYearsToDisplay());
    }

    @Override
    public void testTabIndex() {
        MaterialDatePicker datePicker = getWidget();
        super.checkTabIndex(datePicker.getDateInput());
    }

    public void testDateFormat() {
        // UiBinder
        // given
        MaterialDatePicker datePicker = getWidget(false);

        // when / then
        checkAllFormats(datePicker);

        // Standard
        // given
        attachWidget();

        // when / then
        checkAllFormats(datePicker);
    }

    protected void checkAllFormats(MaterialDatePicker datePicker) {
        checkFormat(datePicker, "YYYY-MM-DD", "yyyy-mm-dd");
        checkFormat(datePicker, "DD", "dd");
        checkFormat(datePicker, "MM", "mm");
        checkFormat(datePicker, "YYYY", "yyyy");
        // If Format is null, expected would be the default format (d mmmm, yyyy)
        checkFormat(datePicker, null, "d mmmm, yyyy");
    }

    protected void checkFormat(MaterialDatePicker datePicker, String format, String expected) {
        datePicker.setFormat(format);
        assertEquals(expected, datePicker.getFormat());
    }
}
