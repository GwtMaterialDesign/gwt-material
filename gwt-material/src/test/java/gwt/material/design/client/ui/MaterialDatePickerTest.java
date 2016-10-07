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

import gwt.material.design.client.constants.DatePickerLanguage;
import gwt.material.design.client.constants.Orientation;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;
import org.junit.Test;

import java.util.Date;

/**
 * Test case fir Datepicker
 *
 * @author kevzlou7979
 */
public class MaterialDatePickerTest extends AbstractValueWidgetTest {

    @Test
    public void testDatePicker() {
        MaterialDatePicker datePicker = new MaterialDatePicker();
        checkAbstractValueWidget(datePicker, datePicker.getDateInput());
        checkOpenHandler(datePicker);
        checkCloseHandler(datePicker);
        checkOptions(datePicker);
        checkOpenCloseControl(datePicker);
    }

    public <T extends MaterialDatePicker> void checkOpenCloseControl(T datePicker) {
        /*final boolean[] isBlurFired = {false};
        datePicker.addBlurHandler(blurEvent -> {
            isBlurFired[0] = true;
        });

        fireBlurEvent(datePicker);
        assertTrue(isBlurFired[0]);*/

        final boolean[] isOpenFired = {false};
        datePicker.addOpenHandler(openEvent -> {
            isOpenFired[0] = true;
        });
        final boolean[] isCloseFired = {false};
        datePicker.addCloseHandler(closeEvent -> {
            isCloseFired[0] = true;
        });
        datePicker.open();
        datePicker.close();
        assertTrue(isOpenFired[0]);
        assertTrue(isCloseFired[0]);
    }

    public <T extends MaterialDatePicker> void checkOptions(T datePicker) {
        datePicker.initialize();
        final String FORMAT = "mm/dd/yyyy";
        final Date DATE_MIN = new Date(116, 2, 2);
        final Date DATE_MAX = new Date(116, 2, 20);
        final DatePickerLanguage LANGUAGE = DatePickerLanguage.DA;
        // Check Format
        datePicker.setFormat(FORMAT);
        assertEquals(datePicker.getFormat(), FORMAT);
        // Check Date Min and Date Max
        datePicker.setDateMin(DATE_MIN);
        assertEquals(datePicker.getDateMin(), DATE_MIN);
        datePicker.setDateMax(DATE_MAX);
        assertEquals(datePicker.getDateMax(), DATE_MAX);
        // Check Language
        datePicker.setLanguage(LANGUAGE);
        assertEquals(datePicker.getLanguage(), LANGUAGE);
        // Check Orientation
        datePicker.setOrientation(Orientation.LANDSCAPE);
        assertEquals(datePicker.getOrientation(), Orientation.LANDSCAPE);
        assertTrue(datePicker.getElement().hasClassName(Orientation.LANDSCAPE.getCssName()));
        datePicker.setOrientation(Orientation.PORTRAIT);
        assertEquals(datePicker.getOrientation(), Orientation.PORTRAIT);
        assertTrue(datePicker.getElement().hasClassName(Orientation.PORTRAIT.getCssName()));
        // Check Selection Type
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
