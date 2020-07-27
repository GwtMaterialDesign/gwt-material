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
package gwt.material.design.client.base;

/**
 * CSS Grid Layout introduces a two-dimensional grid system to CSS. Grids can be used to lay out major page areas or small
 * user interface elements. This article introduces the CSS Grid Layout and the new terminology that is part of the CSS
 * Grid Layout Level 1 specification. The features shown in this overview will then be explained in greater detail in the
 * rest of this guide.
 *
 * <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Grid_Layout/Basic_Concepts_of_Grid_Layout">Documentation</a>
 *
 * @author kevzlou7979
 */
public interface HasGridLayout {

    String GRID = "grid";
    String GRID_AREA = "gridArea";
    String GRID_AUTO_COLUMNS = "gridAutoColumns";
    String GRID_AUTO_FLOW = "gridAutoFlow";
    String GRID_AUTO_ROWS = "gridAutoRows";
    String GRID_COLUMN = "gridColumn";
    String GRID_COLUMN_END = "gridColumnEnd";
    String GRID_COLUMN_GAP = "gridColumnGap";
    String GRID_COLUMN_START = "gridColumnStart";
    String GRID_GAP = "gridGap";
    String GRID_ROW = "gridRow";
    String GRID_ROW_END = "gridRowEnd";
    String GRID_ROW_GAP = "gridRowGap";
    String GRID_ROW_START = "gridRowStart";
    String GRID_TEMPLATE = "gridTemplate";
    String GRID_TEMPLATE_AREAS = "gridTemplateAreas";
    String GRID_TEMPLATE_COLUMNS = "gridTemplateColumns";
    String GRID_TEMPLATE_ROWS = "gridTemplateRows";
    String ALIGN_CONTENT = "alignContent";
    String ALIGN_ITEMS = "alignItems";
    String ALIGN_SELF = "alignSelf";
    String COLUMN_GAP = "columnGap";
    String GAP = "gap";
    String JUSTIFY_CONTENT = "justifyContent";
    String JUSTIFY_ITEMS = "justifyItems";
    String JUSTIFY_SELF = "justifySelf";
    String PLACE_CONTENT = "placeContent";
    String PLACE_ITEMS = "placeItems";
    String PLACE_SELF = "placeSelf";
    String ROW_GAP = "rowGap";
    String ASPECT_RATIO = "aspectRatio";

    /**
     * The grid CSS property is a shorthand property that sets all of the explicit grid properties (grid-template-rows,
     * grid-template-columns, and grid-template-areas), and all the implicit grid properties (grid-auto-rows, grid-auto-columns,
     * and grid-auto-flow), in a single declaration.
     */
    void setGridLayout(String value);

    String getGridLayout();

    /**
     * The grid-area CSS property is a shorthand property for grid-row-start, grid-column-start, grid-row-end and grid-column-end,
     * specifying a grid item’s size and location within the grid by contributing a line, a span, or nothing (automatic)
     * to its grid placement, thereby specifying the edges of its grid area.
     */
    void setGridArea(String value);

    String getGridArea();

    /**
     * The grid-auto-columns CSS property specifies the size of an implicitly-created grid column track or pattern of tracks.
     */
    void setGridAutoColumns(String value);

    String getGridAutoColumns();

    /**
     * The grid-auto-flow CSS property controls how the auto-placement algorithm works, specifying exactly how auto-placed
     * items get flowed into the grid.
     */
    void setGridAutoFlow(String value);

    String getGridAutoFlow();

    /**
     * The grid-auto-rows CSS property specifies the size of an implicitly-created grid row track or pattern of tracks.
     */
    void setGridAutoRows(String value);

    String getGridAutoRows();

    /**
     * The grid-column CSS property is a shorthand property for grid-column-start and grid-column-end specifying a grid
     * item's size and location within the grid column by contributing a line, a span, or nothing (automatic) to its grid
     * placement, thereby specifying the inline-start and inline-end edge of its grid area.
     */
    void setGridColumn(String value);

    String getGridColumn();

    /**
     * The grid-column-end CSS property specifies a grid item’s end position within the grid column by contributing a
     * line, a span, or nothing (automatic) to its grid placement, thereby specifying the block-end edge of its grid area.
     */
    void setGridColumnEnd(String value);

    String getGridColumnEnd();

    /**
     * The column-gap CSS property sets the size of the gap (gutter) between an element's columns.
     */
    void setGridColumnGap(String value);

    String getGridColumnGap();

    /**
     * The grid-column-start CSS property specifies a grid item’s start position within the grid column by contributing
     * a line, a span, or nothing (automatic) to its grid placement. This start position defines the block-start edge of
     * the grid area.
     */
    void setGridColumnStart(String value);

    String getGridColumnStart();

