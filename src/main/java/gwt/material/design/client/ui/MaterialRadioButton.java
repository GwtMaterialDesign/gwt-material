package gwt.material.design.client.ui;

import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.RadioButton;

public class MaterialRadioButton extends RadioButton{

	private String type ="";
	private boolean disabled;
	
	public MaterialRadioButton() {
		// TODO Auto-generated constructor stub
		super("");
	}

	public MaterialRadioButton(String name, SafeHtml label, Direction dir) {
		super(name, label, dir);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, SafeHtml label, DirectionEstimator directionEstimator) {
		super(name, label, directionEstimator);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, SafeHtml label) {
		super(name, label);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, String label, boolean asHTML) {
		super(name, label, asHTML);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, String label, Direction dir) {
		super(name, label, dir);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, String label, DirectionEstimator directionEstimator) {
		super(name, label, directionEstimator);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name, String label) {
		super(name, label);
		// TODO Auto-generated constructor stub
	}

	public MaterialRadioButton(String name) {
		super(name);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.addStyleName("with-" + type);
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		setEnabled(!disabled);
	}
	

	
}
