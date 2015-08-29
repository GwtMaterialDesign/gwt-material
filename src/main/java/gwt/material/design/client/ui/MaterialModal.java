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

import gwt.material.design.client.custom.MaterialWindowHeader;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaterialModal extends Widget {

	private static HTMLPanel panel = new HTMLPanel("");

	private boolean fixedFooter;

	/**
	 * Types of modals to be applied when calling the modal.
	 * Fixed Footer - The action buttons are always displayed on the bottom part of modal
	 * Bottom Sheet - A new modal style wherein it will display on the bottom screen
	 * Default - Default modal without fixed footer
	 * Window - Advance modal with MaterialNavBar and MaterialTabs implementation
	 * @author kevzlou7979
	 *
	 */
	public enum TYPE {
		FIXED_FOOTER, BOTTOM_SHEET, DEFAULT, WINDOW;
	}

	/**
	 * Show the modal with composite as a content of the modal
	 * @param type - Type of modal: FIXED_FOOTER, BOTTOM_SHEET, DEFAULT, WINDOW
	 * @param composite - the content of the modal could be any kind of widgets
	 * @param autoHideEnabled - set true if you want to be your modal closable when clicking outside
	 */
	public static void showModal(Widget composite, TYPE type, boolean autoHideEnabled) {
		panel.clear();
		confirmType(type);
		panel.add(composite);
		RootPanel.get().add(panel);
		showModal(panel.getElement().getId(), autoHideEnabled);
	}

	/**
	 * Show the window with composite as a content of the modal
	 * Its an advance modal with advance features to be added inside like MaterialNavBar, MaterialTabs etc.
	 * @param composite - the content of the modal could be any kind of widgets 
	 * @param type - Type of modal:  WINDOW
	 * @param title - Window title of the modal
	 * @param headerColor - the color theme of the modal 
	 * @param autoHideEnabled - set true if you want to be your modal closable when clicking outside
	 */
	public static void showWindow(Widget composite, TYPE type, String title, String headerColor, boolean autoHideEnabled) {
		panel.clear();
		switch (type) {
			case WINDOW:
				panel.getElement().setId("modal1");
				panel.setStyleName("modal material-window");
				panel.getElement().getStyle().clearBottom();
				panel.add(new MaterialWindowHeader(panel, title, headerColor));
				break;
			default:
				break;
		}
		panel.add(composite);
		RootPanel.get().add(panel);
		showModal(panel.getElement().getId(), autoHideEnabled);
	}

	/**
	 * Setting the modal type for styles and behaviours
	 * @param type
	 */
	private static void confirmType(TYPE type) {
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
		case DEFAULT:
			panel.getElement().setId("modal");
			panel.setStyleName("modal");
			break;
		default:
			break;
		}
	}

	

	/**
	 * Hide the modal 
	 */
	public void hide() {
		panel.getElement().removeAttribute("style");
		closeModal();
	}

	/**
	 * Show the modal with provided id
	 * @param id
	 * @param autoHideEnabled
	 */
	private static native void showModal(String id, boolean autoHideEnabled)/*-{
													$wnd.jQuery('#' + id).openModal({
														dismissible: autoHideEnabled
													});
													
													}-*/;

	/**
	 * Closing the modal with provided id
	 * @param id
	 */
	public static native void closeModal(String id)/*-{
													$wnd.jQuery('#' + id).closeModal();
													}-*/;

	/**
	 * Close the modal
	 */
	public static void closeModal() {
		closeModal(panel.getElement().getId());
	}



	
	
}
