package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialScrollspy extends Composite {

	private static MaterialScrollspyUiBinder uiBinder = GWT.create(MaterialScrollspyUiBinder.class);

	interface MaterialScrollspyUiBinder extends UiBinder<Widget, MaterialScrollspy> {
	}
	
	@UiField UnorderedList ulPanel;

	private String color = "";
	
	public MaterialScrollspy() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	
	@UiChild(tagname = "item")
	public void onAddItem(Widget w){
		ListItem item = new ListItem(w);
		ulPanel.add(item);
	}
	
	
	
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		initScrollspy();
	}

	static native void initScrollspy()/*-{
		$wnd.jQuery(".scrollspy").scrollSpy();
	}-*/;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
