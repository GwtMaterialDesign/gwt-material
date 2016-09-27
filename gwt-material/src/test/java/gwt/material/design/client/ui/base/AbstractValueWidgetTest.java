package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.base.HasPlaceholder;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;

public class AbstractValueWidgetTest extends MaterialWidgetTest {

    public <T extends AbstractValueWidget & HasPlaceholder, H extends UIObject> void checkAbstractValueWidget(T widget, H target) {
        checkWidget(widget);
        checkErrorSuccess(widget, target);
        checkPlaceholder(widget);
    }

    protected <T extends MaterialWidget & HasError, H extends UIObject> void checkErrorSuccess(T widget, H target) {
        widget.setError("Error");
        assertTrue(target.getElement().hasClassName(CssName.INVALID));
        widget.setSuccess("Success");
        assertTrue(target.getElement().hasClassName(CssName.VALID));
        widget.clearErrorOrSuccess();
        assertFalse(target.getElement().hasClassName(CssName.VALID));
        assertFalse(target.getElement().hasClassName(CssName.INVALID));
    }

    protected <T extends HasPlaceholder> void checkPlaceholder(T widget) {
        widget.setPlaceholder("Placeholder");
        assertEquals(widget.getPlaceholder(), "Placeholder");
        widget.setPlaceholder("");
        assertEquals(widget.getPlaceholder(), "");
    }
}
