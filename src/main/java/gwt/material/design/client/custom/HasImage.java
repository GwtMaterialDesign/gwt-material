package gwt.material.design.client.custom;

import com.google.gwt.resources.client.ImageResource;

public interface HasImage {

	/**
	 * Sets the url of an image
	 * @param url
	 */
	public void setUrl(String url);
	
	/**
	 * Gets the image url
	 * @return
	 */
	public String getUrl();
	
	/**
	 * Sets the resource image
	 * @param resource
	 */
	public void setResource(ImageResource resource);
	
	/**
	 * Gets the resource image
	 * @return
	 */
	public ImageResource getResource();
	
}
