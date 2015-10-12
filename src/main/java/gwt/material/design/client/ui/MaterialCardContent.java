package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasColors;

import com.google.gwt.dom.client.Document;

//@formatter:off
/**
* Card Element for contents. 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#cards">Material Cards</a>
*/

//@formatter:on
public class MaterialCardContent extends ComplexWidget implements HasColors{

	public MaterialCardContent(){
		super();
		setElement(Document.get().createDivElement());
		setStyleName("card-content");
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
