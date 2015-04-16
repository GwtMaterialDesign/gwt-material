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

import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MaterialIcon extends FocusPanel {

	protected HTMLPanel panel = new HTMLPanel("");

	protected String icon;
	protected String fontSize;
	private String type = "";
	private String color = "";
	private String textColor = "";
	protected String tooltip = "";
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
		this.getElement().getStyle().setCursor(Cursor.POINTER);
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
		panel = new HTMLPanel("<i class='" + icon + " " + textColor +"-text " + " " + type + " materialIcon'></i>");
		if (!tooltip.isEmpty()) {
			panel = new HTMLPanel("<i class='tooltipped " + icon + " materialIcon' data-position='bottom' data-tooltip=" + tooltip + "></i>");
		}
		this.add(panel);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(type.equals("avatar")){
			this.addStyleName(MaterialResources.INSTANCE.materialcss().collectionAvatarIcon());
		}
		generateIcon();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		this.addStyleName(color);
		generateIcon();
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
		this.addStyleName(shape);
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		generateIcon();
	}
	
	

	
}
