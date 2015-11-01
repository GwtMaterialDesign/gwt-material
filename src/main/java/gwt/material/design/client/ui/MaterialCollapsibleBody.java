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
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.Waves;

//@formatter:off
/**
* CollapsibleItem element to define the body 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#collapsibles">Material Collapsibles</a>
*/
//@formatter:on
public class MaterialCollapsibleBody extends ComplexWidget implements HasColors, HasWaves {
	
	/**
	 * Creates empty collapsible body.
	 */
	public MaterialCollapsibleBody() {
		setElement(Document.get().createDivElement());
		setStyleName("collapsible-body");
	}
	
	/**
	 * Add other components into collapsible body.
	 */
	public MaterialCollapsibleBody(final Widget... widgets) {
		this();
		for(Widget w : widgets){
			add(w);
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
