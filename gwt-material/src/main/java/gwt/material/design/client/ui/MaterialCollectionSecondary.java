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

import com.google.gwt.dom.client.Document;
import gwt.material.design.client.base.HasHref;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.HrefMixin;
import gwt.material.design.client.constants.CssName;

//@formatter:off

/**
 * Collection element to define secondary content such as Material Icons
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#collections">Material Collections</a>
 * @see <a href="https://material.io/guidelines/components/lists-controls.html#lists-controls-types-of-menu-controls">Material Design Specification</a>
 *///@formatter:on
public class MaterialCollectionSecondary extends MaterialWidget implements HasHref {

    public MaterialCollectionSecondary() {
        super(Document.get().createAnchorElement(), CssName.SECONDARY_CONTENT);
    }

    private HrefMixin<MaterialCollectionSecondary> hrefMixin;

    @Override
    public String getHref() {
        return getHrefMixin().getHref();
    }

    @Override
    public void setHref(String href) {
        getHrefMixin().setHref(href);
    }

    @Override
    public void setTarget(String target) {
        getHrefMixin().setTarget(target);
    }

    @Override
    public String getTarget() {
        return getHrefMixin().getTarget();
    }

    public HrefMixin<MaterialCollectionSecondary> getHrefMixin() {
        if (hrefMixin == null) {
            hrefMixin = new HrefMixin<>(this);
        }
        return hrefMixin;
    }
}