    /**
     * The gap CSS property sets the gaps (gutters) between rows and columns. It is a shorthand for row-gap and column-gap.
     */
    void setGridGap(String value);

    String getGridGap();

    /**
     * The grid-row CSS property is a shorthand property for grid-row-start and grid-row-end specifying a grid item’s
     * size and location within the grid row by contributing a line, a span, or nothing (automatic) to its grid placement,
     * thereby specifying the inline-start and inline-end edge of its grid area.
     */
    void setGridRow(String value);

    String getGridRow();

    /**
     * The grid-row-end CSS property specifies a grid item’s end position within the grid row by contributing a line,
     * a span, or nothing (automatic) to its grid placement, thereby specifying the inline-end edge of its grid area.
     */
    void setGridRowEnd(String value);

    String getGridRowEnd();

    /**
     * The row-gap CSS property sets the size of the gap (gutter) between an element's grid rows.
     */
    void setGridRowGap(String value);

    String getGridRowGap();

    /**
     * The grid-row-start CSS property specifies a grid item’s start position within the grid row by contributing a line,
     * a span, or nothing (automatic) to its grid placement, thereby specifying the inline-start edge of its grid area.
     */
    void setGridRowStart(String value);

    String getGridRowStart();

    /**
     * The grid-template CSS property is a shorthand property for defining grid columns, rows, and areas.
     */
    void setGridTemplate(String value);

    String getGridTemplate();

    /**
     * The grid-template-areas CSS property specifies named grid areas, establishing the cells in the grid and assigning them names.
     */
    void setGridTemplateAreas(String value);

    String getGridTemplateAreas();

    /**
     * The grid-template-columns CSS property defines the line names and track sizing functions of the grid columns.
     */
    void setGridTemplateColumns(String value);

    String getGridTemplateColumns();

    /**
     * The grid-template-rows CSS property defines the line names and track sizing functions of the grid rows.
     */
    void setGridTemplateRows(String value);

    String getGridTemplateRows();

    /**
     * The CSS align-content property sets the distribution of space between and around content items along a flexbox's
     * cross-axis or a grid's block axis.
     */
    void setAlignContent(String value);

    String getAlignContent();

    /**
     * The CSS align-items property sets the align-self value on all direct children as a group. In Flexbox, it controls
     * the alignment of items on the Cross Axis. In Grid Layout, it controls the alignment of items on the Block Axis
     * within their grid area.
     */
    void setAlignItems(String value);

    String getAlignItems();

    /**
     * The align-self CSS property overrides a grid or flex item's align-items value. In Grid, it aligns the item inside
     * the grid area. In Flexbox, it aligns the item on the cross axis.
     */
    void setAlignSelf(String value);

    String getAlignSelf();

    /**
     * The column-gap CSS property sets the size of the gap (gutter) between an element's columns.
     */
    void setColumnGap(String value);

    String getColumnGap();

    /**
     * The gap CSS property sets the gaps (gutters) between rows and columns. It is a shorthand for row-gap and column-gap.
     */
    void setGap(String value);

    String getGap();

    /**
     * The CSS justify-content property defines how the browser distributes space between and around content items along
     * the main-axis of a flex container, and the inline axis of a grid container.
     */
    void setJustifyContent(String value);

    String getJustifyContent();

    /**
     * The CSS justify-items property defines the default justify-self for all items of the box, giving them all a default
     * way of justifying each box along the appropriate axis.
     */
    void setJustifyItems(String value);

    String getJustifyItems();

    /**
     * The CSS justify-self property sets the way a box is justified inside its alignment container along the appropriate axis.
     */
    void setJustifySelf(String value);

    String getJustifySelf();

    /**
     * The place-content CSS property is a shorthand for align-content and justify-content. It can be used in any layout
     * method which utilizes both of these alignment values.
     */
    void setPlaceContent(String value);

    String getPlaceContent();

    /**
     * The CSS place-items shorthand property sets the align-items and justify-items properties, respectively.
     * If the second value is not set, the first value is also used for it.
     */
    void setPlaceItems(String value);

    String getPlaceItems();

    /**
     * The place-self CSS property is a shorthand property sets both the align-self and justify-self properties.
     * The first value is the align-self property value, the second the justify-self one. If the second value is not present,
     * the first value is also used for it.
     */
    void setPlaceSelf(String value);

    String getPlaceSelf();

    /**
     * The row-gap CSS property sets the size of the gap (gutter) between an element's grid rows.
     */
    void setRowGap(String value);

    String getRowGap();

    /**
     * The aspect-ratio  CSS property sets a preferred aspect ratio for the box, which will be used in the calculation
     * of auto sizes and some other layout functions.
     */
    void setAspectRatio(String value);

    String getAspectRatio();
}
