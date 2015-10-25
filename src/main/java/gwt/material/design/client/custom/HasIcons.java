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

public interface HasIcons {
	
	/**
	 * Set Google Material Design icon
	 * {@link https://www.google.com/design/icons/}
	 * @param icon
	 */
	public void setIcon(String icon);
	
	/**
	 * Set the position of the icon
	 * - left or right
	 * @param iconPosition
	 */	
	public void setIconPosition(String iconPosition);
	
	/**
	 * Size of icon
	 * tiny: 1rem
     * small: 2rem
     * medium: 4rem
     * large: 6rem
     * @param size
	 */
	public void setSize(String size);
	
	/**
	 * Sets the color of the icon
	 * @param iconColor
	 */
	public void setIconColor(String iconColor);

}
