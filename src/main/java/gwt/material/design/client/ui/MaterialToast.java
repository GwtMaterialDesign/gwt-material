package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.RootPanel;


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

/**
 * GWT Material provides an easy way for you to send unobtrusive alerts to your users through toasts. These toasts are also placed and sized responsively, try it out by clicking the button below on different device sizes.
 */
public class MaterialToast {
	
	
	
	public MaterialToast() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Provides a simple toast display for a specific message
	 * @param msg - Message text for your toast
	 */
	public static native void alert(String msg) /*-{
		 $wnd.Materialize.toast(msg, 4000);
		 $wnd.jQuery(".toast").attr('id',  'toast');
	}-*/;

	/**
	 * Provides an advance toast with any widget (MaterialLink) for declaration of actions when toast is applied.
	 * @param msg - Message text for your toast
	 * @param link - A MaterialLink Widget to have some specific function when clicked.
	 */
	public static void alert(String msg, MaterialLink link) {
		alert(msg);
		link.getElement().getStyle().setPaddingLeft(30, Unit.PX);
		RootPanel.get("toast").add(link);
	}
	
	/**
	 * Provides specific style in your toast for better clarity e.g Rounded , any class name is acceptable for some specific design. Just add it in to
	 * parameter type.
	 * @param msg - Message text for your toast
	 * @param type - Specific class name for styling your toast
	 */
	public static native void alert(String msg, String type) /*-{
		 $wnd.Materialize.toast(msg, 4000, type);
		 $wnd.jQuery(".toast").attr('id',  'toast');
	}-*/;
	
	
}
