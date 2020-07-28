/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
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
package gwt.material.design.client.ui.layout;

import gwt.material.design.client.base.HasGridLayout;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.base.MaterialWidgetTestCase;

public class GridLayoutTest extends MaterialWidgetTestCase<MaterialPanel> {

    public static final String GRID = "minmax(400px, min-content) / repeat(auto-fill, 50px)";
    public static final String GRID_AREA = "4 some-grid-area / 2 another-grid-area";
    public static final String GRID_AUTO_COLUMNS = "minmax(10px, auto);";
    public static final String GRID_AUTO_FLOW = "column dense";
    public static final String GRID_AUTO_ROWS = "minmax(100px, auto) minmax(max-content, 2fr) minmax(20%, 80vmax)";
    public static final String GRID_COLUMN = "1 / span 2";
    public static final String GRID_COLUMN_END = "span 3";
    public static final String GRID_COLUMN_GAP = "12%";
    public static final String GRID_COLUMN_START = "auto";
    public static final String GRID_GAP = "calc(20px + 10%)";
    public static final String GRID_ROW = "2 / -1";
    public static final String GRID_ROW_END = "-1";
    public static final String GRID_ROW_GAP = "1ch";
    public static final String GRID_ROW_START = "3";
    public static final String GRID_TEMPLATE = "100px 1fr / 50px 1fr";
    public static final String GRID_TEMPLATE_AREAS = "a b";
    public static final String GRID_TEMPLATE_COLUMNS = "1fr 60px";
    public static final String GRID_TEMPLATE_ROWS = "3ch auto minmax(10px, 60px)";
    public static final String ALIGN_CONTENT = "center";
    public static final String ALIGN_ITEMS = "stretch";
    public static final String ALIGN_SELF = "start";
    public static final String COLUMN_GAP = "2rem";
    public static final String GAP = "10%";
    public static final String JUSTIFY_CONTENT = "space-between";
    public static final String JUSTIFY_ITEMS = "end";
    public static final String JUSTIFY_SELF = "stretch";
    public static final String PLACE_CONTENT = "space-around start";
    public static final String PLACE_ITEMS = "center stretch";
    public static final String PLACE_SELF = "start end";
    public static final String ROW_GAP = "1ch";

    @Override
    protected MaterialPanel createWidget() {
        return new MaterialPanel();
    }

    public void testGridLayoutProperties() {
        MaterialPanel panel = getWidget();

        panel.setGridLayout(GRID);
        assertEquals(GRID, panel.getGridLayout());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID));

        panel.setGridArea(GRID_AREA);
        assertEquals(GRID_AREA, panel.getGridArea());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_AREA));

        panel.setGridAutoColumns(GRID_AUTO_COLUMNS);
        assertEquals(GRID_AUTO_COLUMNS, panel.getGridAutoColumns());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_AUTO_COLUMNS));

        panel.setGridAutoFlow(GRID_AUTO_FLOW);
        assertEquals(GRID_AUTO_FLOW, panel.getGridAutoFlow());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_AUTO_FLOW));

        panel.setGridAutoRows(GRID_AUTO_ROWS);
        assertEquals(GRID_AUTO_ROWS, panel.getGridAutoRows());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_AUTO_ROWS));

        panel.setGridColumn(GRID_COLUMN);
        assertEquals(GRID_COLUMN, panel.getGridColumn());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_COLUMN));

        panel.setGridColumnEnd(GRID_COLUMN_END);
        assertEquals(GRID_COLUMN_END, panel.getGridColumnEnd());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_COLUMN_END));

        panel.setGridColumnGap(GRID_COLUMN_GAP);
        assertEquals(GRID_COLUMN_GAP, panel.getGridColumnGap());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_COLUMN_GAP));

        panel.setGridColumnStart(GRID_COLUMN_START);
        assertEquals(GRID_COLUMN_START, panel.getGridColumnStart());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_COLUMN_START));

        panel.setGridGap(GRID_GAP);
        assertEquals(GRID_GAP, panel.getGridGap());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_GAP));

        panel.setGridRow(GRID_ROW);
        assertEquals(GRID_ROW, panel.getGridRow());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_ROW));

        panel.setGridRowEnd(GRID_ROW_END);
        assertEquals(GRID_ROW_END, panel.getGridRowEnd());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_ROW_END));

        panel.setGridRowGap(GRID_ROW_GAP);
        assertEquals(GRID_ROW_GAP, panel.getGridRowGap());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_ROW_GAP));

        panel.setGridRowStart(GRID_ROW_START);
        assertEquals(GRID_ROW_START, panel.getGridRowStart());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_ROW_START));

        panel.setGridTemplate(GRID_TEMPLATE);
        assertEquals(GRID_TEMPLATE, panel.getGridTemplate());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_TEMPLATE));

        panel.setGridTemplateAreas(GRID_TEMPLATE_AREAS);
        assertEquals(GRID_TEMPLATE_AREAS, panel.getGridTemplateAreas());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_TEMPLATE_AREAS));

        panel.setGridTemplateColumns(GRID_TEMPLATE_COLUMNS);
        assertEquals(GRID_TEMPLATE_COLUMNS, panel.getGridTemplateColumns());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_TEMPLATE_COLUMNS));

        panel.setGridTemplateRows(GRID_TEMPLATE_ROWS);
        assertEquals(GRID_TEMPLATE_ROWS, panel.getGridTemplateRows());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GRID_TEMPLATE_ROWS));

        panel.setAlignContent(ALIGN_CONTENT);
        assertEquals(ALIGN_CONTENT, panel.getAlignContent());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.ALIGN_CONTENT));

        panel.setAlignItems(ALIGN_ITEMS);
        assertEquals(ALIGN_ITEMS, panel.getAlignItems());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.ALIGN_ITEMS));

        panel.setAlignSelf(ALIGN_SELF);
        assertEquals(ALIGN_SELF, panel.getAlignSelf());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.ALIGN_SELF));

        panel.setColumnGap(COLUMN_GAP);
        assertEquals(COLUMN_GAP, panel.getColumnGap());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.COLUMN_GAP));

        panel.setGap(GAP);
        assertEquals(GAP, panel.getGap());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.GAP));

        panel.setJustifyContent(JUSTIFY_CONTENT);
        assertEquals(JUSTIFY_CONTENT, panel.getJustifyContent());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.JUSTIFY_CONTENT));

        panel.setJustifyItems(JUSTIFY_ITEMS);
        assertEquals(JUSTIFY_ITEMS, panel.getJustifyItems());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.JUSTIFY_ITEMS));

        panel.setJustifySelf(JUSTIFY_SELF);
        assertEquals(JUSTIFY_SELF, panel.getJustifySelf());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.JUSTIFY_SELF));

        panel.setPlaceContent(PLACE_CONTENT);
        assertEquals(PLACE_CONTENT, panel.getPlaceContent());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.PLACE_CONTENT));

        panel.setPlaceItems(PLACE_ITEMS);
        assertEquals(PLACE_ITEMS, panel.getPlaceItems());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.PLACE_ITEMS));

        panel.setPlaceSelf(PLACE_SELF);
        assertEquals(PLACE_SELF, panel.getPlaceSelf());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.PLACE_SELF));

        panel.setRowGap(ROW_GAP);
        assertEquals(ROW_GAP, panel.getRowGap());
        assertNotNull(panel.getElement().getStyle().getProperty(HasGridLayout.ROW_GAP));
    }
}
