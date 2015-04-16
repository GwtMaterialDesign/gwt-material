package gwt.material.design.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class UnorderedList extends ComplexPanel {
	
	private String ulId = "";
	
	public UnorderedList() {
		setElement(DOM.createElement("UL"));
	}

	public void add(Widget w) {
		super.add(w, getElement());
	}

	public void insert(Widget w, int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}

	public String getUlId() {
		return ulId;
	}

	public void setUlId(String ulId) {
		this.ulId = ulId;
		this.getElement().setId(ulId);
	}

	

}
