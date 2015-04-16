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

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaterialModal extends Widget{
	
	private static HTMLPanel panel = new HTMLPanel("");
	
	private boolean fixedFooter;
	
	public enum TYPE{
		FIXED_FOOTER, BOTTOM_SHEET, DEFAULT;
	}
	
	public static void showModal(boolean isShow,Widget composite, TYPE type){
		if(isShow){
			panel.clear();
			
			panel.add(composite);	
			
			switch (type) {
			case FIXED_FOOTER:
				panel.getElement().setId("modalFix");
				panel.setStyleName("modal modal-fixed-footer");
				panel.getElement().getStyle().clearBottom();
				break;
			case BOTTOM_SHEET:
				panel.getElement().setId("modalBottom");
				panel.setStyleName("modal bottom-sheet");
				break;

			default:
				panel.getElement().setId("modal1");
				panel.setStyleName("modal");
				panel.getElement().getStyle().clearBottom();
				break;
			}
			
			RootPanel.get().add(panel);
		}
		showModal(panel.getElement().getId());
	}
	
	public static void showModal(boolean isShow,Widget composite){
		if(isShow){
			panel.clear();
			panel.getElement().setId("modal1");
			panel.addStyleName("modal");
			panel.add(composite);	
			RootPanel.get().add(panel);
		}
		showModal(panel.getElement().getId());
	}
	
	public void hide(){
		panel.getElement().removeAttribute("style");
		closeModal();
	}
	
	public static native void showModal(String id)/*-{
		$wnd.jQuery('#' + id).openModal();
	}-*/;
	
	public static native void closeModal()/*-{
		$wnd.jQuery('#' + id).closeModal();
	}-*/;

	
	
}
