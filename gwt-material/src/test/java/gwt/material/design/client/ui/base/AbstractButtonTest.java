package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.Element;
import gwt.material.design.client.base.AbstractButton;
import gwt.material.design.client.base.HasActivates;
import gwt.material.design.client.base.HasHref;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.constants.ButtonSize;
import gwt.material.design.client.constants.ButtonType;

/**
 * Test case for Abstract button
 *
 * @author kevzlou7979
 */
public abstract class AbstractButtonTest extends MaterialWidgetTest {

    public void checkBaseButton(AbstractButton button) {
        checkWidget(button);
        checkHref(button);
        checkActivates(button);
        checkType(button);
        checkSize(button);
    }

    public <T extends AbstractButton> void checkSize(T widget) {
        final Element element = widget.getElement();
        widget.setSize(ButtonSize.LARGE);
        assertTrue(element.hasClassName(ButtonSize.LARGE.getCssName()));
        widget.setSize(ButtonSize.MEDIUM);
        assertTrue(element.hasClassName(ButtonSize.MEDIUM.getCssName()));
        assertFalse(element.hasClassName(ButtonSize.LARGE.getCssName()));
        assertEquals(widget.getSize(), ButtonSize.MEDIUM);
    }

    public <T extends AbstractButton & HasType<ButtonType>> void checkType(T widget) {
        final Element element = widget.getElement();
        widget.setType(ButtonType.FLAT);
        assertTrue(element.hasClassName(ButtonType.FLAT.getCssName()));
        widget.setType(ButtonType.FLOATING);
        assertTrue(element.hasClassName(ButtonType.FLOATING.getCssName()));
        assertFalse(element.hasClassName(ButtonType.FLAT.getCssName()));
        assertEquals(widget.getType(), ButtonType.FLOATING);
    }

    public <T extends AbstractButton & HasActivates> void checkActivates(T widget) {
        final Element element = widget.getElement();
        widget.setActivates("test");
        assertTrue(element.hasAttribute("data-activates"));
        assertEquals(element.getAttribute("data-activates"), "test");
        assertEquals(widget.getActivates(), "test");
    }

    public <T extends AbstractButton & HasHref> void checkHref(T widget) {
        final Element element = widget.getElement();
        widget.setHref("href-test");
        assertTrue(element.hasAttribute("href"));
        assertEquals(element.getAttribute("href"), "href-test");
        assertEquals(widget.getHref(), "href-test");
        widget.setTarget("_blank");
        assertTrue(element.hasAttribute("target"));
        assertEquals(element.getAttribute("target"), "_blank");
        assertEquals(widget.getTarget(), "_blank");
    }
}
