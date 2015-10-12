package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;

//@formatter:off
/**
* Card Element for revealed content once title is triggered. 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#cards">Material Cards</a>
*/

//@formatter:on
public class MaterialCardReveal extends ComplexWidget{

	public MaterialCardReveal(){
		setElement(Document.get().createDivElement());
		setStyleName("card-reveal");
	}
	
	
}