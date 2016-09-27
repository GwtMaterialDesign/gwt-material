package gwt.material.design.client.ui;

import gwt.material.design.client.ui.base.AbstractValueWidgetTest;
import org.junit.Test;

public class MaterialDatePickerTest extends AbstractValueWidgetTest {

    @Test
    public void testDatePicker() {
        MaterialDatePicker datePicker = new MaterialDatePicker();
        checkAbstractValueWidget(datePicker, datePicker.getDateInput());
    }
}
