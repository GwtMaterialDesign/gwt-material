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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.MaterialWidgetTestCase;
import gwt.material.design.client.base.*;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.MaterialPanel;
import org.junit.Ignore;

/**
 * Test case for MaterialWidget base.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
@Ignore
public abstract class MaterialWidgetTest<T extends MaterialWidget> extends MaterialWidgetTestCase<T> {

    public void testVerticalAlign() {
        // given
        T widget = getWidget();

        // when / then
        checkVerticalAlign(widget, Style.VerticalAlign.MIDDLE);
        checkVerticalAlign(widget, Style.VerticalAlign.TOP);
        checkVerticalAlign(widget, Style.VerticalAlign.BOTTOM);
        checkVerticalAlign(widget, Style.VerticalAlign.SUPER);
        checkVerticalAlign(widget, Style.VerticalAlign.SUB);
        checkVerticalAlign(widget, Style.VerticalAlign.TEXT_BOTTOM);
        checkVerticalAlign(widget, Style.VerticalAlign.TEXT_TOP);
        checkVerticalAlign(widget, Style.VerticalAlign.BASELINE);
    }

    protected void checkVerticalAlign(MaterialWidget widget, Style.VerticalAlign value) {
        widget.setVerticalAlign(value);
        assertEquals(value.getCssName(), widget.getVerticalAlign());
        assertEquals(widget.getVerticalAlign(), widget.getElement().getStyle().getVerticalAlign());
    }

    public void testBorder() {
        // given
        T widget = getWidget();

        // when / then
        final String BORDER = "1px solid red";
        final String BORDER_LEFT = "10px dashed green";
        final String BORDER_RIGHT = "20px dotted";
        final String BORDER_BOTTOM = "10px solid";
        final String BORDER_TOP = "5px ridge";

        widget.setBorder(BORDER);
        assertEquals(BORDER, widget.getBorder());
        assertEquals(BORDER, widget.getElement().getStyle().getProperty("border"));

        widget.setBorderLeft(BORDER_LEFT);
        assertEquals(BORDER_LEFT, widget.getBorderLeft());
        assertEquals(BORDER_LEFT, widget.getElement().getStyle().getProperty("borderLeft"));

        widget.setBorderRight(BORDER_RIGHT);
        assertEquals(BORDER_RIGHT, widget.getBorderRight());
        assertEquals(BORDER_RIGHT, widget.getElement().getStyle().getProperty("borderRight"));

        widget.setBorderBottom(BORDER_BOTTOM);
        assertEquals(BORDER_BOTTOM, widget.getBorderBottom());
        assertEquals(BORDER_BOTTOM, widget.getElement().getStyle().getProperty("borderBottom"));

        widget.setBorderTop(BORDER_TOP);
        assertEquals(BORDER_TOP, widget.getBorderTop());
        assertEquals(BORDER_TOP, widget.getElement().getStyle().getProperty("borderTop"));

        widget.setBorder("");
        assertEquals("", widget.getElement().getStyle().getProperty("border"));
    }

    public void testTransition() {
        // given
        MaterialWidget widget = getWidget();

        final int DURATION = 1000;
        final int DELAY = 0;
        final String PROPERTY = "all";
        final String TIMING_FUNCTION = "cubic-bezier(0, 0, 0.2, 1)";
        final String EXPECTED_RESULT = "all 1000ms cubic-bezier(0, 0, 0.2, 1)0ms";

        TransitionConfig config = new TransitionConfig();
        config.setDuration(DURATION);

        // when / then
        assertEquals(DURATION, config.getDuration());
        config.setDelay(DELAY);
        assertEquals(DELAY, config.getDelay());
        config.setProperty(PROPERTY);
        assertEquals(PROPERTY, config.getProperty());
        config.setTimingFunction(TIMING_FUNCTION);
        assertEquals(TIMING_FUNCTION, config.getTimingFunction());
        widget.setTransition(config);
        // Check the transition property to match with the expected result.
        assertEquals(EXPECTED_RESULT, widget.getElement().getStyle().getProperty("transition"));
    }

    public void testInitialClasses() {
        checkInitialClasses();
    }

    protected void checkInitialClasses(String... initialClasses) {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        if (widget.getInitialClasses() != null) {

            for (int i = 0; i < initialClasses.length; i++) {
                assertTrue(widget.getElement().hasClassName(initialClasses[i]));
            }

            assertNotNull(widget.getInitialClasses());
        }
    }

    public void testChildren() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        if (widget.getChildren().size() == 0) {
            int childCount = 3;
            for (int i = 1; i <= childCount; i++) {
                MaterialPanel panel = new MaterialPanel();
                widget.add(panel);
            }
            assertTrue(widget.getChildren().size() != 0);
            assertEquals(3, widget.getChildren().size());
            widget.getChildren().remove(0);
            assertEquals(2, widget.getChildren().size());
            widget.clear();
            assertEquals(0, widget.getChildren().size());
        }
    }

    public void testTruncate() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setTruncate(true);
        assertTrue(element.hasClassName(CssName.TRUNCATE));
        widget.setTruncate(false);
        assertFalse(element.hasClassName(CssName.TRUNCATE));
    }

    public void testFloat() {
        // given
        MaterialWidget widget = getWidget();
        final Element element = widget.getElement();

        // when
        widget.setFloat(Style.Float.LEFT);

        // then
        assertEquals(Style.Float.LEFT, widget.getFloat());
        assertTrue(element.hasClassName(Style.Float.LEFT.getCssName()));

        // when
        widget.setFloat(Style.Float.RIGHT);

        // then
        assertEquals(Style.Float.RIGHT, widget.getFloat());
        assertTrue(element.hasClassName(Style.Float.RIGHT.getCssName()));

        // when
        widget.setFloat(Style.Float.NONE);

        // then
        assertEquals(Style.Float.NONE, widget.getFloat());
        assertTrue(element.hasClassName(Style.Float.NONE.getCssName()));
    }

    public void testFontSize() {
        // given
        MaterialWidget widget = getWidget();

        // when
        widget.setFontSize("12px");

        // then
        assertEquals("12px", widget.getFontSize());
        assertEquals("12px", widget.getElement().getStyle().getFontSize());

        // when
        widget.setFontSize(14, Style.Unit.PX);

        // then
        assertEquals("14px", widget.getFontSize());
        assertEquals("14px", widget.getElement().getStyle().getFontSize());
    }

    public void testFontWeight() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setFontWeight(Style.FontWeight.BOLD);
        assertEquals(Style.FontWeight.BOLD.getCssName(), element.getStyle().getFontWeight());
        assertEquals(Style.FontWeight.BOLD.getCssName(), widget.getFontWeight());
    }

    public void testHoverable() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setHoverable(true);
        assertTrue(element.hasClassName(CssName.HOVERABLE));
        assertTrue(widget.isHoverable());
        widget.setHoverable(false);
        assertFalse(element.hasClassName(CssName.HOVERABLE));
        assertFalse(widget.isHoverable());
    }

    public void testTooltip() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setTooltip("Tooltip");
        widget.setTooltipPosition(Position.RIGHT);
        widget.setTooltipDelayMs(300);
        assertTrue(element.hasAttribute("data-tooltip"));
        assertEquals("Tooltip", element.getAttribute("data-tooltip"));
        assertEquals("Tooltip", widget.getTooltip());
        assertTrue(element.hasAttribute("data-position"));
        assertEquals(Position.RIGHT.getCssName(), element.getAttribute("data-position"));
        assertEquals(Position.RIGHT, widget.getTooltipPosition());
        assertTrue(element.hasAttribute("data-delay"));
        assertEquals("300", element.getAttribute("data-delay"));
        assertEquals(300, widget.getTooltipDelayMs());
        widget.setTooltipHTML("<b>Tooltip</b>");
        assertEquals("<b>Tooltip</b>", widget.getTooltipHTML());
    }

    public void testWaves() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setWaves(WavesType.YELLOW);
        assertTrue(element.hasClassName(WavesType.YELLOW.getCssName()));
        assertEquals(WavesType.YELLOW, widget.getWaves());
    }

    public void testCircle() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setCircle(true);
        assertTrue(element.hasClassName(CssName.CIRCLE));
        widget.setCircle(false);
        assertFalse(element.hasClassName(CssName.CIRCLE));
        assertFalse(widget.isCircle());
    }

    public void testCenterOn() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setCenterOn(CenterOn.CENTER_ON_SMALL);
        assertTrue(element.hasClassName(CenterOn.CENTER_ON_SMALL.getCssName()));
        assertEquals(CenterOn.CENTER_ON_SMALL, widget.getCenterOn());
    }

    public void testHideOnShowOn() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setHideOn(HideOn.HIDE_ON_MED);
        assertTrue(element.hasClassName(HideOn.HIDE_ON_MED.getCssName()));
        assertEquals(HideOn.HIDE_ON_MED, widget.getHideOn());
        widget.setShowOn(ShowOn.SHOW_ON_MED_UP);
        assertTrue(element.hasClassName(ShowOn.SHOW_ON_MED_UP.getCssName()));
        assertEquals(ShowOn.SHOW_ON_MED_UP, widget.getShowOn());
    }

    public void testScrollspy() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setScrollspy("scrollspy-1");
        assertTrue(element.hasClassName(CssName.SECTION + " " + CssName.SCROLLSPY));
        assertEquals("scrollspy-1", widget.getId());
        assertEquals("scrollspy-1", widget.getScrollspy());
    }

    public void testSeparator() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setSeparator(true);
        assertEquals("1px solid #e9e9e9", element.getStyle().getProperty("borderBottom"));
        assertEquals(widget.isSeparator(), true);
        widget.setSeparator(false);
        assertEquals("", element.getStyle().getProperty("borderBottom"));
        assertFalse(widget.isSeparator());
    }

    public void testShadow() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setShadow(1);
        assertTrue(element.hasClassName(CssName.Z_DEPTH_1));
        assertEquals(1, widget.getShadow());
    }

    public void testGrid() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setGrid("s12 m12 l12");
        assertTrue(element.hasClassName("col s12 m12 l12"));
        widget.setGrid("s12");
        assertTrue(element.hasClassName("col s12"));
        widget.setGrid("s12 l12");
        assertTrue(element.hasClassName("col s12 l12"));
        widget.setOffset("s12 m12 l12");
        assertTrue(element.hasClassName("offset-s12 offset-m12 offset-l12"));
    }

    public void testTextAlign() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setTextAlign(TextAlign.CENTER);
        assertTrue(element.hasClassName(TextAlign.CENTER.getCssName()));
        widget.setTextAlign(TextAlign.LEFT);
        assertTrue(element.hasClassName(TextAlign.LEFT.getCssName()));
        widget.setTextAlign(TextAlign.RIGHT);
        assertTrue(element.hasClassName(TextAlign.RIGHT.getCssName()));
    }

    public void testColor() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        checkColor(widget);
    }

    public void testEnabled() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        checkEnabled(widget, widget);
    }

    public void testId() {
        // given
        MaterialWidget widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setId("test");
        assertNotNull(widget.getId());
        assertTrue(element.hasAttribute("id"));
        assertEquals(widget.getId(), element.getId());
    }

    public void testDepth() {
        // given
        MaterialWidget widget = getWidget();

        final int DEPTH = 999;
        widget.setDepth(DEPTH);
        assertEquals(DEPTH, widget.getDepth());
        assertEquals(String.valueOf(DEPTH), widget.getElement().getStyle().getZIndex());
    }

    public void testDimensions() {
        // given
        MaterialWidget widget = getWidget();

        final String MIN_HEIGHT = "10px";
        final String MIN_WIDTH = "20px";

        final String MAX_HEIGHT = "30px";
        final String MAX_WIDTH = "50px";

        // when / then
        widget.setMinHeight(MIN_HEIGHT);
        assertEquals(MIN_HEIGHT, widget.getElement().getStyle().getProperty("minHeight"));

        widget.setMaxHeight(MAX_HEIGHT);
        assertEquals(MAX_HEIGHT, widget.getElement().getStyle().getProperty("maxHeight"));

        widget.setMinWidth(MIN_WIDTH);
        assertEquals(MIN_WIDTH, widget.getElement().getStyle().getProperty("minWidth"));

        widget.setMaxWidth(MAX_WIDTH);
        assertEquals(MAX_WIDTH, widget.getElement().getStyle().getProperty("maxWidth"));
    }

    public void testLayoutPosition() {
        T widget = getWidget();

        widget.setLayoutPosition(Style.Position.FIXED);
        assertEquals(Style.Position.FIXED.getCssName(), widget.getLayoutPosition());
        assertEquals(Style.Position.FIXED.getCssName(), widget.getElement().getStyle().getPosition());

        widget.setLayoutPosition(Style.Position.ABSOLUTE);
        assertEquals(Style.Position.ABSOLUTE.getCssName(), widget.getLayoutPosition());
        assertEquals(Style.Position.ABSOLUTE.getCssName(), widget.getElement().getStyle().getPosition());

        widget.setLayoutPosition(Style.Position.RELATIVE);
        assertEquals(Style.Position.RELATIVE.getCssName(), widget.getLayoutPosition());
        assertEquals(Style.Position.RELATIVE.getCssName(), widget.getElement().getStyle().getPosition());

        widget.setLayoutPosition(Style.Position.STATIC);
        assertEquals(Style.Position.STATIC.getCssName(), widget.getLayoutPosition());
        assertEquals(Style.Position.STATIC.getCssName(), widget.getElement().getStyle().getPosition());
    }

    public void testOpacity() {
        final double INITIAL_OPACITY = 0.0;
        final double FINAL_OPACITY = 1.0;
        // given
        MaterialWidget widget = getWidget();

        // when / then
        widget.setOpacity(INITIAL_OPACITY);
        assertEquals(INITIAL_OPACITY, widget.getOpacity());

        // when / then
        widget.setOpacity(FINAL_OPACITY);
        assertEquals(FINAL_OPACITY, widget.getOpacity());
    }

    public void testOrientation() {
        // given
        MaterialWidget widget = getWidget();

        // when
        widget.setOrientation(Orientation.PORTRAIT);

        // then
        assertEquals(Orientation.PORTRAIT, widget.getOrientation());
        assertTrue(widget.getElement().hasClassName(Orientation.PORTRAIT.getCssName()));

        // when
        widget.setOrientation(Orientation.LANDSCAPE);

        // then
        assertEquals(Orientation.LANDSCAPE, widget.getOrientation());
        assertTrue(widget.getElement().hasClassName(Orientation.LANDSCAPE.getCssName()));
    }

    public void testPerspective() {
        final String PERSPECTIVE = "500px";
        // given
        MaterialWidget widget = getWidget();

        // when
        widget.setPerspective(PERSPECTIVE);
        assertEquals(PERSPECTIVE, widget.getPerspective());
        assertEquals(PERSPECTIVE, widget.getElement().getStyle().getProperty("perspective"));
    }

    public void testTabIndex() {
        checkTabIndex(getWidget());
    }

    protected <T extends MaterialWidget> void checkTabIndex(T widget) {
        final int INITIAL_TAB_INDEX = 0;
        final int FINAL_TAB_INDEX = 1;

        // when / then
        widget.setTabIndex(INITIAL_TAB_INDEX);
        assertEquals(INITIAL_TAB_INDEX, widget.getTabIndex());
        assertEquals(String.valueOf(INITIAL_TAB_INDEX), widget.getElement().getPropertyString("tabIndex"));

        // when / then
        widget.setTabIndex(FINAL_TAB_INDEX);
        assertEquals(FINAL_TAB_INDEX, widget.getTabIndex());
        assertEquals(String.valueOf(FINAL_TAB_INDEX), widget.getElement().getPropertyString("tabIndex"));
    }

    public void testTransform() {
        final String TRANSFORM_VALUE = "rotate(7deg)";
        final String FINAL_TRANSFORM_VALUE = "scale(0)";
        MaterialWidget widget = getWidget();

        widget.setTransform(TRANSFORM_VALUE);
        assertEquals(TRANSFORM_VALUE, widget.getTransform());
        assertEquals(TRANSFORM_VALUE, widget.getElement().getStyle().getProperty("transform"));

        widget.setTransform(FINAL_TRANSFORM_VALUE);
        assertEquals(FINAL_TRANSFORM_VALUE, widget.getTransform());
        assertEquals(FINAL_TRANSFORM_VALUE, widget.getElement().getStyle().getProperty("transform"));
    }

    protected <T extends HasPlaceholder> void checkPlaceholder(T widget) {
        // when / then
        widget.setPlaceholder("Placeholder");
        assertEquals("Placeholder", widget.getPlaceholder());
        widget.setPlaceholder("");
        assertEquals("", widget.getPlaceholder());
    }

    protected <T extends HasIcon> void checkFieldIcon(T widget) {
        // when / then
        Element iconElement = widget.getIcon().getElement();
        widget.setIconType(IconType.POLYMER);
        assertEquals(IconType.POLYMER.getCssName(), iconElement.getInnerHTML());
        widget.setIconColor(Color.RED);
        assertTrue(iconElement.hasClassName(Color.RED.getCssName() + "-text"));
        widget.setIconPosition(IconPosition.LEFT);
        assertTrue(iconElement.hasClassName(IconPosition.LEFT.getCssName()));
        widget.setIconPrefix(true);
        assertTrue(iconElement.hasClassName("prefix"));
        widget.setIconPrefix(false);
        assertFalse(iconElement.hasClassName("prefix"));
        widget.setIconSize(IconSize.LARGE);
        assertTrue(iconElement.hasClassName(IconSize.LARGE.getCssName()));
    }

    protected <T extends UIObject & HasReadOnly> void checkReadOnly(T widget, UIObject target) {
        // given
        Element widgetElement = widget.getElement();
        Element targetElement = target.getElement();

        // when / then
        widget.setReadOnly(true);
        assertTrue(targetElement.hasAttribute("disabled"));
        assertTrue(widgetElement.hasClassName(CssName.READ_ONLY));
        assertTrue(widget.isReadOnly());
        widget.setReadOnly(false);
        assertFalse(targetElement.hasAttribute("disabled"));
        assertFalse(widgetElement.hasClassName(CssName.READ_ONLY));
        assertFalse(widget.isReadOnly());

        widget.setToggleReadOnly(true);
        assertTrue(widgetElement.hasClassName(CssName.READ_ONLY_TOGGLE));
        assertTrue(widget.isToggleReadOnly());
        widget.setToggleReadOnly(false);
        assertFalse(widgetElement.hasClassName(CssName.READ_ONLY_TOGGLE));
        assertFalse(widget.isToggleReadOnly());
    }

    protected <T extends UIObject & HasReadOnly> void checkReadOnly(T widget) {
        checkReadOnly(widget, widget);
    }
}
