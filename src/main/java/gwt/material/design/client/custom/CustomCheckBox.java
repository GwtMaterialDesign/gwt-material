package gwt.material.design.client.custom;

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class CustomCheckBox  extends ComplexPanel {
	
	private String type="";
	
	public CustomCheckBox() {
		setElement(DOM.createElement("INPUT"));
		this.getElement().setAttribute("type", "checkbox");
	}

	@UiChild(tagname = "child")
	public void addWidget(final Widget item) {
		add(item);
	}
	
	public void add(Widget w) {
		super.add(w, getElement());
	}

	public void insert(Widget w, int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.getElement().setAttribute("type", type);
	}

}