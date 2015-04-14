package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MaterialFloating extends FocusPanel{

	
	private HTMLPanel panel;
	
	public MaterialFloating() {
		this.clear();
		this.getElement().getStyle().setBottom(10, Unit.PX);
		this.getElement().getStyle().setRight(10, Unit.PX);
		this.getElement().getStyle().setPosition(Position.FIXED);
		this.getElement().getStyle().setZIndex(999);
		panel = new  HTMLPanel("<a class='btn-floating btn-large waves-effect waves-light pink darken-4'><i class='mdi-content-add'></i></a>");
		this.add(panel);
	}
	
	
	
}
