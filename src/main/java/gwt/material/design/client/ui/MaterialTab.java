package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTab extends ComplexWidget implements HasColors, HasWaves{

	private int tabIndex;
	
	public MaterialTab() {
		setElement(Document.get().createULElement());
		setStyleName("tabs");
	}
	
	@Override
	public void onLoad() {
		String id = DOM.createUniqueId();
		getElement().setId(id);
		initTab(id);
	}
	
	private native void initTab(String id)/*-{
		$wnd.jQuery(document).ready(function(){
	    	$wnd.jQuery('ul#' + id).tabs();
	  	});
	}-*/;
	
	/**
	 * Line Indicator on Tab Navigation
	 * @param color Color string
	 */
	public native void changeIndicator(String color)/*-{
		$wnd.jQuery( ".indicator" ).css( "background-color", color );
	}-*/;

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
		int i = 0;
		for(Widget w : this){
			if(i == tabIndex){
				if(w.getStyleName().contains("tab")){
					w.addStyleName("active");
				}
			}
			i++;
		}
	}
	

	@Override
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor + "-text"); 
	}

	@Override
	public void setWaves(String waves) {
		// TODO Auto-generated method stub
		addStyleName("waves-effect waves-" + waves);
	}

	@Override
	public native void initWaves()/*-{
	    $wnd.Waves.displayEffect();
	}-*/;

	
}
