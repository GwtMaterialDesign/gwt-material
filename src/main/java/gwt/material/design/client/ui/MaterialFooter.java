package gwt.material.design.client.ui;

import gwt.material.design.client.custom.CustomFooter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialFooter extends Composite {

	private static MaterialFooterUiBinder uiBinder = GWT.create(MaterialFooterUiBinder.class);

	interface MaterialFooterUiBinder extends UiBinder<Widget, MaterialFooter> {
	}
	@UiField CustomFooter footerPanel;
	@UiField Label lblCopyright;
	@UiField MaterialPanel container;
	private String color = "";
	private String copyright = "";
	
	public MaterialFooter() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiChild(tagname = "content")
	public void onAddFooterContent(Widget w){
		container.add(w);
	}
	

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		if(!color.isEmpty()) footerPanel.addStyleName(color);
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
		if(!copyright.isEmpty()) lblCopyright.setText(copyright);
	}

}
