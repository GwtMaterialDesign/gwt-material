package gwt.material.design.client.ui;

import gwt.material.design.client.custom.CustomAnchor;
import gwt.material.design.client.custom.HasHref;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MaterialNavBrand extends CustomAnchor implements HasText, HasWidgets, HasHref {

	/**
	 * Material NavBrand is a component wherein you can pass a text / logo branding of your app
	 */
	@UiConstructor
	public MaterialNavBrand() {
		this.addStyleName("brand-logo");
	}

	/* (non-Javadoc)
	 * @see gwt.material.design.client.custom.CustomAnchor#add(com.google.gwt.user.client.ui.Widget)
	 */
	@Override
	public void add(Widget w) {
		// TODO Auto-generated method stub
		super.add(w);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		super.add(new HTML("" + text));
	}

	@Override
	public void setHref(String href) {
		this.getElement().setAttribute("href", href);
	}

	
}
