package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.base.AbstractIconButton;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;

public class AbstractIconButtonTest extends AbstractButtonTest {

    public void checkIconButton(AbstractIconButton icon) {
        checkBaseButton(icon);
        checkIcon(icon);
    }

    protected <T extends AbstractIconButton> void checkIcon(T widget) {
        MaterialIcon icon = widget.getIcon();
        icon.setIconType(IconType.POLYMER);
        assertNotNull(icon);

        assertEquals(icon.getElement().getInnerHTML(), IconType.POLYMER.getCssName());
        assertEquals(icon.getIconType(), IconType.POLYMER);

        icon.setIconSize(IconSize.LARGE);
        assertTrue(icon.getElement().hasClassName(IconSize.LARGE.getCssName()));
        assertEquals(icon.getIconSize(), IconSize.LARGE);
        icon.setIconSize(IconSize.MEDIUM);
        assertTrue(icon.getElement().hasClassName(IconSize.MEDIUM.getCssName()));
        assertEquals(icon.getIconSize(), IconSize.MEDIUM);
        icon.setIconSize(IconSize.SMALL);
        assertTrue(icon.getElement().hasClassName(IconSize.SMALL.getCssName()));
        assertEquals(icon.getIconSize(), IconSize.SMALL);
        icon.setIconSize(IconSize.TINY);
        assertTrue(icon.getElement().hasClassName(IconSize.TINY.getCssName()));
        assertEquals(icon.getIconSize(), IconSize.TINY);

        icon.setIconPosition(IconPosition.RIGHT);
        assertTrue(icon.getElement().hasClassName(IconPosition.RIGHT.getCssName()));
        icon.setIconPosition(IconPosition.LEFT);
        assertTrue(icon.getElement().hasClassName(IconPosition.LEFT.getCssName()));
        icon.setIconColor(Color.AMBER);
        assertTrue(icon.getElement().hasClassName(Color.AMBER.getCssName() + "-text"));

        icon.setIconPrefix(true);
        assertTrue(icon.isIconPrefix());
        assertTrue(icon.getElement().hasClassName("prefix"));
        icon.setIconPrefix(false);
        assertFalse(icon.getElement().hasClassName("prefix"));
        assertFalse(icon.isIconPrefix());

        icon.setIconFontSize(2, Style.Unit.PX);
        assertEquals(icon.getElement().getStyle().getFontSize(), "2px");
    }
}
