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
package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.base.AbstractIconButton;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;
import org.junit.Ignore;

/**
 * Test case for Abstract button icon.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
@Ignore
public abstract class AbstractIconButtonTest<T extends AbstractIconButton> extends AbstractButtonTest<T> {

    public void testIcon() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        MaterialIcon icon = widget.getIcon();
        assertNotNull(icon);

        icon.setIconType(IconType.POLYMER);
        assertEquals(IconType.POLYMER, icon.getIconType());

        icon.setIconSize(IconSize.LARGE);
        assertEquals(IconSize.LARGE, icon.getIconSize());
        icon.setIconSize(IconSize.MEDIUM);
        assertEquals(IconSize.MEDIUM, icon.getIconSize());
        icon.setIconSize(IconSize.SMALL);
        assertEquals(IconSize.SMALL, icon.getIconSize());
        icon.setIconSize(IconSize.TINY);
        assertEquals(IconSize.TINY, icon.getIconSize());

        icon.setIconPosition(IconPosition.RIGHT);
        icon.setIconPosition(IconPosition.LEFT);
        icon.setIconColor(Color.AMBER);

        icon.setIconPrefix(true);
        assertTrue(icon.isIconPrefix());
        icon.setIconPrefix(false);
        assertFalse(icon.isIconPrefix());

        icon.setIconFontSize(2, Style.Unit.PX);

        // Standard
        // given
        attachWidget();

        // when / then
        icon.setIconType(IconType.POLYMER);
        assertEquals(IconType.POLYMER.getCssName(), icon.getElement().getInnerHTML());

        icon.setIconSize(IconSize.LARGE);
        assertTrue(icon.getElement().hasClassName(IconSize.LARGE.getCssName()));
        icon.setIconSize(IconSize.MEDIUM);
        assertTrue(icon.getElement().hasClassName(IconSize.MEDIUM.getCssName()));
        icon.setIconSize(IconSize.SMALL);
        assertTrue(icon.getElement().hasClassName(IconSize.SMALL.getCssName()));
        icon.setIconSize(IconSize.TINY);
        assertTrue(icon.getElement().hasClassName(IconSize.TINY.getCssName()));

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

        icon.setIconFontSize(2, Style.Unit.PX);
        assertEquals("2px", icon.getElement().getStyle().getFontSize());
    }
}
