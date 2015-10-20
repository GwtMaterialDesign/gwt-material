package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasDismissable;
import gwt.material.design.client.custom.HasWaves;
import gwt.material.design.client.type.CollectionType;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
* Collection element to define every items
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#collections">Material Collections</a>
*///@formatter:on
public class MaterialCollectionItem extends ComplexWidget implements HasColors, HasDismissable, HasWaves, HasClickHandlers{

	public MaterialCollectionItem() {
		setElement(Document.get().createLIElement());
		setStyleName("collection-item");
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		initWaves();
	}

	public void setType(CollectionType type){
		switch (type) {
		case AVATAR:
			addStyleName(type.getValue());
			break;
		case CHECKBOX:
			if(getWidgetCount()> 0) getWidget(0).getElement().getStyle().setProperty("display" , "inline");
			addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					for(Widget w : MaterialCollectionItem.this){
						if(w instanceof MaterialCollectionSecondary){
							for(Widget a : (MaterialCollectionSecondary)w){
								if(a instanceof MaterialCheckBox){
									MaterialCheckBox  cb = (MaterialCheckBox)a;
									if(cb.getValue()){
										cb.setValue(false);
									}else{
										cb.setValue(true);
									}
								}
							}
						}
					}
				}
			});
			break;
		default:
			break;
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
	public void setDismissable(boolean dismissable) {
		if(dismissable)addStyleName("dismissable");
		else removeStyleName("dismissable");
	}

	@Override
	public void setWaves(String waves) {
		addStyleName("waves-effect waves-" + waves);
	}

	@Override
	public native void initWaves()/*-{
	    $wnd.Waves.displayEffect();
	}-*/;

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}
	
}
