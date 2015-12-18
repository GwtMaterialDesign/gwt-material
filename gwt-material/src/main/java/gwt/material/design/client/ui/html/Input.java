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


import com.google.gwt.dom.client.Document;
import gwt.material.design.client.base.ComplexWidget;


/**
 * @author guaido79
 */
public class Input extends ComplexWidget {

    public enum TYPE {
        TEXT,
        HIDDEN,
        DATE
    }

    private TYPE type;

    private String placeholder;

    public Input() {
        super(Document.get().createElement("input"));
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
        getElement().setAttribute("type", type.toString().toLowerCase());
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        getElement().setAttribute("placeholder", placeholder);
    }
}

