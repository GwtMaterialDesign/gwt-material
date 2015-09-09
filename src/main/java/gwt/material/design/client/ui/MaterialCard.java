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

import gwt.material.design.client.custom.HasGrid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCard extends Composite implements HasGrid{

	private static MaterialCardUiBinder uiBinder = GWT
			.create(MaterialCardUiBinder.class);

	interface MaterialCardUiBinder extends UiBinder<Widget, MaterialCard> {
	}

	@UiField
	Image imgCard;
	
	@UiField 
	Label lblTitle, lblDescription;
	
	@UiField 
	HTMLPanel panel, cardPanel, cardContentPanel, cardRevealPanel,cardRevealContent, actionPanel, headerPanel;
	
	private ImageResource resource;
	private String url = "";
	private String title="";
	private String description = "";
	private String size = "medium";
	private String type = "";
	private String imageHeight = "";
	private String color = "";
	
	public MaterialCard() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ImageResource getResource() {
		return resource;
	}

	public void setResource(ImageResource resource) {
		this.resource = resource;
		imgCard.setResource(resource);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		imgCard.setUrl(url);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		generateCard();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		lblDescription.setText(description);
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
		if(!size.isEmpty()){
			cardPanel.addStyleName(size);
		}
	}

	public String getType() {
		return type;
	}
	
	@UiChild( tagname = "link" )
	public void addWidget(Widget item) {
		item.getElement().getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
		item.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		actionPanel.add(item);
	} 
	
	@UiChild( tagname = "reveal" )
	public void addRevealContent(Widget item){
		cardRevealContent.add(item);
	}
	
	@UiChild( tagname = "header")
	public void addHeader(Widget item){
		headerPanel.add(item);
	} 
	
	@UiChild( tagname = "content")
	public void addContent(Widget item){
		cardContentPanel.add(item);
	} 

	public void setType(String type) {
		this.type = type;
		generateCard();
	}
	
	private void generateCard(){
		String textColor = "grey";
		if(!color.isEmpty()){ 
			cardPanel.addStyleName(color);
			textColor = "white";
		}
		if(!color.isEmpty()) actionPanel.addStyleName(color + " darken-1");
		
		switch (type) {
		case "reveal":
		case "chart":
			cardContentPanel.clear();
			cardRevealPanel.clear();
			lblTitle.removeFromParent();
			cardContentPanel.add(new HTML("<span class='card-title activator "+textColor+"-text text-darken-4'>"+title+"<i class='mdi-navigation-more-vert right'></i></span>"));
			cardContentPanel.getElement().getStyle().setPaddingBottom(0, Unit.PX);
			cardRevealPanel.add(new HTML("<span class='card-title activator grey-text text-darken-4'>"+title+"<i class='mdi-navigation-close right'></i></span>"));
			cardRevealPanel.add(lblDescription);
			cardRevealPanel.add(cardRevealContent);
			break;
		case "basic":
			cardContentPanel.clear();
			cardRevealPanel.removeFromParent();
			cardContentPanel.add(new HTML("<span class='card-title "+textColor+"-text'>"+title+"</span>"));
			lblDescription.addStyleName(textColor+"-text");
			cardContentPanel.add(lblDescription);
			headerPanel.removeFromParent();
			if(actionPanel.getWidgetCount()==0) actionPanel.removeFromParent();
			break;
		case "image":
			cardRevealPanel.removeFromParent();
			cardRevealPanel.removeFromParent();
			lblTitle.setText(title);
			lblDescription.setText(description);
			break;
		default:
			break;
		}
	}

	public Image getImgCard() {
		return imgCard;
	}

	public void setImgCard(Image imgCard) {
		this.imgCard = imgCard;
	}

	public String getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
		imgCard.setHeight(imageHeight + "vh");
	}

	public HTMLPanel getCardContentPanel() {
		return cardContentPanel;
	}

	public void setCardContentPanel(HTMLPanel cardContentPanel) {
		this.cardContentPanel = cardContentPanel;
	}

	public HTMLPanel getCardRevealContent() {
		return cardRevealContent;
	}

	public void setCardRevealContent(HTMLPanel cardRevealContent) {
		this.cardRevealContent = cardRevealContent;
	}

	public HTMLPanel getActionPanel() {
		return actionPanel;
	}

	public void setActionPanel(HTMLPanel actionPanel) {
		this.actionPanel = actionPanel;
	}

	public HTMLPanel getHeaderPanel() {
		return headerPanel;
	}

	public void setHeaderPanel(HTMLPanel headerPanel) {
		this.headerPanel = headerPanel;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		generateCard();
	}

	
	/**
	 * Setting the column grid for responsive design
	 * @param grid the grid to set
	 */
	public void setGrid(String grid) {
		panel.addStyleName("col " + grid);
	}

	
	
}
