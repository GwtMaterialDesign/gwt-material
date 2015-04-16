package gwt.material.design.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface MaterialResources extends ClientBundle {
	public static final MaterialResources INSTANCE = GWT
			.create(MaterialResources.class);
	
	@Source("image.png")
	ImageResource image();

	@Source("ic_progress_cancel.png")
	ImageResource ic_progress_cancel();

	@Source("jquery.js")
	TextResource jQuery();

	@Source("materialize.min.js")
	TextResource materializeJs();
	
	@Source("materialcss.gss")
	MaterialCSS materialcss();

	@Source("materialmobilecss.gss")
	MaterialMobileCSS materialmobilecss();

}
