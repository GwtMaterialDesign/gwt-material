package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCollapsibleBody extends ComplexWidget{
	
	/**
	 * Creates empty collapsible body
	 */
	public MaterialCollapsibleBody(){
		setElement(Document.get().createDivElement());
		setStyleName("collapsible-body");
	}
	
	/**
	 * Add other components into collapsible body
	 * @param widgets
	 */
	public MaterialCollapsibleBody(final Widget... widgets){
		this();
		for(Widget w : widgets){
			add(w);
		}
	}
	
}
