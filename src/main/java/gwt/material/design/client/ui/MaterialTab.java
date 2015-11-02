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

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.Waves;

public class MaterialTab extends ComplexWidget implements HasColors, HasWaves {

	private int tabIndex;
	
	public MaterialTab() {
		setElement(Document.get().createULElement());
		setStyleName("tabs");
	}
	
	@Override
	public void onLoad() {
		String id = DOM.createUniqueId();
		getElement().setId(id);
		initTab(id);
	}
	
	private native void initTab(String id) /*-{
		$wnd.jQuery(document).ready(function(){
	    	$wnd.jQuery('ul#' + id).tabs();
	  	});
	}-*/;
	
	/**
	 * Line Indicator on Tab Navigation.
	 * @param color Color string
	 */
	public native void setIndicatorColor(String color)/*-{
		$wnd.jQuery( ".indicator" ).css( "background-color", color );
	}-*/;

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
		int i = 0;
		for(Widget w : this){
			if(i == tabIndex){
				if(w.getStyleName().contains("tab")){
					w.addStyleName("active");
				}
			}
			i++;
		}
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor + "-text"); 
	}

	@Override
	public void setWaves(String waves) {
		addStyleName("waves-effect waves-" + waves);
		Waves.detectAndApply(this);
	}
}
