package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 GwtMaterial
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

import com.google.gwt.user.client.ui.Image;

public class MaterialImage extends Image{
	
	private String type="";
	private String caption="";

	public MaterialImage() {
		
	}
	
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName("responsive-img");
		onInitMaterialDesign();
		
	}
	
	public static native void onInitMaterialDesign()/*-{
		$wnd.jQuery(document).ready(function(){
	    	$wnd.jQuery('.materialboxed').materialbox();
	    });
	}-*/;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.addStyleName(type);
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
		this.getElement().setAttribute("data-caption", caption);
	}
	
	
}
