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

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT Material provides an easy way for you to send unobtrusive alerts
 * to your users through toasts. These toasts are also placed and sized
 * responsively, try it out by clicking the button below on different
 * device sizes.
 */
public class MaterialToast {

	private Runnable callback;
	private Widget[] widgets;

	public MaterialToast(Widget... widgets) {
		this.widgets = widgets;
	}

	public MaterialToast(Runnable callback, Widget... widgets) {
		this.callback = callback;
		this.widgets = widgets;
	}

	/**
	 * @param msg Message text for your toast.
	 */
	public static void fireToast(String msg) {
		fireToast(msg, null);
	}

	/**
	 * Quick fire your toast.
	 * @param msg Message text for your toast.
	 * @param className class name to custom style your toast.
	 */
	public static void fireToast(String msg, String className) {
		new MaterialToast().toast(msg, className);
	}

	/**
	 * @param msg Message text for your toast.
	 */
	public void toast(String msg) {
		toast(msg, null);
	}

	/**
	 * @param msg Message text for your toast.
	 * @param className class name to custom style your toast.
	 */
	public void toast(String msg, String className) {
		String genId = DOM.createUniqueId();
		if(className == null) {
			className = genId;
		}
		toast(msg, genId, className);

		if(widgets != null) {
			for (Widget widget : widgets) {
				widget.getElement().getStyle().setPaddingLeft(30, Unit.PX);
				RootPanel.get(genId).add(widget);
			}
		}
	}

	private static native void toast(String msg, String id, String className)/*-{
        var that = this;
        $wnd.Materialize.toast(msg, 4000, className, function() {
            if(that.callback != null) {
                that.callback.@java.lang.Runnable::run()();
            }
        });
        $wnd.jQuery(".toast." + className).attr('id',  id);
    }-*/;
}
