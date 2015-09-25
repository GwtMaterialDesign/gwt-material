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

import gwt.material.design.client.custom.CustomIcon;
import gwt.material.design.client.custom.HasActivates;
import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.custom.HasSeparator;
import gwt.material.design.client.custom.HasTooltip;
import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FocusPanel;

public class MaterialIcon extends FocusPanel implements HasGrid, HasSeparator, HasTooltip, HasActivates{

	CustomIcon iconElem;

	protected String icon;
	protected String fontSize;
	private String size = "";
	private String type = "";
	private String color = "";
	private String background = "";
	protected String tooltip = "";
	private String tooltipLocation = "";
	private String shape = "";
	protected String title = "";

	public MaterialIcon() {
	}

	public MaterialIcon(String icon, String fontSize) {
		super();
		setIcon(icon);
		setFontSize(fontSize);
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		generateIcon();
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		this.getElement().getStyle().setFontSize(Double.valueOf(fontSize), Unit.EM);
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
		generateIcon();
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		initTooltip();
	}

	public static native void initTooltip()/*-{
											$wnd.jQuery('.tooltipped').tooltip({
											"delay" : 10
											});
											}-*/;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.getElement().setTitle(title);
	}

	public void generateIcon() {
		this.clear();

		iconElem = new CustomIcon();

		if (!icon.isEmpty())
			iconElem.addStyleName(icon);
		if (!type.isEmpty())
			iconElem.addStyleName(type);
		if(!background.isEmpty())
			this.addStyleName(background);
		if (!size.isEmpty()) {
			iconElem.addStyleName(size);
		}
		else {
			iconElem.addStyleName("small");
		}
		iconElem.addStyleName("materialIcon");

		if (!color.isEmpty())
			iconElem.addStyleName(color + "-text");

		if (!tooltip.isEmpty()) {
			iconElem.addStyleName("tooltipped");
			iconElem.getElement().setAttribute("data-position", tooltipLocation);
			iconElem.getElement().setAttribute("data-tooltip", tooltip);
		}
		this.add(iconElem);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if (type.equals("avatar")) {
			this.addStyleName(MaterialResources.INSTANCE.materialcss().collectionAvatarIcon());
		}
		generateIcon();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		generateIcon();
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
		this.addStyleName(shape);
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
		generateIcon();
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
		generateIcon();
	}

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}

	@Override
	public void setSeparator(boolean separator) {
		if (separator) {
			this.getElement().setAttribute("style", "border-bottom: 1px solid #e9e9e9;");
		}
	}

	@Override
	public void setTooltipLocation(String tooltipLocation) {
		this.tooltipLocation = tooltipLocation;
	}
	
	@Override
	public void setActivates(String activates) {
		this.getElement().getStyle().setDisplay(Display.INLINE);
		this.getElement().setAttribute("data-activates", activates);
		this.addStyleName(activates + " dropdown-button");
	}

}
