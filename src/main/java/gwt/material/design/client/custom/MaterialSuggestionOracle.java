package gwt.material.design.client.custom;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

public class MaterialSuggestionOracle extends MultiWordSuggestOracle {

	public MaterialSuggestionOracle() {
	}
	
	private String imageElem = "";

	@Override
	public boolean isDisplayStringHTML() {
		return super.isDisplayStringHTML();
	}

	/**
	 * Autocomplete with Image item selection
	 * @param text
	 * @param image
	 */
	public void add(String text, Image image){
		this.imageElem = image.getElement().toString();
		add(text + image);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.MultiWordSuggestOracle#add(java.lang.String)
	 */
	@Override
	public void add(String suggestion) {
		super.add(suggestion);
	}

	@Override
	protected MultiWordSuggestion createSuggestion(String display, String suggestion) {
		suggestion = display.replace(imageElem, "");
		return new CustomSuggestion(display, suggestion);
	}

	/**
	 * @return the imageElem
	 */
	public String getImageElem() {
		return imageElem;
	}

	/**
	 * @param imageElem the imageElem to set
	 */
	public void setImageElem(String imageElem) {
		this.imageElem = imageElem;
	}



}
