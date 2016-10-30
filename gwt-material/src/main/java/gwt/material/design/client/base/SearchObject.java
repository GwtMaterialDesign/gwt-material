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

import com.google.gwt.resources.client.ImageResource;
import gwt.material.design.client.constants.IconType;

import java.io.Serializable;

/***
 * Its an object provided for search component, it uses a keyword and link to be redirected after search.
 */
public class SearchObject implements Serializable {

    private ImageResource resource;
    private String imageUrl;
    private IconType icon;
    private String keyword;
    private String link = "";
    private Object o;

    public SearchObject() {
    }

    /**
     * Provides a search result with icon
     */
    public SearchObject(IconType icon, String keyword, String link) {
        this.icon = icon;
        this.keyword = keyword;
        this.link = link;
    }

    /**
     * Provides a search result with plain text
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

    /**
     * Plain search only, you may need to add searchfinish callback instead of redirecting
     * to any links.
     */
    public SearchObject(IconType icon, String keyword) {
        this.icon = icon;
        this.keyword = keyword;
    }

    /**
     * Search result with image resource component and link
     */
    public SearchObject(ImageResource resource, String keyword, String link) {
        this.resource = resource;
        this.keyword = keyword;
        this.link = link;
    }

    /**
     * Search result with image resource without link , you may require to use Search Finish callback to apply your search
     */
    public SearchObject(ImageResource resource, String keyword) {
        this.resource = resource;
        this.keyword = keyword;
    }

    /**
     * Search result with image url component and link
     */
    public SearchObject(String imageUrl, String keyword, String link) {
        this.imageUrl = imageUrl;
        this.keyword = keyword;
        this.link = link;
    }

    /**
     * Search result with image url without link, you may require to use Search Finish callback to apply your search
     */
    public SearchObject(String imageUrl, String keyword) {
        this.imageUrl = imageUrl;
        this.keyword = keyword;
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

    public ImageResource getResource() {
        return resource;
    }

    public void setResource(ImageResource resource) {
        this.resource = resource;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
