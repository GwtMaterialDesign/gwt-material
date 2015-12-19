package gwt.material.design.client.base;

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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import gwt.material.design.client.base.mixin.ActivatesMixin;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.ButtonSize;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.html.Span;

/**
 * @author Ben Dol
 */
public abstract class AbstractButton extends MaterialWidget implements HasHref, HasGrid, HasActivates,
        HasTargetHistoryToken, HasType<ButtonType>, HasClickHandlers, HasAllMouseHandlers,
        HasDoubleClickHandlers {

    private final ActivatesMixin<AbstractButton> activatesMixin = new ActivatesMixin<>(this);
    private final CssTypeMixin<ButtonType, AbstractButton> cssTypeMixin = new CssTypeMixin<>(this);

    private Span span = new Span();
    private ButtonSize size;

    private String targetHistoryToken;

    /** Creates button with RAISED type.
     */
    protected AbstractButton() {
        setElement(createElement());

        getElement().getStyle().setCursor(Style.Cursor.POINTER);
    }

    protected AbstractButton(String text, String bgColor, WavesType waves) {
        this(null, text, bgColor);
        setWaves(waves);
    }

    protected AbstractButton(final ButtonType type, String text, String bgColor, WavesType waves) {
        this(type, text, bgColor);
        setWaves(waves);
    }

    protected AbstractButton(final ButtonType type, String text, String bgColor) {
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
        removeStyleName(getActivates() + " dropdown-button");
        activatesMixin.setActivates(activates);
        addStyleName(activates + " dropdown-button");
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

    public void setSize(ButtonSize size) {
        if(this.size != null) {
            removeStyleName(this.size.getCssName());
        }
        this.size = size;

        if(size != null) {
            addStyleName(size.getCssName());
        }
    }

    public ButtonSize getSize() {
        return size;
    }

    public String getText() {
        return span.getText();
    }

    public void setText(String text) {
        span.setText(text);
        add(span);
    }

    /**
     * Set the target history token for the widget. Note, that you should use either
     * {@link #setTargetHistoryToken(String)}or {@link #setHref(String)}, but not both as
     * {@link #setHref(String)} resets the target history token.
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
     * @return String the widget's target history token.
     */
    @Override
    public String getTargetHistoryToken() {
        return targetHistoryToken;
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
        return addDomHandler(handler, MouseDownEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
        return addDomHandler(handler, MouseMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
        return addDomHandler(handler, MouseOutEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
        return addDomHandler(handler, MouseOverEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
        return addDomHandler(handler, MouseUpEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(final MouseWheelHandler handler) {
        return addDomHandler(handler, MouseWheelEvent.getType());
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
        return addDomHandler(handler, DoubleClickEvent.getType());
    }
}
