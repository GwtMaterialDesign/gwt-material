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
import com.google.gwt.dom.client.OptionElement;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialListBox;

/**
 * Option widget that encapsulated a &lt;option&gt; tag. This widget is to be
 * used in conjunction with {@link MaterialListBox}:
 * <p>
 * <p>
 * <blockquote>
 * <p>
 * <pre>
 * {@code
 * <m:MaterialListBox >
 *     <m:html.Option text="One" value="1"/>
 *     <m:html.Option text="Two" value="2"/>
 *     <m:html.Option text="Three" value="3"/>
 *  </m:MaterialListBox>
 * }
 * </pre>
 * <p>
 * </blockquote>
 * </p>
 *
 * @author gilberto-torrezan
 * @see OptionElement
 */
public class Option extends MaterialWidget {

    public Option() {
        super(Document.get().createElement(OptionElement.TAG));
    }

    public Option(String value) {
        this();
        setText(value);
    }

    /**
     * The index of this OPTION in its parent SELECT, starting from 0.
     */
    public int getIndex() {
        return OptionElement.as(this.getElement()).getIndex();
    }

    /**
     * Option label for use in hierarchical menus.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-label-OPTION">W3C
     * HTML Specification</a>
     */
    public String getLabel() {
        return OptionElement.as(this.getElement()).getLabel();
    }

    /**
     * The text contained within the option element.
     */
    public String getText() {
        return OptionElement.as(this.getElement()).getText();
    }

    /**
     * The current form control value.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-value-OPTION">W3C
     * HTML Specification</a>
     */
    public String getValue() {
        return OptionElement.as(this.getElement()).getValue();
    }

    /**
     * Represents the value of the HTML selected attribute. The value of this
     * attribute does not change if the state of the corresponding form control,
     * in an interactive user agent, changes.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-selected">W3C
     * HTML Specification</a>
     */
    public boolean isDefaultSelected() {
        return OptionElement.as(this.getElement()).isDefaultSelected();
    }

    /**
     * The control is unavailable in this context.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-disabled">W3C
     * HTML Specification</a>
     */
    public boolean isDisabled() {
        return OptionElement.as(this.getElement()).isDisabled();
    }

    /**
     * Represents the current state of the corresponding form control, in an
     * interactive user agent. Changing this attribute changes the state of the
     * form control, but does not change the value of the HTML selected
     * attribute of the element.
     */
    public boolean isSelected() {
        return OptionElement.as(this.getElement()).isSelected();
    }

    /**
     * Represents the value of the HTML selected attribute. The value of this
     * attribute does not change if the state of the corresponding form control,
     * in an interactive user agent, changes.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-selected">W3C
     * HTML Specification</a>
     */
    public void setDefaultSelected(boolean selected) {
        OptionElement.as(this.getElement()).setDefaultSelected(selected);
    }

    /**
     * The control is unavailable in this context.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-disabled">W3C
     * HTML Specification</a>
     */
    public void setDisabled(boolean disabled) {
        OptionElement.as(this.getElement()).setDisabled(disabled);
    }

    /**
     * Option label for use in hierarchical menus.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-label-OPTION">W3C
     * HTML Specification</a>
     */
    public void setLabel(String label) {
        OptionElement.as(this.getElement()).setLabel(label);
    }

    /**
     * Represents the current state of the corresponding form control, in an
     * interactive user agent. Changing this attribute changes the state of the
     * form control, but does not change the value of the HTML selected
     * attribute of the element.
     */
    public void setSelected(boolean selected) {
        OptionElement.as(this.getElement()).setSelected(selected);
    }

    /**
     * The text contained within the option element.
     */
    public void setText(String text) {
        OptionElement.as(this.getElement()).setText(text);
    }

    /**
     * The current form control value.
     *
     * @see <a
     * href="http://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-value-OPTION">W3C
     * HTML Specification</a>
     */
    public void setValue(String value) {
        OptionElement.as(this.getElement()).setValue(value);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setDisabled(!enabled);
    }
}
