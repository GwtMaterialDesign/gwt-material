package gwt.material.design.client.custom;

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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
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
import com.google.gwt.user.client.ui.HasText;
import gwt.material.design.client.constants.ButtonSize;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.custom.mixin.ActivatesMixin;
import gwt.material.design.client.custom.mixin.CssTypeMixin;
import gwt.material.design.client.custom.mixin.GridMixin;
import gwt.material.design.client.custom.mixin.IdMixin;
import gwt.material.design.client.custom.mixin.TooltipMixin;
import gwt.material.design.client.custom.mixin.WavesMixin;

/**
 * @author Ben Dol
 */
public abstract class AbstractButton extends ComplexWidget implements HasHref, HasTooltip, HasGrid,
		HasActivates, HasWaves, HasType<ButtonType>, HasClickHandlers, HasAllMouseHandlers, HasText {

	private final IdMixin<AbstractButton> idMixin = new IdMixin<>(this);
	private final TooltipMixin<AbstractButton> tooltipMixin = new TooltipMixin<>(this);
	private final WavesMixin<AbstractButton> wavesMixin = new WavesMixin<>(this);
	private final GridMixin<AbstractButton> gridMixin = new GridMixin<>(this);
	private final ActivatesMixin<AbstractButton> activatesMixin = new ActivatesMixin<>(this);
	private final CssTypeMixin<ButtonType, AbstractButton> cssTypeMixin = new CssTypeMixin<>(this);

	private ButtonSize size;

	/** Creates button with RAISED type.
	 */
	protected AbstractButton() {
		this(ButtonType.RAISED);
	}

	protected AbstractButton(final ButtonType type) {
		setElement(createElement());
		setType(type);
	}

	protected abstract Element createElement();

	@Override
	public void setId(String id) {
		idMixin.setId(id);
	}

	@Override
	public String getId() {
		return idMixin.getId();
	}

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
	public void setTooltip(Tooltip tooltip) {
		tooltipMixin.setTooltip(tooltip);
	}

	@Override
	public Tooltip getTooltip() {
		return tooltipMixin.getTooltip();
	}

	@Override
	public void setWaves(WavesType waves) {
		wavesMixin.setWaves(waves);
	}

	@Override
	public WavesType getWaves() {
		return wavesMixin.getWaves();
	}

	@Override
	public void setGrid(String grid) {
		gridMixin.setGrid(grid);
	}

	@Override
	public void setOffset(String offset) {
		gridMixin.setOffset(offset);
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

	@Override
	public String getText() {
		return getElement().getInnerText();
	}

	@Override
	public void setText(String text) {
		getElement().setInnerText(text);
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
}