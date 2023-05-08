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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import gwt.material.design.client.async.AsyncWidgetCallback;
import gwt.material.design.client.async.IsAsyncWidget;
import gwt.material.design.client.async.loader.AsyncDisplayLoader;
import gwt.material.design.client.base.AbstractButton;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.base.HasSeparator;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ColorsMixin;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.base.mixin.StyleMixin;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.theme.GlobalThemeConfig;

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
 * @see <a href="https://material.io/icons/">Material Design Specification</a>
 */
//@formatter:on
public class MaterialIcon extends AbstractButton
        implements HasSeparator, HasIcon, IsAsyncWidget<MaterialIcon, IconType> {

    private IconDisplay iconDisplay = MaterialWidget.getGlobalTheme().getIconDisplay();
    private CssNameMixin<MaterialIcon, IconPosition> positionMixin;
    private CssNameMixin<MaterialIcon, IconSize> sizeMixin;
    private ToggleStyleMixin<MaterialIcon> prefixMixin;
    private ToggleStyleMixin<MaterialIcon> materialIconToggleStyleMixin;
    private ColorsMixin<MaterialIcon> iconColorMixin;
    private StyleMixin<MaterialIcon> customIconMixin;

    /**
     * Creates an empty icon.
     */
    public MaterialIcon() {
        super();
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
        getMaterialIconToggleStyleMixin().setOn(true);
        getElement().setInnerText(icon.getCssName());
    }

    @Override
    public void setIconPosition(IconPosition position) {
        getPositionMixin().setCssName(position);
    }

    @Override
    public void setIconSize(IconSize size) {
        getSizeMixin().setCssName(size);
    }

    public IconSize getIconSize() {
        return getSizeMixin().getCssName();
    }

    @Override
    public void setIconColor(Color iconColor) {
        getIconColorMixin().setTextColor(iconColor);
    }

    @Override
    public Color getIconColor() {
        return getIconColorMixin().getTextColor();
    }

    @Override
    public void setIconFontSize(double size, Style.Unit unit) {
        getElement().getStyle().setFontSize(size, unit);
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        getPrefixMixin().setOn(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return getPrefixMixin().isOn();
    }

    @Override
    public void setCustomIconType(String iconType) {
        getMaterialIconToggleStyleMixin().setOn(false);
        getCustomIconMixin().setStyle(iconType);
    }

    @Override
    public String getCustomIconType() {
        return getCustomIconMixin().getStyle();
    }

    @Override
    public void setIconDisplay(IconDisplay iconDisplay) {
        this.iconDisplay = iconDisplay;
    }

    @Override
    public IconDisplay getIconDisplay() {
        return iconDisplay;
    }

    protected CssNameMixin<MaterialIcon, IconPosition> getPositionMixin() {
        if (positionMixin == null) {
            positionMixin = new CssNameMixin<>(this);
        }
        return positionMixin;
    }

    protected CssNameMixin<MaterialIcon, IconSize> getSizeMixin() {
        if (sizeMixin == null) {
            sizeMixin = new CssNameMixin<>(this);
        }
        return sizeMixin;
    }

    protected ToggleStyleMixin<MaterialIcon> getPrefixMixin() {
        if (prefixMixin == null) {
            prefixMixin = new ToggleStyleMixin<>(this, CssName.PREFIX);
        }
        return prefixMixin;
    }

    protected ColorsMixin<MaterialIcon> getIconColorMixin() {
        if (iconColorMixin == null) {
            iconColorMixin = new ColorsMixin<>(this);
        }
        return iconColorMixin;
    }

    protected StyleMixin<MaterialIcon> getCustomIconMixin() {
        if (customIconMixin ==  null) {
            customIconMixin = new CssNameMixin<>(this);
        }
        return customIconMixin;
    }

    protected ToggleStyleMixin<MaterialIcon> getMaterialIconToggleStyleMixin() {
        if (materialIconToggleStyleMixin == null) {
            materialIconToggleStyleMixin = new ToggleStyleMixin<>(this, CssName.MATERIAL_ICONS + getIconDisplay().getSuffix());
        }
        return materialIconToggleStyleMixin;
    }

    @Override
    public void setAsynchronous(boolean asynchronous) {

    }

    @Override
    public boolean isAsynchronous() {
        return false;
    }

    @Override
    public void load(AsyncWidgetCallback<MaterialIcon, IconType> asyncCallback) {

    }

    @Override
    public void setLoaded(boolean loaded) {

    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public void setAsyncCallback(AsyncWidgetCallback<MaterialIcon, IconType> asyncCallback) {

    }

    @Override
    public AsyncWidgetCallback<MaterialIcon, IconType> getAsyncCallback() {
        return null;
    }

    @Override
    public void setAsyncDisplayLoader(AsyncDisplayLoader displayLoader) {

    }

    @Override
    public AsyncDisplayLoader getAsyncDisplayLoader() {
        return null;
    }
}
