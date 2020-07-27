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
package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasGridLayout;

/**
 * A mixin for constructing modern css grid layouts.
 *
 * @author kevzlou7979
 * @see HasGridLayout
 */
public class GridLayoutMixin<T extends Widget & HasGridLayout> extends StylePropertyMixin<T> implements HasGridLayout {


    public GridLayoutMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setGridLayout(String value) {
        setProperty(GRID, value);
    }

    @Override
    public String getGridLayout() {
        return getProperty(GRID);
    }

    @Override
    public void setGridArea(String value) {
        setProperty(GRID_AREA, value);
    }

    @Override
    public String getGridArea() {
        return getProperty(GRID_AREA);
    }

    @Override
    public void setGridAutoColumns(String value) {
        setProperty(GRID_AUTO_COLUMNS, value);
    }

    @Override
    public String getGridAutoColumns() {
        return getProperty(GRID_AUTO_COLUMNS);
    }

    @Override
    public void setGridAutoFlow(String value) {
        setProperty(GRID_AUTO_FLOW, value);
    }

    @Override
    public String getGridAutoFlow() {
        return getProperty(GRID_AUTO_FLOW);
    }

    @Override
    public void setGridAutoRows(String value) {
        setProperty(GRID_AUTO_ROWS, value);
    }

    @Override
    public String getGridAutoRows() {
        return getProperty(GRID_AUTO_ROWS);
    }

    @Override
    public void setGridColumn(String value) {
        setProperty(GRID_COLUMN, value);
    }

    @Override
    public String getGridColumn() {
        return getProperty(GRID_COLUMN);
    }

    @Override
    public void setGridColumnEnd(String value) {
        setProperty(GRID_COLUMN_END, value);
    }

    @Override
    public String getGridColumnEnd() {
        return getProperty(GRID_COLUMN);
    }

    @Override
    public void setGridColumnGap(String value) {
        setProperty(GRID_COLUMN_GAP, value);
    }

    @Override
    public String getGridColumnGap() {
        return getProperty(GRID_COLUMN_GAP);
    }

    @Override
    public void setGridColumnStart(String value) {
        setProperty(GRID_COLUMN_START, value);
    }

    @Override
    public String getGridColumnStart() {
        return getProperty(GRID_COLUMN_START);
    }

    @Override
    public void setGridGap(String value) {
        setProperty(GRID_GAP, value);
    }

    @Override
    public String getGridGap() {
        return getProperty(GRID_GAP);
    }

    @Override
    public void setGridRow(String value) {
        setProperty(GRID_ROW, value);
    }

    @Override
    public String getGridRow() {
        return getProperty(GRID_ROW);
    }

    @Override
    public void setGridRowEnd(String value) {
        setProperty(GRID_ROW_END, value);
    }

    @Override
    public String getGridRowEnd() {
        return getProperty(GRID_ROW_END);
    }

    @Override
    public void setGridRowGap(String value) {
        setProperty(GRID_ROW_GAP, value);
    }

    @Override
    public String getGridRowGap() {
        return getProperty(GRID_ROW_GAP);
    }

    @Override
    public void setGridRowStart(String value) {
        setProperty(GRID_ROW_START, value);
    }

    @Override
    public String getGridRowStart() {
        return getProperty(GRID_ROW_START);
    }

    @Override
    public void setGridTemplate(String value) {
        setProperty(GRID_TEMPLATE, value);
    }

    @Override
    public String getGridTemplate() {
        return getProperty(GRID_TEMPLATE);
    }

    @Override
    public void setGridTemplateAreas(String value) {
        setProperty(GRID_TEMPLATE_AREAS, value);
    }

    @Override
    public String getGridTemplateAreas() {
        return getProperty(GRID_TEMPLATE_AREAS);
    }

    @Override
    public void setGridTemplateColumns(String value) {
        setProperty(GRID_TEMPLATE_COLUMNS, value);
    }

    @Override
    public String getGridTemplateColumns() {
        return getProperty(GRID_TEMPLATE_COLUMNS);
    }

    @Override
    public void setGridTemplateRows(String value) {
        setProperty(GRID_TEMPLATE_ROWS, value);
    }

    @Override
    public String getGridTemplateRows() {
        return getProperty(GRID_TEMPLATE_ROWS);
    }

    @Override
    public void setAlignContent(String value) {
        setProperty(ALIGN_CONTENT, value);
    }

    @Override
    public String getAlignContent() {
        return getProperty(ALIGN_CONTENT);
    }

    @Override
    public void setAlignItems(String value) {
        setProperty(ALIGN_ITEMS, value);
    }

    @Override
    public String getAlignItems() {
        return getProperty(ALIGN_ITEMS);
    }

    @Override
    public void setAlignSelf(String value) {
        setProperty(ALIGN_SELF, value);
    }

    @Override
    public String getAlignSelf() {
        return getProperty(ALIGN_SELF);
    }

    @Override
    public void setColumnGap(String value) {
        setProperty(COLUMN_GAP, value);
    }

    @Override
    public String getColumnGap() {
        return getProperty(COLUMN_GAP);
    }

    @Override
    public void setGap(String value) {
        setProperty(GAP, value);
    }

    @Override
    public String getGap() {
        return getProperty(GAP);
    }

    @Override
    public void setJustifyContent(String value) {
        setProperty(JUSTIFY_CONTENT, value);
    }

    @Override
    public String getJustifyContent() {
        return getProperty(JUSTIFY_CONTENT);
    }

    @Override
    public void setJustifyItems(String value) {
        setProperty(JUSTIFY_ITEMS, value);
    }

    @Override
    public String getJustifyItems() {
        return getProperty(JUSTIFY_ITEMS);
    }

    @Override
    public void setJustifySelf(String value) {
        setProperty(JUSTIFY_SELF, value);
    }

    @Override
    public String getJustifySelf() {
        return getProperty(JUSTIFY_SELF);
    }

    @Override
    public void setPlaceContent(String value) {
        setProperty(PLACE_CONTENT, value);
    }

    @Override
    public String getPlaceContent() {
        return getProperty(PLACE_CONTENT);
    }

    @Override
    public void setPlaceItems(String value) {
        setProperty(PLACE_ITEMS, value);
    }

    @Override
    public String getPlaceItems() {
        return getProperty(PLACE_ITEMS);
    }

    @Override
    public void setPlaceSelf(String value) {
        setProperty(PLACE_SELF, value);
    }

    @Override
    public String getPlaceSelf() {
        return getProperty(PLACE_SELF);
    }

    @Override
    public void setRowGap(String value) {
        setProperty(ROW_GAP, value);
    }

    @Override
    public String getRowGap() {
        return getProperty(ROW_GAP);
    }

    @Override
    public void setAspectRatio(String value) {
        setProperty(ASPECT_RATIO, value);
    }

    @Override
    public String getAspectRatio() {
        return getProperty(ASPECT_RATIO);
    }
}
