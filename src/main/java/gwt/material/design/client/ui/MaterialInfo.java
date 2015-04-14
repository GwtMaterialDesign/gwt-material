package gwt.material.design.client.ui;


import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class MaterialInfo {

	
	public MaterialInfo() {
		
	}
	
	public static void showInfo(HTMLPanel panel, ImageResource resource, String message){
		panel.clear();
		
		HTMLPanel container = new HTMLPanel("");
		container.addStyleName("materialInfo");
		Label label = new Label(message);
		container.add(new Image(resource));
		container.add(label);
		panel.add(container);
		
	}
	
}
