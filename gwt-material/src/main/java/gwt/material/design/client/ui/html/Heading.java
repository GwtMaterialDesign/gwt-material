package gwt.material.design.client.ui.html;

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
import gwt.material.design.client.constants.HeadingSize;

import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiConstructor;

public class Heading extends MaterialWidget {

    @UiConstructor
    public Heading(HeadingSize size) {
        super(Document.get().createElement(size.getSize()));
    }

    public void setFontWeight(int fontWeight){
        getElement().getStyle().setProperty("fontWeight", String.valueOf(fontWeight));
    }
}
