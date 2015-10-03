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
import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.custom.HasType;

import com.google.gwt.dom.client.Document;

public class MaterialCollapsible extends ComplexWidget implements HasGrid, HasType{

	/**
	 * Creates an empty collapsible
	 */
	public MaterialCollapsible() {
		setElement(Document.get().createULElement());
		setStyleName("collapsible");
	}
	
	/**
	 *  Creates a list and adds the given widgets.
	 */
	public MaterialCollapsible(final MaterialCollapsibleItem... widgets){
		this();
		for (final MaterialCollapsibleItem item : widgets) {
            add(item);
        }
	}

	/**
	 * Initialize the collapsible material component
	 * @param uniqueId 
	 */
	private native void onInitCollapsible(final com.google.gwt.dom.client.Element e)/*-{
		$wnd.jQuery(document).ready(function(){
			$wnd.jQuery(e).collapsible();	
		})
			
	}-*/;

	/**
	 * Types
	 * 1. accordion
	 * 2. collapsible
	 * 3. popout
	 */
	@Override
	public void setType(String type) {
		if(type.equals("popout")){
			this.getElement().setAttribute("data-collapsible", "accordion");
			this.addStyleName(type);
		}else{
			this.getElement().setAttribute("data-collapsible", type);
		}
	}

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		onInitCollapsible(getElement());
	}

	@Override
	protected void onUnload() {
		super.onUnload();
	}
	
	@Override
	public void setOffset(String offset) {
		String tobeadded = "";
		String[] vals = offset.split(" ");
		for(String val : vals){
			tobeadded = tobeadded + " offset-" +  val;
		}
		this.addStyleName(tobeadded);
	}
	
}
