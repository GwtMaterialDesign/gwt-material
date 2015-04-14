package gwt.material.design.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface MaterialResources extends ClientBundle {
	public static final MaterialResources INSTANCE = GWT
			.create(MaterialResources.class);
	
	ImageResource image();
	ImageResource ic_progress_cancel();
	MaterialCSS materialcss();
	MaterialMobileCSS materialmobilecss();

}
