package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;

public class MaterialTab extends ComplexWidget{

	public MaterialTab() {
		setElement(Document.get().createULElement());
		setStyleName("tabs");
	}
	
}
