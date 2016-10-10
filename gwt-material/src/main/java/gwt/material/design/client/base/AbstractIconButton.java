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
package gwt.material.design.client.base;

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.MaterialIcon;

/**
 * @author Ben Dol
 */
public abstract class AbstractIconButton extends AbstractButton implements HasIcon {

    private MaterialIcon icon = new MaterialIcon();

    public AbstractIconButton(ButtonType type, String text, MaterialIcon icon) {
        super(type, text);

        this.icon = icon;
        ensureIconAttached();
    }

    public AbstractIconButton(ButtonType type, String text) {
        super(type, text);
        setIconPosition(IconPosition.LEFT);
    }

    public AbstractIconButton(IconType iconType) {
        super();
        setIconType(iconType);
        setIconPosition(IconPosition.LEFT);
    }

    public AbstractIconButton(ButtonType type) {
        super(type);
        setIconPosition(IconPosition.LEFT);
    }

    public AbstractIconButton() {
        super();
        setIconPosition(IconPosition.LEFT);
    }

    public AbstractIconButton(String... initialClass) {
        super();
        setInitialClasses(initialClass);
    }

    @Override
    public MaterialIcon getIcon() {
        return icon;
    }

    @Override
    public void setIconType(IconType iconType) {
        icon.setIconType(iconType);
        ensureIconAttached();
    }

    @Override
    public void setIconPosition(IconPosition position) {
        icon.setIconPosition(position);
    }

    @Override
    public void setIconSize(IconSize size) {
        icon.setIconSize(size);
    }

    @Override
    public void setIconFontSize(double size, Style.Unit unit) {
        icon.setIconFontSize(size, unit);
    }

    @Override
    public void setIconColor(Color iconColor) {
        icon.setIconColor(iconColor);
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        icon.setIconPrefix(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return icon.isIconPrefix();
    }

    /**
     * Ensure the icon is attached in slot 0.
     */
    protected void ensureIconAttached() {
        if (icon != null && !icon.isAttached()) {
            insert(icon, 0);
        }
    }
}