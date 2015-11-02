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

import com.google.gwt.dom.client.Element;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.mixin.WavesMixin;

public class MaterialTab extends ComplexWidget implements HasWaves {

	private int tabIndex;

	private final WavesMixin<MaterialTab> wavesMixin = new WavesMixin<>(this);
	
	public MaterialTab() {
		super(Document.get().createULElement());
		setStyleName("tabs");
	}
	
	@Override
	public void onLoad() {
		super.onLoad();

		initTab(getElement());
	}

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
	public void setWaves(WavesType waves) {
		wavesMixin.setWaves(waves);
	}

	@Override
	public WavesType getWaves() {
		return wavesMixin.getWaves();
	}

	private native void initTab(Element e) /*-{
        $wnd.jQuery(document).ready(function(){
            $wnd.jQuery(e).tabs();
        });
    }-*/;

	/**
	 * Line Indicator on Tab Navigation.
	 * @param color Color string
	 */
	public native void setIndicatorColor(Element e, String color)/*-{
        $wnd.jQuery(e).find(".indicator").css("background-color", color);
    }-*/;
}
