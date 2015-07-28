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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSlide extends Composite {

	private static MaterialSlideUiBinder uiBinder = GWT.create(MaterialSlideUiBinder.class);

	interface MaterialSlideUiBinder extends UiBinder<Widget, MaterialSlide> {
	}

	@UiField MaterialPanel panel;
	@UiField UnorderedList ulPanel;
	
	private boolean fullScreen;
	
	public MaterialSlide() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		String className = String.valueOf(hashCode());
		panel.addStyleName(className);
		initializeSlider(className);
	}

	@UiChild(tagname = "slide")
	public void addSlide(Widget w){
		ulPanel.add(w);
	}
	
	/**
	 * Initialize the slider when the widget is attached
	 */
	public native void initializeSlider(String className)/*-{
		$wnd.jQuery(document).ready(function() {
			$wnd.jQuery('.' + className).slider({
				full_width : true
			});
		});
	}-*/;

	public boolean isFullScreen() {
		return fullScreen;
	}

	/**
	 * Set the image slider if its fullscreen view
	 * @param fullScreen
	 */
	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
		if(fullScreen){
			panel.addStyleName("fullscreen");
		}
	}

	
	
}
