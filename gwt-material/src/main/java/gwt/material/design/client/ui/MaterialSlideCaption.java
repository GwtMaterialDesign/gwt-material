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

//@formatter:off
/**
* Material Slide Caption is a child component of Material Slide Item that contains title and description labels
*
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#media">Material Slide</a>
*/
//@formatter:on
public class MaterialSlideCaption extends MaterialWidget {

    public MaterialSlideCaption() {
        super(Document.get().createDivElement());
        setStyleName("caption");
    }
}
