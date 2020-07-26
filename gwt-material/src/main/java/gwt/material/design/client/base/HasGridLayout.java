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
    String GRID_AREA = "grid-area";
    String GRID_AUTO_COLUMNS = "grid-auto-columns";
    String GRID_AUTO_FLOW = "grid-auto-flow";
    String GRID_AUTO_ROWS = "grid-auto-rows";
    String GRID_COLUMN = "grid-column";
    String GRID_COLUMN_END = "grid-column-end";
    String GRID_COLUMN_GAP = "grid-column-gap";
    String GRID_COLUMN_START = "grid-column-start";
    String GRID_GAP = "grid-gap";
    String GRID_ROW = "grid-row";
    String GRID_ROW_END = "grid-row-end";
    String GRID_ROW_GAP = "grid-row-gap";
    String GRID_ROW_START = "grid-row-start";
    String GRID_TEMPLATE = "grid-template";
    String GRID_TEMPLATE_AREAS = "grid-template-areas";
    String GRID_TEMPLATE_COLUMNS = "grid-template-columns";
    String GRID_TEMPLATE_ROWS = "grid-template-rows";


    /**
     * The grid CSS property is a shorthand property that sets all of the explicit grid properties (grid-template-rows,
     * grid-template-columns, and grid-template-areas), and all the implicit grid properties (grid-auto-rows, grid-auto-columns,
     * and grid-auto-flow), in a single declaration.
     *
     * <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/grid">Documentation</a>
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
}
