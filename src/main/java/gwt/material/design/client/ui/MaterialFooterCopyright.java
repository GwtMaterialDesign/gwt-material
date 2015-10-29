package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.CustomDiv;
import gwt.material.design.client.custom.HasColors;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
* Footer specific zone for copyright text
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#footer">Material Footer</a>
*///@formatter:on
public class MaterialFooterCopyright extends ComplexWidget implements HasColors{

	CustomDiv container = new CustomDiv();
	
	public MaterialFooterCopyright() {
		setElement(Document.get().createDivElement());
		setStyleName("footer-copyright");
		container.setStyleName("container");
	}
	
	@Override
	public void add(Widget child) {
		container.add(child);
		super.add(container);
	}
	
	@Override
	public void setBackgroundColor(String bgColor) {
		// TODO Auto-generated method stub
		addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		// TODO Auto-generated method stub
		addStyleName(textColor + "-text");
	}
}
