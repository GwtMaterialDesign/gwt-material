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

import gwt.material.design.client.base.SearchObject;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.InputType;

import java.util.ArrayList;
import java.util.List;

/**
 * Test case for Search.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialSearchTest extends MaterialValueBoxTest<MaterialSearch> {

    static final List<SearchObject> SEARCH_OBJECTS = new ArrayList<>();

    @Override
    protected MaterialSearch createWidget() {
        MaterialSearch search = new MaterialSearch();

        for (int i = 1; i <= 5; i++) {
            SearchObject obj = new SearchObject();
            obj.setKeyword("keyword" + i);
            obj.setLink("link" + i);
            SEARCH_OBJECTS.add(obj);
        }

        search.setListSearches(SEARCH_OBJECTS);
        return search;
    }

    @Override
    public void testValue() {
        // given
        MaterialSearch search = getWidget();

        // when / then
        search.setValue("test");
        assertEquals("test", search.getValue());
    }

    public void testStructure() {
        // given
        MaterialSearch search = getWidget();

        // when / then
        search.open();
        assertEquals(InputType.SEARCH, search.getType());
        assertEquals(4, search.getChildren().size());

        assertEquals(search.getValueBoxBase(), search.getWidget(0));
        assertEquals(search.getLabel(), search.getWidget(1));
        assertEquals(search.getIconClose(), search.getWidget(2));
        assertEquals(search.getSearchResultPanel(), search.getWidget(3));

        MaterialSearchResult searchResultWidget = search.getSearchResultPanel();
        assertNotNull(searchResultWidget);

        assertEquals(SEARCH_OBJECTS.size(), search.getListSearches().size());
        assertEquals(0, searchResultWidget.getChildren().size());
    }

    public void testEmptySearchResult() {
        // given
        MaterialSearch search = getWidget();

        // when / then
        assertNotSame(0, search.getListSearches().size());
        search.open();
        search.setValue("noresult");
        assertEquals("noresult", search.getValue());
        MaterialSearchResult searchResult = search.getSearchResultPanel();
        assertEquals(0, searchResult.getChildren().size());
    }

    public void testValueChangeEvent() {
        // given
        MaterialSearch search = getWidget();

        // when / then
        checkValueChangeEvent(search, "Value 1", "Value 2");
    }

    public void testActiveState() {
        // given
        MaterialSearch search = getWidget();

        // when / then
        search.setActive(true);
        assertNotNull(search.getIconClose());
        assertNotNull(search.getIconSearch());

        assertEquals(Color.BLACK, search.getIconClose().getIconColorMixin().getTextColor());
        assertEquals(Color.BLACK, search.getIconSearch().getIconColorMixin().getTextColor());
        search.setActive(false);
        assertEquals(Color.WHITE, search.getIconClose().getIconColorMixin().getTextColor());
        assertEquals(Color.WHITE, search.getIconSearch().getIconColorMixin().getTextColor());

        // when / then
        search.open();
        assertTrue(search.isActive());
        checkOpenHandler(search);

        search.close();
        assertFalse(search.isActive());
        checkCloseHandler(search);
    }

    @Override
    public void testFieldErrorSuccess() {
        // given
        MaterialSearch widget = getWidget();

        // when / then
        checkFieldErrorSuccess(widget, widget.getErrorLabel());
    }

    public void testSelectedObject() {
        // given
        MaterialSearch widget = getWidget();

        assertTrue(widget.getListSearches().size() > 0);
        SearchObject object = widget.getListSearches().get(0);

        // when / then
        widget.setSelectedObject(object);
        assertEquals(object, widget.getSelectedObject());
        assertEquals(object.getKeyword(), widget.getText());
    }
}
