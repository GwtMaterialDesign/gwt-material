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
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.LoaderSize;

//@formatter:off

/**
 * Material Preloader is a wrapper for Material Spinner which handle multiple flashing circular loaders
 * <p>
 * <h3>Java Usage:</h3>
 * <pre>
 * {@code
 *
 * MaterialLoader.loading(true);
 *
 * }
 * <pre>
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#loader">Material PreLoader</a>
 * @see <a href="https://material.io/guidelines/components/progress-activity.html#">Material Design Specification</a>
 */
//@formatter:on
public class MaterialPreLoader extends MaterialWidget {

    private CssNameMixin<MaterialPreLoader, LoaderSize> sizeMixin;

    public MaterialPreLoader() {
        super(Document.get().createDivElement(), CssName.PRELOADER_WRAPPER, CssName.ACTIVE);
    }

    /**
     * Sets the size of the pre loader.
     */
    public void setSize(LoaderSize size) {
        getSizeMixin().setCssName(size);
    }

    /**
     * Same as {@link #setSize(LoaderSize)}, but will not clash with {@link com.google.gwt.user.client.ui.UIObject#setSize(String, String)}
     */
    public void setLoaderSize(LoaderSize size) {
        setSize(size);
    }

    public LoaderSize getSize() {
        return getSizeMixin().getCssName();
    }

    public LoaderSize getLoaderSize() {
        return getSize();
    }

    protected CssNameMixin<MaterialPreLoader, LoaderSize> getSizeMixin() {
        if (sizeMixin == null) {
            sizeMixin = new CssNameMixin<>(this);
        }
        return sizeMixin;
    }
}
