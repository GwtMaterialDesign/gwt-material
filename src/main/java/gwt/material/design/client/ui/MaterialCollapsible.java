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

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCollapsible extends UnorderedList {

	private String wave = "";
	private String type = "";
	
	public MaterialCollapsible() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.getElement().addClassName("collapsible");
		onInitCollapsible();
	}

	@UiChild(tagname = "item")
	public void addItem(final Widget item) {
		if(!wave.isEmpty()){
			item.addStyleName("waves-effect waves-" + wave);
		}
		this.add(item);
	}
	
	public static native void onInitCollapsible()/*-{
		$wnd.jQuery(document).ready(function() {
			$wnd.jQuery('.collapsible').collapsible({
				accordion : false
			});
		});
	}-*/;

	public String getWave() {
		return wave;
	}

	public void setWave(String wave) {
		this.wave = wave;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(type.equals("popout")){
			this.getElement().setAttribute("data-collapsible", "accordion");
			this.addStyleName(type);
		}else{
			this.getElement().setAttribute("data-collapsible", type);
		}
	}

	
	
}
