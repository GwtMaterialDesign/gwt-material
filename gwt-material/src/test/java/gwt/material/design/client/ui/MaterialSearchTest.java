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
package gwt.material.design.client.ui;

import gwt.material.design.client.base.SearchObject;
import gwt.material.design.client.constants.InputType;

import java.util.ArrayList;
import java.util.List;

/**
 * Test case for Search
 *
 * @author kevzlou7979
 */
public class MaterialSearchTest extends MaterialValueBoxTest {

    public void init() {
        MaterialSearch search = new MaterialSearch();
        checkStructure(search);
        checkSearchEvents(search);
        checkCloseHandler(search);
        checkValueBox(search);
    }

    public <T extends MaterialSearch> void checkStructure(T search) {
        assertEquals(search.getType(), InputType.SEARCH);
        assertEquals(search.getChildren().size(), 3);

        search.onLoad();
        MaterialSearchResult searchResultWidget = search.getSearchResultPanel();
        assertNotNull(searchResultWidget);

        List<SearchObject> objects = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            SearchObject obj = new SearchObject();
            obj.setKeyword("keyword" + i);
            obj.setLink("link" + i);
            objects.add(obj);
        }

        // Expected result by default 0
        search.setListSearches(objects);
        assertEquals(objects.size(), search.getListSearches().size());
        assertEquals(searchResultWidget.getChildren().size(), 0);
    }
}
