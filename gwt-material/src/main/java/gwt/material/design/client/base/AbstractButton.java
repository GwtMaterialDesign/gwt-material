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
package gwt.material.design.client.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.History;
import gwt.material.design.client.base.mixin.ActivatesMixin;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.html.Span;

/**
 * @author Ben Dol
 */
public abstract class AbstractButton extends MaterialWidget implements HasHref, HasGrid, HasActivates,
        HasTargetHistoryToken, HasType<ButtonType> {

    private final ActivatesMixin<AbstractButton> activatesMixin = new ActivatesMixin<>(this);
    private final CssTypeMixin<ButtonType, AbstractButton> cssTypeMixin = new CssTypeMixin<>(this);

    private Span span = new Span();
    private ButtonSize size;

    private String targetHistoryToken;

    /**
     * Creates button with RAISED type.
     */
    protected AbstractButton() {
        setElement(createElement());

        getElement().getStyle().setCursor(Style.Cursor.POINTER);
    }

    protected AbstractButton(String... initialClass) {
        this();
        setInitialClasses(initialClass);
    }

    protected AbstractButton(String text, Color bgColor, WavesType waves) {
        this(null, text, bgColor);
        setWaves(waves);
    }

    protected AbstractButton(final ButtonType type, String text, Color bgColor, WavesType waves) {
        this(type, text, bgColor);
        setWaves(waves);
    }

    protected AbstractButton(final ButtonType type, String text, Color bgColor) {
        this(type, text);
        setBackgroundColor(bgColor);
    }

    protected AbstractButton(final ButtonType type, String text) {
        this(type);
        setText(text);
    }

    protected AbstractButton(final ButtonType type) {
        this();
        setType(type);
    }

    protected abstract Element createElement();

    @Override
    public void setHref(String href) {
        getElement().setAttribute("href", href);
    }

    @Override
    public String getHref() {
        return getElement().getAttribute("href");
    }

    @Override
    public void setTarget(String target) {
        getElement().setAttribute("target", target);
    }

    @Override
    public String getTarget() {
        return getElement().getAttribute("target");
    }

    @Override
    public void setActivates(String activates) {
        removeStyleName(getActivates() + " " + CssName.DROPDOWN_BUTTON);
        activatesMixin.setActivates(activates);
        addStyleName(activates + " " + CssName.DROPDOWN_BUTTON);
    }

    @Override
    public String getActivates() {
        return activatesMixin.getActivates();
    }

    @Override
    public void setType(ButtonType type) {
        cssTypeMixin.setType(type);
    }

    @Override
    public ButtonType getType() {
        return cssTypeMixin.getType();
    }

    /**
     * Set the buttons size.
     */
    public void setSize(ButtonSize size) {
        if (this.size != null) {
            removeStyleName(this.size.getCssName());
        }
        this.size = size;

        if (size != null) {
            addStyleName(size.getCssName());
        }
    }

    /**
     * Get the buttons size.
     */
    public ButtonSize getSize() {
        return size;
    }

    /**
     * Get the buttons span text.
     */
    public String getText() {
        return span.getText();
    }

    /**
     * Set the buttons span text.
     */
    public void setText(String text) {
        span.setText(text);

        if (!span.isAttached()) {
            add(span);
        }
    }

    /**
     * Set the target history token for the widget. Note, that you should use either
     * {@link #setTargetHistoryToken(String)} or {@link #setHref(String)}, but not both as
     * {@link #setHref(String)} resets the target history token.
     *
     * @param targetHistoryToken String target history token of the widget
     */
    @Override
    public void setTargetHistoryToken(final String targetHistoryToken) {
        this.targetHistoryToken = targetHistoryToken;
        if (targetHistoryToken != null) {
            setHref("#" + History.encodeHistoryToken(targetHistoryToken));
        }
    }

    /**
     * Get the target history token for the widget. May return {@code null} if no
     * history token has been set or if it has been reset by {@link #setHref(String)}.
     *
     * @return String the widget's target history token.
     */
    @Override
    public String getTargetHistoryToken() {
        return targetHistoryToken;
    }
}
