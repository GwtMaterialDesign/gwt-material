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
package gwt.material.design.client.ui.html;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.OptGroupElement;
import gwt.material.design.client.base.MaterialWidget;

/**
 * @author kevzlou7979
 */
public class OptGroup extends MaterialWidget {

    public OptGroup() {
        super(Document.get().createOptGroupElement());
    }

    public OptGroup(String value) {
        this();
        setLabel(value);
    }

    /**
     * Option label for use in hierarchical menus.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-label-OPTION">W3C
     * HTML Specification</a>
     */
    public String getLabel() {
        return OptGroupElement.as(this.getElement()).getLabel();
    }

    /**
     * The control is unavailable in this context.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-disabled">W3C
     * HTML Specification</a>
     */
    public boolean isDisabled() {
        return OptGroupElement.as(this.getElement()).isDisabled();
    }

    /**
     * The control is unavailable in this context.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-disabled">W3C
     * HTML Specification</a>
     */
    public void setDisabled(boolean disabled) {
        OptGroupElement.as(this.getElement()).setDisabled(disabled);
    }

    ;

    /**
     * Option label for use in hierarchical menus.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-label-OPTION">W3C
     * HTML Specification</a>
     */
    public void setLabel(String label) {
        OptGroupElement.as(this.getElement()).setLabel(label);
    }
}
