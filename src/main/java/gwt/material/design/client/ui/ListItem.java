package gwt.material.design.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class ListItem extends ComplexPanel {
	
	private String opacity;
	
	public ListItem() {
		setElement(DOM.createElement("LI"));
	}

	public ListItem(Widget item) {
		setElement(DOM.createElement("LI"));
		add(item);
	}

	public void add(Widget w) {
		super.add(w, getElement());
	}

	public void insert(Widget w, int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}

	public String getOpacity() {
		return opacity;
	}

	public void setOpacity(String opacity) {
		this.opacity = opacity;
		this.getElement().getStyle().setOpacity(Double.parseDouble(opacity));
	}

}