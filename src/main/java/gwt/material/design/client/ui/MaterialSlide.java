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
import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSlide extends ComplexWidget {

	private UnorderedList ul = new UnorderedList();

	public MaterialSlide() {
		super(Document.get().createDivElement());
		setStyleName("slider");
		ul.setStyleName("slides");
		super.add(ul);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		initializeSlider();
	}

	@Override
	public void add(Widget child) {
		ul.add(child);
	}
	
	/**
	 * Set the image slider to fullscreen view.
	 */
	public void setFullScreen(boolean isFullscreen) {
		removeStyleName("fullscreen");
		if(isFullscreen){
			addStyleName("fullscreen");
		}
	}

	/**
	 * Initialize the slider when the widget is attached.
	 */
	private void initializeSlider() {
		initializeSlider(getElement());
	}

	private native void initializeSlider(Element e)/*-{
        $wnd.jQuery(document).ready(function() {
            $wnd.jQuery(e).slider({
                full_width : true
            });
        });
    }-*/;
}
