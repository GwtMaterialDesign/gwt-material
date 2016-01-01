package gwt.material.design.client.ui;

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

import gwt.material.design.client.base.MaterialWidget;

import com.google.gwt.dom.client.Document;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.constants.LoaderSize;

//@formatter:off
/**
* Material Preloader is a wrapper for Material Spinner which handle multiple flashing circular loaders
*
* <h3>Java Usage:</h3>
* <pre>
* {@code
* 
* MaterialLoader.showLoading(true);
* 
* }
*<pre>
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#loaders">Material PreLoader</a>
*/
//@formatter:on
public class MaterialPreLoader extends MaterialWidget {

    private final CssNameMixin<MaterialPreLoader, LoaderSize> sizeMixin = new CssNameMixin<>(this);


    public MaterialPreLoader() {
        super(Document.get().createDivElement());
        setStyleName("preloader-wrapper active");
    }

    /**
     * Sets the size of the pre loader.
     */
    public void setSize(LoaderSize size){
        sizeMixin.setCssName(size);
    }

    public LoaderSize getSize() {
        return sizeMixin.getCssName();
    }
}
