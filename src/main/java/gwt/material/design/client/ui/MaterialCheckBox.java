package gwt.material.design.client.ui;

import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.CheckBox;

public class MaterialCheckBox extends CheckBox implements HasClickHandlers{

	private Object object;
	private String old;
	private boolean disabled;
	private String type;
	
	public MaterialCheckBox() {
		// TODO Auto-generated constructor stub
		this.getElement().getStyle().setMarginRight(20, Unit.PX);
	}

	public MaterialCheckBox(Element elem) {
		super(elem);
		// TODO Auto-generated constructor stub
	}

	public MaterialCheckBox(SafeHtml label, Direction dir) {
		super(label, dir);
		// TODO Auto-generated constructor stub
	}

	public MaterialCheckBox(SafeHtml label, DirectionEstimator directionEstimator) {
		super(label, directionEstimator);
		// TODO Auto-generated constructor stub
	}

	public MaterialCheckBox(SafeHtml label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public MaterialCheckBox(String label, boolean asHTML) {
		super(label, asHTML);
		// TODO Auto-generated constructor stub
	}

	public MaterialCheckBox(String label, Direction dir) {
		super(label, dir);
		// TODO Auto-generated constructor stub
	}

	public MaterialCheckBox(String label, DirectionEstimator directionEstimator) {
		super(label, directionEstimator);
		// TODO Auto-generated constructor stub
	}

	public MaterialCheckBox(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
	

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.getElement().getStyle().setDisplay(Display.BLOCK);
		this.getElement().getStyle().setMarginBottom(3, Unit.PCT);
	}

	public String getOld() {
		return old;
	}

	public void setOld(String old) {
		this.old = old;
		this.addStyleName(MaterialResources.INSTANCE.materialcss().oldCheckBox());
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		this.setEnabled(!disabled);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(type.equalsIgnoreCase("indeterminate")){
			this.addStyleName(type + "-checkbox");
		}else if(type.equalsIgnoreCase("filled")){
			this.addStyleName(type + "-in");
		}else{
			this.addStyleName(type);
		}
		
	}

}
