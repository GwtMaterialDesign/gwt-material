package gwt.material.design.client.custom;

public interface HasTooltip {
	
	/**
	 * Tooltip for material specific components
	 * - buttons, links, icons
	 * @param tooltip
	 */
	public void setTooltip(String tooltip);
	
	/**
	 * Tooltip location for material specific components
	 * - bottom, right, left, top
	 * @param tooltip
	 */
	public void setTooltipLocation(String tooltipLocation);

}
