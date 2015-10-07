package gwt.material.design.client.custom;

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

}
