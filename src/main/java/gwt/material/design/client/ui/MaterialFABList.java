package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class MaterialFABList extends ComplexWidget{

	public MaterialFABList() {
		setElement(Document.get().createULElement());
	}
	
	@Override
	public void add(Widget child) {
		ListItem item = new ListItem(child);
		super.add(item);
	}
	
}
