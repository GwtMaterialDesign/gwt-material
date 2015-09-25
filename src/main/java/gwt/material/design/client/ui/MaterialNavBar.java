package gwt.material.design.client.ui;

import gwt.material.design.client.custom.CustomAnchor;
import gwt.material.design.client.custom.CustomHeader;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasType;
import gwt.material.design.client.custom.NavBarType;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MaterialNavBar extends Composite implements HasWidgets, HasColors, HasType{

	private static NavBarUiBinder uiBinder = GWT.create(NavBarUiBinder.class);

	interface NavBarUiBinder extends UiBinder<Widget, MaterialNavBar> {
	}
	
	@UiField HTMLPanel panel;
	@UiField CustomAnchor navMenu;
	@UiField CustomHeader header;

	/**
	 * Nav Bar is a component which contains your app tool bar and app sidebar
	 */
	@UiConstructor
	public MaterialNavBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/**
	 * Set the sidebar name to activate it
	 * @param sideBar
	 */
	public void setSideBar(String sideBar){
		navMenu.addStyleName(sideBar);
		navMenu.getElement().setAttribute("data-activates", sideBar);
	}
	
	/**
	 * Will add a child widget into your nav bar
	 */
	@Override
	public void add(Widget w) {
		panel.add(w);
	}

	/**
	 * This will clear all the child of your nav bar
	 */
	@Override
	public void clear() {
		panel.clear();
	}

	@Override
	public Iterator<Widget> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Widget w) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		panel.addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		panel.addStyleName(textColor + "-text");
	}


	@Override
	public void setType(String type) {
		NavBarType navType = NavBarType.fromString(type);
		switch (navType) {
		case FIXED:
			header.addStyleName("navbar-fixed");
			break;
		case TALL:
			panel.addStyleName("navbar-tall");
			break;
		default:
			break;
		}
	}

}
