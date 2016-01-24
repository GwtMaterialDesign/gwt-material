package gwt.material.design.client.base;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import gwt.material.design.client.constants.IconType;

import java.io.Serializable;

/***
 * Its an object provided for search component, it uses a keyword and link to be redirected after search.
 */
public class SearchObject implements Serializable {

    private IconType icon;
    private String keyword;
    private String link;
    private Object o;

    public SearchObject() {}

    /**
     * Provides a search result with icon
     * @param icon
     * @param keyword
     * @param link
     */
    public SearchObject(IconType icon, String keyword, String link) {
        this.icon = icon;
        this.keyword = keyword;
        this.link = link;
    }

    /**
     * Provides a search result with plain text
     * @param keyword
     * @param link
     * @param o
     */
    public SearchObject(String keyword, String link, Object o) {
        this.keyword = keyword;
        this.link = link;
        this.o = o;
    }

    public SearchObject(Object o, String link, String keyword, IconType icon) {
        this.o = o;
        this.link = link;
        this.keyword = keyword;
        this.icon = icon;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public IconType getIcon() {
        return icon;
    }

    public void setIcon(IconType icon) {
        this.icon = icon;
    }
}
