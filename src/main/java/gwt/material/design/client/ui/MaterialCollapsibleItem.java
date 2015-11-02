package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

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

//@formatter:off
/**
* Collapsible element to define every items
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#collapsibles">Material Collapsibles</a>
*/
//@formatter:on
public class MaterialCollapsibleItem extends ComplexWidget implements HasWidgets {

	/**
	 * Creates an empty collapsible item.
	 */
	public MaterialCollapsibleItem() {
		super(Document.get().createLIElement());
	}
	
	/**
	 * Adds MaterialCollapsible contents.
	 */
	public MaterialCollapsibleItem(final Widget... widgets) {
		this();
		for(Widget w : widgets){
			add(w);
		}
	}
}
