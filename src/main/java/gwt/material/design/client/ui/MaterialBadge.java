package gwt.material.design.client.ui;

import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class MaterialBadge  extends ComplexPanel {
	
	private String type;
	private String text;
	private String color;
	private Label label;
	
	public MaterialBadge() {
		setElement(DOM.createElement("SPAN"));
		this.addStyleName("badge");
		this.addStyleName(MaterialResources.INSTANCE.materialcss().badge());
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
		this.addStyleName(type);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.label = new Label(text);
		add(label);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		this.addStyleName(color);
	}

}