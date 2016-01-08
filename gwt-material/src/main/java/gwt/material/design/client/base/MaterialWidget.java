package gwt.material.design.client.base;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.helper.StyleHelper;
import gwt.material.design.client.base.mixin.*;
import gwt.material.design.client.constants.*;

public class MaterialWidget extends ComplexPanel implements HasId, HasEnabled, HasTextAlign, HasColors, HasGrid,
        HasShadow, Focusable, HasInlineStyle, HasSeparator, HasScrollspy, HasHideOn, HasShowOn, HasCenterOn,
        HasCircle, HasWaves, HasDataAttributes, HasFloat, HasTooltip, HasFlexbox, HasHoverable, HasFontWeight, HasDepth {

    private IdMixin<MaterialWidget> idMixin;
    private EnabledMixin<MaterialWidget> enabledMixin;
    private CssNameMixin<MaterialWidget, TextAlign> textAlignMixin;
    private ColorsMixin<MaterialWidget> colorsMixin;
    private FocusableMixin<MaterialWidget> focusableMixin;
    private GridMixin<MaterialWidget> gridMixin;
    private ShadowMixin<MaterialWidget> shadowMixin;
    private SeparatorMixin<MaterialWidget> separatorMixin;
    private ScrollspyMixin<MaterialWidget> scrollspyMixin;
    private CssNameMixin<MaterialWidget, HideOn> hideOnMixin;
    private CssNameMixin<MaterialWidget, ShowOn> showOnMixin;
    private CssNameMixin<MaterialWidget, CenterOn> centerOnMixin;
    private FontSizeMixin<MaterialWidget> fontSizeMixin;
    private ToggleStyleMixin<MaterialWidget> circleMixin;
    private WavesMixin<MaterialWidget> wavesMixin;
    private CssNameMixin<MaterialWidget, Style.Float> floatMixin;
    private TooltipMixin<MaterialWidget> tooltipMixin;
    private FlexboxMixin<MaterialWidget> flexboxMixin;
    private ToggleStyleMixin<MaterialWidget> hoverableMixin;
    private CssNameMixin<MaterialWidget, Style.FontWeight> fontWeightMixin;

    public MaterialWidget() {
    }

    public MaterialWidget(Element element) {
        setElement(element);
    }

    @Override
    public void add(final Widget child) {
        add(child, (Element) getElement());
    }

    /**
     * Inserts a widget at a specific index
     *
     * @param child       - widget to be inserted
     * @param beforeIndex - index for the widget
     */
    public void insert(final Widget child, final int beforeIndex) {
        insert(child, (Element) getElement(), beforeIndex, true);
    }

    private IdMixin<MaterialWidget> getIdMixin() {
        if(idMixin == null) { idMixin = new IdMixin<>(this); }
        return idMixin;
    }

    private EnabledMixin<MaterialWidget> getEnabledMixin() {
        if(enabledMixin == null) { enabledMixin = new EnabledMixin<>(this); }
        return enabledMixin;
    }

    private CssNameMixin<MaterialWidget, TextAlign> getTextAlignMixin() {
        if(textAlignMixin == null) { textAlignMixin = new CssNameMixin<>(this); }
        return textAlignMixin;
    }

    private ColorsMixin<MaterialWidget> getColorsMixin() {
        if(colorsMixin == null) { colorsMixin = new ColorsMixin<>(this); }
        return colorsMixin;
    }

    private FocusableMixin<MaterialWidget> getFocusableMixin() {
        if(focusableMixin == null) { focusableMixin = new FocusableMixin<>(this); }
        return focusableMixin;
    }

    private GridMixin<MaterialWidget> getGridMixin() {
        if(gridMixin == null) { gridMixin = new GridMixin<>(this); }
        return gridMixin;
    }

    private ShadowMixin<MaterialWidget> getShadowMixin() {
        if(shadowMixin == null) { shadowMixin = new ShadowMixin<>(this); }
        return shadowMixin;
    }

    private SeparatorMixin<MaterialWidget> getSeparatorMixin() {
        if(separatorMixin == null) { separatorMixin = new SeparatorMixin<>(this); }
        return separatorMixin;
    }

    private ScrollspyMixin<MaterialWidget> getScrollspyMixin() {
        if(scrollspyMixin == null) { scrollspyMixin = new ScrollspyMixin<>(this); }
        return scrollspyMixin;
    }

    private CssNameMixin<MaterialWidget, HideOn> getHideOnMixin() {
        if(hideOnMixin == null) { hideOnMixin = new CssNameMixin<>(this); }
        return hideOnMixin;
    }

    private CssNameMixin<MaterialWidget, ShowOn> getShowOnMixin() {
        if(showOnMixin == null) { showOnMixin = new CssNameMixin<>(this); }
        return showOnMixin;
    }

    private CssNameMixin<MaterialWidget, CenterOn> getCenterOnMixin() {
        if(centerOnMixin == null) { centerOnMixin = new CssNameMixin<>(this); }
        return centerOnMixin;
    }

    private FontSizeMixin<MaterialWidget> getFontSizeMixin() {
        if(fontSizeMixin == null) { fontSizeMixin = new FontSizeMixin<>(this); }
        return fontSizeMixin;
    }

    private ToggleStyleMixin<MaterialWidget> getCircleMixin() {
        if(circleMixin == null) { circleMixin = new ToggleStyleMixin<>(this, "circle"); }
        return circleMixin;
    }

    private ToggleStyleMixin<MaterialWidget> getHoverableMixin() {
        if(hoverableMixin == null) { hoverableMixin = new ToggleStyleMixin<>(this, "hoverable"); }
        return hoverableMixin;
    }

    private WavesMixin<MaterialWidget> getWavesMixin() {
        if(wavesMixin == null) { wavesMixin = new WavesMixin<>(this); }
        return wavesMixin;
    }

    private CssNameMixin<MaterialWidget, Style.Float> getFloatMixin() {
        if(floatMixin == null) { floatMixin = new CssNameMixin<>(this); }
        return floatMixin;
    }

    private TooltipMixin<MaterialWidget> getTooltipMixin() {
        if(tooltipMixin == null) { tooltipMixin = new TooltipMixin<>(this); }
        return tooltipMixin;
    }

    private FlexboxMixin<MaterialWidget> getFlexboxMixin() {
        if(flexboxMixin == null) { flexboxMixin = new FlexboxMixin<>(this); }
        return flexboxMixin;
    }

    private CssNameMixin<MaterialWidget, Style.FontWeight> getFontWeightMixin() {
        if(fontWeightMixin == null) { fontWeightMixin = new CssNameMixin<>(this); }
        return fontWeightMixin;
    }

    @Override
    public void setId(String id) {
        getIdMixin().setId(id);
    }

    @Override
    public String getId() {
        return getIdMixin().getId();
    }

    @Override
    public boolean isEnabled() {
        return getEnabledMixin().isEnabled();
    }

    @Override
    public void setEnabled(boolean enabled) {
        getEnabledMixin().setEnabled(enabled);
    }

    @Override
    public TextAlign getTextAlign() {
        return getTextAlignMixin().getCssName();
    }

    @Override
    public void setTextAlign(TextAlign align) {
        getTextAlignMixin().setCssName(align);
    }

    @Override
    public void setBackgroundColor(String bgColor) {
        getColorsMixin().setBackgroundColor(bgColor);
    }

    @Override
    public String getBackgroundColor() {
        return getColorsMixin().getBackgroundColor();
    }

    @Override
    public void setTextColor(String textColor) {
        getColorsMixin().setTextColor(textColor);
    }

    @Override
    public String getTextColor() {
        return getColorsMixin().getTextColor();
    }

    @Override
    public int getTabIndex() {
        return getFocusableMixin().getTabIndex();
    }

    @Override
    public void setAccessKey(char key) {
        getFocusableMixin().setAccessKey(key);
    }

    @Override
    public void setFocus(boolean focused) {
        getFocusableMixin().setFocus(focused);
    }

    @Override
    public void setTabIndex(int index) {
        getFocusableMixin().setTabIndex(index);
    }

    @Override
    public void setGrid(String grid) {
        getGridMixin().setGrid(grid);
    }

    @Override
    public void setOffset(String offset) {
        getGridMixin().setOffset(offset);
    }

    @Override
    public void setShadow(int shadow) {
        getShadowMixin().setShadow(shadow);
    }

    @Override
    public int getShadow() {
        return getShadowMixin().getShadow();
    }

    @Override
    public void setMargin(double margin) {
        getElement().getStyle().setMargin(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginTop(final double margin) {
        getElement().getStyle().setMarginTop(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginLeft(final double margin) {
        getElement().getStyle().setMarginLeft(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginRight(final double margin) {
        getElement().getStyle().setMarginRight(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginBottom(final double margin) {
        getElement().getStyle().setMarginBottom(margin, Style.Unit.PX);
    }

    @Override
    public void setPadding(double padding) {
        getElement().getStyle().setPadding(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingTop(final double padding) {
        getElement().getStyle().setPaddingTop(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingLeft(final double padding) {
        getElement().getStyle().setPaddingLeft(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingRight(final double padding) {
        getElement().getStyle().setPaddingRight(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingBottom(final double padding) {
        getElement().getStyle().setPaddingBottom(padding, Style.Unit.PX);
    }

    @Override
    public void setDisplay(Style.Display display) {
        getFlexboxMixin().setDisplay(display);
    }

    @Override
    public void setOpacity(double opacity) {
        getElement().getStyle().setOpacity(opacity);
    }

    @Override
    public double getOpacity() {
        return Double.parseDouble(getElement().getStyle().getOpacity());
    }

    @Override
    public void setSeparator(boolean separator) {
        getSeparatorMixin().setSeparator(separator);
    }

    @Override
    public boolean isSeparator() {
        return getSeparatorMixin().isSeparator();
    }

    @Override
    public void setScrollspy(String scrollspy) {
        getScrollspyMixin().setScrollspy(scrollspy);
    }

    @Override
    public String getScrollspy() {
        return getScrollspyMixin().getScrollspy();
    }

    @Override
    public void setCenterOn(CenterOn centerOn) {
        getCenterOnMixin().setCssName(centerOn);
    }

    @Override
    public CenterOn getCenterOn() {
        return getCenterOnMixin().getCssName();
    }

    @Override
    public void setHideOn(HideOn hideOn) {
        getHideOnMixin().setCssName(hideOn);
    }

    @Override
    public HideOn getHideOn() {
        return getHideOnMixin().getCssName();
    }

    @Override
    public void setShowOn(ShowOn showOn) {
        getShowOnMixin().setCssName(showOn);
    }

    @Override
    public ShowOn getShowOn() {
        return getShowOnMixin().getCssName();
    }

    @Override
    public void setFontSize(String fontSize) {
        getFontSizeMixin().setFontSize(fontSize);
    }

    @Override
    public String getFontSize() {
        return getFontSizeMixin().getFontSize();
    }

    @Override
    public void setFontSize(double fontSize, Style.Unit unit) {
        getFontSizeMixin().setFontSize(fontSize, unit);
    }

    @Override
    public void setCircle(boolean circle) {
        getCircleMixin().setOn(circle);
    }

    @Override
    public boolean isCircle() {
        return getCircleMixin().isOn();
    }

    @Override
    public void setWaves(WavesType waves) {
        getWavesMixin().setWaves(waves);
    }

    @Override
    public WavesType getWaves() {
        return getWavesMixin().getWaves();
    }

    @Override
    public void setDataAttribute(String dataAttr, String value) {
        if(!dataAttr.startsWith("data-")) {
            dataAttr = "data-" + dataAttr;
        }
        getElement().setAttribute(dataAttr, value);
    }

    @Override
    public String getDataAttribute(String dataAttr) {
        if(!dataAttr.startsWith("data-")) {
            dataAttr = "data-" + dataAttr;
        }
        return getElement().getAttribute(dataAttr);
    }

    @Override
    public void setFloat(Style.Float floatAlign) {
        getFloatMixin().setCssName(floatAlign);
    }

    @Override
    public Style.Float getFloat() {
        return StyleHelper.fromStyleName(Style.Float.class, getFloatMixin().getCssName());
    }

    @Override
    public String getTooltip() {
        return getTooltipMixin().getTooltip();
    }

    @Override
    public void setTooltip(String tooltip) {
        getTooltipMixin().setTooltip(tooltip);
    }

    @Override
    public Position getTooltipPosition() {
        return getTooltipMixin().getTooltipPosition();
    }

    @Override
    public void setTooltipPosition(Position position) {
        getTooltipMixin().setTooltipPosition(position);
    }

    @Override
    public int getTooltipDelayMs() {
        return getTooltipMixin().getTooltipDelayMs();
    }

    @Override
    public void setTooltipDelayMs(int delayMs) {
        getTooltipMixin().setTooltipDelayMs(delayMs);
    }

    public void setVisibility(Style.Visibility visibility) {
        getElement().getStyle().setVisibility(visibility);
    }

    @Override
    public void setDisplay(Display display) {
        getFlexboxMixin().setDisplay(display);
    }

    @Override
    public void setFlexDirection(FlexDirection flexDirection) {
        getFlexboxMixin().setFlexDirection(flexDirection);
    }

    @Override
    public void setFlex(Flex flex) {
        getFlexboxMixin().setFlex(flex);
    }

    @Override
    public void setFlexGrow(Integer flexGrow) {
        getFlexboxMixin().setFlexGrow(flexGrow);
    }

    @Override
    public void setFlexShrink(Integer flexShrink) {
        getFlexboxMixin().setFlexShrink(flexShrink);
    }

    @Override
    public void setFlexBasis(String flexBasis) {
        getFlexboxMixin().setFlexBasis(flexBasis);
    }

    @Override
    public void setFlexOrder(Integer flexOrder) {
        getFlexboxMixin().setFlexOrder(flexOrder);
    }

    @Override
    public void setFlexWrap(FlexWrap flexWrap) {
        getFlexboxMixin().setFlexWrap(flexWrap);
    }

    @Override
    public void setFlexAlignContent(FlexAlignContent flexAlignContent) {
        getFlexboxMixin().setFlexAlignContent(flexAlignContent);
    }

    @Override
    public void setFlexAlignSelf(FlexAlignSelf flexAlignSelf) {
        getFlexboxMixin().setFlexAlignSelf(flexAlignSelf);
    }

    @Override
    public void setFlexAlignItems(FlexAlignItems flexAlignItems) {
        getFlexboxMixin().setFlexAlignItems(flexAlignItems);
    }

    @Override
    public void setFlexJustifyContent(FlexJustifyContent flexJustifyContent) {
        getFlexboxMixin().setFlexJustifyContent(flexJustifyContent);
    }

    public void setOverflow(Style.Overflow overflow) {
        getElement().getStyle().setOverflow(overflow);
    }

    public void setLayoutPosition(Style.Position position) {
        getElement().getStyle().setPosition(position);
    }

    public void setLeft(double value) {
        getElement().getStyle().setLeft(value, Style.Unit.PX);
    }

    public void setRight(double value) {
        getElement().getStyle().setRight(value, Style.Unit.PX);
    }

    public void setTop(double value) {
        getElement().getStyle().setTop(value, Style.Unit.PX);
    }

    public void setBottom(double value) {
        getElement().getStyle().setBottom(value, Style.Unit.PX);
    }

    @Override
    public void setHoverable(boolean hoverable) {
        getHoverableMixin().setOn(hoverable);
    }

    @Override
    public boolean isHoverable() {
        return getHoverableMixin().isOn();
    }

    @Override
    public void setFontWeight(Style.FontWeight fontWeight) {
        getElement().getStyle().setFontWeight(fontWeight);
    }

    @Override
    public String getFontWeight() {
        return getElement().getStyle().getFontWeight();
    }

    @Override
    public void setDepth(int depth) {
        getElement().getStyle().setZIndex(depth);
    }

    @Override
    public int getDepth() {
        return Integer.parseInt(getElement().getStyle().getZIndex());
    }
}
