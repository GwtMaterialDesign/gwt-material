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


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialChip extends Composite implements HasClickHandlers {

	private static MaterialChipUiBinder uiBinder = GWT
			.create(MaterialChipUiBinder.class);

	interface MaterialChipUiBinder extends UiBinder<Widget, MaterialChip> {
	}

	@UiField
	Label lblChip;
	@UiField
	Image imgChip;
	@UiField
	MaterialIcon btnClose;
	@UiField
	MaterialPanel chipPanel;

	private String text;
	private String imageUrl = "";
	private ImageResource imageResource;

	public MaterialChip() {
		initWidget(uiBinder.createAndBindUi(this));
		imgChip.getElement().getStyle().setDisplay(Display.NONE);
	}

	/**
	 * Chips represent complex entities in small blocks, such as a contact. They
	 * can contain a photo, short title string, and brief information.
	 * 
	 * @param text
	 *            - Default chip with text only
	 */
	public MaterialChip(String text) {
		initWidget(uiBinder.createAndBindUi(this));
		imgChip.getElement().getStyle().setDisplay(Display.NONE);
		setText(text);
	}

	/* *
	 * Chips represent complex entities in small blocks, such as a contact. They
	 * can contain a photo, short title string, and brief information.
	 * 
	 * @param text - Chip text
	 * 
	 * @param imageUrl - Image addition into your chip. It can be used on
	 * Contact Chips
	 */
	public MaterialChip(String text, String imageUrl) {
		initWidget(uiBinder.createAndBindUi(this));
		imgChip.getElement().getStyle().setDisplay(Display.NONE);
		setText(text);
		setImageUrl(imageUrl);
	}

	/**
	 * @return the lblChip
	 */
	public Label getLblChip() {
		return lblChip;
	}

	/**
	 * @return the imgChip
	 */
	public Image getImgChip() {
		return imgChip;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
		lblChip.setText(text);
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		imgChip.getElement().getStyle().setDisplay(Display.INLINE);
		getImgChip().setUrl(imageUrl);
	}

	/**
	 * @return the imageResource
	 */
	public ImageResource getImageResource() {
		return imageResource;
	}

	/**
	 * @param imageResource
	 *            the imageResource to set
	 */
	public void setImageResource(ImageResource imageResource) {
		this.imageResource = imageResource;
		imgChip.getElement().getStyle().setDisplay(Display.INLINE);
		getImgChip().setResource(imageResource);
	}

	/**
	 * @return the btnClose
	 */
	public MaterialIcon getBtnClose() {
		return btnClose;
	}

	/**
	 * Will remove the chip from its parent.
	 * 
	 * @param e
	 */
	@UiHandler("btnClose")
	void onRemoveChip(ClickEvent e) {
		this.removeFromParent();
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	/**
	 * @return the chipPanel
	 */
	public MaterialPanel getChipPanel() {
		return chipPanel;
	}

	/**
	 * @param chipPanel
	 *            the chipPanel to set
	 */
	public void setChipPanel(MaterialPanel chipPanel) {
		this.chipPanel = chipPanel;
	}

}
