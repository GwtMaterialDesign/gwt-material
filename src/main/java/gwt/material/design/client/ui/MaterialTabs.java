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

import gwt.material.design.client.custom.CustomAnchor;
import gwt.material.design.client.custom.MaterialWidget;

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

	
	private String color = "";
	private String textColor = "";
	private String indicatorColor = "";
	private String waves = "";
	private int tabIndex = 1;

	/**
	 * The tabs structure consists of an unordered list of tabs that have hashes corresponding to tab ids. Then when you click on each tab, only the container with the corresponding tab id will become visible.
	 */
	public MaterialTabs() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		tabPanel.getElement().setId(String.valueOf(System.currentTimeMillis()));
		String name = String.valueOf(hashCode());
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
		initTabs(tabPanel.getElement().getId());
		changeIndicator(indicatorColor);
		
		String id = contentPanel.getWidget(tabIndex).getElement().getId();
		selectTab(id, tabPanel.getElement().getId());
	}
	
	/**
	 * Line Indicator on Tab Navigation
	 * @param color Color string
	 */
	public native void changeIndicator(String color)/*-{
		$wnd.jQuery( ".indicator" ).css( "background-color", color );
	}-*/;

	/**
	 * Adding each tab nav , must add also the Content if you add a tab nav item
	 * @param item Widget item
	 */
	@UiChild(tagname = "tab")
	public void addTab(final Widget item) {
		CustomAnchor a = new CustomAnchor();
		a.add(item);
		ListItem li = new ListItem(a);
		tabPanel.add(li);
	}

	/**
	 * Adding each tab content on the navigation
	 * @param item Widget content
	 */
	@UiChild(tagname = "content")
	public void addContent(final Widget item) {
		item.addStyleName("col s12");
		contentPanel.add(item);
	}

	/**
	 * Initialize the Material Tab
	 */
	public static native void initTabs(String ulId)/*-{
		$wnd.jQuery(document).ready(function() {
			$wnd.jQuery('ul#' + ulId).tabs();
		});
	}-*/;
	
	
	/**
	 * Selecting the tab with id on the content 
	 * @param id Id of the tab
	 * @param ulId Id of the list
	 */
	private native void selectTab(String id, String ulId)/*-{
		 $wnd.jQuery(document).ready(function(){
		    $wnd.jQuery('ul#' + ulId).tabs('select_tab', id);
		 });
	}-*/;
	
	/**
	 * Get the color of the tab
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Setting the color of the tab
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Get the text color of the tab
	 */
	public String getTextColor() {
		return textColor;
	}

	/**
	 * Set the text color of the tab
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	/**
	 * Get the indicator underlined color
	 * @return Color string
	 */
	public String getIndicatorColor() {
		return indicatorColor;
	}

	/**
	 * Set the indicator underlined color
	 * @param indicatorColor Color string
	 */
	public void setIndicatorColor(String indicatorColor) {
		this.indicatorColor = indicatorColor;
	}

	/**
	 * Get the waves effect of the tab
	 */
	public String getWaves() {
		return waves;
	}

	/**
	 * Set the waves effect of the tab
	 */
	public void setWaves(String waves) {
		this.waves = waves;
	}


	/**
	 * @return the tabIndex
	 */
	public int getTabIndex() {
		return tabIndex;
	}


	/**
	 * @param tabIndex You can automatically set the tab index by passing the desired tab index number, starts from 1.
	 */
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	
	
}
