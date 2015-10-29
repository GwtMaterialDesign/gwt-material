package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;

public class MaterialSlideItem extends ComplexWidget{

	public MaterialSlideItem(){
		setElement(Document.get().createLIElement());
	}
	
}
