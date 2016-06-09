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

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.base.MaterialWidget;

//@formatter:off
/**
* Material Container is a wrapper of MAIN element, primary content of your app.
* 
* <p>
* <h3>UiBinder Usage:</h3>
* <pre>
* {@code
* <m:MaterialContainer>
*     <!-- Content goes here -->
* </m:MaterialContainer>
* }
* </pre>
*
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#grid">Material Column</a>
*/
//@formatter:on
public class MaterialContainer extends MaterialWidget {

    /**
     * Creates an empty collapsible item.
     */
    public MaterialContainer() {
        super(Document.get().createElement("main"));
    }

    /**
     * Adds MaterialCollapsible contents.
     */
    public MaterialContainer(final Widget... widgets) {
        this();
        for(Widget w : widgets) {
            add(w);
        }
    }
}
