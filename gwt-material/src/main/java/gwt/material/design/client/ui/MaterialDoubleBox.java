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

//@formatter:off

import com.google.gwt.user.client.ui.DoubleBox;

/**
* Material Integer Box is an input field that accepts any Double based string from user.
* <h3>UiBinder Usage:</h3>
* <pre>
*{@code <m:MaterialIntegerBox placeholder="Your doble" step=100/>}
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material DoubleBox</a>
* @author paulux84
*/
//@formatter:on
public class MaterialDoubleBox extends MaterialNumberBox<Double> {

    public MaterialDoubleBox() {
        super(new DoubleBox());
    }

}
