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
package gwt.material.design.client.base.mixin;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasImage;

/**
 * @author kevzlou7979
 */
public class ImageMixin<T extends UIObject & HasImage> extends AbstractMixin<T> implements HasImage {

    private String url = "";
    private ImageResource resource;

    public ImageMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
        uiObject.getElement().setAttribute("src", url);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setResource(ImageResource resource) {
        this.resource = resource;
        setUrl(resource.getSafeUri().asString());
    }

    @Override
    public ImageResource getResource() {
        return resource;
    }
}
