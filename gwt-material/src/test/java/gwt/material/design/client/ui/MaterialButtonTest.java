package gwt.material.design.client.ui;

import gwt.material.design.client.ui.base.MaterialWidgetTest;
import org.junit.Test;

/**
 * Test case for Material Button
 *
 * @kevzlou7979
 */
public class MaterialButtonTest extends MaterialWidgetTest {

    @Test
    public void testButton() {
        MaterialButton button = new MaterialButton();
        button.setText("Sample");
        checkEnabled(button);
    }
}
