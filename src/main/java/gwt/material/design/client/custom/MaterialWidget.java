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

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialWidget extends Composite{

	protected String waves = "";
	protected String color = "";
	protected String textColor = "";
	protected String type = "";
	protected Widget widget;
	protected String align = "";
	protected String tooltip = "";
	protected String tooltipLocation = "bottom";
	protected String tooltipDelay = "50";
	protected boolean disable;
	private String padding;

	public String getWaves() {
		return waves;
	}

	public void setWaves(String waves) {
		this.waves = waves;
		applyMaterialEffect();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		applyMaterialEffect();
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		applyMaterialEffect();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void applyMaterialEffect() {
		boolean applyWavesEffect = false;
		if(widget != null) {
			if(!isDisable()) {
				if(!waves.isEmpty()) {
					applyWavesEffect = true;
					widget.getElement().addClassName("waves-effect waves-" + waves);
				}
				if(!color.isEmpty()) widget.getElement().addClassName(color);
				if(!textColor.isEmpty()) widget.getElement().addClassName(textColor + "-text");
				if(!align.isEmpty()) widget.getElement().addClassName(align + "-align");

				if(!tooltip.isEmpty()) getWidget().addStyleName("tooltipped");
				if(!tooltipLocation.isEmpty()) getWidget().getElement().setAttribute("data-position", tooltipLocation);
				if(!tooltipDelay.isEmpty()) getWidget().getElement().setAttribute("data-delay", tooltipDelay);
				if(!tooltip.isEmpty()) getWidget().getElement().setAttribute("data-tooltip", tooltip);
			} else {
				widget.addStyleName("disabled");
				widget.getElement().addClassName("grey lighten-2 ");
			}
		}
		initToolTip();
		if (applyWavesEffect) Waves.detectAndApply(this);
	}

	@Override
	public Widget getWidget() {
		return widget;
	}

	@Override
	public void setWidget(Widget widget) {
		this.widget = widget;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
		applyMaterialEffect();
	}

	public String getTooltipLocation() {
		return tooltipLocation;
	}

	public void setTooltipLocation(String tooltipLocation) {
		this.tooltipLocation = tooltipLocation;
		applyMaterialEffect();
	}

	public String getTooltipDelay() {
		return tooltipDelay;
	}

	public void setTooltipDelay(String tooltipDelay) {
		this.tooltipDelay = tooltipDelay;
		applyMaterialEffect();
	}

	private native void initToolTip()/*-{
		 $wnd.jQuery(document).ready(function(){
		    $wnd.jQuery('.tooltipped').tooltip({delay: 50});
		  });
	}-*/;

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public String getPadding() {
		return padding;
	}

	public void setPadding(String padding) {
		this.padding = padding;
		this.getElement().getStyle().setPadding(Double.parseDouble(padding), Unit.PCT);
	}
}
