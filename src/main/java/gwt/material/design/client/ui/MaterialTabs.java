package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 GwtMaterial
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

import gwt.material.design.client.custom.CustomAnchor;
import gwt.material.design.client.custom.ListItem;
import gwt.material.design.client.custom.MaterialWidget;
import gwt.material.design.client.custom.UnorderedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTabs extends MaterialWidget {

	private static MaterialTabsUiBinder uiBinder = GWT.create(MaterialTabsUiBinder.class);

	interface MaterialTabsUiBinder extends UiBinder<Widget, MaterialTabs> {
	}

	@UiField
	MaterialPanel contentPanel;
	@UiField
	UnorderedList tabPanel;

	private String name;
	
	private String color = "";
	private String textColor = "";
	private String indicatorColor = "";
	private String waves = "";

	public MaterialTabs() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		name = String.valueOf(hashCode());
		int col = 12 / tabPanel.getWidgetCount();
		int index = tabPanel.getWidgetCount();
		for (Widget w : tabPanel) {
			if (w instanceof ListItem) {
				if(!waves.isEmpty()) ((ListItem) w).getWidget(0).getElement().addClassName("waves-effect waves-" + waves);
				if(!color.isEmpty()) w.getElement().addClassName(color);
				if(!textColor.isEmpty()) ((ListItem) w).getWidget(0).getElement().addClassName(textColor + "-text");
				((ListItem) w).getWidget(0).getElement().setAttribute("href", "#" + name + index);
				w.addStyleName("tab col s" + col);
				index--;
			}
		}

		int indexC = contentPanel.getWidgetCount();
		for (Widget w : contentPanel) {
			w.getElement().setAttribute("id", name + indexC);
			indexC--;
		}
		initTabs();
		changeIndicator(indicatorColor);
	}
	
	public native void changeIndicator(String color)/*-{
		$wnd.jQuery( ".indicator" ).css( "background-color", color );
	}-*/;

	@UiChild(tagname = "tab")
	public void addTab(final Widget item) {
		CustomAnchor a = new CustomAnchor();
		a.add(item);
		ListItem li = new ListItem(a);
		tabPanel.add(li);
	}

	@UiChild(tagname = "content")
	public void addContent(final Widget item) {
		item.addStyleName("col s12");
		contentPanel.add(item);
	}

	public static native void initTabs()/*-{
		$wnd.jQuery(document).ready(function() {
			$wnd.jQuery('ul.tabs').tabs();
		});
	}-*/;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getIndicatorColor() {
		return indicatorColor;
	}

	public void setIndicatorColor(String indicatorColor) {
		this.indicatorColor = indicatorColor;
	}

	public String getWaves() {
		return waves;
	}

	public void setWaves(String waves) {
		this.waves = waves;
	}
	
}
