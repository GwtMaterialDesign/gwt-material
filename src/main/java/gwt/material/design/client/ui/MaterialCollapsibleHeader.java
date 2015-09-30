package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCollapsibleHeader extends ComplexWidget{
	
	/**
	 * Creates empth collapsible header
	 */
	public MaterialCollapsibleHeader() {
		setElement(Document.get().createDivElement());
		setStyleName("collapsible-header");
	}
	
	/**
	 * Adds other components as header
	 * @param widgets
	 */
	public MaterialCollapsibleHeader(final Widget... widgets){
		this();
		for(Widget w : widgets){
			add(w);
		}
	}

	
}
