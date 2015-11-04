package gwt.material.design.client.ui;

import gwt.material.design.client.base.ComplexWidget;
import gwt.material.design.client.ui.html.Div;

import com.google.gwt.dom.client.Document;

public class MaterialSpinner extends ComplexWidget{

	private Div circleClipperLeft = new Div();
	private Div circleClipperRight = new Div();
	private Div circle1 = new Div();
	private Div circle2 = new Div();
	private Div circle3 = new Div();
	private Div gapPatch = new Div();
	
	
	public MaterialSpinner() {
		super(Document.get().createDivElement());
		setStyleName("spinner-layer");
		add(circleClipperLeft);
		circleClipperLeft.add(circle1);
		add(gapPatch);
		gapPatch.add(circle2);
		add(circleClipperRight);
		circleClipperRight.add(circle3);
		
		circleClipperLeft.setStyleName("circle-clipper left");
		gapPatch.setStyleName("gap-patch");
		circleClipperRight.setStyleName("circle-clipper right");
		
		circle1.setStyleName("circle");
		circle2.setStyleName("circle");
		circle3.setStyleName("circle");
	}
	
	public MaterialSpinner(String color) {
		setColor(color);
	}
	
	public void setColor(String color){
		addStyleName("spinner-" + color);
	}
	
}
