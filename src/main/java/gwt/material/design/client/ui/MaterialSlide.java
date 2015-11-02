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

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSlide extends ComplexWidget {

	private UnorderedList ul = new UnorderedList();

	public MaterialSlide() {
		setElement(Document.get().createDivElement());
		setStyleName("slider");
		ul.setStyleName("slides");
		super.add(ul);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		String className = DOM.createUniqueId();
		addStyleName(className);
		initializeSlider(className);
	}

	@Override
	public void add(Widget child) {
		ul.add(child);
	}

	/**
	 * Initialize the slider when the widget is attached.
	 * @param className CSS class name
	 */
	private native void initializeSlider(String className)/*-{
		$wnd.jQuery(document).ready(function() {
			$wnd.jQuery('.' + className).slider({
				full_width : true
			});
		});
	}-*/;
	
	/**
	 * Set the image slider to fullscreen view.
	 */
	public void setFullScreen(boolean isFullscreen) {
		if(isFullscreen){
			addStyleName("fullscreen");
		} else {
			removeStyleName("fullscreen");
		}
	}
}
