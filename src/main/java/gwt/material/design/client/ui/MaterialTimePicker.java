package gwt.material.design.client.ui;

import gwt.material.design.client.custom.CustomInput;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTimePicker extends Composite {

	private static MaterialTimePickerUiBinder uiBinder = GWT
			.create(MaterialTimePickerUiBinder.class);

	interface MaterialTimePickerUiBinder extends
			UiBinder<Widget, MaterialTimePicker> {
	}

	@UiField CustomInput inputElement;
	private String time;
	private String placeholder;

	public MaterialTimePicker() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		String genId = DOM.createUniqueId();
		inputElement.getElement().setAttribute("type", "text");
		inputElement.getElement().setAttribute("id", genId);
		initTimePicker(genId);
	}

	public native void initTimePicker(String id)/*-{
		$wnd.jQuery('#' + id).lolliclock({autoclose:false});
	}-*/;

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
		inputElement.getElement().setAttribute("value", time.toUpperCase());
	}

	/**
	 * @return the placeholder
	 */
	public String getPlaceholder() {
		return placeholder;
	}

	/**
	 * @param placeholder the placeholder to set
	 */
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		inputElement.getElement().setAttribute("placeholder", "Time");
	}

	
}
