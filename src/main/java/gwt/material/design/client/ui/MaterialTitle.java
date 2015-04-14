package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTitle extends Composite {

	private static MaterialTitleUiBinder uiBinder = GWT
			.create(MaterialTitleUiBinder.class);

	interface MaterialTitleUiBinder extends UiBinder<Widget, MaterialTitle> {
	}

	private String title="";
	private String description = "";
	private String color="";
	private MaterialTitle materialTitle;
	private double fontSize;
	
	@UiField HTMLPanel titlePanel;
	@UiField Label lblTitle, lblDescription;
	
	public MaterialTitle(String title, String description){
		initWidget(uiBinder.createAndBindUi(this));
		setTitle(title);
		setDescription(description);
	}
	
	public MaterialTitle(String title){
		initWidget(uiBinder.createAndBindUi(this));
		setTitle(title);
	}
	
	public MaterialTitle() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		lblDescription.setText(description);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		lblTitle.setText(title);
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		lblTitle.getElement().getStyle().setColor(color);
		lblDescription.getElement().getStyle().setColor(color);
	}

	public MaterialTitle getMaterialTitle() {
		return materialTitle;
	}

	public void setMaterialTitle(MaterialTitle materialTitle) {
		this.materialTitle = materialTitle;
	}



	public double getFontSize() {
		return fontSize;
	}



	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
		lblTitle.getElement().getStyle().setFontSize(fontSize, Unit.EM);
	}
	
	

}
