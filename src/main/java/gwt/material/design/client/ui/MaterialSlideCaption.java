package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasAlign;

import com.google.gwt.dom.client.Document;

public class MaterialSlideCaption extends ComplexWidget implements HasAlign{

	public MaterialSlideCaption() {
		setElement(Document.get().createDivElement());
		setStyleName("caption");
	}

	@Override
	public void setAlign(String align) {
		addStyleName(align + "-align");
	}
	
}
