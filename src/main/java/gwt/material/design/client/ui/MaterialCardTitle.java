package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.CustomSpan;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasIcons;

import com.google.gwt.dom.client.Document;

//@formatter:off
/**
* Card Element for card title. 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#cards">Material Cards</a>
*/

//@formatter:on
public class MaterialCardTitle extends ComplexWidget implements HasIcons, HasColors{

	private MaterialIcon iconElem = new MaterialIcon();
	private CustomSpan spanElem = new CustomSpan();
	private String text;
	
	public MaterialCardTitle(){
		setElement(Document.get().createSpanElement());
		setStyleName("card-title activator");
	}

	@Override
	public void setIcon(String icon) {
		iconElem.setIcon(icon);
		add(iconElem);
	}

	@Override
	public void setIconPosition(String iconPosition) {
		iconElem.setIconPosition(iconPosition);
	}

	@Override
	public void setSize(String size) {
		iconElem.addStyleName(size);
	}

	@Override
	public void setIconColor(String iconColor) {
		iconElem.addStyleName(iconColor + "-text");
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		spanElem.setText(text);
		add(spanElem);
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor + "-text");
	}

}
