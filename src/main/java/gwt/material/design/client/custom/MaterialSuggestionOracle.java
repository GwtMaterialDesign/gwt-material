package gwt.material.design.client.custom;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


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
