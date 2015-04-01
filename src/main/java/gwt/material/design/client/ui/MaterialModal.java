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

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaterialModal {
	
	private static HTMLPanel panel = new HTMLPanel("");
	
	private boolean fixedFooter;
	
	public static void showModal(boolean isShow,Widget composite){
		if(isShow){
			panel.clear();
			panel.getElement().setId("modal1");
			panel.addStyleName("modal");
			panel.add(composite);	
			RootPanel.get().add(panel);
			panel.removeStyleName("modal-fixed-footer");
		}
		showModal();
	}
	
	public static void showModal(boolean isShow,Widget composite, boolean isFixedFooter){
		if(isShow){
			panel.clear();
			panel.getElement().setId("modal1");
			panel.addStyleName("modal");
			panel.add(composite);	
			RootPanel.get().add(panel);
			
		}
		if(isFixedFooter){
			panel.addStyleName("modal-fixed-footer");
		}
		showModal();
	}
	

	public void show(){
		showModal();
	}
	
	public void hide(){
		panel.getElement().removeAttribute("style");
		closeModal();
	}
	
	public static native void showModal()/*-{
		$wnd.jQuery('#modal1').openModal();
	}-*/;
	
	public static native void closeModal()/*-{
		$wnd.jQuery('#modal1').closeModal();
	}-*/;

	
	
}
