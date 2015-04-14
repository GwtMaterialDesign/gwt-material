package gwt.material.design.client.ui;

import gwt.material.design.client.custom.CustomCheckBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSwitch extends Composite implements HasChangeHandlers {

	private static MaterialSwitchUiBinder uiBinder = GWT.create(MaterialSwitchUiBinder.class);

	interface MaterialSwitchUiBinder extends UiBinder<Widget, MaterialSwitch> {
	}
	
	@UiField CustomCheckBox cbBox;
	private Boolean value = false;
	private boolean disabled;
	public MaterialSwitch() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		cbBox.getElement().setId("switch");
	}

	public Boolean getValue() {
		if(value){
			value = false;
			return false;
		}
		else {
			value = true;
			return true	;
		}
	}
	
	/*public native String getCheckBoxValue()-{
		return $wnd.jQuery('#switch').val();
	}-;*/

	public void setValue(Boolean value) {
		this.value = value;
		setInputValue(true);
	}

	public static native void setInputValue(Boolean real)/*-{
		$wnd.jQuery('#switch').prop('checked', real);
	}-*/;

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		// TODO Auto-generated method stub
		return addDomHandler(handler, ChangeEvent.getType());
	}


	public boolean isDisabled() {
		return disabled;
	}


	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		cbBox.getElement().setAttribute("disabled", "true");
	}

	
	
}
