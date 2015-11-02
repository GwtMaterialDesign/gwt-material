package gwt.material.design.client.custom;

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

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaterialWindowHeader extends Composite {

	private static MaterialWindowHeaderUiBinder uiBinder = GWT.create(MaterialWindowHeaderUiBinder.class);

	interface MaterialWindowHeaderUiBinder extends UiBinder<Widget, MaterialWindowHeader> {}

	private HTMLPanel windowPanel;
	@UiField MaterialLabel lblTitle;
	@UiField MaterialPanel headerPanel;
	@UiField MaterialLink btnRestore, btnMaximize, btnClose;

	public MaterialWindowHeader(HTMLPanel windowPanel, String title, String headerColor) {
		initWidget(uiBinder.createAndBindUi(this));
		this.windowPanel = windowPanel;
		lblTitle.setText(title);
		headerPanel.addStyleName(headerColor);
		btnRestore.setVisible(false);
	}

	@UiHandler("btnMaximize")
	public void onMaximize(ClickEvent e){
		windowPanel.addStyleName("material-window-maximize");
		btnMaximize.setVisible(false);
		btnRestore.setVisible(true);
	}
	
	@UiHandler("btnRestore")
	public void onRestore(ClickEvent e){
		windowPanel.removeStyleName("material-window-maximize");
		btnMaximize.setVisible(true);
		btnRestore.setVisible(false);
	}
	
	@UiHandler("btnClose")
	public void onClose(ClickEvent e){
		MaterialModal.closeModal();
	}
}
