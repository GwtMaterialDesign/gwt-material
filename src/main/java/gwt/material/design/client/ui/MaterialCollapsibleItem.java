package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ListItem;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCollapsibleItem extends ListItem{

	public MaterialCollapsibleItem() {
		// TODO Auto-generated constructor stub
	}
	
	@UiChild(tagname = "header")
	public void addHeader(final Widget header) {
		header.addStyleName("collapsible-header");
		this.add(header);
	}
	
	@UiChild(tagname = "content")
	public void addContent(final Widget content) {
		MaterialPanel panel = new MaterialPanel("");
		panel.addStyleName("collapsible-body");
		panel.add(content);
		panel.getElement().getStyle().setPadding(2, Unit.EM);
		panel.getElement().getStyle().setMargin(0, Unit.EM);
		this.add(panel);
	}
	
}
