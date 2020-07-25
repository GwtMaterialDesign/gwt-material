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
    void setGrid(String value);

    String getGrid();

    /**
     * The grid-area CSS property is a shorthand property for grid-row-start, grid-column-start, grid-row-end and grid-column-end,
     * specifying a grid item’s size and location within the grid by contributing a line, a span, or nothing (automatic)
     * to its grid placement, thereby specifying the edges of its grid area.
     *
     * <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/grid-area">Documentation</a>
     */
    void setGridArea(String value);

    String getGridArea();

    /**
     * The grid-auto-columns CSS property specifies the size of an implicitly-created grid column track or pattern of tracks.
     *
     * <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/grid-auto-columns">Documentation</a>
     */
    void setGridAutoColumns(String value);

    String getGridAutoColumns();

    /**
     * The grid-auto-flow CSS property controls how the auto-placement algorithm works, specifying exactly how auto-placed
     * items get flowed into the grid.
     *
     * <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/grid-auto-flow">Documentation</a>
     */
    void setGridAutoFlow(String value);

    String getGridAutoFlow();

    /**
     * The grid-auto-rows CSS property specifies the size of an implicitly-created grid row track or pattern of tracks.
     *
     * <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/grid-auto-rows">Documentation</a>
     */
    void setGridAutoRows(String value);

    String getGridAutoRows();
}
