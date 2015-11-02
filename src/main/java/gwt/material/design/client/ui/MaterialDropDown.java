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
import gwt.material.design.client.custom.ComplexNav;
import gwt.material.design.client.custom.HasGrid;

import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiConstructor;

//@formatter:off
/**
 * You can add dropdown easily by specifying it's item
 * content and add a UiHandler on it to implement any event.
 *
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code<m:MaterialDropDown>
 *   <m:MaterialLink text="First" />
 *   <m:MaterialLink text="Second" />
 *   <m:MaterialLink text="Third" />
 * </m:MaterialDropDown>
 * }
 * </pre>
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#dropdowns">Material DropDowns</a>
 */
//@formatter:on
public class MaterialDropDown extends ComplexNav {

	private String name;
	private boolean belowOrigin;
	private boolean constraintWidth;

	public MaterialDropDown(){
		setElement(Document.get().createULElement());
		setStyleName("dropdown-content");
	}
	
	/**
	 * Material Dropdown - adds a list item selection when button , link , icon button pressed
	 * @param name - name of your dropdown, a unique name
	 * @param constraintWidth - Does not change width of dropdown to that of the activator
	 * @param belowOrigin - displays dropdown below the button
	 */
	@UiConstructor
	public MaterialDropDown(String name,boolean belowOrigin, boolean constraintWidth) {
		this();
		this.name = name;
		this.belowOrigin = belowOrigin;
		this.constraintWidth = constraintWidth;
		getElement().setId(name);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		initDropDown(name, belowOrigin,  constraintWidth);
	}

	@Override
	protected void onUnload() {
		super.onUnload();
	}

	/**
	 * Initialize the dropdown components
	 */
	private native void initDropDown(String name,boolean belowOrigin, boolean constraintWidth)/*-{
		$wnd.jQuery(document).ready(function(){
			$wnd.jQuery('.'+name).dropdown({
			  inDuration: 300,
			  outDuration: 225,
			  constrain_width: constraintWidth, // Does not change width of dropdown to that of the activator
			  hover: false, // Activate on hover
			  gutter: 0, // Spacing from edge
			  belowOrigin: belowOrigin, // Displays dropdown below the button
			  alignment: 'right' // Displays dropdown with edge aligned to the left of button
			  // Displays dropdown below the button
			});
		});
	}-*/;
}
