package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialNoResult extends Composite {

	private static MaterialNoResultUiBinder uiBinder = GWT
			.create(MaterialNoResultUiBinder.class);

	interface MaterialNoResultUiBinder extends
			UiBinder<Widget, MaterialNoResult> {
	}

	@UiField MaterialIcon iconElem;
	@UiField MaterialTitle titleElem;
	@UiField MaterialPanel panel;
	
	private String color = "";
	private String textColor="";
	private String icon="";
	private String title = "";
	private String description = "";
	
	public MaterialNoResult() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	

	public MaterialNoResult(String color, String textColor, String icon,
			String title, String description) {
		initWidget(uiBinder.createAndBindUi(this));
		setColor(color);
		setTextColor(textColor);
		setIcon(icon);
		setTitle(title);
		setDescription(description);;
	}



	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		panel.setColor(color);
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		iconElem.setTextColor(textColor);
		titleElem.setColor(textColor);
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		iconElem.setIcon(icon);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		titleElem.setTitle(title);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		titleElem.setDescription(description);
	}

	
}
