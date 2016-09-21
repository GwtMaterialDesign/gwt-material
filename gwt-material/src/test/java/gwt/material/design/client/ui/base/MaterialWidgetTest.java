package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.HasEnabled;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;

/**
 * Test case for MaterialWidget base
 *
 * @author kebzlou7979
 */
public class MaterialWidgetTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "gwt.material.design.GWTMaterialDesign";
    }

    public <T extends MaterialWidget & HasEnabled> void checkEnabled(T widget) {
        final Element element = widget.getElement();
        assertFalse(element.hasClassName(CssName.DISABLED));
        assertFalse(element.hasAttribute(CssName.DISABLED));
        widget.setEnabled(true);
        assertFalse(element.hasClassName(CssName.DISABLED));
        assertFalse(element.hasAttribute(CssName.DISABLED));
        widget.setEnabled(false);
        assertTrue(element.hasClassName(CssName.DISABLED));
        assertTrue(element.hasAttribute(CssName.DISABLED));
    }
}
