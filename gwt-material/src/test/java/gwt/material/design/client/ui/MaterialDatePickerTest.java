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

import gwt.material.design.client.constants.DatePickerContainer;
import gwt.material.design.client.constants.DatePickerLanguage;
import gwt.material.design.client.constants.Orientation;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;

import java.util.Date;

import static gwt.material.design.client.js.JsMaterialElement.$;

/**
 * Test case for Datepicker
 *
 * @author kevzlou7979
 */
public class MaterialDatePickerTest extends AbstractValueWidgetTest {

    public void init() {
        MaterialDatePicker datePicker = new MaterialDatePicker();
        checkAbstractValueWidget(datePicker, datePicker.getDateInput());
        datePicker.initialize();
        assertNotNull(datePicker.getPickerId());
        checkFormat(datePicker);
        checkDateMinAndMax(datePicker);
        checkDateValue(datePicker);
        checkOrientation(datePicker);
        checkLanguage(datePicker);
        checkSelectionType(datePicker);
        checkOpenCloseControl(datePicker);
        checkAutoClose(datePicker);
        checkContainaer(datePicker);
    }

    public <T extends MaterialDatePicker> void checkContainaer(T datePicker) {
        // Check the default container - SELF
        assertEquals(datePicker.getContainer(), DatePickerContainer.SELF);

        datePicker.setContainer(DatePickerContainer.BODY);
        assertEquals(datePicker.getContainer(), DatePickerContainer.BODY);

        datePicker.setContainer(DatePickerContainer.SELF);
        assertEquals(datePicker.getContainer(), DatePickerContainer.SELF);
    }

    public <T extends MaterialDatePicker> void checkAutoClose(T datePicker) {
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, false);
        // Test multiple set to autoclose (leaks checking)
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, false);
        checkAutoClose(datePicker, false);
    }

    protected void checkAutoClose(MaterialDatePicker datePicker, boolean autoClose) {
        datePicker.setAutoClose(autoClose);
        if (autoClose) {
            assertTrue(datePicker.isAutoClose());
            assertNotNull(datePicker.autoCloseHandler);
        } else {
            assertFalse(datePicker.isAutoClose());
            assertNull(datePicker.autoCloseHandler);
        }
    }

    public <T extends MaterialDatePicker> void checkOpenCloseControl(T datePicker) {
        datePicker.setEnabled(true);
        assertTrue(datePicker.isEnabled());
        checkOpenHandler(datePicker);
        checkCloseHandler(datePicker);
    }

    public <T extends MaterialDatePicker> void checkFormat(T datePicker) {
        final String FORMAT = "mm/dd/yyyy";
        datePicker.setFormat(FORMAT);
        assertEquals(datePicker.getFormat(), FORMAT);
    }

    public <T extends MaterialDatePicker> void checkDateMinAndMax(T datePicker) {
        final Date DATE_MIN = new Date(116, 2, 2);
        final Date DATE_MAX = new Date(116, 2, 20);
        datePicker.setDateMin(DATE_MIN);
        assertEquals(datePicker.getDateMin(), DATE_MIN);
        datePicker.setDateMax(DATE_MAX);
        assertEquals(datePicker.getDateMax(), DATE_MAX);
    }

    public <T extends MaterialDatePicker> void checkDateValue(T datePicker) {
        final Date DATE = new Date(116, 2, 5);
        final Date SECOND_DATE = new Date(116, 1, 0);

        boolean[] isValueChanged = {false};
        datePicker.addValueChangeHandler(event -> isValueChanged[0] = true);

        datePicker.setDate(DATE);
        assertEquals(datePicker.getDate(), DATE);

        assertEquals(datePicker.getValue(), DATE);

        datePicker.setEnabled(true);

        datePicker.setValue(DATE);
        assertEquals(datePicker.getValue(), DATE);

        datePicker.setValue(DATE);
        datePicker.setValue(DATE, false);
        assertEquals(datePicker.getValue(), DATE);
        assertFalse(isValueChanged[0]);

        datePicker.setValue(SECOND_DATE, true);
        assertEquals(datePicker.getValue(), DATE);
        assertTrue(isValueChanged[0]);
    }

    public <T extends MaterialDatePicker> void checkLanguage(T datePicker) {
        final DatePickerLanguage LANGUAGE = DatePickerLanguage.DA;
        datePicker.setLanguage(LANGUAGE);
        assertEquals(datePicker.getLanguage(), LANGUAGE);
    }

    public <T extends MaterialDatePicker> void checkOrientation(T datePicker) {
        JsMaterialElement element = $(datePicker.pickatizedDateInput).pickadate("picker");
        datePicker.setOrientation(Orientation.LANDSCAPE);
        assertEquals(datePicker.getOrientation(), Orientation.LANDSCAPE);
        assertTrue(element.root.hasClass(Orientation.LANDSCAPE.getCssName()));
        datePicker.setOrientation(Orientation.PORTRAIT);
        assertEquals(datePicker.getOrientation(), Orientation.PORTRAIT);
        assertTrue(element.root.hasClass(Orientation.PORTRAIT.getCssName()));
    }

    public <T extends MaterialDatePicker> void checkSelectionType(T datePicker) {
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.DAY);
        assertEquals(datePicker.getSelectionType(), MaterialDatePicker.MaterialDatePickerType.DAY);
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR_MONTH_DAY);
        assertEquals(datePicker.getSelectionType(), MaterialDatePicker.MaterialDatePickerType.YEAR_MONTH_DAY);
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.MONTH_DAY);
        assertEquals(datePicker.getSelectionType(), MaterialDatePicker.MaterialDatePickerType.MONTH_DAY);
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR);
        assertEquals(datePicker.getSelectionType(), MaterialDatePicker.MaterialDatePickerType.YEAR);
    }

}
