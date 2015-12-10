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
import com.google.gwt.dom.client.Element;
import gwt.material.design.client.base.AbstractIconButton;
import gwt.material.design.client.constants.ButtonType;

//@formatter:off

/**
* There are 3 main button types described in material design. The raised
* button is a standard button that signify actions and seek to give depth
* to a mostly flat page. The floating circular action button is meant for
* very important functions. Flat buttons are usually used within elements
* that already have depth like cards or modals.
* <h3>UiBinder Usage:</h3>
* <pre>
*{@code
* //Raised (Default) Button
* <m:MaterialButton text="Button" waves="LIGHT" backgroundColor="blue" />
*
* // Adding icon
* <m:MaterialButton text="Button" waves="LIGHT" backgroundColor="blue" iconType="CLOUD" iconPosition="LEFT"/>
*
* // FLOATING+ Button
* <m:MaterialButton type="FLOATING" waves="LIGHT" size="LARGE"  iconType="ADD"/>
*
* // FLAT Button
* <m:MaterialButton text="Button" type="FLAT" waves="GREY" />
*
* // LARGE Button
* <m:MaterialButton size="LARGE" text="Button" waves="LIGHT" backgroundColor="blue" iconType="CLOUD" iconPosition="RIGHT"/>}
* </pre>
*
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#buttons">Material Button</a>
*/
//@formatter:on
public class MaterialAnchorButton extends AbstractIconButton {

    public MaterialAnchorButton(ButtonType type, String text, MaterialIcon icon) {
        super(type, text, icon);
    }

    public MaterialAnchorButton(String text, MaterialIcon icon) {
        super(ButtonType.RAISED, text, icon);
    }

    public MaterialAnchorButton(String text) {
        super(ButtonType.RAISED, text);
    }

    public MaterialAnchorButton(ButtonType type) {
        super(type);
    }

    public MaterialAnchorButton() {
        super(ButtonType.RAISED);
    }

    @Override
    protected Element createElement() {
        return Document.get().createAnchorElement();
    }
}
