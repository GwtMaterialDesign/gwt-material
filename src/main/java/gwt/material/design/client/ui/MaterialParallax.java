package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class MaterialParallax extends Composite {

	private static MaterialParallaxUiBinder uiBinder = GWT.create(MaterialParallaxUiBinder.class);

	interface MaterialParallaxUiBinder extends UiBinder<Widget, MaterialParallax> {
	}

	@UiField
	MaterialRow container;
	@UiField
	MaterialPanel imageContainer;
	@UiField
	Image image;

	private String height;
	private String url;
	private ImageResource resource;

	public MaterialParallax() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public MaterialParallax(String url) {
		super();
		this.url = url;
	}

	public MaterialParallax(ImageResource resource) {
		super();
		this.resource = resource;
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		initParallax();
	}

	@UiChild(tagname = "content")
	public void onAddContent(Widget w) {
		container.add(w);
	}

	public native void initParallax()/*-{
		$wnd.jQuery(document).ready(function() {
			$wnd.jQuery('.parallax').parallax();
		});
	}-*/;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		image.setUrl(url);
	}

	public ImageResource getResource() {
		return resource;
	}

	public void setResource(ImageResource resource) {
		this.resource = resource;
		image.setResource(resource);
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
		imageContainer.getElement().getStyle().setHeight(Double.parseDouble(height), Unit.PX);
	}
	
	

}
