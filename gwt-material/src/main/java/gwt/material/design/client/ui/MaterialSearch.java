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
import gwt.material.design.client.base.HasPlaceholder;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.html.Label;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

//@formatter:off
/**
* Material Search is a value box component that returs a result based on your search
* 
* <p>
* <h3>UiBinder Usage:</h3>
* <pre>
* {@code 
* <m:MaterialSearch placeholder="Sample"/>
* }
* </pre>
* </p>
* 
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#navigations">Material Search</a>
*/
//@formatter:on
public class MaterialSearch extends MaterialWidget implements HasText, HasKeyUpHandlers, HasPlaceholder {

    private MaterialInput searchInput = new MaterialInput();
    private Label label = new Label();
    private MaterialIcon iconSearch = new MaterialIcon(IconType.SEARCH);
    private MaterialIcon iconClose = new MaterialIcon(IconType.CLOSE);

    public MaterialSearch() {
        super(Document.get().createDivElement());
        setStyleName("input-field");
        searchInput.setType(InputType.SEARCH);
        searchInput.setRequired(true);
        add(searchInput);
        add(label);
        label.add(iconSearch);
        add(iconClose);
    }

    @Override
    public String getPlaceholder() {
        return searchInput.getElement().getAttribute("placeholder");
    }

    @Override
    public void setPlaceholder(String placeholder) {
        searchInput.getElement().setAttribute("placeholder", placeholder);
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return addDomHandler(handler, KeyUpEvent.getType());
    }

    @Override
    public String getText() {
        return searchInput.getElement().getInnerHTML();
    }

    @Override
    public void setText(String text) {
        searchInput.getElement().setInnerHTML(text);
    }
}


