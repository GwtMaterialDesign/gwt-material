package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.user.client.ui.Label;

public class MaterialLabel extends Label {
	
	String fontSize = "";
	String textColor = "";

	public MaterialLabel() {
		// TODO Auto-generated constructor stub
	}

	public MaterialLabel(Element element) {
		super(element);
		// TODO Auto-generated constructor stub
	}

	public MaterialLabel(String text, boolean wordWrap) {
		super(text, wordWrap);
		// TODO Auto-generated constructor stub
	}

	public MaterialLabel(String text, Direction dir) {
		super(text, dir);
		// TODO Auto-generated constructor stub
	}

	public MaterialLabel(String text, DirectionEstimator directionEstimator) {
		super(text, directionEstimator);
		// TODO Auto-generated constructor stub
	}

	public MaterialLabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.getElement().getStyle().setOpacity(0.71);
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		this.getElement().getStyle().setFontSize(Double.valueOf(fontSize), Unit.EM);
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		this.addStyleName(textColor + "-text");
	}

	
	
}
