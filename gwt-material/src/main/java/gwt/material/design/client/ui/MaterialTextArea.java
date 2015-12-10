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
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextArea;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.CounterMixin;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.html.Label;

//@formatter:off

/**
* Material Text Area represents a multiple line textbox where users can define comment, detail and etc.
* <h3>UiBinder Usage:</h3>
* <pre>
*{@code <m:MaterialTextArea placeholder="Your Comment" />
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material TextArea</a>
* @author kevzlou7979
* @author Ben Dol
*/
//@formatter:on
public class MaterialTextArea extends ComplexWidget implements HasText, HasValue<String>, HasKeyPressHandlers,
        HasKeyDownHandlers, HasKeyUpHandlers, HasChangeHandlers, HasError, HasIcon, HasPlaceholder, HasCounter {

    private String placeholder;
    private boolean isValid = true;

    private Label label = new Label();
    private MaterialLabel lblError = new MaterialLabel();
    private MaterialLabel lblName = new MaterialLabel();
    private TextArea textArea = new TextArea();
    private MaterialIcon icon = new MaterialIcon();

    private CounterMixin<MaterialTextArea> counterMixin = new CounterMixin<>(this);
    private final ErrorMixin<MaterialTextArea, MaterialLabel> errorMixin = new ErrorMixin<>(this, lblError, null);

    public MaterialTextArea() {
        super(Document.get().createDivElement());
        setStyleName("input-field");
        add(icon);
        add(textArea);
        textArea.setStyleName("materialize-textarea");
        add(label);
        label.add(lblName);
        lblError.setVisible(false);
        add(lblError);
    }

    public void removeErrorModifiers() {
        textArea.removeStyleName("valid");
        textArea.removeStyleName("invalid");
        lblName.removeStyleName("green-text");
        lblName.removeStyleName("red-text");
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        label.getElement().setAttribute("for", "field");
    }

    @Override
    public String getText() {
        return textArea.getText();
    }

    @Override
    public void setText(String text) {
        textArea.setText(text);
        label.addStyleName("active");
    }

    @Override
    public void clear() {
        textArea.setText("");
        clearErrorOrSuccess();
        label.removeStyleName("active");
    }

    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        lblName.setText(placeholder);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return addDomHandler(handler, KeyDownEvent.getType());
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return addDomHandler(handler, KeyUpEvent.getType());
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return addDomHandler(handler, KeyPressEvent.getType());
    }

    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return addDomHandler(handler, ChangeEvent.getType());
    }

    @Override
    public void setError(String error) {
        errorMixin.setError(error);

        removeErrorModifiers();
        lblName.setStyleName("red-text");
        textArea.addStyleName("invalid");
        isValid = false;
    }

    @Override
    public void setSuccess(String success) {
        errorMixin.setSuccess(success);

        removeErrorModifiers();
        lblName.setStyleName("green-text");
        textArea.addStyleName("valid");
        isValid = true;
    }

    @Override
    public void clearErrorOrSuccess() {
        errorMixin.clearErrorOrSuccess();
        removeErrorModifiers();
    }

    @Override
    public MaterialIcon getIcon() {
        return icon;
    }

    @Override
    public void setIconType(IconType iconType) {
        icon.setIconType(iconType);
        icon.setIconPrefix(true);
    }

    @Override
    public void setIconPosition(IconPosition position) {
        icon.setIconPosition(position);
    }

    @Override
    public void setIconSize(IconSize size) {
        icon.setIconSize(size);
    }

    @Override
    public void setIconFontSize(double size, Style.Unit unit) {
        icon.setIconFontSize(size, unit);
    }

    @Override
    public void setIconColor(String iconColor) {
        icon.setIconColor(iconColor);
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        icon.setIconPrefix(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return icon.isIconPrefix();
    }

    @Override
    public void setLength(int length) {
        counterMixin.setLength(length);
    }

    @Override
    public int getLength() {
        return counterMixin.getLength();
    }

    @Override
    public String getValue() {
        return getText();
    }

    @Override
    public void setValue(String value) {
        setValue(value, true);
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        setText(value);

        if(fireEvents) {
            ValueChangeEvent.fire(this, value);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    public TextArea asGwtTextArea() {
        return textArea;
    }
}
