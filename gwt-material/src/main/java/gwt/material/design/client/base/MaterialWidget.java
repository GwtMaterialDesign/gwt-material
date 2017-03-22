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
import gwt.material.design.client.constants.*;
import gwt.material.design.client.events.DragEndEvent;
import gwt.material.design.client.events.DragEnterEvent;
import gwt.material.design.client.events.DragLeaveEvent;
import gwt.material.design.client.events.*;
import gwt.material.design.client.events.DragOverEvent;
import gwt.material.design.client.events.DragStartEvent;
import gwt.material.design.client.events.DropEvent;
import gwt.material.design.jquery.client.api.JQuery;
import gwt.material.design.jquery.client.api.JQueryElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class MaterialWidget extends ComplexPanel implements HasId, HasEnabled, HasTextAlign, HasColors, HasGrid,
        HasShadow, Focusable, HasInlineStyle, HasSeparator, HasScrollspy, HasHideOn, HasShowOn, HasCenterOn,
        HasCircle, HasWaves, HasDataAttributes, HasFloat, HasTooltip, HasFlexbox, HasHoverable, HasFontWeight,
        HasDepth, HasInitialClasses, HasInteractionHandlers, HasAllFocusHandlers {

    private static JQueryElement window = null;
    private static JQueryElement body = null;

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
    private ToggleStyleMixin<MaterialWidget> truncateMixin;

    public MaterialWidget() {
    }

    public MaterialWidget(JQueryElement jQueryElement) {
        setElement(jQueryElement.asElement());

        // We are already attached to the DOM.
        // This will happen in instances where
        // we are taking an element from JQuery.
        onAttach();
    }

    public MaterialWidget(Element element) {
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

    public ToggleStyleMixin<MaterialWidget> getTruncateMixin() {
        if (truncateMixin == null) {
            truncateMixin = new ToggleStyleMixin<>(this, CssName.TRUNCATE);
        }
        return truncateMixin;
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
    public void setGwtDisplay(Style.Display display) {
        getFlexboxMixin().setGwtDisplay(display);
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
        if (!dataAttr.startsWith("data-")) {
            dataAttr = "data-" + dataAttr;
        }
        getElement().setAttribute(dataAttr, value);
    }

    @Override
    public String getDataAttribute(String dataAttr) {
        if (!dataAttr.startsWith("data-")) {
            dataAttr = "data-" + dataAttr;
        }
        return getElement().getAttribute(dataAttr);
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
        return Integer.parseInt(getElement().getStyle().getZIndex());
    }

    /**
     * If true the label inside this component will be truncated by ellipsis
     **/
    public void setTruncate(boolean truncate) {
        getTruncateMixin().setOn(truncate);
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
}
