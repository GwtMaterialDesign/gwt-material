package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasDismissable;
import gwt.material.design.client.type.CollectionType;

import com.google.gwt.dom.client.Document;

//@formatter:off
/**
* Collection element to define every items
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#collections">Material Collections</a>
*///@formatter:on
public class MaterialCollectionItem extends ComplexWidget implements HasColors, HasDismissable{

	public MaterialCollectionItem() {
		setElement(Document.get().createLIElement());
		setStyleName("collection-item");
	}
	
	public void setType(CollectionType type){
		addStyleName(type.getValue());
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor + "-text");
	}

	@Override
	public void setDismissable(boolean dismissable) {
		if(dismissable)addStyleName("dismissable");
		else removeStyleName("dismissable");
	} 
	
}
