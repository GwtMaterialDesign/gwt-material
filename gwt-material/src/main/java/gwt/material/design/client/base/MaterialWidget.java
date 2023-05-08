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
package gwt.material.design.client.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;
import gwt.material.design.client.base.helper.StyleHelper;
import gwt.material.design.client.base.mixin.*;
import gwt.material.design.client.base.validator.HasValidators;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.events.DragEndEvent;
import gwt.material.design.client.events.DragEnterEvent;
import gwt.material.design.client.events.DragLeaveEvent;
import gwt.material.design.client.events.DragOverEvent;
import gwt.material.design.client.events.DragStartEvent;
import gwt.material.design.client.events.DropEvent;
import gwt.material.design.client.events.*;
import gwt.material.design.client.events.OrientationChangeEvent.OrientationChangeHandler;
import gwt.material.design.client.theme.GlobalThemeConfig;
import gwt.material.design.client.theme.ThemeManager;
import gwt.material.design.jquery.client.api.JQuery;
import gwt.material.design.jquery.client.api.JQueryElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class MaterialWidget extends ComplexPanel implements HasId, HasEnabled, HasTextAlign, HasDimension, HasColors, HasGrid,
    HasShadow, Focusable, HasInlineStyle, HasSeparator, HasScrollspy, HasHideOn, HasShowOn, HasCenterOn, HasCircle, HasWaves,
    HasDataAttributes, HasFloat, HasTooltip, HasFlexbox, HasHoverable, HasFontWeight, HasFontSize, HasDepth, HasInitialClasses,
    HasInteractionHandlers, HasAllFocusHandlers, HasFilterStyle, HasBorder, HasVerticalAlign, HasTransform, HasOrientation,
    HasContainer, HasWordBreak, HasZoom, HasGridLayout, HasResize, HasContentEditable, HasTruncate {

    private static JQueryElement window = null;
    private static JQueryElement body = null;
    private static GlobalThemeConfig themeConfig;

    public static JQueryElement window() {
        if (window == null) {
            window = $(JQuery.window());
        }
        return window;
    }

    public static JQueryElement body() {
        if (body == null) {
            body = $("body");
        }
        return body;
    }

    /**
     * Configurable features enum see {@link #enableFeature(Feature, boolean)}.
     */
    public enum Feature {
        /**
         * Feature for adding or inserting children
         * before this widget has loaded (attached).
         */
        ONLOAD_ADD_QUEUE
    }

    class Appender {
        Widget widget;
        int index = -1;

        public Appender(Widget widget, int index) {
            this.widget = widget;
            this.index = index;
        }

        public Appender(Widget widget) {
            this.widget = widget;
        }
    }

    private Map<Feature, Boolean> features;
    private List<Appender> onLoadAdd;
    private String[] initialClasses;
    protected JQueryElement $this;
    private HandlerRegistry handlerRegistry;
    private String translationKey;

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
    private CssNameMixin<MaterialWidget, Float> floatMixin;
    private TooltipMixin<MaterialWidget> tooltipMixin;
    private FlexboxMixin<MaterialWidget> flexboxMixin;
    private ToggleStyleMixin<MaterialWidget> hoverableMixin;
    private CssNameMixin<MaterialWidget, FontWeight> fontWeightMixin;
    private TruncateMixin<MaterialWidget> truncateMixin;
    private FilterStyleMixin<MaterialWidget> filterMixin;
    private BorderMixin<MaterialWidget> borderMixin;
    private DimensionMixin<MaterialWidget> dimensionMixin;
    private VerticalAlignMixin<MaterialWidget> verticalAlignMixin;
    private TransformMixin<MaterialWidget> transformMixin;
    private OrientationMixin<MaterialWidget> orientationMixin;
    private ContainerMixin<MaterialWidget> containerMixin;
    private GridLayoutMixin<MaterialWidget> gridLayoutMixin;

    public MaterialWidget() {
    }

    public MaterialWidget(JQueryElement jQueryElement) {
        this();

        setElement(jQueryElement.asElement());

        // We are already attached to the DOM.
        // This will happen in instances where
        // we are taking an element from JQuery.
        onAttach();
    }

    public MaterialWidget(Element element) {
        this();

        setElement(element);
    }

    public MaterialWidget(Element element, String... initialClass) {
        this(element);
        setInitialClasses(initialClass);
    }

    public JQueryElement $this() {
        if ($this == null) {
            $this = JQuery.$(this);
        }
        return $this;
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        ThemeManager.applyLoad(this);

        if (initialClasses != null) {
            for (String initial : initialClasses) {
                if (!initial.isEmpty()) {
                    removeStyleName(initial);
                    addStyleName(initial);
                }
            }
        }

        if (isFeatureEnabled(Feature.ONLOAD_ADD_QUEUE) && onLoadAdd != null) {
            // Check the onLoadAdd items.
            for (Appender item : onLoadAdd) {
                if (item.index == -1) {
                    add(item.widget, (Element) getElement());
                } else {
                    insert(item.widget, item.index);
                }
            }
            onLoadAdd.clear();
        }
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        ThemeManager.applyUnload(this);

        getHandlerRegistry().clearHandlers();
    }

    public HandlerRegistry getHandlerRegistry() {
        if (handlerRegistry == null) {
            handlerRegistry = new DefaultHandlerRegistry(this);
        }
        return handlerRegistry;
    }

    @Override
    public void add(Widget child) {
        super.add(child, (Element) getElement());
    }

    @Override
    protected void add(Widget child, com.google.gwt.user.client.Element container) {
        if (!isAttached() && isFeatureEnabled(Feature.ONLOAD_ADD_QUEUE)) {
            if (onLoadAdd == null) {
                onLoadAdd = new ArrayList<>();
            }
            onLoadAdd.add(new Appender(child));
        } else {
            super.add(child, container);
        }
    }

    @Override
    protected void insert(Widget child, com.google.gwt.user.client.Element container, int beforeIndex, boolean domInsert) {
        if (!isAttached() && isFeatureEnabled(Feature.ONLOAD_ADD_QUEUE)) {
            if (onLoadAdd == null) {
                onLoadAdd = new ArrayList<>();
            }
            onLoadAdd.add(new Appender(child, beforeIndex));
        } else {
            // Regular child addition
            super.insert(child, container, beforeIndex, domInsert);
        }
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

    /**
     * Set the style attribute of your element.
     * Note that this will override any {@link Element#getStyle()} changes and vice-versa.
     */
    public void setStyle(String style) {
        getElement().setAttribute("style", style);
    }

    /**
     * Set the 'class' attribute of this element.
     * Note that this will override {@link #addStyleName(String)} and vice-versa.
     */
    public void setClass(String cssClasses) {
        getElement().setAttribute("class", cssClasses);
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
        getEnabledMixin().setEnabled(this, enabled);
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
    public void setBackgroundColor(Color bgColor) {
        getColorsMixin().setBackgroundColor(bgColor);
    }

    @Override
    public Color getBackgroundColor() {
        return getColorsMixin().getBackgroundColor();
    }

    @Override
    public void setTextColor(Color textColor) {
        getColorsMixin().setTextColor(textColor);
    }

    @Override
    public Color getTextColor() {
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

    public void setFocus(boolean focused, boolean appendFocusStyleName) {
        getFocusableMixin().setFocus(focused, appendFocusStyleName);
    }

    @Override
    public void setTabIndex(int index) {
        getFocusableMixin().setTabIndex(index);
    }

    @Override
    public String getGridLayout() {
        return getGridLayoutMixin().getGridLayout();
    }

    @Override
    public void setGridArea(String value) {
        getGridLayoutMixin().setGridArea(value);
    }

    @Override
    public String getGridArea() {
        return getGridLayoutMixin().getGridArea();
    }

    @Override
    public void setGridAutoColumns(String value) {
        getGridLayoutMixin().setGridAutoColumns(value);
    }

    @Override
    public String getGridAutoColumns() {
        return getGridLayoutMixin().getGridAutoColumns();
    }

    @Override
    public void setGridAutoFlow(String value) {
        getGridLayoutMixin().setGridAutoFlow(value);
    }

    @Override
    public String getGridAutoFlow() {
        return getGridLayoutMixin().getGridAutoFlow();
    }

    @Override
    public void setGridAutoRows(String value) {
        getGridLayoutMixin().setGridAutoRows(value);
    }

    @Override
    public String getGridAutoRows() {
        return getGridLayoutMixin().getGridAutoRows();
    }

    @Override
    public void setGridColumn(String value) {
        getGridLayoutMixin().setGridColumn(value);
    }

    @Override
    public String getGridColumn() {
        return getGridLayoutMixin().getGridColumn();
    }

    @Override
    public void setGridColumnEnd(String value) {
        getGridLayoutMixin().setGridColumnEnd(value);
    }

    @Override
    public String getGridColumnEnd() {
        return getGridLayoutMixin().getGridColumnEnd();
    }

    @Override
    public void setGridColumnGap(String value) {
        getGridLayoutMixin().setGridColumnGap(value);
    }

    @Override
    public String getGridColumnGap() {
        return getGridLayoutMixin().getGridColumnGap();
    }

    @Override
    public void setGridColumnStart(String value) {
        getGridLayoutMixin().setGridColumnStart(value);
    }

    @Override
    public String getGridColumnStart() {
        return getGridLayoutMixin().getGridColumnStart();
    }

    @Override
    public void setGridGap(String value) {
        getGridLayoutMixin().setGridGap(value);
    }

    @Override
    public String getGridGap() {
        return getGridLayoutMixin().getGridGap();
    }

    @Override
    public void setGridRow(String value) {
        getGridLayoutMixin().setGridRow(value);
    }

    @Override
    public String getGridRow() {
        return getGridLayoutMixin().getGridRow();
    }

    @Override
    public void setGridRowEnd(String value) {
        getGridLayoutMixin().setGridRowEnd(value);
    }

    @Override
    public String getGridRowEnd() {
        return getGridLayoutMixin().getGridRowEnd();
    }

    @Override
    public void setGridRowGap(String value) {
        getGridLayoutMixin().setGridRowGap(value);
    }

    @Override
    public String getGridRowGap() {
        return getGridLayoutMixin().getGridRowGap();
    }

    @Override
    public void setGridRowStart(String value) {
        getGridLayoutMixin().setGridRowStart(value);
    }

    @Override
    public String getGridRowStart() {
        return getGridLayoutMixin().getGridRowStart();
    }

    @Override
    public void setGridTemplate(String value) {
        getGridLayoutMixin().setGridTemplate(value);
    }

    @Override
    public String getGridTemplate() {
        return getGridLayoutMixin().getGridTemplate();
    }

    @Override
    public void setGridTemplateAreas(String value) {
        getGridLayoutMixin().setGridTemplateAreas(value);
    }

    @Override
    public String getGridTemplateAreas() {
        return getGridLayoutMixin().getGridTemplateAreas();
    }

    @Override
    public void setGridTemplateColumns(String value) {
        getGridLayoutMixin().setGridTemplateColumns(value);
    }

    @Override
    public String getGridTemplateColumns() {
        return getGridLayoutMixin().getGridTemplateColumns();
    }

    @Override
    public void setGridTemplateRows(String value) {
        getGridLayoutMixin().setGridTemplateRows(value);
    }

    @Override
    public String getGridTemplateRows() {
        return getGridLayoutMixin().getGridTemplateRows();
    }

    @Override
    public void setAlignContent(String value) {
        getGridLayoutMixin().setAlignContent(value);
    }

    @Override
    public String getAlignContent() {
        return getGridLayoutMixin().getAlignContent();
    }

    @Override
    public void setAlignItems(String value) {
        getGridLayoutMixin().setAlignItems(value);
    }

    @Override
    public String getAlignItems() {
        return getGridLayoutMixin().getAlignItems();
    }

    @Override
    public void setAlignSelf(String value) {
        getGridLayoutMixin().setAlignSelf(value);
    }

    @Override
    public String getAlignSelf() {
        return getGridLayoutMixin().getAlignSelf();
    }

    @Override
    public void setColumnGap(String value) {
        getGridLayoutMixin().setColumnGap(value);
    }

    @Override
    public String getColumnGap() {
        return getGridLayoutMixin().getColumnGap();
    }

    @Override
    public void setGap(String value) {
        getGridLayoutMixin().setGap(value);
    }

    @Override
    public String getGap() {
        return getGridLayoutMixin().getGap();
    }

    @Override
    public void setJustifyContent(String value) {
        getGridLayoutMixin().setJustifyContent(value);
    }

    @Override
    public String getJustifyContent() {
        return getGridLayoutMixin().getJustifyContent();
    }

    @Override
    public void setJustifyItems(String value) {
        getGridLayoutMixin().setJustifyItems(value);
    }

    @Override
    public String getJustifyItems() {
        return getGridLayoutMixin().getJustifyItems();
    }

    @Override
    public void setJustifySelf(String value) {
        getGridLayoutMixin().setJustifySelf(value);
    }

    @Override
    public String getJustifySelf() {
        return getGridLayoutMixin().getJustifySelf();
    }

    @Override
    public void setPlaceContent(String value) {
        getGridLayoutMixin().setPlaceContent(value);
    }

    @Override
    public String getPlaceContent() {
        return getGridLayoutMixin().getPlaceContent();
    }

    @Override
    public void setPlaceItems(String value) {
        getGridLayoutMixin().setPlaceItems(value);
    }

    @Override
    public String getPlaceItems() {
        return getGridLayoutMixin().getPlaceItems();
    }

    @Override
    public void setPlaceSelf(String value) {
        getGridLayoutMixin().setPlaceSelf(value);
    }

    @Override
    public String getPlaceSelf() {
        return getGridLayoutMixin().getPlaceSelf();
    }

    @Override
    public void setRowGap(String value) {
        getGridLayoutMixin().setRowGap(value);
    }

    @Override
    public String getRowGap() {
        return getGridLayoutMixin().getRowGap();
    }

    @Override
    public void setAspectRatio(String value) {
        getGridLayoutMixin().setAspectRatio(value);
    }

    @Override
    public String getAspectRatio() {
        return getGridLayoutMixin().getAspectRatio();
    }

    @Override
    public void setGrid(String grid) {
        getGridMixin().setGrid(grid);
    }

    @Override
    public void setGridLayout(String value) {
        getGridLayoutMixin().setGridLayout(value);
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
    public void setGwtDisplay(Style.Display display) {
        getFlexboxMixin().setGwtDisplay(display);
    }

    @Override
    public void setOpacity(double opacity) {
        getElement().getStyle().setOpacity(opacity);
    }

    @Override
    public Double getOpacity() {
        try {
            return Double.parseDouble(getElement().getStyle().getOpacity());
        } catch (NumberFormatException ex) {
            return null;
        }
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
        getElement().setAttribute(!dataAttr.startsWith("data-") ? "data-" + dataAttr : dataAttr, value);
    }

    @Override
    public String getDataAttribute(String dataAttr) {
        return getElement().getAttribute(!dataAttr.startsWith("data-") ? "data-" + dataAttr : dataAttr);
    }

    @Override
    public void setFloat(Float floatAlign) {
        getFloatMixin().setCssName(floatAlign);
    }

    @Override
    public Float getFloat() {
        return StyleHelper.fromStyleName(Float.class, getFloatMixin().getCssName());
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
    public void setTooltip(String tooltip, String... classes) {
        getTooltipMixin().setTooltip(tooltip, classes);
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

    @Override
    public void setTooltipHTML(String html) {
        getTooltipMixin().setTooltipHTML(html);
    }

    @Override
    public String getTooltipHTML() {
        return getTooltipMixin().getTooltipHTML();
    }

    @Override
    public JQueryElement getTooltipElement() {
        return getTooltipMixin().getTooltipElement();
    }

    @Override
    public void removeTooltip() {
        getTooltipMixin().removeTooltip();
    }

    public void setVisibility(Style.Visibility visibility) {
        getElement().getStyle().setVisibility(visibility);
    }

    /**
     * Get the visibility style or null if not applied.
     */
    public Style.Visibility getVisibility() {
        String visibility = getElement().getStyle().getVisibility();
        if (visibility != null && !visibility.isEmpty()) {
            return Style.Visibility.valueOf(visibility.toUpperCase());
        }
        return null;
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
    public void setFlexValue(String value) {
        getElement().getStyle().setProperty("flex", value);
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

    @Override
    public void setVerticalAlign(Style.VerticalAlign value) {
        getVerticalAlignMixin().setVerticalAlign(value);
    }

    @Override
    public String getVerticalAlign() {
        return getVerticalAlignMixin().getVerticalAlign();
    }

    public void setOverflow(Style.Overflow overflow) {
        getElement().getStyle().setOverflow(overflow);
    }

    public void setLayoutPosition(Style.Position position) {
        getElement().getStyle().setPosition(position);
    }

    public String getLayoutPosition() {
        return getElement().getStyle().getPosition();
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

    public void setLineHeight(double value) {
        getElement().getStyle().setLineHeight(value, Style.Unit.PX);
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
    public void setFontWeight(FontWeight fontWeight) {
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
        try {
            return Integer.parseInt(getElement().getStyle().getZIndex());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void setFilterStyle(String property) {
        getFilterStyleMixin().setFilterStyle(property);
    }

    @Override
    public String getFilterStyle() {
        return getFilterStyleMixin().getFilterStyle();
    }


    @Override
    public void setTruncate(boolean truncate) {
        getTruncateMixin().setTruncate(truncate);
    }

    @Override
    public boolean isTruncate() {
        return getTruncateMixin().isTruncate();
    }

    @Override
    public void setEnableTruncateTitle(boolean value) {
        getTruncateMixin().setEnableTruncateTitle(value);
    }

    @Override
    public boolean isEnableTruncateTitle() {
        return getTruncateMixin().isEnableTruncateTitle();
    }

    @Override
    public void setBorder(String value) {
        getBorderMixin().setBorder(value);
    }

    @Override
    public String getBorder() {
        return getBorderMixin().getBorder();
    }

    @Override
    public void setBorderLeft(String value) {
        getBorderMixin().setBorderLeft(value);
    }

    @Override
    public String getBorderLeft() {
        return getBorderMixin().getBorderLeft();
    }

    @Override
    public void setBorderRight(String value) {
        getBorderMixin().setBorderRight(value);
    }

    @Override
    public String getBorderRight() {
        return getBorderMixin().getBorderRight();
    }

    @Override
    public void setBorderTop(String value) {
        getBorderMixin().setBorderTop(value);
    }

    @Override
    public String getBorderTop() {
        return getBorderMixin().getBorderTop();
    }

    @Override
    public void setBorderBottom(String value) {
        getBorderMixin().setBorderBottom(value);
    }

    @Override
    public String getBorderBottom() {
        return getBorderMixin().getBorderBottom();
    }

    @Override
    public void setBorderRadius(String value) {
        getBorderMixin().setBorderRadius(value);
    }

    @Override
    public String getBorderRadius() {
        return getBorderMixin().getBorderRadius();
    }

    @Override
    public void setMinHeight(String value) {
        getDimensionMixin().setMinHeight(value);
    }

    @Override
    public String getMinHeight() {
        return getDimensionMixin().getMinHeight();
    }

    @Override
    public void setMaxHeight(String value) {
        getDimensionMixin().setMaxHeight(value);
    }

    @Override
    public String getMaxHeight() {
        return getDimensionMixin().getMaxHeight();
    }

    @Override
    public void setMinWidth(String value) {
        getDimensionMixin().setMinWidth(value);
    }

    @Override
    public String getMinWidth() {
        return getDimensionMixin().getMinWidth();
    }

    @Override
    public void setMaxWidth(String value) {
        getDimensionMixin().setMaxWidth(value);
    }

    @Override
    public String getMaxWidth() {
        return getDimensionMixin().getMaxWidth();
    }

    @Override
    public void setTransform(String value) {
        getTransformMixin().setTransform(value);
    }

    @Override
    public String getTransform() {
        return getTransformMixin().getTransform();
    }

    @Override
    public void setTransformOrigin(String value) {
        getTransformMixin().setTransformOrigin(value);
    }

    @Override
    public String getTransformOrigin() {
        return getTransformMixin().getTransformOrigin();
    }

    @Override
    public void setTransformStyle(String value) {
        getTransformMixin().setTransformStyle(value);
    }

    @Override
    public String getTransformStyle() {
        return getTransformMixin().getTransformStyle();
    }

    @Override
    public void setPerspective(String value) {
        getTransformMixin().setPerspective(value);
    }

    @Override
    public String getPerspective() {
        return getTransformMixin().getPerspective();
    }

    @Override
    public void setPerspectiveOrigin(String value) {
        getTransformMixin().setPerspectiveOrigin(value);
    }

    @Override
    public String getPerspectiveOrigin() {
        return getTransformMixin().getPerspectiveOrigin();
    }

    @Override
    public void setBackfaceVisibility(String value) {
        getTransformMixin().setBackfaceVisibility(value);
    }

    @Override
    public String getBackfaceVisibility() {
        return getTransformMixin().getBackfaceVisibility();
    }

    @Override
    public void setOrientation(Orientation orientation) {
        getOrientationMixin().setOrientation(orientation);
    }

    @Override
    public Orientation getOrientation() {
        return getOrientationMixin().getOrientation();
    }

    @Override
    public void setDetectOrientation(boolean detectOrientation) {
        getOrientationMixin().setDetectOrientation(detectOrientation);
    }

    @Override
    public boolean isDetectOrientation() {
        return getOrientationMixin().isDetectOrientation();
    }

    public void setLetterSpacing(String value) {
        getElement().getStyle().setProperty("letterSpacing", value);
    }

    public String getLetterSpacing() {
        return getElement().getStyle().getProperty("letterSpacing");
    }

    @Override
    public void setWordBreak(WordBreak wordBreak) {
        getElement().getStyle().setProperty("wordBreak", wordBreak.getCssName());
    }

    @Override
    public WordBreak getWordBreak() {
        return WordBreak.fromStyleName(getElement().getStyle().getProperty("wordBreak"));
    }

    public HandlerRegistration registerHandler(HandlerRegistration handler) {
        return getHandlerRegistry().registerHandler(handler);
    }

    public void removeHandler(HandlerRegistration handler) {
        getHandlerRegistry().removeHandler(handler);
    }

    // Avoid touch events on mobile devices
    public void stopTouchStartEvent() {
        $(getElement()).bind("touchstart", e -> {
            e.stopPropagation();
            return true;
        });
    }

    public int getWidth() {
        return $(getElement()).outerWidth();
    }

    protected void clearActiveClass(HasWidgets widget) {
        for (Widget child : widget) {
            Element element = child.getElement();
            if (StyleHelper.containsStyle(element.getClassName(), CssName.ACTIVE)) {
                element.removeClassName(CssName.ACTIVE);
            }

            if (child instanceof HasWidgets) {
                clearActiveClass((HasWidgets) child);
            }
        }
    }

    /**
     * Applies a CSS3 Transition property to this widget.
     */
    public void setTransition(TransitionConfig property) {
        Element target = getElement();
        if (property.getTarget() != null) {
            target = property.getTarget();
        }
        target.getStyle().setProperty("WebkitTransition", property.getProperty() + " " + property.getDuration() + "ms " + property.getTimingFunction() + property.getDelay() + "ms");
        target.getStyle().setProperty("transition", property.getProperty() + " " + property.getDuration() + "ms " + property.getTimingFunction() + property.getDelay() + "ms");
    }

    public String getCursor() {
        return getElement().getStyle().getCursor();
    }

    /**
     * Will set the {@link com.google.gwt.dom.client.Style.Cursor} style property into this widget
     */
    public void setCursor(Style.Cursor cursor) {
        getElement().getStyle().setCursor(cursor);
    }

    @Override
    public void setContainerEnabled(boolean value) {
        getContainerMixin().setContainerEnabled(value);
    }

    @Override
    public boolean isContainerEnabed() {
        return getContainerMixin().isContainerEnabed();
    }

    @Override
    public void setValignWrapper(boolean value) {
        getContainerMixin().setValignWrapper(value);
    }

    @Override
    public boolean isValignWrapper() {
        return getContainerMixin().isValignWrapper();
    }

    @Override
    public void setZoom(Double level) {
        getElement().getStyle().setProperty("zoom", level != null ? String.valueOf(level) : "");
    }

    @Override
    public void setResize(Resizable value) {
        getElement().getStyle().setProperty("resize", value != null ? value.getName() : "");
    }

    @Override
    public String getResize() {
        return getElement().getStyle().getProperty("resize");
    }

    @Override
    public void setContentEditable(boolean value) {
        getElement().setAttribute("contenteditable", String.valueOf(value));
    }

    @Override
    public boolean isContentEditable() {
        return Boolean.parseBoolean(getElement().getAttribute("contenteditable"));
    }

    @Override
    public void setWidth(String width) {
        getElement().getStyle().setProperty("width", width);
    }

    @Override
    public void setHeight(String height) {
        getElement().getStyle().setProperty("height", height);
    }

    /**
     * Add an {@code AttachHandler} for attachment events.
     *
     * @param handler Attach event handler.
     * @param oneTime Only execute this handler once, then detach handler.
     * @return The events handler registration.
     */
    public HandlerRegistration addAttachHandler(final AttachEvent.Handler handler, boolean oneTime) {
        if (!oneTime) {
            return addAttachHandler(handler);
        } else {
            final HandlerRegistration[] registration = {null};
            registration[0] = addAttachHandler(event -> {
                handler.onAttachOrDetach(event);

                if (registration[0] != null) {
                    registration[0].removeHandler();
                }
            });
            return registration[0];
        }
    }

    @Override
    public void setInitialClasses(String... initialClasses) {
        this.initialClasses = initialClasses;
    }

    @Override
    public String[] getInitialClasses() {
        return initialClasses;
    }

    @Override
    public WidgetCollection getChildren() {
        return super.getChildren();
    }

    public List<Widget> getChildrenList() {
        List<Widget> children = new ArrayList<>();
        for (int i = 0; i < getWidgetCount(); i++) {
            children.add(getWidget(i));
        }
        return children;
    }

    /**
     * Enable or disable a complex {@link Feature}.<br/>
     *
     * @param feature the feature to enable.
     * @param enabled true to enable false to disable.
     */
    public void enableFeature(Feature feature, boolean enabled) {
        if (features == null) {
            features = new HashMap<>();
        }
        features.put(feature, enabled);
    }

    /**
     * Check if a {@link Feature} is enabled.
     */
    public boolean isFeatureEnabled(Feature feature) {
        if (features != null) {
            Boolean enabled = features.get(feature);
            return enabled != null && enabled;
        } else {
            return false;
        }
    }

    public boolean validate() {
        boolean valid = true;
        for (Widget child : getChildren()) {
            if (child instanceof HasValidators && !((HasValidators) child).validate()) {
                valid = false;
            } else if (child instanceof MaterialWidget && !((MaterialWidget) child).validate()) {
                valid = false;
            }
        }
        return valid;
    }


    // Events
    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onClick(event);
            }
        }, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onMouseDown(event);
            }
        }, MouseDownEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onMouseMove(event);
            }
        }, MouseMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onMouseOut(event);
            }
        }, MouseOutEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onMouseOver(event);
            }
        }, MouseOverEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onMouseUp(event);
            }
        }, MouseUpEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(final MouseWheelHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onMouseWheel(event);
            }
        }, MouseWheelEvent.getType());
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onDoubleClick(event);
            }
        }, DoubleClickEvent.getType());
    }

    @Override
    public HandlerRegistration addDragStartHandler(DragStartEvent.DragStartHandler handler) {
        return addHandler(event -> {
            if (isEnabled()) {
                handler.onDragStart(event);
            }
        }, DragStartEvent.getType());
    }

    @Override
    public HandlerRegistration addDragMoveHandler(DragMoveEvent.DragMoveHandler handler) {
        return addHandler(event -> {
            if (isEnabled()) {
                handler.onDragMove(event);
            }
        }, DragMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addDragEndHandler(DragEndEvent.DragEndHandler handler) {
        return addHandler(event -> {
            if (isEnabled()) {
                handler.onDragEnd(event);
            }
        }, DragEndEvent.getType());
    }

    @Override
    public HandlerRegistration addDropActivateHandler(DropActivateEvent.DropActivateHandler handler) {
        return addHandler(event -> {
            if (isEnabled()) {
                handler.onDropActivate(event);
            }
        }, DropActivateEvent.getType());
    }

    @Override
    public HandlerRegistration addDragEnterHandler(DragEnterEvent.DragEnterHandler handler) {
        return addHandler(event -> {
            if (isEnabled()) {
                handler.onDragEnter(event);
            }
        }, DragEnterEvent.getType());
    }

    @Override
    public HandlerRegistration addDragLeaveHandler(DragLeaveEvent.DragLeaveHandler handler) {
        return addHandler(event -> {
            if (isEnabled()) {
                handler.onDragLeave(event);
            }
        }, DragLeaveEvent.getType());
    }

    @Override
    public HandlerRegistration addDragOverHandler(final DragOverEvent.DragOverHandler handler) {
        return addHandler(event -> {
            if (isEnabled()) {
                handler.onDragOver(event);
            }
        }, DragOverEvent.getType());
    }

    @Override
    public HandlerRegistration addDropDeactivateHandler(DropDeactivateEvent.DropDeactivateHandler handler) {
        return addHandler(event -> {
            if (isEnabled()) {
                handler.onDropDeactivate(event);
            }
        }, DropDeactivateEvent.getType());
    }

    @Override
    public HandlerRegistration addDropHandler(DropEvent.DropHandler handler) {
        return addHandler(event -> {
            if (isEnabled()) {
                handler.onDrop(event);
            }
        }, DropEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onTouchCancel(event);
            }
        }, TouchCancelEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onTouchEnd(event);
            }
        }, TouchEndEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onTouchMove(event);
            }
        }, TouchMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onTouchStart(event);
            }
        }, TouchStartEvent.getType());
    }

    @Override
    public HandlerRegistration addGestureChangeHandler(GestureChangeHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onGestureChange(event);
            }
        }, GestureChangeEvent.getType());
    }

    @Override
    public HandlerRegistration addGestureEndHandler(GestureEndHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onGestureEnd(event);
            }
        }, GestureEndEvent.getType());
    }

    @Override
    public HandlerRegistration addGestureStartHandler(GestureStartHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onGestureStart(event);
            }
        }, GestureStartEvent.getType());
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onKeyDown(event);
            }
        }, KeyDownEvent.getType());
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onKeyPress(event);
            }
        }, KeyPressEvent.getType());
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onKeyUp(event);
            }
        }, KeyUpEvent.getType());
    }

    @Override
    public HandlerRegistration addBlurHandler(BlurHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onBlur(event);
            }
        }, BlurEvent.getType());
    }

    @Override
    public HandlerRegistration addFocusHandler(FocusHandler handler) {
        return addDomHandler(event -> {
            if (isEnabled()) {
                handler.onFocus(event);
            }
        }, FocusEvent.getType());
    }

    @Override
    public HandlerRegistration addOrientationChangeHandler(OrientationChangeHandler handler) {
        return addHandler(event -> {
            if (isEnabled()) {
                handler.onOrientationChange(event);
            }
        }, OrientationChangeEvent.TYPE);
    }

    protected IdMixin<MaterialWidget> getIdMixin() {
        if (idMixin == null) {
            idMixin = new IdMixin<>(this);
        }
        return idMixin;
    }

    protected EnabledMixin<MaterialWidget> getEnabledMixin() {
        if (enabledMixin == null) {
            enabledMixin = new EnabledMixin<>(this);
        }
        return enabledMixin;
    }

    protected CssNameMixin<MaterialWidget, TextAlign> getTextAlignMixin() {
        if (textAlignMixin == null) {
            textAlignMixin = new CssNameMixin<>(this);
        }
        return textAlignMixin;
    }

    protected ColorsMixin<MaterialWidget> getColorsMixin() {
        if (colorsMixin == null) {
            colorsMixin = new ColorsMixin<>(this);
        }
        return colorsMixin;
    }

    protected FocusableMixin<MaterialWidget> getFocusableMixin() {
        if (focusableMixin == null) {
            focusableMixin = new FocusableMixin<>(this);
        }
        return focusableMixin;
    }

    protected GridMixin<MaterialWidget> getGridMixin() {
        if (gridMixin == null) {
            gridMixin = new GridMixin<>(this);
        }
        return gridMixin;
    }

    protected ShadowMixin<MaterialWidget> getShadowMixin() {
        if (shadowMixin == null) {
            shadowMixin = new ShadowMixin<>(this);
        }
        return shadowMixin;
    }

    protected SeparatorMixin<MaterialWidget> getSeparatorMixin() {
        if (separatorMixin == null) {
            separatorMixin = new SeparatorMixin<>(this);
        }
        return separatorMixin;
    }

    protected ScrollspyMixin<MaterialWidget> getScrollspyMixin() {
        if (scrollspyMixin == null) {
            scrollspyMixin = new ScrollspyMixin<>(this);
        }
        return scrollspyMixin;
    }

    protected CssNameMixin<MaterialWidget, HideOn> getHideOnMixin() {
        if (hideOnMixin == null) {
            hideOnMixin = new CssNameMixin<>(this);
        }
        return hideOnMixin;
    }

    protected CssNameMixin<MaterialWidget, ShowOn> getShowOnMixin() {
        if (showOnMixin == null) {
            showOnMixin = new CssNameMixin<>(this);
        }
        return showOnMixin;
    }

    protected CssNameMixin<MaterialWidget, CenterOn> getCenterOnMixin() {
        if (centerOnMixin == null) {
            centerOnMixin = new CssNameMixin<>(this);
        }
        return centerOnMixin;
    }

    protected FontSizeMixin<MaterialWidget> getFontSizeMixin() {
        if (fontSizeMixin == null) {
            fontSizeMixin = new FontSizeMixin<>(this);
        }
        return fontSizeMixin;
    }

    protected ToggleStyleMixin<MaterialWidget> getCircleMixin() {
        if (circleMixin == null) {
            circleMixin = new ToggleStyleMixin<>(this, CssName.CIRCLE);
        }
        return circleMixin;
    }

    protected ToggleStyleMixin<MaterialWidget> getHoverableMixin() {
        if (hoverableMixin == null) {
            hoverableMixin = new ToggleStyleMixin<>(this, CssName.HOVERABLE);
        }
        return hoverableMixin;
    }

    protected WavesMixin<MaterialWidget> getWavesMixin() {
        if (wavesMixin == null) {
            wavesMixin = new WavesMixin<>(this);
        }
        return wavesMixin;
    }

    protected CssNameMixin<MaterialWidget, Float> getFloatMixin() {
        if (floatMixin == null) {
            floatMixin = new CssNameMixin<>(this);
        }
        return floatMixin;
    }

    protected TooltipMixin<MaterialWidget> getTooltipMixin() {
        if (tooltipMixin == null) {
            tooltipMixin = new TooltipMixin<>(this);
        }
        return tooltipMixin;
    }

    protected FlexboxMixin<MaterialWidget> getFlexboxMixin() {
        if (flexboxMixin == null) {
            flexboxMixin = new FlexboxMixin<>(this);
        }
        return flexboxMixin;
    }

    protected CssNameMixin<MaterialWidget, FontWeight> getFontWeightMixin() {
        if (fontWeightMixin == null) {
            fontWeightMixin = new CssNameMixin<>(this);
        }
        return fontWeightMixin;
    }

    public TruncateMixin<MaterialWidget> getTruncateMixin() {
        if (truncateMixin == null) {
            truncateMixin = new TruncateMixin<>(this);
        }
        return truncateMixin;
    }

    public BorderMixin<MaterialWidget> getBorderMixin() {
        if (borderMixin == null) {
            borderMixin = new BorderMixin<>(this);
        }
        return borderMixin;
    }

    public DimensionMixin<MaterialWidget> getDimensionMixin() {
        if (dimensionMixin == null) {
            dimensionMixin = new DimensionMixin<>(this);
        }
        return dimensionMixin;
    }

    public VerticalAlignMixin<MaterialWidget> getVerticalAlignMixin() {
        if (verticalAlignMixin == null) {
            verticalAlignMixin = new VerticalAlignMixin<>(this);
        }
        return verticalAlignMixin;
    }

    public TransformMixin<MaterialWidget> getTransformMixin() {
        if (transformMixin == null) {
            transformMixin = new TransformMixin<>(this);
        }
        return transformMixin;
    }

    public OrientationMixin<MaterialWidget> getOrientationMixin() {
        if (orientationMixin == null) {
            orientationMixin = new OrientationMixin<>(this);
        }
        return orientationMixin;
    }

    public FilterStyleMixin<MaterialWidget> getFilterStyleMixin() {
        if (filterMixin == null) {
            filterMixin = new FilterStyleMixin<>(this);
        }
        return filterMixin;
    }

    public ContainerMixin<MaterialWidget> getContainerMixin() {
        if (containerMixin == null) {
            containerMixin = new ContainerMixin<>(this);
        }
        return containerMixin;
    }

    public GridLayoutMixin<MaterialWidget> getGridLayoutMixin() {
        if (gridLayoutMixin == null) {
            gridLayoutMixin = new GridLayoutMixin<>(this);
        }
        return gridLayoutMixin;
    }

    public void setTranslationKey(String key) {
        this.translationKey = key;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public static GlobalThemeConfig getGlobalTheme() {
        if (themeConfig == null) {
            themeConfig = new GlobalThemeConfig();
        }
        return themeConfig;
    }
}
