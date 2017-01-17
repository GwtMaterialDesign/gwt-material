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

import com.google.gwt.dom.client.Document;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;

//@formatter:off

/**
 * MaterialColumn is a panel that act as a fluid panel to easily sets your desired column.
 * We are using 12 grid layout with screens small, medium and large.
 * Just set grid='s12 m12 l12' to define your grid layout values.
 * <p>
 * <p>
 * <h4>UiBinder Usage:</h4>
 * <pre>
 * {@code
 * <m:MaterialColumn grid='s12 m6 l4'/>
 *     Small Device - 12 grid
 *     Medium Device - 6 grid
 *     Large Device - 4 grid
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#grid">Material Column</a>
 */
//@formatter:on
public class MaterialColumn extends MaterialWidget {

    public MaterialColumn() {
        super(Document.get().createDivElement(), CssName.COL);
    }

    public MaterialColumn(int small, int medium, int large) {
        this();
        setGrid("s" + small + " m" + medium + " l" + large);
    }
}
