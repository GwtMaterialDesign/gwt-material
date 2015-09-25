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

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaterialLink extends FocusPanel implements HasGrid, HasSeparator, HasActivates{

	protected HTMLPanel panel = new HTMLPanel("");
	private String text = "";
	private String href = "";
	private String icon = "";
	private String target = "";
	private String iconPosition = "";
	private String textColor = "";
	protected String fontSize = "";
	private String wave = "";
	private Object object;
	private String tooltip = "";
	private String tooltipLocation = "bottom";
	private String tooltipDelay = "50";
	private Widget container;
	private Anchor anchorElem;
	private CustomIcon iconElem;
	private MaterialBadge badge;

	private boolean separator = false;
	private boolean active = false;

	public MaterialLink() {
		// TODO Auto-generated constructor stub
	}

	public MaterialLink(String text, String textColor) {
		super();
		setText(text);
		setTextColor(textColor);
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		initToolTip();
	}

	public MaterialLink(String text) {
		super();
		this.text = text;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		generateLink();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		generateLink();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		generateLink();
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
		generateLink();
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		this.getElement().getStyle().setFontSize(Double.valueOf(fontSize), Unit.EM);
	}

	public String getIconPosition() {
		return iconPosition;
	}

	public void setIconPosition(String iconPosition) {
		this.iconPosition = iconPosition;
		generateLink();
	}

	public void generateLink() {
		panel.clear();
		this.clear();
		anchorElem = new Anchor();
		iconElem = new CustomIcon();
		if (!iconPosition.isEmpty())
			iconElem.addStyleName(iconPosition);

		if (!href.isEmpty())
			anchorElem.setHref(href);
		if(!target.isEmpty())
			anchorElem.setTarget(target);
		if (!textColor.isEmpty())
			anchorElem.addStyleName(textColor + "-text");
		if (!text.isEmpty()) {
			anchorElem.setText(text);
		}
		;
		if (!icon.isEmpty()) {
			anchorElem.getElement().appendChild(iconElem.getElement());
			iconElem.addStyleName(icon);
		}
		if(getBadge()!=null) anchorElem.getElement().appendChild(getBadge().getElement());
		panel.add(anchorElem);
		panel.getElement().getStyle().setCursor(Cursor.POINTER);
		this.add(panel);
	}

	@UiChild(tagname = "badge")
	public void addBadge(Widget w){
		MaterialBadge badge = ((MaterialBadge)w) ;
		badge.addStyleName("sideBarBadge");
		setBadge(badge);
	}
	
	public String getWave() {
		return wave;
	}

	public void setWave(String wave) {
		this.wave = wave;
		if (!wave.isEmpty()) {
			this.addStyleName("waves-effect waves-" + wave);
		}
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
		generateTooltip();
	}

	public String getTooltipLocation() {
		return tooltipLocation;
	}

	public void setTooltipLocation(String tooltipLocation) {
		this.tooltipLocation = tooltipLocation;
		generateTooltip();
	}

	public String getTooltipDelay() {
		return tooltipDelay;
	}

	public void setTooltipDelay(String tooltipDelay) {
		this.tooltipDelay = tooltipDelay;
		generateTooltip();
	}

	private void generateTooltip() {
		this.addStyleName("tooltipped");
		this.getElement().setAttribute("data-position", tooltipLocation);
		this.getElement().setAttribute("data-delay", tooltipDelay);
		this.getElement().setAttribute("data-tooltip", tooltip);
	}

	public native void initToolTip()/*-{
									$wnd.jQuery(document).ready(function(){
									$wnd.jQuery('.tooltipped').tooltip({delay: 50});
									});
									}-*/;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		if (active) {
			this.addStyleName("active");
		}
	}

	public boolean isSeparator() {
		return separator;
	}

	public void setSeparator(boolean separator) {
		this.separator = separator;
		if (separator) {
			this.getElement().setAttribute("style", "border-bottom: 1px solid #e9e9e9;");
		}
	}

	public Anchor getAnchorElem() {
		return anchorElem;
	}

	public void setAnchorElem(Anchor anchorElem) {
		this.anchorElem = anchorElem;
	}

	public CustomIcon getIconElem() {
		return iconElem;
	}

	public void setIconElem(CustomIcon iconElem) {
		this.iconElem = iconElem;
	}

	public MaterialBadge getBadge() {
		return badge;
	}

	public void setBadge(MaterialBadge badge) {
		this.badge = badge;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
		generateLink();
	}

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);	
	}

	@Override
	public void setActivates(String activates) {
		this.getElement().setAttribute("data-activates", activates);
		this.addStyleName(activates + " dropdown-button");
	}

}
