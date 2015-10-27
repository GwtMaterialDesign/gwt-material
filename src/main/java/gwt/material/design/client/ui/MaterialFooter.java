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

import gwt.material.design.client.custom.CustomFooter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialFooter extends Composite {

	private static MaterialFooterUiBinder uiBinder = GWT.create(MaterialFooterUiBinder.class);

	interface MaterialFooterUiBinder extends UiBinder<Widget, MaterialFooter> {
	}
	@UiField CustomFooter footerPanel;
	@UiField Label lblCopyright;
	@UiField MaterialPanel container;
	private String color = "";
	private String copyright = "";
	private String type = "";
	
	public MaterialFooter() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiChild(tagname = "content")
	public void onAddFooterContent(Widget w){
		container.add(w);
	}
	

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		if(!color.isEmpty()) footerPanel.addStyleName(color);
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
		if(!copyright.isEmpty()) lblCopyright.setText(copyright);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(type.equals("fixed")){
			footerPanel.getElement().getStyle().setPosition(Position.FIXED);
			footerPanel.getElement().getStyle().setWidth(100, Unit.PCT);
			footerPanel.getElement().getStyle().setBottom(0, Unit.PCT);
		}
	}

	public CustomFooter getFooterPanel() {
		return footerPanel;
	}

	public void setFooterPanel(CustomFooter footerPanel) {
		this.footerPanel = footerPanel;
	}

	public MaterialPanel getContainer() {
		return container;
	}

	public void setContainer(MaterialPanel container) {
		this.container = container;
	}

	

}
