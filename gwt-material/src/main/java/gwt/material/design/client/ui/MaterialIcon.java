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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import gwt.material.design.client.base.AbstractButton;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.base.HasSeparator;
import gwt.material.design.client.base.mixin.ColorsMixin;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.*;

//@formatter:off

/**
 * We have included 740 Material Design Icons courtesy of Google.
 * You can download them directly from the Material Design specs.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code <m:MaterialIcon waves="LIGHT" iconType="POLYMER"/>
 * <m:MaterialIcon waves="LIGHT" iconType="POLYMER" textColor="BLUE" type="CIRCLE"/>
 * <m:MaterialIcon waves="LIGHT" iconType="POLYMER" backgroundColor="BLUE" textColor="WHITE" type="CIRCLE" tooltip="Tooltip" tooltipLocation="BOTTOM"/>}
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://www.google.com/design/icons/">Search Google Icons</a>
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#icons">Material Icons Documentation</a>
 */
//@formatter:on
public class MaterialIcon extends AbstractButton implements HasSeparator, HasIcon {

    private final CssNameMixin<MaterialIcon, IconPosition> posMixin = new CssNameMixin<>(this);
    private final CssNameMixin<MaterialIcon, IconSize> sizeMixin = new CssNameMixin<>(this);
    private final ToggleStyleMixin<MaterialIcon> prefixMixin = new ToggleStyleMixin<>(this, "prefix");
    private final ColorsMixin<MaterialIcon> colorsMixin = new ColorsMixin<>(this);

    /**
     * Creates an empty icon.
     */
    public MaterialIcon() {
        super(CssName.MATERIAL_ICONS);
    }

    /**
     * Sets a simple icon with a given type.
     */
    public MaterialIcon(IconType iconType) {
        this();
        setIconType(iconType);
    }

    /**
     * Sets an icon with backgroundColor.
     */
    public MaterialIcon(IconType iconType, Color bgColor) {
        this();
        setIconType(iconType);
        setBackgroundColor(bgColor);
    }

    /**
     * Sets an icon with textColor and backgroundColor.
     */
    public MaterialIcon(IconType iconType, Color textColor, Color bgColor) {
        this();
        setIconType(iconType);
        setTextColor(textColor);
        setBackgroundColor(bgColor);
    }

    public void setInnerText(String innerText) {
        getElement().setInnerText(innerText);
    }

    @Override
    protected Element createElement() {
        return Document.get().createElement("i");
    }

    @Override
    public MaterialIcon getIcon() {
        return this;
    }

    public IconType getIconType() {
        return IconType.fromStyleName(getElement().getInnerText());
    }

    @Override
    public void setIconType(IconType icon) {
        getElement().setInnerText(icon.getCssName());
    }

    @Override
    public void setIconPosition(IconPosition position) {
        posMixin.setCssName(position);
    }

    @Override
    public void setIconSize(IconSize size) {
        sizeMixin.setCssName(size);
    }

    public IconSize getIconSize() {
        return sizeMixin.getCssName();
    }

    @Override
    public void setIconColor(Color iconColor) {
        colorsMixin.setTextColor(iconColor);
    }

    @Override
    public void setIconFontSize(double size, Style.Unit unit) {
        getElement().getStyle().setFontSize(size, unit);
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        prefixMixin.setOn(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return prefixMixin.isOn();
    }
}
