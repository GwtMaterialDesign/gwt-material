package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasHref;

//@formatter:off
/**
* Collection element to define secondary content such as MaterialIcons
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#collections">Material Collections</a>
*///@formatter:on
public class MaterialCollectionSecondary extends ComplexWidget implements HasHref{

	public MaterialCollectionSecondary(){
		setElement(Document.get().createAnchorElement());
		setStyleName("secondary-content");
	}
	
	@Override
	public void setHref(String href) {
		getElement().setAttribute("href", href);
	}

	@Override
	public void setTarget(String target) {
		getElement().setAttribute("target", target);
	}

}
