package gwt.material.design.client.ui;

import gwt.material.design.client.custom.HasType;
import gwt.material.design.client.custom.HasWaves;
import gwt.material.design.client.custom.SideNavType;

import java.util.Iterator;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSideNav extends UnorderedList implements HasWidgets, HasType, HasWaves{

	
	
	
	private String name;
	
	/**
	 * SideNav is a material component that gives you a lists of menus and other navigation components.
	 * @param width
	 * @param name
	 */
	@UiConstructor
	public MaterialSideNav(int width, String name, boolean closeOnClick) {
		super.getElement().setId(name);
		addStyleName("side-nav");
		getElement().getStyle().setWidth(width, Unit.PX);
		setName(name);
		initNavBar(width, name, closeOnClick);
	}
	
	@Override
	public void add(Widget w) {
		
		ListItem item = new ListItem(w);
		if(w instanceof MaterialImage){
			item.getElement().getStyle().setProperty("border", "1px solid #e9e9e9");
			item.getElement().getStyle().setProperty("textAlign", "center");
		}
		super.add(item);
	}

	@Override
	public void clear() {
		this.clear();
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
	
	private static native void initNavBar(int width, String name, boolean closeOnClick)/*-{
		$wnd.jQuery( document ).ready(function(){
			$wnd.jQuery(".button-collapse").sideNav({
				menuWidth: width,
				closeOnClick: closeOnClick
			});
		}) 
	}-*/;

	/**
	 * Set the type of the sideBar
	 * OPEN, CLOSE, MINI, CLIP 
	 */
	@Override
	public void setType(String type) {
		SideNavType type2 = SideNavType.fromString(type);
		switch (type2) {
		case OPEN:
			addStyleName("fixed open");
			break;
		case CLIP:
			addStyleName("fixed clip");
			break;
		case MINI:
			addStyleName("fixed mini");
			showNavMenu(name);
			break;
		case FLOAT:
			addStyleName("fixed clip float");
			break;
		case CARD:
			addStyleName("fixed clip card");
			break;
		case CLOSE:
		default:
			addStyleName("close");
			showNavMenu(name);
			break;
		}
	}

	/**
	 * Show the NavMenu
	 * @param sideNav
	 */
	public native void showNavMenu(String sideNav)/*-{
		$wnd.jQuery( document ).ready(function(){
			$wnd.jQuery("." + sideNav).addClass('show-on-large');
		})
	}-*/;
	
	/**
	 * Hide the overlay menu
	 */
	public native void hideOverlay(String sideNav)/*-{
		$wnd.jQuery( document ).ready(function(){
			$wnd.jQuery('#sidenav-overlay').css('z-index', '994');
		})
	}-*/;
	
	/**
	 * Shoe the sidenav
	 * @param sidenav
	 */
	public native void show(String sideNav)/*-{
		$wnd.jQuery("." + sideNav).sideNav('show');
	}-*/;
	
	/**
	 * Hide the sidenav
	 * @param sideNav - the name of your sidenav
	 */
	public native void hide(String sideNav)/*-{
		$wnd.jQuery("." + sideNav).sideNav('hide');
	}-*/;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void setWaves(String waves) {
		for(Widget w : this){
			if(w instanceof ListItem){
				w.addStyleName(waves + " waves-effect");
			}
		}
	}
	
	@Override
	public native void initWaves() /*-{
	    $wnd.Waves.displayEffect();
	}-*/;
}
